package elements;
import utilities.Constants;
import utilities.SpriteList;

public class Player extends Entity {
    public Player(int x, int y, int index) {
        super(x, y);
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
    
    public void setPos(int[] xy) {
        setX(xy[Constants.X]);
        setY(xy[Constants.Y]);
    }

    public int[] getPos() {
        int[] pos = {getX(), getY()};
        return pos;
    }

    public String toString() {
        return "P";
    }
}