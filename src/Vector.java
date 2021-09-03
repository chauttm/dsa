public class Vector 
{
	double x;
	double y;
	
	public Vector(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public Vector() {
		this(0d,0d);
	}

	public String toString() {
		return "(" + x + "," + y + ")";
	}
	
	public Vector add(Vector other) {
		Vector sum = new Vector();
		
		sum.x = this.x + other.x;
		sum.y = this.y + other.y;
		return sum;
	}
}
