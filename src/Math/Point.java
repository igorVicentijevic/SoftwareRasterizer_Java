package Math;

public class Point {
	public float x;
	public float y;
	public Point(float x, float y) {
		super();
		this.x = x;
		this.y = y;
	}
	
	@Override
	public String toString() {
		return "(x: "+x+" y: "+y+")";
	}
	
	public float2 toFloat2() {
		return new float2((float)x,(float)y);
	}
	
}
