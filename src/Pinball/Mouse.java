package Pinball;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class Mouse {
    private double x;

    public EventHandler<MouseEvent> getMouseMovedHandler() {
        return event -> {
            x = event.getX();
        };
    }

    public double getX() {
        return x;
    }

}
