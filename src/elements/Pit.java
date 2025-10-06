package elements;

public class Pit extends Space {
    public Pit(int x, int y) {
        super(x, y);
    }

    @Override
    public boolean isBlocked() {
        return false;
    }

    public String toString() {
        return "X";
    }
}