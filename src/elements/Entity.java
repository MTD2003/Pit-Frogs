package elements;
import utilities.SpriteList;

public abstract class Entity {
    private int x;
    private int y;
    private SpriteList spriteIndex;
    private int imageIndex;

    public Entity(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getSprite() {
        return spriteIndex.ordinal();
    }

    public int getFrame() {
        return imageIndex;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setSprite(SpriteList spriteIndex) {
        this.spriteIndex = spriteIndex;
    }

    public void setFrame(int imageIndex) {
        this.imageIndex = imageIndex;
    }
}