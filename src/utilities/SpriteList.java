package utilities;
public enum SpriteList {
    SPR_POINTER("/sprites/Pointer.png", 2),
    SPR_GRID_CURSOR("/sprites/grid/Cursor.png", 1),
    SPR_PLAYER_SELECTOR("/sprites/grid/Selector.png", 1),
    SPR_TILE("/sprites/grid/Tile.png", 1),
    SPR_PIT_CRUMBLE("/sprites/grid/Tile.png", 1),
    SPR_FROG1("/sprites/grid/Frog1.png", 1),
    SPR_FROG2("/sprites/grid/Frog2.png", 1),
    SPR_BLANK("/sprites/grid/Tile.png", 1); // Final sprite is always blank so we know the size of the sprite list.

	private final String path;
	private final int frames;
    

    SpriteList(String path, int frames) { // Note that frames start at 1.
        this.path = path;
        this.frames = frames;
    }

    public int frames() {
        return frames;
    }

    public String path() {
        return path;
    }
    
    /* 
    public static BufferedImage[] createSprite(int SpriteList) {
        BufferedImage sprite[] = null; 
        /*try {

        } catch {

        } finally {
            
        }
        return sprite;
    }
    */
}
