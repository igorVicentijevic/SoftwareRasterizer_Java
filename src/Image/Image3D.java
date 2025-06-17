package Image;

import java.util.ArrayList;
import java.util.List;

import AdditionalComponents.ColorForPixelChooser_Depth;
import Math.Point;
import Math.Transform;
import Math.float2;
import Math.float3;

public class Image3D extends Image {
	
	protected List<Object3D> objects3D;
	private float[][] depthBuffer;
	private ColorForPixelChooser_Depth chooser;

	public Image3D(int width, int height) {
		super(width, height);
		objects3D = new ArrayList<>();
		depthBuffer = new float[width][height];
		//ioHandler = new ImageObjectHandler_DepthBuffer();
		//this.setImageObjectHandler(ioHandler);
		chooser = new ColorForPixelChooser_Depth();
		this.setColorForPixelChooser(chooser);
		
	}
//	
//	private void addTriangles(Object3D objectToAdd) {
//		for(TrianglePart triangle: objectToAdd.triangles) {
//			
//			float2 convertedToScreenPosition[] = new float2[3];
//			int i = 0;
//			
//			for(float3 vertex: triangle.getVerteces()){
//				
//				convertedToScreenPosition[i++] =  VertexToScreen(vertex,objectToAdd.getTransform(),this.width,this.height);
//			}
//			
//			Point A = new Point((int)( convertedToScreenPosition[0].x), (int)(convertedToScreenPosition[0].y));
//			Point B = new Point((int)( convertedToScreenPosition[1].x), (int)(convertedToScreenPosition[1].y));
//			Point C = new Point((int)( convertedToScreenPosition[2].x), (int)(convertedToScreenPosition[2].y));
//			
//			this.getImageObjects().add(new TriangleObject(A,B,C,triangle.getColor()));
//			
//			//System.out.println(A+" "+B+" "+C);
//		}
//	}
	
	private void updateTriangles() {
		System.out.println("Updating triangles...");
		for(Object3D object: this.objects3D)
		{
			for(TrianglePart triangle: object.triangles) {
				
				float2 convertedToScreenPosition[] = new float2[3];
				int i = 0;
				
				for(float3 vertex: triangle.getVerteces()){
					
					convertedToScreenPosition[i++] =  VertexToScreen(vertex,object.getTransform(),this.width,this.height,(float)Math.PI/3);
				}
				
				Point A = new Point( convertedToScreenPosition[0].x, convertedToScreenPosition[0].y);
				Point B = new Point( convertedToScreenPosition[1].x, convertedToScreenPosition[1].y);
				Point C = new Point( convertedToScreenPosition[2].x, convertedToScreenPosition[2].y);
				
				float3[] verteces = triangle.getVerteces();
				this.getImageObjects().add(new TriangleObjectWithDepth(A,B,C,triangle.getColor(),verteces[0].z,verteces[1].z,verteces[2].z));
				
				//System.out.println(A+" "+B+" "+C);
			}
			//int gdg= 2;
		}
	}
	private void clearDepthBuffer() {
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				depthBuffer[i][j] = Float.POSITIVE_INFINITY;
			}
		}
	}
	
	
	@Override
	public String GenerateImage(String imageName) {
		this.clearDepthBuffer();
		this.clearImage();
		this.updateTriangles();
		return super.GenerateImage(imageName);
	}
	
	public void addObject(Object3D objectToAdd) {
		this.objects3D.add(objectToAdd);
		//addTriangles(objectToAdd);
		
	}
	
	public List<Object3D> getObjects3D(){
		return objects3D;
	}
	
	
	
	//Orthographic projection
	public static float2 VertexToScreen(float3 vertex,Transform transform, int width, int height,float fov) {
		vertex = transform.ToWorldPoint(vertex);
		
		float screenHeight_world =(float)Math.tan(fov/2f)*2f; //Kolika je visina ekrana izrazena u jedinicama prostora
		float pixelsPerWorldUnit = height / screenHeight_world / vertex.z; // deli se sa vertex.z zbog perspektive;
		
		float2 pixelOffset = new float2(vertex.x*pixelsPerWorldUnit, vertex.y*pixelsPerWorldUnit);
		return new float2(width/2+pixelOffset.x, height/2+pixelOffset.y);
	}
	

}
