package menu;

import java.awt.Font;
import java.awt.Graphics;

public class MenuText extends MenuElement {
	String text;
	Font fontData;
	int textSize;
	
	public MenuText(String text, int x, int y, int width, int height) {
		super(x, y, width, height);
		this.text = text;
	}
	
	@Override
	public void onDraw(Graphics g) {
		//g.setFont(fontData);
		g.drawString(text, getX(), getY());
	}
	
	public String getText() {
		return text;
	}
	
	public void setText(String text) {
		this.text = text;
	}
}
