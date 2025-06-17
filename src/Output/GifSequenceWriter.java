package Output;

import javax.imageio.*;
import javax.imageio.metadata.*;
import javax.imageio.stream.ImageOutputStream;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Iterator;

public class GifSequenceWriter {
    private ImageWriter writer;
    private ImageWriteParam params;
    private IIOMetadata metadata;
    private ImageOutputStream stream;

    public GifSequenceWriter(ImageOutputStream outputStream, int imageType, int delay, boolean loop) throws IOException {
        writer = ImageIO.getImageWritersBySuffix("gif").next();
        stream = outputStream;
        writer.setOutput(stream);
        writer.prepareWriteSequence(null);

        ImageTypeSpecifier typeSpecifier = ImageTypeSpecifier.createFromBufferedImageType(imageType);
        metadata = writer.getDefaultImageMetadata(typeSpecifier, null);

        String metaFormatName = metadata.getNativeMetadataFormatName();
        IIOMetadataNode root = (IIOMetadataNode) metadata.getAsTree(metaFormatName);

        IIOMetadataNode graphicsControlExtensionNode = getNode(root, "GraphicControlExtension");
        graphicsControlExtensionNode.setAttribute("disposalMethod", "none");
        graphicsControlExtensionNode.setAttribute("userInputFlag", "FALSE");
        graphicsControlExtensionNode.setAttribute("transparentColorFlag", "FALSE");
        graphicsControlExtensionNode.setAttribute("delayTime", Integer.toString(delay / 10));
        graphicsControlExtensionNode.setAttribute("transparentColorIndex", "0");

        if (loop) {
            IIOMetadataNode appExtensions = getNode(root, "ApplicationExtensions");
            IIOMetadataNode appExtension = new IIOMetadataNode("ApplicationExtension");

            appExtension.setAttribute("applicationID", "NETSCAPE");
            appExtension.setAttribute("authenticationCode", "2.0");

            byte[] loopBytes = new byte[]{0x1, 0x0, 0x0};
            appExtension.setUserObject(loopBytes);
            appExtensions.appendChild(appExtension);
        }

        metadata.setFromTree(metaFormatName, root);
    }

    private IIOMetadataNode getNode(IIOMetadataNode rootNode, String nodeName) {
        for (int i = 0; i < rootNode.getLength(); i++) {
            if (rootNode.item(i).getNodeName().equalsIgnoreCase(nodeName)) {
                return (IIOMetadataNode) rootNode.item(i);
            }
        }
        IIOMetadataNode node = new IIOMetadataNode(nodeName);
        rootNode.appendChild(node);
        return node;
    }

    public void writeToSequence(BufferedImage image) throws IOException {
        writer.writeToSequence(new IIOImage(image, null, metadata), null);
    }

    public void close() throws IOException {
        writer.endWriteSequence();
        stream.close();
    }
}

