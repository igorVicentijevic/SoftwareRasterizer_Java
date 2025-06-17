package Image;

import Math.Point;

import java.util.ArrayList;
import java.util.List;

import Math.Math;

public class TriangleImage extends Image{

	//private Pixel backgroundColor;
	//private TriangleObject triangle;
	
	
	public TriangleImage(int width, int height) {
		this(width,height,null,null,null, Image.GetRandomColor(),Image.GetRandomColor());
		
	}
	
	public TriangleImage(int width, int height,Point A, Point B, Point C){	
		this(width,height,A,B,C, Image.GetRandomColor(),Image.GetRandomColor());
		
	}
	public TriangleImage(int width, int height,Point A, Point B, Point C, Pixel backgroundColor, Pixel triangleColor) {
		super(width, height);
		if(A == null) A = this.GenerateRandomPoint();
		if(B == null) B = this.GenerateRandomPoint();
		if(C == null) C = this.GenerateRandomPoint();
		
		TriangleObject triangle = new TriangleObject(A,B,C,triangleColor);
		this.addImageObject(triangle);
		//this.backgroundColor = backgroundColor;
		this.setDefaultBackgroundColor(backgroundColor);
	}

	
	public Pixel getTriangleColor() {
		return getImageObjects().get(0).getColor();
	}
//
//	@Override
//	protected Pixel getColorForPixel(int x, int y) {
//		ImageObject triangle = this.getImageObjects().get(0);
//		if(Math.isPointInTriangle(new Point(x,y),triangle.getPoints().get(0),triangle.getPoints().get(1),triangle.getPoints().get(2))) {
//			return triangle.getColor();
//		}
//		
//		return this.backgroundColor;
//	}
	
	public static void main(String[] args) {
		TriangleImage image = new TriangleImage(512,512);
		image.GenerateImage("triangleV2");
	}
}
