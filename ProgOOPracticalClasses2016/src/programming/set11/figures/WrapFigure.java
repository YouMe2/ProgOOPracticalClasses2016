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

	public WrapFigure(int width, String wrapFigureFilename, double scale, String text) {
		this.width = width;

		this.image = new GImage(wrapFigureFilename);
		image.scale(scale);

		this.text = text;

	}

	public void setTextFont(String font) {
		this.font = font;
	}

	public void setBorder(int border) {
		this.border = border;
	}

	public void setSpacing(int space) {
		this.space = space;
	}

	public void setLineSpacingFactor(double spacing) {
		this.lineSpacing = spacing;
	}

	public GCompound getCompound() {
		GLabel exampleLabel = new GLabel("");
		exampleLabel.setFont(font);
		FontMetrics metric = exampleLabel.getFontMetrics();

		GCompound comp = new GCompound();
		comp.add(image, border, border);
		text = text.replace("\n", " \n");
		StringTokenizer tokenizer = new StringTokenizer(text, " ", true);
		String line = "";
		
		int x = (int)(image.getWidth()) + border + space;
		int restWidth = width - x - border; // border, image, border, rest, border
		int y = border + metric.getHeight();
		
		while (tokenizer.hasMoreTokens()) {
			
			String token = tokenizer.nextToken();
			
			
			
			if(token.startsWith("\n") || metric.stringWidth(line + token) > restWidth -1 || !tokenizer.hasMoreTokens()) {
				
				GLabel label = new GLabel(line, x, y);
				label.setFont(font);
				comp.add(label);
				line = token ;
				y = y + (int)(metric.getHeight() * lineSpacing);
				if (y >= (border + image.getHeight() + space + metric.getAscent())) {
					x = border;
					restWidth = width - 2 * border;
				}
			}
			else {
				line += token;
			}	
		}

		return comp;
	}

}
