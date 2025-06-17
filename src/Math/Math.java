package Math;

public class Math {
	public static float calculateDotProduct(Point a, Point b) {
		return a.x*b.x + a.y*b.y;
	}
	
	//90 degrees CCW
	public static Point Perpendicular(Point vector) {
		return new Point(-vector.y, vector.x);
	}
	
	public static float SignedTriangleArea(float2 A, float2 B, float2 C) {
		Vector AC = new Vector(C.x-A.x,C.y-A.y);
		Vector ABn = (new Vector(B.x-A.x, B.y - A.y)).Perpendicular();
		return Vector.calculateDotProduct(AC, ABn)/2;
		
	}

	
	public static boolean isPointInTriangle(Point P, Point A, Point B, Point C, float[] weights ) {
		
		float2 a = A.toFloat2();
		float2 b = B.toFloat2();
		float2 c = C.toFloat2();
		float2 p = P.toFloat2();
		
		float areaABP = Math.SignedTriangleArea(a,b,p);
		float areaBCP = Math.SignedTriangleArea(b, c, p);
		float areaCAP = Math.SignedTriangleArea(c, a, p);
		
		boolean inTriangle = areaABP > 0 && areaBCP > 0 && areaCAP > 0;
		
		float invAreaSum = 1f / (areaABP+areaBCP+areaCAP);
		float weightA = areaBCP*invAreaSum;
		float weightB = areaCAP*invAreaSum;
		float weightC = areaABP*invAreaSum;
		
		weights[0] = weightA;
		weights[1] = weightB;
		weights[2] = weightC;
		return inTriangle;
//		Point AB = new Point(B.x-A.x, B.y - A.y);
//		Point AP = new Point(P.x-A.x, P.y - A.y);
//		
//		Point nAB = Perpendicular(AB);
//		
//		float dotABP = Math.calculateDotProduct(nAB, AP);
//		
//		Point BC = new Point(C.x-B.x, C.y - B.y);
//		Point BP = new Point(P.x-B.x, P.y - B.y);
//		
//		Point nBC = Perpendicular(BC);
//		
//		float dotBCP = Math.calculateDotProduct(nBC, BP);
//		
//		Point CA = new Point(A.x-C.x, A.y - C.y);
//		Point CP = new Point(P.x-C.x, P.y - C.y);
//		
//		Point nCA = Perpendicular(CA);
//		
//		float dotCAP = Math.calculateDotProduct(nCA, CP);
//		
//		return (dotABP > 0 && dotBCP > 0 && dotCAP > 0);
		
		
			      // (dotABP <= 0 && dotBCP <= 0 && dotCAP <= 0);
		
		//return dotABP*dotBCP > 0 && dotABP*dotCAP > 0 && dotBCP*dotCAP>0;
	}
}
