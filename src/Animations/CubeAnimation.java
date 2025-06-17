package Animations;

import java.nio.file.Path;

import Image.CubeImage;
import Image.ImageObject;
import Image.Object3D;
import Math.Transform;
import Math.float3;

public class CubeAnimation extends Animation {

	private CubeImage cubeImage;
	private float rotationSpeed = 0.02f;
	private float currentRotation = 0f;
	private Transform cubeTransform;
	public CubeAnimation(int numberOfFrames, String name, int animHeight, int animWidth) {
		super(numberOfFrames, name, animHeight, animWidth);
		cubeImage = new CubeImage("objFiles/cube.obj",animWidth,animHeight);
		cubeTransform = cubeImage.getObjects3D().get(0).getTransform();
		cubeTransform.setPosition(new float3(0f,0f,-5f));
	}

	@Override
	protected String GenerateFrame(int frameId, Path tempFolder) {
		
		cubeTransform.setAngleAroundY(currentRotation);
		cubeTransform.setAngleAroundX(currentRotation);
		currentRotation +=rotationSpeed;
		
		String path = cubeImage.GenerateImage(tempFolder.toString()+"/"+reducedName + frameId);
		return path;
		
	}
	
	public static void main(String[] args) {
		Animation cubeAnimation = new CubeAnimation(240,"cubePerspectiveAnimFOV",712,712);
		cubeAnimation.GenerateAnimation();
	}

}
