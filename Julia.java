package julia;

import java.awt.Color;

import sedgewick.StdDraw;
import julia.Complex;

public class Julia {
	private double width;
	private double height;
	private int maxiters;
	private Complex ll;
	private Complex ur;
	
	/**
	 * Remembers the width and height as instance variables, and establishes the default picture. See {@link reset() reset}
	 * @param width
	 * @param height
	 */
	public Julia(int width, int height) {
		this.width=width;
		this.height=height;
		//reset();
	}
	
	
	/**
	 * Establish the coordinates of the display to go from -2 - 2i at the lower-lefthand corner to 2 + 2i at the upper-right,
	 * and set the maximum number of iterations to 50.
	 */
	public void reset() {
		this.maxiters=50;
		setCoordinates(new Complex(-2.0, -2.0), new Complex(2.0, 2.0));

	}
	
	/**
	 * Reset the display so that the supplied complex coordinates frame the lower left and upper right of what is seen.
	 * @param ll new lower-left coordinate for the displayed area
	 * @param ur new upper-right coordinate for the displayed area.
	 */
	public void setCoordinates(Complex ll, Complex ur) {
		this.ll=ll;
		this.ur=ur;
		draw();
		
		
	}
	
	/**
	 * Zoom in to the area framed by 1/4 of the current display.
	 */
	public void zoomIn() {
		double width = (this.ur.getReal()-this.ll.getReal());
		double height =(this.ur.getImaginary()-this.ll.getImaginary());
		double width1 = width/4;
		double height1 = height/4;
		Complex Ll=new Complex((ll.getReal()+width1),(ll.getImaginary()+height1));
		Complex Ur=new Complex((ur.getReal()-width1),(ur.getImaginary()-height1));
		setCoordinates(Ll, Ur);
		
	}
	
	/**
	 * Refresh the display by computing point by point the color value as described in the lab.
	 * It may be helpful to call StdDraw.show(0) before and after you are done, so that the display need not be
	 * update after each point is computed.
	 * It may also be helpful to fill the display with a white rectangle before computing and establishing the points' colors.
	 */
	public void draw(){
		StdDraw.setXscale(this.ll.getReal(), this.ur.getReal());
		StdDraw.setYscale(this.ll.getImaginary(), this.ur.getImaginary());
		StdDraw.clear();
		StdDraw.show(0);
		double o = this.ur.getReal()-this.ll.getReal();
		double h = this.ur.getImaginary()-this.ll.getImaginary();
		for (double z=this.ll.getReal(); z<=this.ur.getReal(); z=(z+(o/this.width))){
			for (double y=this.ll.getImaginary(); y<=this.ur.getImaginary(); y=+(y+(h/this.height))){
				
				Complex a = new Complex(z,y);
				Color color = Color.black;
				if (rigor(a) < this.maxiters){
				   color = Color.getHSBColor((rigor(a) % 256)/255.0f, 1.0f, 1.0f);
				StdDraw.setPenColor(color);
				StdDraw.point(z, y);
				}
			}
		}
		StdDraw.show(0);
	}

	public int rigor(Complex c){
		Complex z = new Complex(-0.7795, .134);
		int iters = 0;
		while ((c.abs() < 2) && (iters < this.maxiters)){
		     c = z.plus(c.times(c));
		     iters++;
		}

		return iters;
	}
	
	/**
	 *  Inverse of {@link zoomIn() zoomIn}:  as if the currently viewed display becomes framed by 1/4 of the new display all around.
	 */
	public void zoomOut() {
		double width = (this.ur.getReal()-this.ll.getReal());
		double height =(this.ur.getImaginary()-this.ll.getImaginary());
		double width1 = width/2;
		double height1 = height/2;
		Complex Ll=new Complex((ll.getReal()-width1),(ll.getImaginary()-height1));
		Complex Ur=new Complex((ur.getReal()+width1),(ur.getImaginary()+height1));
		setCoordinates(Ll, Ur);
	}

	/**
	 * Increase the maximum number of iterations per point by 50.
	 */
	public void bump() {
		this.maxiters= this.maxiters+50;
		draw();
		
	}

	/**
	 * Decrease the maximum number of iterations per point by 50, but make sure the result does not drop below 50.
	 */
	public void unbump() {
		if (this.maxiters>50){
			this.maxiters=this.maxiters-50;
		}
		draw();
	}
	

}
