package elements;
import utilities.SpriteList;

public class Player extends Entity {
	private boolean dropPit;
	private boolean active;
	private boolean bot;
	
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
    
    public boolean isBot() {
    	return bot;
    }
    
    public void togglePit() {
    	dropPit = (dropPit) ? false : true;
    }
    
    public boolean getPit() {
    	return dropPit;
    }
    
    public void setPlayerSprite(int index) {
    	setSprite(SpriteList.playerSpriteAtIndex(index));
    }

    public void setPos(int newX, int newY) {
        setX(newX);
        setY(newY);
    }
}