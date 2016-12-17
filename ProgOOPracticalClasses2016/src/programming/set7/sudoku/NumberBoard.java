package programming.set7.sudoku;

/**
 * A two-dimensional board of non-negative integers. Each cell on the board can
 * either be {@link #EMPTY} or contain an integer.
 */
public class NumberBoard {
	
	/** Value of an empty cell. */
	public static final int EMPTY = -1;
	
	/** The board's state. */
	private int[][] board;
	
	
	/**
	 * Creates a new board with the given number of rows and columns, all
	 * initialized to be {@link #EMPTY}.
	 * 
	 * @param rows
	 *            the number of rows. Must be {@code >= 1}.
	 * @param columns
	 *            the number of columns. Must be {@code >= 1}.
	 * @throws IllegalArgumentException
	 *             if rows or columns are below 1.
	 */
	public NumberBoard(int rows, int columns) {
		if (rows < 1 || columns < 1) {
			throw new IllegalArgumentException("Rows and columns must be >= 1");
		}
		
		board = new int[rows][columns];
		
		for (int row = 0; row < board.length; row++) {
			for (int col = 0; col < board[row].length; col++) {
				board[row][col] = EMPTY;
			}
		}
	}
	
	
	/**
	 * Returns the value in the given cell of the board.
	 * 
	 * @param row
	 *            the cell's row, starting at 0.
	 * @param col
	 *            the cell's column, starting at 0.
	 * @return the value in the cell, which may be {@link #EMPTY}. Will also
	 *         return {@link #EMPTY} if the coordinates are out of range.
	 */
	public int getValueAt(int row, int col) {
		if (isInRange(row, col)) {
			return board[row][col];
		} else {
			return EMPTY;
		}
	}
	
	/**
	 * Sets the value of the given cell.
	 * 
	 * @param row
	 *            the cell's row, starting at 0.
	 * @param col
	 *            the cell's column, starting at 0.
	 * @param value
	 *            the cell's value. Must be {@code >= 0} or {@link #EMPTY}.
	 */
	public void setValueAt(int row, int col, int value) {
		if (isInRange(row, col) && (value == EMPTY || value >= 0)) {
			board[row][col] = value;
		}
	}
	
	
	/**
	 * Checks if the given coordinates identify a valid cell on the board.
	 * 
	 * @param row
	 *            the cell's row, starting at 0.
	 * @param col
	 *            the cell's column, starting at 0.
	 * @return {@code true} if the coordinate is in range, {@code false}
	 *          otherwise.
	 */
	protected final boolean isInRange(int row, int col) {
		return 0 <= row
				&& row < board.length
				&& 0 <= col
				&& col < board[row].length;
	}
}
