package Pinball;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.Random;

public class Ball extends Circle {

    private Display display;




    public Ball(Display display) {
        super(10);
        setFill(Color.RED);
        this.display = display;
        setCenterX(100);
        setCenterY(100);
    }





    public int getLocationX() {
        return 0;
    }

    public int getLocationY() {
        return 0;
    }



    public void setOffPlay() {

    }

    public void setStartLocation() {

    }

}
