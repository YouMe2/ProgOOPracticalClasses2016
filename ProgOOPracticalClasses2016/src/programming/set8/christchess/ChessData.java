package programming.set8.christchess;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import static programming.set8.christchess.ChessPieceType.*;
import static programming.set8.christchess.ChessPlayer.*;

public class ChessData {

	private ChessPiece[][] board = new ChessPiece[8][8];

	private ChessPlayer activePLayer = PLAYER_WHITE;

	private ArrayList<ChessPiece> kings = new ArrayList<>();

	// INIT
	public void initNewGame() {

		addNewPiece(ROOK, PLAYER_BLACK, 0, 0);
		addNewPiece(KNIGHT, PLAYER_BLACK, 1, 0);
		addNewPiece(BISHOP, PLAYER_BLACK, 2, 0);
		addNewPiece(QUEEN, PLAYER_BLACK, 3, 0);

		addNewPiece(KING, PLAYER_BLACK, 4, 0);
		addNewPiece(BISHOP, PLAYER_BLACK, 5, 0);
		addNewPiece(KNIGHT, PLAYER_BLACK, 6, 0);
		addNewPiece(ROOK, PLAYER_BLACK, 7, 0);

		addNewPiece(PAWN, PLAYER_BLACK, 0, 1);
		addNewPiece(PAWN, PLAYER_BLACK, 1, 1);
		addNewPiece(PAWN, PLAYER_BLACK, 2, 1);
		addNewPiece(PAWN, PLAYER_BLACK, 3, 1);

		addNewPiece(PAWN, PLAYER_BLACK, 4, 1);
		addNewPiece(PAWN, PLAYER_BLACK, 5, 1);
		addNewPiece(PAWN, PLAYER_BLACK, 6, 1);
		addNewPiece(PAWN, PLAYER_BLACK, 7, 1);

		addNewPiece(ROOK, PLAYER_WHITE, 0, 7);
		addNewPiece(KNIGHT, PLAYER_WHITE, 1, 7);
		addNewPiece(BISHOP, PLAYER_WHITE, 2, 7);
		addNewPiece(QUEEN, PLAYER_WHITE, 3, 7);

		addNewPiece(KING, PLAYER_WHITE, 4, 7);
		addNewPiece(BISHOP, PLAYER_WHITE, 5, 7);
		addNewPiece(KNIGHT, PLAYER_WHITE, 6, 7);
		addNewPiece(ROOK, PLAYER_WHITE, 7, 7);

		addNewPiece(PAWN, PLAYER_WHITE, 0, 6);
		addNewPiece(PAWN, PLAYER_WHITE, 1, 6);
		addNewPiece(PAWN, PLAYER_WHITE, 2, 6);
		addNewPiece(PAWN, PLAYER_WHITE, 3, 6);

		addNewPiece(PAWN, PLAYER_WHITE, 4, 6);
		addNewPiece(PAWN, PLAYER_WHITE, 5, 6);
		addNewPiece(PAWN, PLAYER_WHITE, 6, 6);
		addNewPiece(PAWN, PLAYER_WHITE, 7, 6);

	}

	// ACTIONS
	public void togglePlayer() {
		activePLayer = activePLayer.opponent();
	}

	public void setActivePlayer(ChessPlayer player) {
		activePLayer = player;
	}

	/**
	 * Adds a new specific piece to a specific position on the chess board and
	 * returns the newly created ChessPiece. You can use this method to create
	 * specific scenarios on the board.
	 * 
	 * @param type
	 * @param player
	 * @param x
	 * @param y
	 * @return
	 */
	public ChessPiece addNewPiece(ChessPieceType type, ChessPlayer player, int x, int y) {

		ChessPiece piece = new ChessPiece(type, player, x, y);

		if (type == KING) {
			kings.add(piece);
		}

		setPieceAt(piece, x, y);

		return piece;
	}

	/**
	 * Actually moves the piece to the specified location. If there is an
	 * opponent's piece on that square, remove it from the board and return the
	 * captured ChessPiece object.
	 * 
	 * @param piece
	 * @param x
	 * @param y
	 * @return captured ChessPiece object
	 */
	public ChessPiece movePieceTo(ChessPiece piece, int x, int y) {

		if (!isValidPosition(new Point(x, y)))
			throw new IllegalArgumentException("Position out of bounds!");

		ChessPiece oldPiece = getPieceAt(x, y);
		setPieceAt(null, piece.getX(), piece.getY());

		piece.moveTo(x, y);
		setPieceAt(piece, x, y);

		// if (oldPiece != null && oldPiece.getPlayer() ==
		// piece.getPlayer().opponent()) {
		return oldPiece;
		// } else if (oldPiece != null && oldPiece.getPlayer() ==
		// piece.getPlayer()) {
		// // TODOo??
		// return null;
		// }
		// return null;

	}

	public void removeAllPieces() {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				setPieceAt(null, i, j);
			}
		}
	}

	private void setPieceAt(ChessPiece piece, int x, int y) {

		if (isValidPosition(new Point(x, y))) {
			board[x][y] = piece;
		} else
			throw new IllegalArgumentException("Position out of bounds!");
	}
	// ACTIONS

	// GETTERS
	public ChessPlayer getActivePlayer() {
		return activePLayer;
	}

	public List<ChessPiece> getPieces() {

		ArrayList<ChessPiece> pieces = new ArrayList<>();

		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {

				ChessPiece p = getPieceAt(i, j);
				if (p != null) {
					pieces.add(p);
				}

			}
		}

		return pieces;
	}

	public ChessPiece getPieceAt(String selectStr) {
		return getPieceAt(stringToX(selectStr), stringToY(selectStr));
	}

	/**
	 * inlcs a validPosCheck
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	public ChessPiece getPieceAt(int x, int y) {

		if (isValidPosition(new Point(x, y))) {
			return board[x][y];
		} else
			return null;

	}
	// GETTERS

	// IS-ERS

	public boolean isValidPosition(Point p) {
		return p.x >= 0 && p.x < board.length && p.y >= 0 && p.y < board[p.x].length;
	}

	/**
	 * Returns true if the ChessPiece is valid and the active player is allowed
	 * to move this piece.
	 * 
	 * @param piece
	 * @return
	 */
	public boolean isValidSelection(ChessPiece piece) {

		return piece != null && piece.getPlayer() == getActivePlayer() && !piece.getValidTargetSquares(this).isEmpty();
	}

	public ChessPlayer isCheckmate() {

		List<ChessPiece> pieces = getPieces();

		for (ChessPiece king : kings) {

			List<Point> validMoves = king.getValidTargetSquares(this);
			validMoves.add(new Point(king.getX(), king.getY()));
			
			for (ChessPiece piece : pieces) {

				if (piece.getPlayer() == king.getPlayer().opponent())
					validMoves.removeAll(piece.getValidTargetSquares(this));// WONT REMOVE POSSIBLE PAWN KILL SQUARES!!!

				if (validMoves.isEmpty())
					return king.getPlayer();
			}

			

		}

		return NO_PLAYER;
	}

	public boolean isValidMove(ChessPiece piece, int x, int y) {

		return piece.getValidTargetSquares(this).contains(new Point(x, y));
	}
	// IS-ERS

	// STATIC
	public static int stringToX(String moveStr) {
		if (moveStr.length() < 2)
			return -1;
		return moveStr.charAt(0) - 'a';
	}

	public static int stringToY(String moveStr) {
		if (moveStr.length() < 2)
			return -1;
		return 8 - (moveStr.charAt(1) - '0');
	}
	// STATIC

}
