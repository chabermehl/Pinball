package Pinball;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.Random;

public class Ball extends Circle {

    private Display display;

    private int hitWall = 0;
    private double dx;
    private double dy;


    public Ball(Display display) {
        super(10);
        setFill(Color.RED);
        this.display = display;
        setCenterX(100);
        setCenterY(100);
    }

    public void reset() {
        hitWall = 0;

        dx = 0;
        dy = 0;
        setCenterX(100);
        setCenterY(100);
    }

    public void move(){

        setCenterX(getCenterX() + dx);
        setCenterY(getCenterY() + dy);

        if(getCenterX() <= 0 || getCenterX() >= display.getBoardWidth()) {
            dx = -dx;
            hitWall++;
        }
        if(getCenterY() <= 0 || getCenterY() >= display.getBoardHeight()) {
            dy = -dy;
            hitWall++;
        }
//        if(hitWall >= 3) {
//            reset();
//        }
        System.out.println(getCenterX()+","+getCenterY());
    }

    public int getLocationX() {
        return 0;
    }

    public int getLocationY() {
        return 0;
    }

    public void setInPlay() {
        double velocity = 5;
        Random rand = new Random();
        double angle = rand.nextDouble();
        dx = velocity * Math.cos(Math.PI * angle);
        dy = velocity * -Math.sin(Math.PI * angle);
    }

    public void setOffPlay() {

    }

    public void setStartLocation() {

    }

}
