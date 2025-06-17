package Math;

public class Transform {

	private float angleAroundY = 0.0f;
	private float angleAroundX = 0.0f;
	private Vector3D position = new Vector3D(0f,0f,0f);
	
	//Calculate i,j,k
	public Vector3D[] getBasisVectors() {
		
		// Rotation Around Y
		Vector3D i_aY = new Vector3D((float)java.lang.Math.cos(angleAroundY), 0.0f,(float)java.lang.Math.sin(angleAroundY));
		Vector3D j_aY = new Vector3D(0f,1f,0f);
		Vector3D k_aY =  new Vector3D(-(float)java.lang.Math.sin(angleAroundY), 0.0f,(float)java.lang.Math.cos(angleAroundY));
		
		//Rotation Around X
		Vector3D i_aX = new Vector3D(1.0f, 0.0f,0.0f);
		Vector3D j_aX = new Vector3D(0f,(float)java.lang.Math.cos(angleAroundX), -(float)java.lang.Math.sin(angleAroundX));
		Vector3D k_aX =  new Vector3D(0f,(float)java.lang.Math.sin(angleAroundX),(float)java.lang.Math.cos(angleAroundX));
		
		Vector3D iNew = this.transformVector(i_aY, j_aY, k_aY, i_aX);
		Vector3D jNew = this.transformVector(i_aY, j_aY, k_aY, j_aX);
		Vector3D kNew = this.transformVector(i_aY, j_aY, k_aY, k_aX);
		
		
		
		
		return new Vector3D[]{iNew,jNew,kNew};
	}
	
	public Vector3D transformVector(Vector3D i, Vector3D j, Vector3D k, Vector3D v) {
		float newX = v.x*i.x + v.y*j.x + v.z * k.x;
		float newY = v.x*i.y + v.y*j.y + v.z * k.y;
		float newZ = v.x*i.z + v.y*j.z + v.z * k.z;
		
		return new Vector3D(newX, newY, newZ );
		
	}
	
	
	
	public float3 getPosition() {
		return position.toFloat3();
	}

	public void setPosition(float3 position) {
		this.position = Vector3D.convertFromFloat3(position);
	}

	public float getAngleAroundX() {
		return angleAroundX;
	}

	public void setAngleAroundX(float angleAroundX) {
		this.angleAroundX = angleAroundX;
	}

	public void setAngleAroundY(float angleAroundY) {
		this.angleAroundY = angleAroundY;
	}
	
	public float3 ToWorldPoint(float3 p) {
		Vector3D[] basisVectors = this.getBasisVectors();
		Vector3D newVectPos = transformVector(basisVectors[0], basisVectors[1], basisVectors[2], new Vector3D(p.x,p.y,p.z));
		newVectPos = Vector3D.addTwoVectors(newVectPos, this.position);
		return newVectPos.toFloat3();
		
	}
	
}
