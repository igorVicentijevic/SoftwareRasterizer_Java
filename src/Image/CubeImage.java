package Image;

import java.util.Iterator;

import Input.ObjectFileHandler;
import Math.Point;
import Math.Transform;
import Math.float3;

public class CubeImage extends Image3D {

	public CubeImage(String objPath,int width, int height) {
		super(width, height);
		
		Transform transform = new Transform();
		transform.setAngleAroundY((float)Math.PI/10f);
		transform.setPosition(new float3(0f,0f,-2.5f));
		Object3D cube = new Object3D(transform);
		
		float3[] trianglePoints = ObjectFileHandler.LoadObjFile(objPath);
		
		for(int i = 0; i < trianglePoints.length; i+=3) {
			TrianglePart currTriangle = new TrianglePart(trianglePoints[i+0],trianglePoints[i+1],trianglePoints[i+2], Image.GetRandomColor());
			cube.addTriangle(currTriangle);
		}
		
		this.addObject(cube);
		
		
	}
	
	public static void main(String[] args) {
		Image myImage = new CubeImage("objFiles/cube.obj",512,512);
		myImage.GenerateImage("output/CubeTestPerspective");
	}

	
}
