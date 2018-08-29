package Pinball;

public class Tile {

    private boolean state;

    public Tile(boolean state) {
        this.state = state;
    }

    public boolean getState() {
        return this.state;
    }

    public void setState(boolean isPoint) {
        this.state = isPoint;
    }
}
