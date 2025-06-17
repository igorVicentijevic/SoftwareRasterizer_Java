package Image;

import Image.Image.Pixel;
import Math.Math;
import Math.Point;

public class TriangleObject extends ImageObject {

	public TriangleObject(Point A, Point B, Point C, Pixel color) {
		super(color,3);
		try {
			this.addPoint(A);
			this.addPoint(B);
			this.addPoint(C);
		} catch (EThisObjectCantHaveMorePoints e) {}

	}

	@Override
	public Pixel getColorForPixel(int x, int y) {
		float[] weights = new float[3];
		if(Math.isPointInTriangle(new Point(x,y),this.getPoints().get(0),this.getPoints().get(1),this.getPoints().get(2),weights)) {
			return this.getColor();
		}
		
		return null;
	}
	
	public Pixel getColorForPixelWithWeights(int x,int y, float[] weights) {
		if(Math.isPointInTriangle(new Point(x,y),this.getPoints().get(0),this.getPoints().get(1),this.getPoints().get(2),weights)) {
			return this.getColor();
		}
		
		return null;
	}
	
	
	
	
	
	
}
