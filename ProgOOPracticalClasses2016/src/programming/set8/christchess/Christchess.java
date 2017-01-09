package programming.set8.christchess;

import acm.program.GraphicsProgram;
import static programming.set8.christchess.ChessPlayer.*;
import static programming.set8.christchess.ChessPieceType.*;

/**
 * This class controls a simplified chess game.
 */
@SuppressWarnings("serial")
public class Christchess extends GraphicsProgram {

	@Override
	public void run() {
		// Create the view using the programs canvas and
		// set the size appropriately.
		ChessView chessView = new ChessView(getGCanvas());
		setSize(getGCanvas().getWidth(), getGCanvas().getHeight());

		// Create the game data object and
		// initialize a game.
		ChessData chessData = new ChessData();
		
		chessData.initNewGame();
		chessView.init(chessData);

		// Main game loop
		do {
			ChessPiece piece = null;
			String moveStr;
			int x = -1;
			int y = -1;

			turnloop: while (true) {

				chessView.update(chessData);

				// Ask the user to select a piece.
				do {
					String selectStr = readLine(
							(chessData.getActivePlayer() == PLAYER_WHITE ? "White" : "Black") + ", select a piece: ");
					piece = chessData.getPieceAt(selectStr);

				} while (!chessData.isValidSelection(piece));

				// Display which squares are valid moves.
				chessView.updateValidMoves(chessData, piece);

				// Ask the user where to move the selected piece.
				do {
					moveStr = readLine("Move " + piece + " to: ");
					if (moveStr.equals("c"))
						continue turnloop;
					x = ChessData.stringToX(moveStr);
					y = ChessData.stringToY(moveStr);
				} while (!chessData.isValidMove(piece, x, y));

				// Reset the visual for the valid moves.
				// chessView.updateValidMoves(chessData, null); not needed??

				break;
			}

			// Move the piece and check for captured pieces.
			ChessPiece capturedPiece = chessData.movePieceTo(piece, x, y);
			if (capturedPiece != null) {
				println("Capture: " + piece + " captures " + capturedPiece);
			}

			// Change the player and update the board.
			chessData.togglePlayer();

			// Repeat until a player is checkmate.
		} while (chessData.isCheckmate() == NO_PLAYER);
		chessView.update(chessData);
		println("Player " + (chessData.isCheckmate() == PLAYER_WHITE ? "White" : "Black") + " is checkmate.");
	}

}