package utilities;

public enum SpriteList {
    SPR_POINTER("/sprites/Pointer.png", 2),
    SPR_GRID_CURSOR("/sprites/grid/Cursor.png", 2),
    SPR_PLAYER_SELECTOR("/sprites/grid/Selector.png", 1),
    SPR_TILE("/sprites/grid/Tile.png", 1),
    SPR_PIT_CRUMBLE("/sprites/grid/Pit.png", 1),
    SPR_FROG1("/sprites/grid/Frog1.png", 2),
    SPR_FROG2("/sprites/grid/Frog2.png", 2),
    SPR_FROG3("/sprites/grid/Frog3.png", 2),
    SPR_FROG4("/sprites/grid/Frog4.png", 2);
	
	public static final int SPRITE_DIMENSIONS = 16;
	private final String path;
	private final int frames;

    SpriteList(String path, int frames) { // Note that frames start at 1.
        this.path = path;
        this.frames = frames;
    }
    
    public static int size() {
    	return values().length;
    }

    public int frames() {
        return frames;
    }

    public String path() {
        return path;
    }
}
