package elements;
import utilities.SpriteList;

public class Player extends Entity {
	private boolean dropPit;
	private boolean active;
	
	private static final int IN_PIT = 1;
	private static final int WIN_POSE = 2;
	
    public Player(int x, int y, int index) {
        super(x, y);
        dropPit = false;
        active = true;
        setPlayerSprite(index);
    }

    public void kill() {
    	active = false;
    	setFrame(IN_PIT);
    }
    
    public void win() {
    	setFrame(WIN_POSE);
    }
    
    public boolean isActive() {
    	return active;
    }
    
    public boolean getPit() {
    	return dropPit;
    }
    
    public void togglePit() {
    	dropPit = (dropPit) ? false : true;
    }
    
    public void setPlayerSprite(int index) {
    	switch(index) {
    		case 0:
    			setSprite(SpriteList.SPR_FROG1);
    			break;
    		case 1:
    			setSprite(SpriteList.SPR_FROG2);
    			break;
    		case 2:
    			setSprite(SpriteList.SPR_FROG3);
    			break;
    		default:
    			setSprite(SpriteList.SPR_FROG4);
    	}
    }

    public void setPos(int newX, int newY) {
        setX(newX);
        setY(newY);
    }
}