package Image;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import Image.Image.Pixel;
import Math.Point;
import Math.Vector;

public abstract class ImageObject {
	protected List<Point> points;
	protected Pixel color;
	protected int numOfPoints;
	
	
	
	public ImageObject(Pixel color,int numOfPoints) {
		super();
		this.color = color;
		points = new ArrayList<Point>();
		this.numOfPoints = numOfPoints;
	}



	public void addPoint(Point pointToAdd) throws EThisObjectCantHaveMorePoints {
		if(points.size() >= numOfPoints) throw new EThisObjectCantHaveMorePoints();
		
		points.add(pointToAdd);
	}
	
	public void translate(Vector translationVector) {
		for(Point p: points) {
			p.x += translationVector.x;
			p.y += translationVector.y;
		}
	}



	public List<Point> getPoints() {
		return points;
	}
	
	public abstract Pixel getColorForPixel(int x,int y);



	public Pixel getColor() {
		return color;
	}



	public void setColor(Pixel color) {
		this.color = color;
	}



	public int getNumOfPoints() {
		return numOfPoints;
	}

	
	
}
