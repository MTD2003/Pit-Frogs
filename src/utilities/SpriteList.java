package utilities;
public enum SpriteList {
    SPR_POINTER("/sprites/Pointer.png",1),
    SPR_GRID_CURSOR("/sprites/grid/Cursor.png", 0),
    SPR_TILE("/sprites/grid/Tile.png", 0),
    SPR_PIT_CRUMBLE("/sprites/grid/Tile.png", 0),
    SPR_FROG1("/sprites/grid/Frog1.png", 0),
    SPR_FROG2("/sprites/grid/Frog2.png", 0),
    SPR_BLANK("", 0); // Final sprite is always blank so we know the size of the sprite list.

    private final int frames;
    private final String path;
    //public static final int FRAME_SIZE = 16;

    SpriteList(String path, int frames) { // Note that frames start at 0 for logic reasons
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
