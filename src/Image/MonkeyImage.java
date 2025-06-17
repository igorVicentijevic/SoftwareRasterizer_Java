package Image;

import Input.ObjectFileHandler;
import Math.Transform;
import Math.float3;

public class MonkeyImage extends Image3D {

	public MonkeyImage(String objPath,int width, int height) {
		super(width, height);
		
		Transform transform = new Transform();
		transform.setAngleAroundY((float)Math.PI/4f);
		transform.setPosition(new float3(0f,0f,-2.5f));
		Object3D monkey = new Object3D(transform);
		
		float3[] trianglePoints = ObjectFileHandler.LoadObjFile(objPath);
		
		for(int i = 0; i < trianglePoints.length; i+=3) {
			TrianglePart currTriangle = new TrianglePart(trianglePoints[i+0],trianglePoints[i+1],trianglePoints[i+2], Image.GetRandomColor());
			 monkey.addTriangle(currTriangle);
		}
		
		this.addObject(monkey);
		
	}
	
	public static void main(String[] args) {
		Image myImage = new MonkeyImage("objFiles/monkey.obj",512,512);
		myImage.GenerateImage("output/MonkeyTest3");
	}
}
