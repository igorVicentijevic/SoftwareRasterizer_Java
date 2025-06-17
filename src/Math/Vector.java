package Math;

public class Vector {
	public float x;
	public float y;
	public Vector(float x, float y) {
		super();
		this.x = x;
		this.y = y;
	}
	
	public Vector Perpendicular() {
		return new Vector(-this.y, this.x);
	}
	
	public static float calculateDotProduct(Vector a, Vector b) {
		return a.x*b.x + a.y*b.y;
	}
	
	
}
