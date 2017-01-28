package programming.set8.christchess;

import static programming.set8.christchess.ChessPieceType.BISHOP;
import static programming.set8.christchess.ChessPlayer.PLAYER_BLACK;
import static programming.set8.christchess.ChessPlayer.PLAYER_WHITE;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ChessPiece {

	private ChessPieceType type;
	private ChessPlayer player;
	private int x;
	private int y;


	public ChessPiece(ChessPieceType type, ChessPlayer player, int x, int y) {
		this.type = type;
		this.player = player;
		this.x = x;
		this.y = y;

	}

	private boolean isValidTargetSquare(ChessData data, Point p) {
		return data.isValidPosition(p) && (data.getPieceAt(p.x, p.y) == null
				|| data.getPieceAt(p.x, p.y).getPlayer() == this.getPlayer().opponent());
	}

	/**
	 * Retrieves a list of points that are valid target squares for this piece.
	 * This includes opponent's squares if that would be a valid figure capture.
	 * 
	 * @param data
	 * @return
	 */
	public List<Point> getValidTargetSquares(ChessData data) {

		List<Point> validSquares = new ArrayList<Point>() {

			@Override
			public boolean add(Point e) {

				if (isValidTargetSquare(data, e)) {
					super.add(e);
					if (data.getPieceAt(e.x, e.y) == null) { // sonst wurde
																// geschlagen!
						return true;
					}
				}
				return false;
			}
		};

		switch (getType()) {
		case PAWN:

			int dy = 0;

			if (getPlayer() == PLAYER_WHITE) { // weiﬂ von unten
				dy = -1;

				// unten reihe 2 special move
				if (y == 6)
					validSquares.add(new Point(x, y + 2 * dy));

			} else if (getPlayer() == PLAYER_BLACK) { // schwarz von oben
				dy = 1;

				// oben reihe 7 special move
				if (y == 1)
					validSquares.add(new Point(x, y + 2 * dy));
			}

			validSquares.add(new Point(x, y + dy));

			// special killing move
			if (data.getPieceAt(x - 1, y + dy) != null
					&& data.getPieceAt(x - 1, y + dy).getPlayer() == this.getPlayer().opponent())
				validSquares.add(new Point(x - 1, y + dy));

			// special killing move
			if (data.getPieceAt(x + 1, y + dy) != null
					&& data.getPieceAt(x + 1, y + dy).getPlayer() == this.getPlayer().opponent())
				validSquares.add(new Point(x + 1, y + dy));

			break;

		case KNIGHT:

			validSquares.add(new Point(x + 1, y + 2));
			validSquares.add(new Point(x + 2, y + 1));

			validSquares.add(new Point(x - 1, y - 2));
			validSquares.add(new Point(x - 2, y - 1));

			validSquares.add(new Point(x - 1, y + 2));
			validSquares.add(new Point(x - 2, y + 1));

			validSquares.add(new Point(x + 1, y - 2));
			validSquares.add(new Point(x + 2, y - 1));

			break;

		case QUEEN:
		case BISHOP:

			Point pb = new Point(x, y);
			do {
				pb = pb.getLocation();
				pb.x += 1;
				pb.y += 1;

			} while (validSquares.add(pb));

			pb = new Point(x, y);
			do {
				pb = pb.getLocation();
				pb.x -= 1;
				pb.y += 1;

			} while (validSquares.add(pb));

			pb = new Point(x, y);
			do {
				pb = pb.getLocation();
				pb.x += 1;
				pb.y -= 1;

			} while (validSquares.add(pb));

			pb = new Point(x, y);
			do {
				pb = pb.getLocation();
				pb.x -= 1;
				pb.y -= 1;

			} while (validSquares.add(pb));

			if (getType() == BISHOP)
				break;

		case ROOK:

			Point pr = new Point(x, y);
			do {
				pr = pr.getLocation();
				pr.x += 1;

			} while (validSquares.add(pr));

			pr = new Point(x, y);
			do {
				pr = pr.getLocation();
				pr.x -= 1;

			} while (validSquares.add(pr));

			pr = new Point(x, y);
			do {
				pr = pr.getLocation();

				pr.y -= 1;

			} while (validSquares.add(pr));

			pr = new Point(x, y);
			do {
				pr = pr.getLocation();
				pr.y += 1;

			} while (validSquares.add(pr));

			break;

		case KING:

			validSquares.add(new Point(x + 0, y + 1));
			validSquares.add(new Point(x + 0, y - 1));
			validSquares.add(new Point(x + 1, y + 0));
			validSquares.add(new Point(x - 1, y + 0));

			validSquares.add(new Point(x + 1, y + 1));
			validSquares.add(new Point(x - 1, y - 1));

			validSquares.add(new Point(x - 1, y + 1));
			validSquares.add(new Point(x + 1, y - 1));

			break;

		default:
			break;
		}

		return new ArrayList<>(validSquares);
	}

	public void moveTo(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public ChessPieceType getType() {
		return type;
	}

	public ChessPlayer getPlayer() {
		return player;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	@Override
	public String toString() {
		return player.toString() + " " + type.toString();
	}

	public String toIconString() {
		return type.toIconString(player);
	}
}
