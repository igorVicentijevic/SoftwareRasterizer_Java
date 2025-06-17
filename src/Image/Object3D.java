package Image;

import java.util.ArrayList;
import java.util.List;

import Image.Image.Pixel;
import Math.Transform;
import Math.float2;
import Math.float3;

public class Object3D {
	protected List<TrianglePart> triangles;
	protected Transform transform;
	
	public Object3D(Transform transform) {
		triangles = new ArrayList<>();
		this.transform = transform;
	}

	public Transform getTransform() {
		return transform;
	}
	public void addTriangle(TrianglePart triangleToAdd) {
		this.triangles.add(triangleToAdd);
	}
	
	public List<TrianglePart> getTriangles() {
		return this.triangles;
	}
	
	
}
