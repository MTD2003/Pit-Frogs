package elements;
import utilities.SpriteList;

public class Player extends Entity {
	private boolean dropPit;
	private boolean active;
	
    public Player(int x, int y, int index) {
        super(x, y);
        dropPit = false;
        active = true;
        setPlayerSprite(index);
    }

    public void kill() {
    	active = false;
    	// setSprite();
    }
    
    public void togglePit() {
    	dropPit = (dropPit) ? false : true;
    }
    
    public void setPlayerSprite(int index) {
    	switch(index) {
    		case 0 -> setSprite(SpriteList.SPR_FROG1);
    		default -> setSprite(SpriteList.SPR_FROG2);
    	}
    }

    public void setPos(int newX, int newY) {
        setX(newX);
        setY(newY);
    }
    
    public boolean isActive() {
    	return active;
    }
    
    public boolean getPit() {
    	return dropPit;
    }

    public String toString() {
        return "P";
    }
}