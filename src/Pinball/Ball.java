package Pinball;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Ball extends Circle {

    private Display display;

    public Ball(Display display) {
        super(10);
        setFill(Color.RED);
        this.display = display;
    }

    public void reset() {

    }

    public void move(){

    }

    public int getLocationX() {
        return 0;
    }

    public int getLocationY() {
        return 0;
    }

    public void setInPlay() {

    }

    public void setOffPlay() {

    }

    public void setStartLocation() {

    }

}
