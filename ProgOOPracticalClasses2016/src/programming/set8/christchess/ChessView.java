package programming.set8.christchess;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import acm.graphics.GCanvas;
import acm.graphics.GLabel;
import acm.graphics.GRect;

public class ChessView {

	public static final int CELL_SIZE = 52;
	public static final int CHESS_FIELDS = 8;
	public static final int BOARD_SIZE = CELL_SIZE * CHESS_FIELDS;
	public static final int BOARD_OFFSET = 50;

	// Space for the board descriptions.
	public static final int DESCRIPTION_OFFSET = 5;
	public static final int OVERALL_OFFSET = BOARD_OFFSET + DESCRIPTION_OFFSET;

	GCanvas gCanvas;

	public ChessView(GCanvas gCanvas) {
		this.gCanvas = gCanvas;
		gCanvas.setSize(OVERALL_OFFSET * 2 + BOARD_SIZE, OVERALL_OFFSET * 2 + BOARD_SIZE);
	}

	public void init(ChessData chessData) {
		// TODO Auto-generated method stub
		drawChessboard();
	}

	public void updateValidMoves(ChessData chessData, ChessPiece piece) {

		drawChessboard();

		if (piece != null) {
			List<Point> squares = piece.getValidTargetSquares(chessData);
			for (Point point : squares) {
				
				Color c = Color.GREEN;			
				if (chessData.getPieceAt(point.x, point.y) != null) {
					c = Color.RED;					
				}
				drawSquare(point.x, point.y, c);
				
			}
			
			

		}

		drawPieces(chessData);
	}

	public void update(ChessData chessData) {
		drawChessboard();
		drawPieces(chessData);
	}

	private void drawPieces(ChessData chessData) {

		List<ChessPiece> pieces = chessData.getPieces();

		for (ChessPiece piece : pieces) {

			drawPiece(piece);

		}

	}

	/**
	 * This method draws a standard chessboard with all the labels.
	 * 
	 */
	private void drawChessboard() {

		// Draw a boarder
		gCanvas.add(new GRect(OVERALL_OFFSET, OVERALL_OFFSET, BOARD_SIZE, BOARD_SIZE));

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
			gCanvas.add(leftNumber);

			// Calculate the position for the right description.
			GLabel rightNumber = new GLabel("" + (8 - i));
			rightNumber.setLocation(OVERALL_OFFSET + BOARD_SIZE + DESCRIPTION_OFFSET,
					OVERALL_OFFSET + CELL_SIZE * i + CELL_SIZE / 2 + leftNumber.getHeight() / 2);
			gCanvas.add(rightNumber);

			// Calculate the position for the top description.
			GLabel topLetter = new GLabel("" + (char) ('A' + i));
			topLetter.setLocation(OVERALL_OFFSET + CELL_SIZE * i + CELL_SIZE / 2 - topLetter.getWidth() / 2,
					BOARD_OFFSET);
			gCanvas.add(topLetter);

			// Calculate the position for the bottom description.
			GLabel bottomLetter = new GLabel("" + (char) ('A' + i));
			bottomLetter.setLocation(OVERALL_OFFSET + CELL_SIZE * i + CELL_SIZE / 2 - bottomLetter.getWidth() / 2,
					BOARD_OFFSET + BOARD_SIZE + DESCRIPTION_OFFSET + bottomLetter.getHeight());
			gCanvas.add(bottomLetter);

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
	private void drawSquare(int x, int y, Color color) {
		// int x, int y, Color color are the PARAMTERS of TYPE int and Color

		// boardPiece is a LOCALVARIABLE, its SCOPE is the methodbody
		GRect boardPiece = new GRect(OVERALL_OFFSET + x * CELL_SIZE, OVERALL_OFFSET + y * CELL_SIZE, CELL_SIZE,
				CELL_SIZE);
		boardPiece.setColor(Color.BLACK);
		boardPiece.setFillColor(color);
		boardPiece.setFilled(true);
		gCanvas.add(boardPiece);
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
	private void drawPiece(ChessPiece piece) {

		GLabel chessPiece = new GLabel(piece.toIconString());
		chessPiece.setFont("SansSerif-bold-44");

		chessPiece.setLocation(OVERALL_OFFSET + piece.getX() * CELL_SIZE + CELL_SIZE / 2 - chessPiece.getWidth() / 2,
				OVERALL_OFFSET + piece.getY() * CELL_SIZE + CELL_SIZE / 2 + chessPiece.getHeight() / 3);

		chessPiece.setColor(Color.BLACK);
		gCanvas.add(chessPiece);
	}

}
