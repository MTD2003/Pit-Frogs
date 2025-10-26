package elements;
import utilities.SpriteList;

public class Pit extends Space {
    public Pit(int x, int y) {
        super(x, y);
        setSprite(SpriteList.SPR_PIT_CRUMBLE);
    }
    
    @Override
    public void onLand(Player player) {
    	player.kill();
    }

    @Override
    public boolean isBlocked() {
        return false;
    }
}