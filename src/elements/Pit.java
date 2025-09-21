public class Pit extends Space {
    public Pit(int x, int y) {
        super(x, y);
    }

    // Returns gridType index.
    @Override
    public int hitboxType() {
        return 1;
    }

    public String toString() {
        return "X";
    }
}