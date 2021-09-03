import java.util.Scanner;

public class HelloWorld 
{
  public static void main(String[] argv) 
  {
		Vector v1 = new Vector(1,2);
		Vector v2 = new Vector(4,2);
		Vector sum = v1.add(v2);
		
		System.out.println("(" + sum.x + "," + sum.y + ")");
		System.out.println(v1.toString() + " + " + v2 + " = " + sum);
		// (1,2) + (4,2) = (5,4)
	}
}
