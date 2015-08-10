package recursivepatterns;

import java.awt.Color;
import sedgewick.*;

public class PersianRug {

	/**
	 * 
	 * @param palette an array of Colors to choose from
	 * @param llx lower left x coordinate of this rug square
	 * @param lly lower left y coordinate of this rug square
	 * @param size width (and therefore also height) of this rug square
	 * @param north color index of the north side of this rug square
	 * @param east color index of the east side of this rug square
	 * @param south color index of the south side of this rug square
	 * @param west color index of the west side of this rug square
	 */
	private static void persianRug(
			Color[] palette, 
			double llx, double lly,
			double size, 
			int north, int east, int south, int west) {
		if(size<=.002){
			return;
		}
		size=size/2;
		int index = (north+east+south+west);
		index*=3;
		index++;
		index=index%palette.length;
		StdDraw.setPenColor(palette[index]);
		
		StdDraw.line(llx+size, lly, llx+size, lly+size*.5);
		StdDraw.line(llx, lly+size, llx+size*.5, lly);
		persianRug(palette,llx,lly,size,index,index,south,west);
		persianRug(palette,llx+size,lly,size,index,east,south,index);
		persianRug(palette,llx,lly+size,size,north,index,index,west);
		persianRug(palette,llx+size,lly+size,size,north,east,index,index);
	}

	public static void main(String args[]) {
		//
		// Generate a palette of colors
		//
		Color[] palette = { StdDraw.BLUE, StdDraw.CYAN, StdDraw.DARK_GRAY,
				StdDraw.GRAY, StdDraw.GREEN, StdDraw.LIGHT_GRAY,
				StdDraw.MAGENTA, StdDraw.ORANGE, StdDraw.PINK, StdDraw.RED,
				StdDraw.WHITE, StdDraw.YELLOW };
		//
		// Draw the outermost square as a special case
		// Use color 0 for that
		//
		StdDraw.setPenColor(palette[0]);
		StdDraw.line(0, 0, 1, 0);
		StdDraw.line(1, 0, 1, 1);
		StdDraw.line(1, 1, 0, 1);
		StdDraw.line(0, 1, 0, 0);

		//
		// Kick off the recursion
		// Lower left is point (0,0)
		// Size of the square is 1
		// The color index of each surrounding side is 0
		//
		persianRug(palette, 0, 0, 1, 0, 0, 0, 0);
	}

}
