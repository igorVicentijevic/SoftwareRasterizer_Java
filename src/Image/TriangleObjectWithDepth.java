package Image;

import Image.Image.Pixel;
import Math.Point;

public class TriangleObjectWithDepth extends TriangleObject {

	private float depthA;
	private float depthB;
	private float depthC;
	
	public TriangleObjectWithDepth(Point A, Point B, Point C, Pixel color,float depthA, float depthB, float depthC) {
		super(A, B, C, color);
		this.depthA = depthA;
		this.depthB = depthB;
		this.depthC = depthC;
		
	}

	public float getDepthA() {
		return depthA;
	}

	public float getDepthB() {
		return depthB;
	}

	public float getDepthC() {
		return depthC;
	}
	
	

}
