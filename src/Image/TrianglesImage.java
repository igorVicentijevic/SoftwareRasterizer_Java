package Image;

import java.util.Iterator;

import Image.Image.Pixel;
import Math.Point;
import Math.Math;

public class TrianglesImage extends Image {

	private Pixel backgroundColor;
	private int numOfTriangles;
	
	public TrianglesImage(int width, int height,int numOfTriangles) {
		super(width, height);
		
	
		backgroundColor = TrianglesImage.GetRandomColor();
		this.numOfTriangles = numOfTriangles;
		
		GenerateTriangles(numOfTriangles);
	}
	
	private void GenerateTriangles(int numOfTriangles) {
		for (int i = 0; i < numOfTriangles; i++) {
			
			TriangleObject newTriangle = new TriangleObject(GenerateRandomPoint(),GenerateRandomPoint(),GenerateRandomPoint(), Image.GetRandomColor());
			
			this.addImageObject(newTriangle);
			
		}
	}

	@Override
	protected Pixel getColorForPixel(int x, int y) {
		Pixel p = backgroundColor;
		
		for (int i = 0; i < numOfTriangles; i++) {
			ImageObject currTriangle = this.getImageObjects().get(i);
			float[] weights = new float[3];
			if(Math.isPointInTriangle(new Point(x,y),currTriangle.getPoints().get(0), currTriangle.getPoints().get(1), currTriangle.getPoints().get(2),weights))
			{
				return currTriangle.getColor();
			}
		}
		
		return this.backgroundColor;
	}

	
	public static void main(String[] args) {
		TrianglesImage image = new TrianglesImage(512,512,30);
		
		image.GenerateImage("output/trianglesV2");
	}
}

