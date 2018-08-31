/*
 * @author Charles Habermehl
 * CS 351
 * 8/31/18
 * Pinball
 */

//really wish i had time to use this class

package Pinball;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class Mouse {
    private double x;

    /**
     * captures where the mouse clicks
     *
     * @return x coord of mouse click
     */
    public EventHandler<MouseEvent> getMouseMovedHandler() {
        return event -> {
            x = event.getX();
        };
    }

    /**
     * getter for x click
     *
     * @return double value
     */
    public double getX() {
        return x;
    }

}
