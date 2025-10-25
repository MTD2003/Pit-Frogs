package elements;
import utilities.SpriteList;

public class Player extends Entity {
	private Boolean dropPit;
	
    public Player(int x, int y, int index) {
        super(x, y);
        dropPit = false;
        setPlayerSprite(index);
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
    
    public Boolean getPit() {
    	return dropPit;
    }
    
    public void togglePit() {
    	dropPit = (dropPit) ? false : true;
    }
    
    public void onDie() {
    	
    }

    public String toString() {
        return "P";
    }
}