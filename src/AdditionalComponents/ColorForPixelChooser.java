package AdditionalComponents;

import java.util.Iterator;
import java.util.List;

import Image.Image.Pixel;
import Image.ImageObject;

public class ColorForPixelChooser {
	
	public Pixel getColorForPixel(List<ImageObject> imageObjects,int x,int y){
		for(ImageObject io: imageObjects) {
			Pixel color = io.getColorForPixel(x, y);
			if(color != null) {
				return color;
			}
			
		}
		return null;
	}
	
}
