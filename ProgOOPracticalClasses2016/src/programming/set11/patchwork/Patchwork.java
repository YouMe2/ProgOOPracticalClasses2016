package programming.set11.patchwork;

import java.awt.Color;

import acm.graphics.GArc;
import acm.graphics.GCompound;
import acm.graphics.GLine;
import acm.graphics.GPolygon;
import acm.graphics.GRect;
import acm.program.GraphicsProgram;
import acm.util.RandomGenerator;

public class Patchwork extends GraphicsProgram {

	private static final int BLOCK_SIZE = 80;

	@Override
	public void run() {

		int columns = 5;
		int rows = 3;
		 do {
		 columns = readInt("columns: ");
		
		 } while (columns < 1);
		 do {
		 rows = readInt("rows: ");
		
		 } while (columns < 1);

		GCompound comp = getPatchworkArt(columns, rows, BLOCK_SIZE);
		setSize(columns * BLOCK_SIZE + 1, rows * BLOCK_SIZE + 1);

		// POSTCONDITION!
		assert comp.getWidth() == columns * BLOCK_SIZE : "Die breiten passen nicht zusammen!";
		assert comp.getHeight() == rows * BLOCK_SIZE : "Die höhen passen nicht zusammen!";

		add(comp);

	}

	/**
	 * Returns a {@link GCompound} filled with funny patchwork art in rows and
	 * columns.
	 * 
	 * @param col
	 * @param row
	 * @param blockSize
	 *            the size of one patch
	 * @return The funny patchwork art
	 */
	private static GCompound getPatchworkArt(int col, int row, int blockSize) {
		GCompound comp = new GCompound();

		for (int x = 0; x < col; x++) {
			for (int y = 0; y < row; y++) {

				comp.add(getRandomPatch(blockSize), x * blockSize, y * blockSize);
			}
		}

		return comp;

	}

	/**
	 * Returns a random Patch of the given size. One of Circle-,Line-,Chess- or
	 * SuperPatch.
	 * 
	 * @param size
	 * @return a random Patch
	 */
	private static GCompound getRandomPatch(int size) {

		RandomGenerator rand = RandomGenerator.getInstance();

		switch (rand.nextInt(4)) {
		case 0:
			return getCirclePatch(size);
		case 1:
			return getLinePatch(size);
		case 2:
			return getChessPatch(size);
		case 3:
			return getSuperPatch(size);
		default:
			return null;

		}

	}

	/**
	 * Returns a {@link GCompound} holding a super patch of the given size.
	 * 
	 * @param size
	 * @return a superPatch
	 */
	private static GCompound getSuperPatch(int size) {
		GCompound comp = new GCompound();

		GRect rect = new GRect(size, size);
		rect.setFilled(true);
		rect.setFillColor(new Color((int) (Math.random() * 256 * 256 * 256)));
		comp.add(rect, 0, 0);

		// INVARIANT - zwar seeeehr überflüßig hier aber sie ist da!
		assert comp.getElementAt(size / 2, size / 2) == rect : "es sollte rect zusehen sein in der Mitte...";

		CrazyShape c = new CrazyShape(size / 4, size);
		c.setFilled(true);
		c.setFillColor(new Color((int) (Math.random() * 256 * 256 * 256)));
		comp.add(c);

		return comp;
	}

	/**
	 * Returns a {@link GCompound} holding a chess patch of the given size.
	 * 
	 * @param size
	 * @return a chess patch
	 */
	private static GCompound getChessPatch(int size) {
		// PRECONDITION
		assert size / 8 == size / 8.0 : "size war kein n-Faches von 8! -> lücke im Bild";

		GCompound comp = new GCompound();

		int s = size / 8;

		for (int x = 0; x < 8; x++) {
			for (int y = 0; y < 8; y++) {

				GRect rect = new GRect(s, s);
				rect.setFilled(true);
				rect.setFillColor(new Color((int) (Math.random() * 256 * 256 * 256)));
				comp.add(rect, x * s, y * s);

			}
		}

		return comp;
	}

	/**
	 * Returns a {@link GCompound} holding a line patch of the given size.
	 * 
	 * @param size
	 * @return a line patch
	 */
	private static GCompound getLinePatch(int size) {
		GCompound comp = new GCompound();

		GRect rect = new GRect(size, size);
		rect.setFilled(true);
		rect.setFillColor(new Color((int) (Math.random() * 256 * 256 * 256)));
		comp.add(rect, 0, 0);

		for (int x = 0; x < size; x++) {

			GLine line = new GLine((int) (Math.random() * size), (int) (Math.random() * size),
					(int) (Math.random() * size), (int) (Math.random() * size));
			line.setColor(new Color((int) (Math.random() * 256 * 256 * 256)));
			comp.add(line);

		}
		return comp;
	}

	/**
	 * Returns a {@link GCompound} holding a circle patch of the given size.
	 * 
	 * @param size
	 * @return a circle patch
	 */
	private static GCompound getCirclePatch(int size) {
		GCompound comp = new GCompound();

		GRect rect = new GRect(size, size);
		rect.setFilled(true);
		rect.setFillColor(new Color((int) (Math.random() * 256 * 256 * 256)));
		comp.add(rect, 0, 0);

		for (int i = 0; i < 3; i++) {
			for (int a = 0; a < 4; a++) {
				GArc arc = new GArc(size / (i + 1), size / (i + 1), a * 90, 90);
				arc.setFilled(true);
				arc.setFillColor(new Color((int) (Math.random() * 256 * 256 * 256)));
				comp.add(arc, i * size / (i + 1) / 2, i * size / (i + 1) / 2);

			}
		}

		return comp;
	}

}

class CrazyShape extends GPolygon {

	/**
	 * Creates a new crazy shape, being a polygon with n edges and a maximum.
	 * 
	 * @param n
	 *            the number of edges n > 2
	 * @param size
	 *            the max size of the polygon
	 */
	public CrazyShape(int n, int size) {

		if (n < 3)
			throw new IllegalArgumentException("n may not ne smaller than 3!");

		for (int i = 0; i < n; i++) {

			this.addVertex((int) (Math.random() * size), (int) (Math.random() * size));

		}

	}

}
