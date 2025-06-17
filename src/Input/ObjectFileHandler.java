package Input;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Math.float3;

public class ObjectFileHandler {
	public static float3[] LoadObjFile(String objFile) {
		List<float3> allPoints = new ArrayList<>();
		List<float3> trianglePoints = new ArrayList<>();
		
		try (BufferedReader reader = new BufferedReader(new FileReader(objFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();

                if (line.startsWith("v ")) {
                    
                	String[] parts = line.split("\\s+");
                	
                	float x = Float.parseFloat(parts[1]);
                	float y = Float.parseFloat(parts[2]);
                	float z = Float.parseFloat(parts[3]);
                
        
                	allPoints.add(new float3(x,y,z));
                	
                } else if (line.startsWith("vn ")) {
                	
                	//TODO
                    
                } else if (line.startsWith("vt ")) {
                	//TODO
                   
                } else if (line.startsWith("f ")) {
                   String[] parts = line.split("\\s+");
                   for(int i = 1; i < parts.length; i++) {
                	   
                	   String[] currGroup = parts[i].split("/");
                	   int[] currPointsGroup = new int[currGroup.length];
                	   
                	   for (int j = 0; j < currPointsGroup.length; j++) 
                	   {
                		   if(currGroup[j].equals("")) continue;
                		   currPointsGroup[j] = (int)Float.parseFloat( currGroup[j]);
                	   }
                	   
                	   if(i >= 4) {
                		   trianglePoints.add(trianglePoints.get(trianglePoints.size()-2)); // TODO Mozda ce trebati ispravka
                		   trianglePoints.add(trianglePoints.get(trianglePoints.size()-1));
                	   }
                	   
                	   int pointIndex = currPointsGroup[0]-1;
						
                	   trianglePoints.add(allPoints.get(pointIndex));
					
                   }
                }
             
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
		
		return trianglePoints.toArray(new float3[0]);
		
    }
	
	public static void main(String[] args) {
		
		float3[] myTrianglePoints = ObjectFileHandler.LoadObjFile("objFiles/cube.obj");
		for(float3 point: myTrianglePoints) {
			System.out.println(point);
		}
	}
}
