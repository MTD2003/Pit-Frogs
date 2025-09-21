public class Space {
    private int x;
    private int y;

    public Space(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    // Returns gridType index.
    public int hitboxType() {
        return 0;
    }

    public String toString() {
        return " ";
    }
}