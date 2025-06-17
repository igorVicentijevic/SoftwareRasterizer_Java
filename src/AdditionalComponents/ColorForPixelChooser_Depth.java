package AdditionalComponents;

import java.util.Iterator;
import java.util.List;

import Image.Image.Pixel;
import Image.ImageObject;
import Image.TriangleObject;
import Image.TriangleObjectWithDepth;

public class ColorForPixelChooser_Depth extends ColorForPixelChooser {
	
	
	
	@Override
	public Pixel getColorForPixel(List<ImageObject> imageObjects, int x, int y) {
		
		float currMinDepth = Float.POSITIVE_INFINITY;
		Pixel currColor = null;
		for(ImageObject io: imageObjects) {
			TriangleObjectWithDepth triangle = (TriangleObjectWithDepth) io;
			float[] weights = new float[3];
			Pixel color = triangle.getColorForPixelWithWeights(x, y, weights);
			if (color == null) {
				continue;
			}
			
			float depthA = triangle.getDepthA();
			float depthB = triangle.getDepthB();
			float depthC = triangle.getDepthC();
			
			float depth = depthA*weights[0] + depthB*weights[1] + depthC*weights[2];
			
			//System.out.println("Weights are: "+weights[0] +weights[1]+weights[2]);
			
			if (-depth < currMinDepth - 1e-6f) {
				currMinDepth = -depth;
				currColor = color; 
			}
			
			
			
		}
		//System.out.println("Depth: "+currMinDepth);
		return currColor;
	}

}
