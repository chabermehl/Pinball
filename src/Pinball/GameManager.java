package Pinball;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import java.text.SimpleDateFormat;
import java.util.*;
import javafx.animation.AnimationTimer;

public class GameManager extends Application {

    private Stage window;
    private Timer timer;
    public Label timeLabel;
    Display display = new Display(100, 100, 8, 5);
    private Tile[][] board = new Tile[display.getBoardRows()][display.getBoardColumns()];

    public static void main(String[] args) {
        launch(args);
    }


    private void start(Stage primaryStage) {
        window = primaryStage;
        window.setTitle("Pinball");
        gameController();
    }

    private void startScene() {
        BorderPane startLayout = new BorderPane();
    }

    private void gameController() {
        Scene gameScene;
        BorderPane rootPane = new BorderPane();
        GridPane gameTile = new GridPane();
        timer = new Timer();
        setBoard(display.getBoardRows(), display.getBoardColumns());
        fillBoard(display.getBoardRows(), display.getBoardColumns());

        Label totalscore = new Label();
        totalscore.setText("thanks wesley");

        HBox buttonsbois = new HBox(3);
        Button reset = new Button("RESET");
        Button play = new Button("PLAY");

        for (int i = 0; i < display.getBoardColumns(); i++) {
            for (int j = 0; j < display.getBoardRows(); j++) {
                gameTile.add(board[i][j], i, j);
            }
        }
    }

    private void fillBoard(int rows, int cols) {
        int randrows = (int)(Math.random()*(rows-1));
        int randcols = (int)(Math.random()*(cols-1));

        for (int i = 0; i < 3; i++) {
            board[randrows][randcols].setState(true);
        }
    }

    private void setBoard(int rows, int cols) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                board[i][j].setState(false);
            }
        }
    }
}
