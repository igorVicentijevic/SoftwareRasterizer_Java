package Animations;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import Image.TriangleImage;
import Image.TriangleObject;
import Image.TrianglesImage;
import Image.Image.Pixel;
import Image.ImageObject;
import Math.Point;
import Math.Vector;

public class TrianglesAnimation extends Animation {

	private TrianglesImage myImage;
	private float speed;
	private Pixel backgroundColor;
	private Map<ImageObject, Vector> triangleDirection;
	
	public TrianglesAnimation(int numberOfFrames, int numOfTriangles,float speed,String animName,int animWidth,int animHeight) {
		super(numberOfFrames,animName,animWidth,animHeight);
		
	
		myImage = new TrianglesImage(this.animWidth,this.animHeight,numOfTriangles);
		myImage.GenerateImage("testImg");
		//myImage.GenerateRandomPointsForTriangle();
		this.speed = speed;
		
		triangleDirection = new HashMap<>();
		
		Random random = new Random();
		
		List<ImageObject> triangleObjects =myImage.getImageObjects();
		
		
		for (int i = 0; i <numOfTriangles; i++) {
			Vector direction = new Vector((float)(random.nextFloat()*2.0 - 1.0),(float)(random.nextFloat()*2.0 - 1.0));
			
			triangleDirection.put(triangleObjects.get(i), direction);
			
		}
		
	}
	@Override
	protected String GenerateFrame(int frameId, Path tempFolder) {
		
		for (ImageObject triangle: myImage.getImageObjects()) {

			Vector direction = triangleDirection.get(triangle);
			
			for(Point p : triangle.getPoints()) {
				if(p.x < 0 ) {
					direction.x = Math.abs(direction.x);
				}
				else if(p.x >= myImage.getWidth())
				{
					direction.x = -Math.abs(direction.x);
				}
				
				if(p.y < 0 ){
					direction.y = Math.abs(direction.y);
				}
				else if(p.y >= myImage.getHeight()) {
					direction.y = -Math.abs(direction.y);
				}
				
			}
			
			float dx = direction.x * this.speed;
			float dy = direction.y * this.speed;
			
			
			//myImage.MoveAllPointsBy((int) dx, (int) dy);
			
			triangle.translate(new Vector(dx,dy));
			
			
		}
		
		
		System.out.println(tempFolder.toString());
		
		String path = myImage.GenerateImage(tempFolder.toString()+"/"+reducedName + frameId);
		
		return path;
	}

	public static void main(String[] args) {
		Animation anim = new TrianglesAnimation(120,30,7,"trianglesAnimV2",1024,1024);
		anim.GenerateAnimation();
	}
}
