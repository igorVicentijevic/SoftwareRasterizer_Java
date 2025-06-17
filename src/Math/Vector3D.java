package Math;

public class Vector3D {
	public float x;
	public float y;
	public float z;
	public Vector3D(float x, float y,float z) {
		super();
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public static Vector3D addTwoVectors(Vector3D v1, Vector3D v2) {
		return new Vector3D(v1.x+v2.x, v1.y+v2.y, v1.z+v2.z);
	}
	
	public float3 toFloat3() {
		return new float3(this.x,this.y,this.z);
	}
	
	public static Vector3D convertFromFloat3(float3 v) {
		return new Vector3D(v.x,v.y,v.z);
	}
}
