package Pinball;

import javafx.scene.shape.Circle;

public class Ball extends Circle {

    private int x;
    private int y;

    public Ball(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void reset() {

    }

    public void move(){

    }

    public int getLocationX() {
        return this.x;
    }

    public int getLocationY() {
        return this.y;
    }

    public void setInPlay() {

    }

    public void setOffPlay() {

    }

    public void setStartLocation() {

    }

}
