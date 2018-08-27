package Pinball;

public class Score {

    private int score;

    public Score (int score){
        this.score = score;
    }
    public void reset() {
        this.score = 0;
    }

    public int getCurrentValue() {
        return this.score;
    }

    public void incrementBy(int value) {
        this.score += value;
    }
}
