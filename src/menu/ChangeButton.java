package menu;

import controllers.MenuState;
import elements.Entity;
import utilities.SpriteList;
import view.Interactable;

public class ChangeButton extends Entity implements Interactable {
	private final MenuState parent;
	private final int index;
	private final int modifier;
	
	public ChangeButton(MenuState parent, int x, int y, int index, int modifier) {
		super(x, y);
		
		this.parent = parent;
		this.index = index;
		this.modifier = modifier;
		
		spriteCheck();
	}
	
	private void spriteCheck() {
		if(modifier > 0)
			setSprite(SpriteList.SPR_UP_ARROW);
		else
			setSprite(SpriteList.SPR_DOWN_ARROW);
	}

	public void onNothing() {
		setFrame(0);
	}

	public void onHover() {
		setFrame(1);
	}

	public void onPress() {
		setFrame(2);
	}

	public void onActivate() {
		parent.changePlanters(index, modifier);
	}
}
