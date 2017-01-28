package programming.set8.christchess;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public enum ChessPieceType {

	PAWN("pawn", "\u2659", "\u265F"),
	KNIGHT("knight", "\u2658", "\u265E"), 
	BISHOP("bishop", "\u2657", "\u265D"), 
	ROOK("rook", "\u2656", "\u265C"), 
	QUEEN("queen", "\u2655", "\u265B"), 
	KING("king", "\u2654", "\u265A");

	private final String NAME;
	private final String ART1;
	private final String ART2;

	private ChessPieceType(String name, String art1, String art2) {
		NAME = name;
		ART1 = art1;
		ART2 = art2;
	}

	@Override
	public String toString() {
		return NAME;
	}

	public String toIconString(ChessPlayer player) {
		switch (player) {
		case PLAYER_WHITE:
			return ART1;

		case PLAYER_BLACK:

			return ART2;

		default:
			return "ERR";

		}
	}

}