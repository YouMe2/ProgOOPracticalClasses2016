package programming.set8.christchess;

public enum ChessPlayer {

	NO_PLAYER("no_player"), PLAYER_WHITE("white")/*unten*/, PLAYER_BLACK("black")/*oben*/;

	private final String COLOR_TEXT;

	ChessPlayer(String color) {
		COLOR_TEXT = color;
	}

	public ChessPlayer opponent() {
		switch (this) {
		case PLAYER_WHITE:
			return PLAYER_BLACK;

		case PLAYER_BLACK:

			return PLAYER_WHITE;

		default:
			return NO_PLAYER;

		}
	}

	@Override
	public String toString() {
		return COLOR_TEXT;
	}
}
