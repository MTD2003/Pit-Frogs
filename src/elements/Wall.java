public class Wall extends Space {
    public Wall(int x, int y) {
        super(x, y);
    }

    // Returns gridType index.
    @Override
    public int hitboxType() {
        return 2;
    }


    public String toString() {
        return "+";
    }
}