package Math;

public class float3 {
	public float x;
	public float y;
	public float z;
	public float3(float x, float y, float z) {
		super();
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	@Override
	public String toString() {
		return "("+x+","+y+","+z+")";
	}
}
