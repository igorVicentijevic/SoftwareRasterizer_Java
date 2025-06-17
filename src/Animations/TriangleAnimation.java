package Animations;

import java.nio.file.Path;
import java.util.List;
import java.util.Random;

import Image.Image.Pixel;
import Image.TriangleImage;
import Math.Point;
import Math.Vector;

public class TriangleAnimation extends Animation {

	private TriangleImage myImage;
	private float speed;
	private Pixel backgroundColor;
	private Pixel triangleColor;
	//private Point[] points;
	private Vector direction;
	
	public TriangleAnimation(int numberOfFrames, float speed,String animName,int animWidth,int animHeight) {
		super(numberOfFrames,animName,animWidth,animHeight);
		myImage = new TriangleImage(this.animWidth,this.animHeight);
		//myImage.GenerateRandomPointsForTriangle();
		this.speed = speed;
		//this.points = myImage.getPoints();
		this.backgroundColor = myImage.getBackgroundColor();
		this.triangleColor = myImage.getTriangleColor();
		Random random = new Random();
		this.direction = new Vector((float)(random.nextFloat()*2.0 - 1.0),(float)(random.nextFloat()*2.0 - 1.0));
		//System.out.println(direction[0] + ";"+direction[1]);
	}
	
	
	
	

	@Override
	protected String GenerateFrame(int frameId, Path tempFolder) {
		
		List<Point> points =  myImage.getImageObjects().get(0).getPoints();
		for(Point p :  points) {
			if(p.x < 0 ){
				direction.x= Math.abs(direction.x);
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
		float dy = direction.y  * this.speed;
		
		myImage.getImageObjects().get(0).translate(new Vector(dx,dy));
		
		//myImage.MoveAllPointsBy((int) dx, (int) dy);
		
		
		
		System.out.println(tempFolder.toString());
	
		String path = myImage.GenerateImage(tempFolder.toString()+"/"+reducedName + frameId);
		
		//myImage = new TriangleImage(this.animHeight,this.animWidth,points[0], points[1], points[2],backgroundColor,triangleColor);
		
		return path;
		
	}
	

	public static void main(String[] args) {
		
		Animation animation = new TriangleAnimation(120,7,"firstAnimV2",512,512);
		animation.GenerateAnimation();
		
	}
	
	
	

}
