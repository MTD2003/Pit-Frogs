package menu;

import java.awt.Font;
import java.awt.Graphics;

public class MenuText {
	private String text;
	private int x, y;
	private float textSize;
	private Font textFont;
	
	public MenuText(String text, int x, int y, float textSize, Font textFont) {
		this.x = x;
		this.y = y;
		this.text = text;
		this.textSize = textSize;
		this.textFont = textFont;
	}
	
	// Self draw is used here for simplicity.
	public void selfDraw(Graphics g) {
		// Font oldFont = g.getFont();
		g.setFont(textFont.deriveFont(textSize));
		g.drawString(text, x, y);
	}
	
	public int deriveWidth() {
		return (int)(text.length() * textSize);
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public String getText() {
		return text;
	}
	
	public float getSize() {
		return textSize;
	}
	
	public Font getFont() {
		return textFont;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public void setText(String text) {
		this.text = text;
	}
	
	public void setSize(int textSize) {
		this.textSize = textSize;
	}
	
	public void setFont(Font textFont) {
		this.textFont = textFont;
	}
}
