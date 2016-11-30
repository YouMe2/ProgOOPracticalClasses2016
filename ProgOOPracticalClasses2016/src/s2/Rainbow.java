package s2;

import java.awt.Color;

import acm.graphics.GArc;
import acm.program.GraphicsProgram;

/**
 * This program draws a nice and colorful rainbow on blue(ish) sky.
 * 
 * 
 * @author Yannik Eikmeier
 *
 */
public class Rainbow extends GraphicsProgram {

	@Override
	public void run() {
		// resizing the applet so that i can see what i'm doing
		setSize(700, 400);

		// saving the background color for later use
		Color bgColor = new Color(157, 217, 237);

		// saving alle the color to be used in the rainbow
		Color c[] = new Color[] { Color.RED, Color.ORANGE, Color.YELLOW, Color.GREEN, Color.CYAN, Color.BLUE };

		// setting the background color
		setBackground(bgColor);

		// draws the rainbow (see below)
		drawRainbow(c, bgColor, 10, 10, 600, 600, 20);

	}

	/**
	 * This method draws a rainbow in the given colors, on a given background
	 * color. The position and size of the Rainbow needs to be specified by the
	 * x and y cords, width, height and the thickness.
	 * 
	 * @param colors
	 *            The color Array from wich the rainbow will be drawn.
	 * @param bgColor
	 *            The background color on which the rainbow will be drawn.
	 * @param x
	 *            The x cord of the rainbows upper left corner.
	 * @param y
	 *            The y cord of the rainbows upper left corner.
	 * @param width
	 *            The width of the rain bow.
	 * @param height
	 *            The height of he rain bow.
	 * @param thickness
	 *            The thickness of the single layers of the rainbow.
	 */
	private void drawRainbow(Color colors[], Color bgColor, int x, int y, int width, int height, int thickness) {

		// every layer is the same...
		for (int i = 0; i < colors.length + 1; i++) {

			GArc arc;

			// checking if still drawing the rainbow or already redrawing the
			// sky
			if (i == colors.length) {

				// This change of size is only done to get rid of some lonely
				// pixels shining through under the rainbow if done otherwise.
				arc = new GArc(x + i * thickness, y + i * thickness, width - i * 2 * thickness,
						height - i * 2 * thickness, 0, 180);
				arc.setColor(bgColor);
			} else {
				arc = new GArc(x + i * thickness, y + i * thickness, width - i * 2 * thickness,
						height - i * 2 * thickness, 20, 140);
				arc.setColor(colors[i]);
			}

			arc.setFilled(true);
			add(arc);
		}

	}

}
