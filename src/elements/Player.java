package elements;

public class Player extends Entity {
    public Player(int x, int y) {
        super(x, y);
    }

    public void setPos(int newX, int newY) {
        setX(newX);
        setY(newY);
    }
    
    public void setPos(int[] xy) {
        setX(xy[0]);
        setY(xy[1]);
    }

    public int[] getPos() {
        int[] pos = {getX(), getY()};
        return pos;
    }

    public String toString() {
        return "P";
    }
}