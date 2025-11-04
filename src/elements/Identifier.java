package elements;

import utilities.SpriteList;

public class Identifier extends Entity {
	Player attached;
	public Identifier(Player attached, int x, int y) {
		super(x, y);
		this.attached = attached;
		setSprite(SpriteList.SPR_IDENTIFIER);
	}
	
	public void setPlayer(Player attached) {
		this.attached = attached;
		update();
	}
	
	public void update() {
		setX(attached.getX());
		setY(attached.getY());
	}
}
