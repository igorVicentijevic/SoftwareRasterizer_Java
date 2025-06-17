package Image;

public class CircleImage extends Image{

	private Pixel backgroundColor;
	private Pixel circleColor;
	private int r;
	
	public CircleImage(int width, int height,int r,Pixel backgroundColor, Pixel circleColor) {
		super(width, height);
		this.backgroundColor = backgroundColor;
		this.circleColor = circleColor;
		this.r = r;
	
	}
	
	public CircleImage(int width, int height,int r) {
		this(width,height,r,new Pixel(1f,0f,1f),new Pixel(0.5f,0.5f,0.5f));
	
	}
	
	@Override
	protected Pixel getColorForPixel(int x, int y) {
		
		int coordinateStartingPointX = (int)(width/2f);
		int coordinateStartingPointY = (int)(height/2f);
		
		int newX = x - coordinateStartingPointX;
		int newY = y - coordinateStartingPointY;
		
//		if(Math.pow(newX, 2) + Math.pow(newY, 2) <= r2*r2) {
//			
//			p = circleColor2;
//		}
//		
		if(Math.pow(newX, 2) + Math.pow(newY, 2) <= r*r) {
			
			return  circleColor;
		}
		
		return this.backgroundColor;
	}
	
	public static void main(String[] args) {
		Image circleImage = new CircleImage(256,256,50);
		circleImage.GenerateImage("circle");
	}
	
}
