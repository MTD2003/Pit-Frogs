package elements;

public class Pit extends Space {
    public Pit(int x, int y) {
        super(x, y);
    }

    @Override
    public boolean isBlocked() {
        return true;
    }

    public String toString() {
        return "X";
    }
}