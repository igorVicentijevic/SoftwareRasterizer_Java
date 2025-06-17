package Image;

import Image.Image.Pixel;
import Math.float3;

public class TrianglePart {
	private float3[] verteces;
	private Pixel color;
	public TrianglePart(float3 A, float3 B, float3 C,Pixel color) {
		super();
		this.color = color;
		verteces = new float3[3];
		verteces[0] = A;
		verteces[1] = B;
		verteces[2] = C;
	}
	public float3[] getVerteces() {
		return verteces;
	}

	public Pixel getColor() {
		return color;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		sb.append("[");
		for(float3 vertex: verteces) {
			sb.append("Vertex: ");
			sb.append(vertex.x+" ");
			sb.append(vertex.y+" ");
			sb.append(vertex.z+" ");
		}
		sb.append("]");
		return sb.toString();
	}

	
	

	
}
