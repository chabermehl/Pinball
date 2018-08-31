/*
 * @author Charles Habermehl
 * CS 351
 * 8/31/18
 * Pinball
 */
package Pinball;

public class Score {

    private int score;

    /**
     * score constructor
     *
     * @param score integer value of the score
     */
    public Score(int score) {
        this.score = score;
    }

    /**
     * gets the current score
     *
     * @return score object
     */
    public int getCurrentValue() {
        return this.score;
    }

    /**
     * increments the score by a chosen value
     *
     * @param value integer value
     * @return score object incremented by the integer value
     */
    public int incrementBy(int value) {
        return this.score += value;
    }
}
