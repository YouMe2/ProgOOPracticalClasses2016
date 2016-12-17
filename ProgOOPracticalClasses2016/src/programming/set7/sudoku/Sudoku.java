package programming.set7.sudoku;

public class Sudoku extends NumberBoard {

	/**
	 * The size of the smaller boxes on the sudoku board.
	 */
	public static final int BOX_SIZE = 3;

	/**
	 * The size of the full sudoku board.
	 */
	public static final int SUDOKU_SIZE = BOX_SIZE * BOX_SIZE;

	/**
	 * Constructs a new {@link Sudoku} board.
	 */
	public Sudoku() {
		super(SUDOKU_SIZE, SUDOKU_SIZE);
	}

	/**
	 * Sets the {@code value} at the specified position on the board.
	 * 
	 * 
	 * @param col
	 *            the cell's column, starting at 0.
	 * @param row
	 *            the cell's row, starting at 0.
	 * @param value
	 *            the cell's value. Must be {@code >= 1} or {@link #EMPTY}.
	 */
	@Override
	public void setValueAt(int col, int row, int value) {
		if (value == EMPTY || (0 < value && value < SUDOKU_SIZE + 1))
			super.setValueAt(row, col, value);
	}

	/**
	 * Returns the value in the given cell of the board.
	 * 
	 * @param col
	 *            the cell's column, starting at 0.
	 * @param row
	 *            the cell's row, starting at 0.
	 * @return the value in the cell, which may be {@link #EMPTY}. Will also
	 *         return {@link #EMPTY} if the coordinates are out of range.
	 */
	@Override
	public int getValueAt(int col, int row) {
		return super.getValueAt(row, col); // only done to correct the oder of
											// x/row and y/col.
	}

	/**
	 * Returns true only if the Sudoku board contains no wrong numbers.
	 * 
	 * @return true only is the Sudoku board is valid correct
	 */
	public boolean isValid() {

		for (int count = 0; count < SUDOKU_SIZE; count++) {

			if (!rowValid(count))
				return false;

			if (!colValid(count))
				return false;

			if (!boxValid(count % BOX_SIZE, count / BOX_SIZE))
				return false;

		}
		return true;
	}

	/**
	 * Returns true only is the specified box contains no wrong numbers.
	 * 
	 * @param x
	 *            the x coord of the box
	 *            {@code (x < }{@value #BOX_SIZE}{@code )}
	 * @param y
	 *            the y coord of the box
	 *            {@code (y < }{@value #BOX_SIZE}{@code )}
	 * @return true only if the box in valid
	 */
	private boolean boxValid(int x, int y) {

		// This is an int-Array of length 9. I use it to count the different
		// appearances of the numbers on the board.
		int[] counter = new int[9];

		for (int i = 0; i < BOX_SIZE; i++) {
			for (int j = 0; j < BOX_SIZE; j++) {

				int number = getValueAt(i + BOX_SIZE * x, j + BOX_SIZE * y);

				if (number != EMPTY) {
					if (counter[number - 1] != 0)
						return false;

					counter[number - 1]++;
				}

			}
		}

		return true;
	}

	/**
	 * Returns true only is the specified row contains no wrong numbers.
	 * 
	 * @param row
	 *            the index of the row {@code (row < }{@value #SUDOKU_SIZE}
	 *            {@code )}
	 * @return true only if the row in valid
	 */
	private boolean rowValid(int row) {

		// This is an int-Array of length 9. I use it to count the different
		// appearances of the numbers on the board.
		int[] counter = new int[9];

		for (int col = 0; col < SUDOKU_SIZE; col++) {

			int number = getValueAt(row, col);

			if (number != EMPTY) {
				if (counter[number - 1] != 0)
					return false;

				counter[number - 1]++;
			}

		}

		return true;
	}

	/**
	 * Returns true only is the specified column contains no wrong numbers.
	 * 
	 * @param col
	 *            the index of the row
	 *            {@code (col < }{@value #SUDOKU_SIZE}{@code )}
	 * @return true only if the row in valid
	 */
	private boolean colValid(int col) {

		// This is an int-Array of length 9. I use it to count the different
		// appearances of the numbers on the board.
		int[] counter = new int[9];

		for (int row = 0; row < SUDOKU_SIZE; row++) {

			int number = getValueAt(row, col);

			if (number != EMPTY) {
				if (counter[number - 1] != 0)
					return false;

				counter[number - 1]++;
			}

		}

		return true;
	}

	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();

		for (int col = 0; col < SUDOKU_SIZE; col++) {
			for (int row = 0; row < SUDOKU_SIZE; row++) {

				int value = getValueAt(col, row);

				if (value == EMPTY)
					str.append(" ");
				else
					str.append(value);

				str.append(" ");

			}
			str.append("\n");
		}

		return str.toString();
	}

}

/*
 * 
 * - Array
 * 		Arrays are homogeneous and ordered "sets" or "lists" of Elements.
 * 		Their length can not be changed after initialization.
 * 
 * NO appearances of: 
 * 
 * - ArrayList or any other List-Type 
 * 		Lists are (same as Arrays) homogeneous and ordered but can adjust
 * 		there size in order to store a changing number of Elements.
 * 
 * - Exceptions
 * 		Exceptions are "error messages" or events that can disrupt the
 * 		normal flow of a program.
 * 
 * - The keyword "throw"
 * 		"throw" is used to indicate that an Exception occured and specifies
 * 		which Exception to throw.
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 */