package lab4;
import cse131.ArgsProcessor;
import sedgewick.StdDraw;


public class BumpingBalls {
/**
 * Evan Schwartzman
 * Implementation of 2-d bouncing balls in the box from (-1, -1) to (1, 1).
 *
 * @param args
 */
	public static void main(String[] args) {
		ArgsProcessor ap = new ArgsProcessor(args);
		int pause = ap.nextInt("Enter pause time:");
		int balls = ap.nextInt("How many balls");

		

		StdDraw.setXscale(-1.0, 1.0);
		StdDraw.setYscale(-1.0, 1.0);

		double[][] position =new double[balls][2];
		double[][] velocity =new double[balls][2];
		double[] radius =new double[balls];
		// initial values
		for (int i=0; i< balls;i++){
			radius[i]=.099; 
			position[i][0]=Math.random(); 
			position[i][1]=Math.random(); 
			velocity[i][0]=((.045)*Math.random()); 
			velocity[i][1]=((.045)*Math.random()); 
			if (position[i][0]+radius[i]>1){position[i][0]=position[i][0]-radius[i];} // makes sure the balls stays within the canvas
			if (position[i][1]+radius[i]>1){position[i][0]=position[i][0]-radius[i];}
			if (position[i][0]-radius[i]<-1){position[i][0]=position[i][0]+radius[i];}
			if (position[i][1]-radius[i]<-1){position[i][0]=position[i][0]+radius[i];}
			for (int j=0; j<i;j++){
				//TA: This loop is problematic. Depending on where the balls spawn, the loop can run forever. -1
				while (Math.sqrt(Math.pow(position[j][0]-position[i][0], 2)+Math.pow(position[j][1]-position[i][1], 2))<=radius[i]+radius[j]);
				position[i][0]=Math.random();
				position[i][1]=Math.random();
				if (position[i][0]+radius[i]>1){position[i][0]=position[i][0]-radius[i];} // makes sure the balls stays within the canvas
				if (position[i][1]+radius[i]>1){position[i][0]=position[i][0]-radius[i];}
				if (position[i][0]-radius[i]<-1){position[i][0]=position[i][0]+radius[i];}
				if (position[i][1]-radius[i]<-1){position[i][0]=position[i][0]+radius[i];}
			}
		}
		// main animation loop
		while(true){
			for (int i=0; i< balls; i++){
				if (Math.abs(position[i][0] + velocity[i][0]) > (1.0 - radius[i])) velocity[i][0] = -velocity[i][0]; // bounce off wall according to law of elastic collision
				if (Math.abs(position[i][1] + velocity[i][1]) > (1.0 - radius[i])) velocity[i][1] = -velocity[i][1];
				for (int j=i+1; j<balls;j++){
					if (Math.sqrt(Math.pow(position[j][0]-position[i][0], 2)+Math.pow(position[j][1]-position[i][1], 2))<=radius[i]+radius[j]){ //bounce balls off one another
						velocity[i][0] = -velocity[i][0]; 
						velocity[i][1] = -velocity[i][1];
						velocity[j][0] = -velocity[j][0];
						velocity[j][1] = -velocity[j][1];

					}
				}

				// update position
				position[i][0]=velocity[i][0]+position[i][0];
				position[i][1]=velocity[i][1]+position[i][1];
			} // animation
			StdDraw.setPenColor(StdDraw.GRAY);
			StdDraw.filledSquare(0, 0, 1.0);
			StdDraw.setPenColor(StdDraw.BLACK);
			for (int i=0; i< balls; i++){
				StdDraw.filledCircle(position[i][0], position[i][1], radius[i]);
			}
			StdDraw.show(pause); // display and pause for 20 ms

		}
	}
}

