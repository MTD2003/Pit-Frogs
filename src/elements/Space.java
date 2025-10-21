package elements;
import utilities.SpriteList;

public class Space extends Entity {
    private boolean blocked;

    public Space(int x, int y) {
        super(x, y);
        setSprite(SpriteList.SPR_TILE);
    }

    public boolean isBlocked() {
        return blocked;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }

    public String toString() {
        if(blocked)
            return "P";
        else
            return " ";
    }
}