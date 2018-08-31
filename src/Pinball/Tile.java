/*
 * @author Charles Habermehl
 * CS 351
 * 8/31/18
 * Pinball
 */
package Pinball;

public class Tile {

    private boolean state;

    /**
     * tile constructor
     *
     * @param state takes a boolean for the state of the tile
     */
    public Tile(boolean state) {
        this.state = state;
    }

    /**
     * gets the state object
     *
     * @return state object
     */
    public boolean getState() {
        return this.state;
    }

    /**
     * set the state of the tile
     *
     * @param isPoint boolean value
     */
    public void setState(boolean isPoint) {
        this.state = isPoint;
    }
}
