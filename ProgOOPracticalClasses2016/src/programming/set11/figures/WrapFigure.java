package programming.set11.figures;

import java.awt.FontMetrics;
import java.util.StringTokenizer;

import acm.graphics.GCompound;
import acm.graphics.GImage;
import acm.graphics.GLabel;

public class WrapFigure {

	private int width;
	private GImage image;
	private String text;
	private String font = "Serif-12";
	private int border = 10;
	private int space = 10;
	private double lineSpacing = 1.2;

	/**
	 * Constructs a new {@link WrapFigure} from the given width, image, scale,
	 * and text. The finished {@link GCompound} containing the image and the
	 * text can be obtained by the {@link #getCompound()} method.
	 * 
	 * @param width the width of the {@link GCompound}
	 * @param wrapFigureFilename the path of the image to be displayed in the {@link GCompound}
	 * @param scale the scale of the image
	 * @param text the text to be displayed next to the image
	 */
	public WrapFigure(int width, String wrapFigureFilename, double scale, String text) {
		this.width = width;

		this.image = new GImage(wrapFigureFilename);
		image.scale(scale);

		this.text = text;

	}

	/**
	 * Sets the font of the text.
	 * 
	 * @param font
	 */
	public void setTextFont(String font) {
		this.font = font;
	}

	/**
	 * Sets the border.
	 * 
	 * @param border
	 */
	public void setBorder(int border) {
		this.border = border;
	}

	/**
	 * Stes the spacing.
	 * 
	 * @param space
	 */
	public void setSpacing(int space) {
		this.space = space;
	}

	/**
	 * Sets the linespacingfactor.
	 * 
	 * @param spacing
	 */
	public void setLineSpacingFactor(double spacing) {
		this.lineSpacing = spacing;
	}

	/**
	 * Rerurns the {@link GCompound} containing the image and the wraped text.
	 * 
	 * @return the finished {@link GCompound}
	 */
	public GCompound getCompound() {
		GLabel exampleLabel = new GLabel("");
		exampleLabel.setFont(font);
		FontMetrics metric = exampleLabel.getFontMetrics();

		GCompound comp = new GCompound();
		comp.add(image, border, border);
		text = text.replace("\n", " \n");
		StringTokenizer tokenizer = new StringTokenizer(text, " ", true);
		String line = "";

		int x = (int) (image.getWidth()) + border + space;
		int restWidth = width - x - border; // border, image, border, rest,
											// border
		int y = border + metric.getAscent();

		while (tokenizer.hasMoreTokens()) {

			String token = tokenizer.nextToken();
			assert token != null : "There musst by a token != null";

			if (token.startsWith("\n") || metric.stringWidth(line + token) >= restWidth || !tokenizer.hasMoreTokens()) {

				if (!tokenizer.hasMoreTokens()) {
					line += token;
				}

				GLabel label = new GLabel(line, x, y);
				label.setFont(font);
				comp.add(label);
				line = token.trim();

				y = y + (int) (metric.getHeight() * lineSpacing);

				if (y >= (border + image.getHeight() + space + metric.getAscent())) {
					x = border;
					restWidth = width - 2 * border;
				}
			} else {
				line += token;
				assert tokenizer.hasMoreTokens() : "Can not have been the last token!";
			}
		}

		return comp;
	}

}
