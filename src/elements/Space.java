package elements;
import utilities.SpriteList;

public class Space extends Entity {
    private boolean blocked;

    public Space(int x, int y) {
        super(x, y);
        setSprite(SpriteList.SPR_TILE);
    }
    
    // TODO: Add some sort of feedback.
    public void onLand(Player player) {
    	blocked = true;
    	player.togglePit(); // Alerts player to change if next jump makes a pit.
    }

    public boolean isBlocked() {
        return blocked;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }
}