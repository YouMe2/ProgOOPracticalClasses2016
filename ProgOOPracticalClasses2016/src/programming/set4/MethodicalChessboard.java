package programming.set4;

import java.awt.Color;

import acm.graphics.GLabel;
import acm.graphics.GRect;
import acm.program.GraphicsProgram;

public class MethodicalChessboard extends GraphicsProgram {

	// all of these are CLASSVARIABLES and CONSTANTS!

	public static final int CELL_SIZE = 52;
	public static final int CHESS_FIELDS = 8;
	public static final int BOARD_SIZE = CELL_SIZE * CHESS_FIELDS;
	public static final int BOARD_OFFSET = 50;

	// Space for the board descriptions.
	public static final int DESCRIPTION_OFFSET = 5;
	public static final int OVERALL_OFFSET = BOARD_OFFSET + DESCRIPTION_OFFSET;

	// 0 1 2 3 4 5
	// P N B R Q K

	public static final String[][] CHESS_PIECES = { { "\u2659", "\u2658", "\u2657", "\u2656", "\u2655", "\u2654" }, // WHITE
			{ "\u265F", "\u265E", "\u265D", "\u265C", "\u265B", "\u265A" } }; // BLACK

	// scope type name value
	public static final int CHESS_PIECE_PAWN = 0;
	public static final int CHESS_PIECE_KNIGHT = 1;
	public static final int CHESS_PIECE_BISHOP = 2;
	public static final int CHESS_PIECE_ROOK = 3;
	public static final int CHESS_PIECE_QUEEN = 4;
	public static final int CHESS_PIECE_KING = 5;

	public static final int PLAYER_WHITE = 0;
	public static final int PLAYER_BLACK = 1;

	@Override
	public void run() {
		// Make space for the whole board.
		setSize(OVERALL_OFFSET * 2 + BOARD_SIZE, OVERALL_OFFSET * 2 + BOARD_SIZE);

		drawChessboard();

		
		// PLAYER BLACK
		drawPiece(0, 0, CHESS_PIECE_ROOK, PLAYER_BLACK);
		drawPiece(1, 0, CHESS_PIECE_KNIGHT, PLAYER_BLACK);
		drawPiece(2, 0, CHESS_PIECE_BISHOP, PLAYER_BLACK);
		drawPiece(3, 0, CHESS_PIECE_QUEEN, PLAYER_BLACK);

		drawPiece(4, 0, CHESS_PIECE_KING, PLAYER_BLACK);
		drawPiece(5, 0, CHESS_PIECE_BISHOP, PLAYER_BLACK);
		drawPiece(6, 0, CHESS_PIECE_KNIGHT, PLAYER_BLACK);
		drawPiece(7, 0, CHESS_PIECE_ROOK, PLAYER_BLACK);

		drawPiece(0, 1, CHESS_PIECE_PAWN, PLAYER_BLACK);
		drawPiece(1, 1, CHESS_PIECE_PAWN, PLAYER_BLACK);
		drawPiece(2, 1, CHESS_PIECE_PAWN, PLAYER_BLACK);
		drawPiece(3, 1, CHESS_PIECE_PAWN, PLAYER_BLACK);

		drawPiece(4, 1, CHESS_PIECE_PAWN, PLAYER_BLACK);
		drawPiece(5, 1, CHESS_PIECE_PAWN, PLAYER_BLACK);
		drawPiece(6, 1, CHESS_PIECE_PAWN, PLAYER_BLACK);
		drawPiece(7, 1, CHESS_PIECE_PAWN, PLAYER_BLACK);

		// PLAYER WHITE
		drawPiece(0, 7, CHESS_PIECE_ROOK, PLAYER_WHITE);
		drawPiece(1, 7, CHESS_PIECE_KNIGHT, PLAYER_WHITE);
		drawPiece(2, 7, CHESS_PIECE_BISHOP, PLAYER_WHITE);
		drawPiece(3, 7, CHESS_PIECE_QUEEN, PLAYER_WHITE);

		drawPiece(4, 7, CHESS_PIECE_KING, PLAYER_WHITE);
		drawPiece(5, 7, CHESS_PIECE_BISHOP, PLAYER_WHITE);
		drawPiece(6, 7, CHESS_PIECE_KNIGHT, PLAYER_WHITE);
		drawPiece(7, 7, CHESS_PIECE_ROOK, PLAYER_WHITE);

		drawPiece(0, 6, CHESS_PIECE_PAWN, PLAYER_WHITE);
		drawPiece(1, 6, CHESS_PIECE_PAWN, PLAYER_WHITE);
		drawPiece(2, 6, CHESS_PIECE_PAWN, PLAYER_WHITE);
		drawPiece(3, 6, CHESS_PIECE_PAWN, PLAYER_WHITE);

		drawPiece(4, 6, CHESS_PIECE_PAWN, PLAYER_WHITE);
		drawPiece(5, 6, CHESS_PIECE_PAWN, PLAYER_WHITE);
		drawPiece(6, 6, CHESS_PIECE_PAWN, PLAYER_WHITE);
		drawPiece(7, 6, CHESS_PIECE_PAWN, PLAYER_WHITE); // HERE 7, 6,
															// CHESS_PIECE_PAWN,
															// PLAYER_WHITE are
															// the ARGUMENTS of
															// TYPE int

	}

