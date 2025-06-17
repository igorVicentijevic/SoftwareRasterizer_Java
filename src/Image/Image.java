package Image;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import AdditionalComponents.ColorForPixelChooser;
import Math.Point;
import Output.BmpWriter;

public abstract class Image {

	public static class Pixel{
		public float r = 0;
		public float g = 0;
		public float b = 0;
		
		public Pixel() {

		}
		
		public Pixel(float r, float g, float b) {
			this.r = r;
			this.g = g;
			this.b = b;
		}
		
		@Override
		public String toString() {
			return "(r: "+r+" g: "+g+" b: )";
		}
		
	}
	
	protected int width;
	protected int height;
	private List<ImageObject> imageObjects;
	//private ImageObjectHandler imageObjectHandler;
	private Pixel defaultBackgroundColor;
	private ColorForPixelChooser chooser;
	
	private Pixel[][] pixels;
	

	
	public Image(int width, int height) {
		this.width = width;
		this.height = height;
		this.pixels = new Pixel[width][height];
		this.imageObjects = new ArrayList<ImageObject>();
		//imageObjectHandler = iohandler;
		this.defaultBackgroundColor = new Pixel(0f,0f,0f);
		chooser = new ColorForPixelChooser();
	}
	
	

	
	public void setColorForPixelChooser(ColorForPixelChooser chooser) {
		this.chooser = chooser;
	}




	@Override
	public String toString() {
		
		StringBuilder sb = new StringBuilder();
		
		for( int x = 0; x < width; x ++) {
			for (int y = 0; y < height; y++) {
	
				sb.append(pixels[x][y]);
				
			}
			
			sb.append("\n");
		}
		
		return sb.toString();
		
		
	}
	
	protected void addImageObject(ImageObject io) {
		imageObjects.add(io);
		//imageObjectHandler.addImageObject(io);
	}
	
	public List<ImageObject> getImageObjects(){
		return imageObjects;
		//return imageObjectHandler.getImageObjects();
	}
	
	
	

	public static Pixel GetRandomColor() {
		Random random = new Random();
		return new Pixel(random.nextFloat(),random.nextFloat(),random.nextFloat());
	}
	
	public Point GenerateRandomPoint() {
		Random random = new Random();
		return new Point(random.nextInt(this.width),random.nextInt(this.height));
		
	}

	
	protected Pixel getColorForPixel(int x, int y) {
		return defaultBackgroundColor;
	}
	
	public String GenerateImage(String imageName) {
		//List<ImageObject> imageObjects = imageObjectHandler.getImageObjects();
		for(int x = 0; x < width; x ++) {
			for (int y = 0; y < height; y++) {

				//pixels[x][y] = this.defaultBackgroundColor;
				
//				for(ImageObject object: this.imageObjects) {
//					if (object.getColorForPixel(x, y) == null) continue;
//					
//					pixels[x][y] = object.getColorForPixel(x, y);
//					break;
//				}
				
			
				pixels[x][y] = chooser.getColorForPixel(imageObjects, x, y);
				if (pixels[x][y] == null)
					pixels[x][y] = this.defaultBackgroundColor;
				
				
			}
		}
		
		return BmpWriter.CreateBMPFIle(this, imageName);
	}
	

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
	
	public void setDefaultBackgroundColor(Pixel backgroundColor) {
		this.defaultBackgroundColor = backgroundColor;
	}
	
	public Pixel getBackgroundColor(){
		return this.defaultBackgroundColor;
	}

	public Pixel[][] getPixels() {
		return pixels;
	}

	public void setPixels(Pixel[][] pixels) {
		this.pixels = pixels;
	}

	public void clearImage() {
		this.imageObjects.clear();
		//imageObjectHandler.clear();
	}
}
