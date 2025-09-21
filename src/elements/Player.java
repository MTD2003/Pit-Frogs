public class Player extends Wall {
    public Player(int x, int y) {
        super(x, y);
    }

    public int[] getPos() {
        int[] pos = {getX(), getY()};
        return pos;
    }

    public void setPos(int[] xy) {
        setX(xy[0]);
        setY(xy[1]);
    }

    // Returns gridType index.
    @Override
    public int hitboxType() {
        return 3;
    }

    public String toString() {
        return "P";
    }
}