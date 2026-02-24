package menu;

import controllers.MenuState;
import utilities.SpriteList;

public class ChangeButton extends MenuButton {
	private final int index;
	private final int modifier;
	
	public ChangeButton(MenuState parent, int x, int y, int index, int modifier) {
		super(parent, x, y);
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

	public void onActivate() {
		System.out.println(index);
	}
}