	// This is a METHOD with java-doc and a return TYPE of void, therefore its a
	// procedure, this method takes no PARAMETERS as input, its SCOPE is PUBLIC
	/**
	 * This method draws a standard chessboard with all the labels.
	 * 
	 */
	public void drawChessboard() {

		// Draw a boarder
		add(new GRect(OVERALL_OFFSET, OVERALL_OFFSET, BOARD_SIZE, BOARD_SIZE));

		// these for-statements are CONTROL STATEMENT
		for (int i = 0; i < CHESS_FIELDS; i++) {
			for (int j = 0; j < CHESS_FIELDS; j++) {

				// this if-else statement are CONTROL STATEMENT
				if ((i + j) % 2 == 1) {
					drawSquare(i, j, Color.LIGHT_GRAY);
				} else {
					drawSquare(i, j, Color.WHITE);
				}

			}

			// Calculate the position for the left description.
			GLabel leftNumber = new GLabel("" + (8 - i));
			leftNumber.setLocation(BOARD_OFFSET - leftNumber.getWidth(),
					OVERALL_OFFSET + CELL_SIZE * i + CELL_SIZE / 2 + leftNumber.getHeight() / 2);
			add(leftNumber);

			// Calculate the position for the right description.
			GLabel rightNumber = new GLabel("" + (8 - i));
			rightNumber.setLocation(OVERALL_OFFSET + BOARD_SIZE + DESCRIPTION_OFFSET,
					OVERALL_OFFSET + CELL_SIZE * i + CELL_SIZE / 2 + leftNumber.getHeight() / 2);
			add(rightNumber);

			// Calculate the position for the top description.
			GLabel topLetter = new GLabel("" + (char) ('A' + i));
			topLetter.setLocation(OVERALL_OFFSET + CELL_SIZE * i + CELL_SIZE / 2 - topLetter.getWidth() / 2,
					BOARD_OFFSET);
			add(topLetter);

			// Calculate the position for the bottom description.
			GLabel bottomLetter = new GLabel("" + (char) ('A' + i));
			bottomLetter.setLocation(OVERALL_OFFSET + CELL_SIZE * i + CELL_SIZE / 2 - bottomLetter.getWidth() / 2,
					BOARD_OFFSET + BOARD_SIZE + DESCRIPTION_OFFSET + bottomLetter.getHeight());
			add(bottomLetter);

		}
	}

	// THIS IS A METHOD WITH JAVA-DOC
	/**
	 * This method draws the square identified by {@code x} and {@code y} in the
	 * color {@code color}.
	 * 
	 * @param x
	 *            file index
	 * @param y
	 *            rank index
	 * @param color
	 *            chosen color for the inside of the square.
	 */
	public void drawSquare(int x, int y, Color color) {
		// int x, int y, Color color are the PARAMTERS of TYPE int and Color

		// boardPiece is a LOCALVARIABLE, its SCOPE is the methodbody
		GRect boardPiece = new GRect(OVERALL_OFFSET + x * CELL_SIZE, OVERALL_OFFSET + y * CELL_SIZE, CELL_SIZE,
				CELL_SIZE);
		boardPiece.setColor(Color.BLACK);
		boardPiece.setFillColor(color);
		boardPiece.setFilled(true);
		add(boardPiece);
	}

	/**
	 * This method draws a specific chess {@code piece} of a {@code player} at
	 * the position of a specific square identified by {@code x} and {@code y}.
	 * 
	 * @see Pieces: {@link #CHESS_PIECE_PAWN}, {@link #CHESS_PIECE_KNIGHT},
	 *      {@link #CHESS_PIECE_BISHOP}, {@link #CHESS_PIECE_ROOK},
	 *      {@link #CHESS_PIECE_QUEEN}, {@link #CHESS_PIECE_KING}
	 * 
	 * @see Players: {@link #PLAYER_WHITE}, {@link #PLAYER_BLACK}
	 * 
	 * @param x
	 *            file index
	 * @param y
	 *            rank index
	 * @param piece
	 *            the piece
	 * @param player
	 *            player 1 or player 2
	 */
	public void drawPiece(int x, int y, int piece, int player) {

		GLabel chessPiece = new GLabel(CHESS_PIECES[player][piece]);
		chessPiece.setFont("SansSerif-bold-44");

		chessPiece.setLocation(OVERALL_OFFSET + x * CELL_SIZE + CELL_SIZE / 2 - chessPiece.getWidth() / 2,
				OVERALL_OFFSET + y * CELL_SIZE + CELL_SIZE / 2 + chessPiece.getHeight() / 3);

		chessPiece.setColor(Color.BLACK);
		add(chessPiece);
	}

}