package Pinball;

public class Tile {

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
