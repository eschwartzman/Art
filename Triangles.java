package lab6;

import java.awt.Color;
import sedgewick.StdDraw;
import cse131.ArgsProcessor;
/**
 * 
 * @author Evan Schwartzman
 * Use Recursion to Draw Triangle Design
 */
public class Triangles {
	
	public static void main(String[] args) { //Create initial and ensuing Triangles
		ArgsProcessor ap= new ArgsProcessor(args);
		int triangleDivisions=ap.nextInt("How many divisions would you like to create?");
		StdDraw.setXscale(0, 1.0);
		StdDraw.setYscale(0, 1.0);
		StdDraw.setPenColor(Color.BLACK);
		draw(triangleDivisions, 0.0, 0.0, .5, 1.0, 1.0, 0.0);
	}

	//TA: javadoc?
	private static void draw(int n, double x1, double y1, double x2, double y2, double x3, double y3) { //Draw Triangles Divisions
		if (n!=0){
			double x4=(x1+x2)/2; //Create vertices for new triangles
			double y4=(y1+y2)/2;
			double x5=(x1+x3)/2;
			double y5=(y3+y3)/2;
			double x6=(x2+x3)/2;
			double y6=(y2+y3)/2;
			n--;
			draw(n, x1, y1, x4, y4, x5, y5); //Draw these new triangles
			draw(n, x4, y4, x2, y2, x6, y6);
			draw(n, x5, y5, x6, y6, x3, y3);
		}
		StdDraw.line(x1, y1, x2, y2); //Connect lines
		StdDraw.line(x2, y2, x3, y3);
		StdDraw.line(x3, y3, x1, y1);
		
	}

}
