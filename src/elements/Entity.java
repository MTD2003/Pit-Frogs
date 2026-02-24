package elements;
import utilities.SpriteList;

public class Entity {
    private int x, y;
    private int xScale, yScale;
    private SpriteList spriteIndex;
    private int imageIndex;

    public Entity(int x, int y) {
        this.x = x;
        this.y = y;
        xScale = 1;
        yScale = 1;
        imageIndex = 0;
    }
    
    public Entity(SpriteList spriteIndex, int x, int y) {
    	this.spriteIndex = spriteIndex;
        this.x = x;
        this.y = y;
        xScale = 1;
        yScale = 1;
        imageIndex = 0;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    
    public int getScaleX() {
    	return xScale;
    }
    
    public int getScaleY() {
    	return yScale;
    }

    public int getSprite() {
        return spriteIndex.ordinal();
    }

    public int getFrame() {
        return imageIndex;
    }
    
    public int getWidth() {
    	return spriteIndex.width();
	}
	
	public int getHeight() {
		return spriteIndex.height();
	}

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
    
    public void setScaleX(int xScale) {
    	this.xScale = xScale;
    }
    
    public void setScaleY(int yScale) {
    	this.yScale = yScale;
    }

    public void setSprite(SpriteList spriteIndex) {
        this.spriteIndex = spriteIndex;
    }

    public void setFrame(int imageIndex) {
        this.imageIndex = imageIndex;
    }
}