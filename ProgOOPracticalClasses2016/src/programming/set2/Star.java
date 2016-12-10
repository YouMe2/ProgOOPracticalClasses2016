package programming.set2;

import acm.graphics.GLine;
import acm.program.GraphicsProgram;

public class Star extends GraphicsProgram {

	@Override
	public void run() {

		setSize(700, 700);
		// userinput beeing saved in variables
		int points = readInt("number of star points: ");
		int outerR = readInt("outer radius: ");
		int innerR = readInt("inner radius: ");

		// draws the star (see below)
		drawStar(points, outerR, innerR, 350, 350);
	}

	/**
	 * This method draws a Star with a certain number of {@code points} and the
	 * two given radiuses at the specified location.
	 * 
	 * @param points
	 *            The number of points of the Star
	 * @param outerR
	 *            The radius of the points
	 * @param innerR
	 *            The inner radius of the solid part of the star
	 * @param centerX
	 *            The x-coord of the center of the star
	 * @param centerY
	 *            The y-coord of the center of the star
	 */
	private void drawStar(int points, int outerR, int innerR, int centerX, int centerY) {

		// calculate the angle for each point: (2*PI / points) 
		// calculate the angle for each edge of one point: (2*PI/points)/2 = PI/points
		double angle = Math.PI / points;

		//for every point...
		for (int point = 0; point < points; point++) {

			// ... there are 2 edges:
			int edge = point * 2;

			//the coord of the point it self => the 1st cord of the two edges
			double xOuterSin = Math.sin(angle * edge) * outerR + centerX;
			double yOuterCos = Math.cos(angle * edge) * outerR + centerY;

			//the 2nd coord of the RIGHT edge 
			double xInnerSinR = Math.sin(angle * (edge + 1)) * innerR + centerX;
			double yInnerCosR = Math.cos(angle * (edge + 1)) * innerR + centerY;

			//the 2nd coord of the LEFT edge 
			double xInnerSinL = Math.sin(angle * (edge - 1)) * innerR + centerX;
			double yInnerCosL = Math.cos(angle * (edge - 1)) * innerR + centerY;

			
			// the two edges:
			add(new GLine(xOuterSin, yOuterCos, xInnerSinR, yInnerCosR));
			add(new GLine(xOuterSin, yOuterCos, xInnerSinL, yInnerCosL));

		}

	}

}
