package utilities;

public enum SpriteList {
    SPR_POINTER("/sprites/Pointer.png", 2, 16, 16),
    SPR_GRID_CURSOR("/sprites/grid/Cursor.png", 3, 16, 16),
    SPR_IDENTIFIER("/sprites/grid/Selector.png", 1, 16, 16),
    SPR_TILE("/sprites/grid/Tile.png", 1, 16, 16),
    SPR_PIT_CRUMBLE("/sprites/grid/Pit.png", 1, 16, 16),
    SPR_FROG1("/sprites/grid/Frog1.png", 3, 16, 16),
    SPR_FROG2("/sprites/grid/Frog2.png", 3, 16, 16),
    SPR_FROG3("/sprites/grid/Frog3.png", 3, 16, 16),
    SPR_FROG4("/sprites/grid/Frog4.png", 3, 16, 16),
    SPR_NO_PLAYER("/sprites/menu/NOPLAYER.png", 1, 16, 16),
    SPR_UP_ARROW("/sprites/menu/ArrowUp.png", 3, 16, 16),
    SPR_DOWN_ARROW("/sprites/menu/ArrowDown.png", 3, 16, 16),
    SPR_SIDE_ARROW("/sprites/menu/ArrowSide.png", 3, 10, 9),
    SPR_MENU_BUTTON("/sprites/menu/MenuButton.png", 3, 48, 16),
    SPR_OPTION_BUTTON("/sprites/menu/OptionButton.png", 3, 48, 16),
    SPR_START_BUTTON("/sprites/menu/StartButton.png", 3, 80, 16);
	
	public static final int SPRITE_DIMENSIONS = 16;
	private final String path;
	private final int frames, width, height;

    SpriteList(String path, int frames, int width, int height) { // Note that frames start at 1.
        this.path = path;
        this.frames = frames;
        this.width = width;
        this.height = height;
    }
    
    public static int size() {
    	return values().length;
    }
    
    public static SpriteList playerSpriteAtIndex(int index) {
    	switch(index) {
    		case 0:
    			return SPR_FROG1;
    		case 1:
    			return SPR_FROG2;
    		case 2:
    			return SPR_FROG3;
    		case 3:
    			return SPR_FROG4;
    		default:
    			return SPR_NO_PLAYER;
    	}
    }

    public int frames() {
        return frames;
    }
    
    public int width() {
    	return width;
    }
    
    public int height() {
    	return height;
    }

    public String path() {
        return path;
    }
}
