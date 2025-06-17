package Animations;

import java.nio.file.Path;

import Image.CubeImage;
import Image.MonkeyImage;
import Math.Transform;
import Math.float3;

public class MonkeyAnimation extends Animation {
	private MonkeyImage monkeyImage;
	private float rotationSpeed = 0.02f;
	private float currentRotation = 0f;
	private Transform monkeyTransform;
	public MonkeyAnimation(int numberOfFrames, String name, int animHeight, int animWidth) {
		super(numberOfFrames, name, animHeight, animWidth);
		monkeyImage = new MonkeyImage("objFiles/monkey.obj",animWidth,animHeight);
		monkeyTransform = monkeyImage.getObjects3D().get(0).getTransform();
		monkeyTransform.setPosition(new float3(0f,0f,-2.5f));
	}

	@Override
	protected String GenerateFrame(int frameId, Path tempFolder) {
		
		monkeyTransform.setAngleAroundY(currentRotation);
		monkeyTransform.setAngleAroundX(currentRotation);
		currentRotation +=rotationSpeed;
		
		String path = monkeyImage.GenerateImage(tempFolder.toString()+"/"+reducedName + frameId);
		return path;
		
	}
	
	public static void main(String[] args) {
		Animation monkeyAnimation = new MonkeyAnimation(60,"monkeyPerspectiveAnimFOV4_DepthFinal",512,512);
		monkeyAnimation.GenerateAnimation();
	}
}
