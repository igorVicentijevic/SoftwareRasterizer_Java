package Animations;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

import FileManagement.FolderManager;
import Math.Point;
import Output.GifMaker;

public abstract class Animation implements Runnable {

	protected int numberOfFrames;
	protected String animName;
	protected String[] framePaths;
	protected String reducedName;
	protected int animHeight;
	protected int animWidth;
	
	
	public Animation(int numberOfFrames) {
		this(numberOfFrames, "animation",256,256);
	}
	public Animation(int numberOfFrames, String name, int animHeight, int animWidth) {
		this.numberOfFrames = numberOfFrames;
		this.animName = "/Animations/"+name;
		this.reducedName = name;
		this.framePaths = new String[numberOfFrames];
		this.animHeight = animHeight;
		this.animWidth = animWidth;
	}
	
	public void GenerateAnimation() {
		
		Thread thread = new Thread(this);
		thread.start();
		
	}
	
	protected abstract String GenerateFrame(int frameId, Path tempFolder);
	
	public void run() {
		try {
			Path tempFolder = FolderManager.createTempFolder("temp");
			
			for (int i = 0; i < numberOfFrames; i++) {
				framePaths[i] = GenerateFrame(i,tempFolder);
			}
			
			GifMaker.createGifFromBmps(framePaths, "./output/"+reducedName +".gif",50 , true);
			
			FolderManager.deleteFolder(tempFolder);
			
		} catch (IOException e) {

			e.printStackTrace();
		}

	}
	
	public void MoveAllPointsBy(Point[] points,int dx, int dy) {
		
		for(Point point:points) {
			point.x+=dx;
			point.y+=dy;
		}
	}

}
