package elements;

public abstract class Entity {
    private int x;
    private int y;
    private int spriteIndex;
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
        return spriteIndex;
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

    public void setSprite(int spriteIndex) {
        this.spriteIndex = spriteIndex;
    }

    public void setFrame(int imageIndex) {
        this.imageIndex = imageIndex;
    }
}