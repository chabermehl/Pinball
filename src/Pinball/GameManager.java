/*
 * @author Charles Habermehl
 * CS 351
 * 8/31/18
 * Pinball
 */
package Pinball;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.Random;


public class GameManager extends Application {


    private Stage window;
    private Display display = new Display(255, 400, 8, 5);

    private Tile[][] board = new Tile[display.getBoardRows()][display.getBoardColumns()];
    private Rectangle[][] boardTile = new Rectangle[display.getBoardRows()][display.getBoardColumns()];

    private GridPane gameTile = new GridPane();


    private Group root = new Group();
    private ObservableList<Node> list = root.getChildren();

    private Button reset = new Button("RESET");
    private Button play = new Button("PLAY");
    private Score score = new Score(0);

    private int hitWall = 0;
    private double dx;
    private double dy;


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        window = primaryStage;
        window.setTitle("Pinball");
        gameController();
    }

    /**
     * Main function
     * Controls GUI and has all the action handlers
     * pretty much all functions, little to no logic
     */
    private void gameController() {
        Circle ball = new Circle(10);
        ball.setFill(Color.RED);

        Scene gameScene;

        window.setMaxWidth(display.getBoardWidth());

        Rectangle grayRect = new Rectangle(window.getMaxWidth(), 20);
        grayRect.setStroke(Color.BLACK);
        grayRect.setFill(Color.GRAY);

        Label totalscore = new Label();

        play.setStyle("-fx-background-color: yellow;" + "-fx-text-fill: black;" + "-fx-border-color: black;");
        reset.setStyle("-fx-background-color: gray;" + "-fx-text-fill: black;" + "-fx-border-color: black;");

        totalscore.setAlignment(Pos.CENTER);
        totalscore.setPrefSize(30, 10);
        totalscore.setStyle("-fx-background-color: black;" + "-fx-text-fill: red;" + "-fx-border-color: black;" + "-fx-font-size: 16px;");

        fillGrid(display.getBoardRows(), display.getBoardColumns());

        play.setLayoutY(display.getBoardHeight() + 29);
        play.setLayoutX(210);

        totalscore.setLayoutY(display.getBoardHeight() + 29);
        totalscore.setLayoutX(127);

        reset.setLayoutY(display.getBoardHeight() + 29);
        reset.setLayoutX(10);

        grayRect.setY(display.getBoardHeight() + 9);

        list.addAll(gameTile, grayRect, ball, play, reset, totalscore);

        gameScene = new Scene(root, 260, 500);
        window.setMinHeight(500);
        window.setMinWidth(300);
        window.setScene(gameScene);
        window.show();

        play.setOnAction(event -> {
            setInPlay();
            reset.setStyle("-fx-background-color: yellow;" + "-fx-text-fill: black;" + "-fx-border-color: black;");
            play.setStyle("-fx-background-color: gray;" + "-fx-text-fill: black;" + "-fx-border-color: black;");
        });
        reset.setOnAction(event -> {
            reset(ball);
            play.setStyle("-fx-background-color: yellow;" + "-fx-text-fill: black;" + "-fx-border-color: black;");
            reset.setStyle("-fx-background-color: gray;" + "-fx-text-fill: black;" + "-fx-border-color: black;");
        });
        AnimationTimer animationTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                move(ball);
                totalscore.setText("" + score.getCurrentValue());
                checkCollision(ball);

            }
        };
        animationTimer.start();
    }

    /**
     * picks 3 random point squares on game board
     *
     * @param rows number of rows in the board
     * @param cols number of cols in the board
     */
    private void fillBoard(int rows, int cols) {
        int randCount = 0;
        while (randCount < 3) {
            int randrows = (int) (Math.random() * (rows - 1));
            int randcols = (int) (Math.random() * (cols - 1));
            if (!board[randrows][randcols].getState()) {
                board[randrows][randcols].setState(true);
                randCount++;
            }
        }
    }

    /**
     * fills pre allocated empty board
     *
     * @param rows number of rows in the board
     * @param cols number of cols in the board
     */
    private void setBoard(int rows, int cols) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                Tile tile = new Tile(false);
                tile.setState(false);
                board[i][j] = tile;
            }
        }
    }

    /**
     * fills game board with rectangle objects that are colored based on state
     *
     * @param rows number of rows in the board
     * @param cols number of columns in the board
     * @return 2D array of rectangle objects
     */
    private Rectangle[][] buildBoard(int rows, int cols) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                Rectangle rect = new Rectangle(50, 50);
                if (board[i][j].getState()) {
                    rect.setFill(Color.YELLOW);
                    rect.setStroke(Color.BLACK);
                } else {
                    rect.setFill(Color.BLUE);
                    rect.setStroke(Color.BLACK);
                }
                boardTile[i][j] = rect;
            }
        }
        return boardTile;
    }

    /**
     * calls board making functions and adds them to a gridpane
     *
     * @param rows number of rows in the board
     * @param cols number of columns in the board
     */
    private void fillGrid(int rows, int cols) {
        setBoard(rows, cols);
        fillBoard(rows, cols);
        boardTile = buildBoard(rows, cols);

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                gameTile.add(boardTile[i][j], j, i);
            }
        }
    }

    /**
     * puts the ball in its initial position
     *
     * @param ball circle object
     */
    private void reset(Circle ball) {
        hitWall = 0;

        dx = 0;
        dy = 0;
        setStartLocation(ball);
        play.setStyle("-fx-background-color: yellow;" + "-fx-text-fill: black;" + "-fx-border-color: black;");
        reset.setStyle("-fx-background-color: gray;" + "-fx-text-fill: black;" + "-fx-border-color: black;");

        fillGrid(display.getBoardRows(), display.getBoardColumns());
    }

    /**
     * handles moving ball and when the ball hits a wall
     * called in animation timer, direction and velocity are updated every time
     *
     * @param ball circle object
     */
    private void move(Circle ball) {

        ball.setCenterX(ball.getCenterX() + dx);
        ball.setCenterY(ball.getCenterY() + dy);

        if (ball.getCenterX() <= 0 || ball.getCenterX() >= display.getBoardWidth()) {
            dx = -dx;
            hitWall++;
        }
        if (ball.getCenterY() <= 0 || ball.getCenterY() >= display.getBoardHeight()) {
            dy = -dy;
            hitWall++;
        }
        if (hitWall >= 3) {
            reset(ball);
        }
    }

    /**
     * sets the ball into play at a random direction and velocity
     */
    private void setInPlay() {
        double velocity = 5;
        Random rand = new Random();
        double angle = rand.nextDouble();
        dx = velocity * Math.cos(Math.PI * angle);
        dy = velocity * -Math.sin(Math.PI * angle);
    }

    /**
     * sets the default start location of the ball
     *
     * @param ball circle object
     */
    private void setStartLocation(Circle ball) {
        ball.setCenterY(display.getBoardHeight() - 10);
        ball.setCenterX(127);
    }

    /**
     * checks if the ball touches a point square
     * if it does, increments score by 10 and paints square blue
     * currently not painting correctly but is scoring correctly
     *
     * @param ball circle object
     */
    private void checkCollision(Circle ball) {
        int xIndex = (int) ((ball.getCenterX()) / 50);
        int yIndex = (int) ((ball.getCenterY()) / 50);

        if (xIndex > 4) {
            xIndex = 4;
        }
        if (yIndex > 7) {
            yIndex = 7;
        }
        if (board[yIndex][xIndex].getState()) {
            board[yIndex][xIndex].setState(false);
            Rectangle rect = new Rectangle(50, 50);
            rect.setStroke(Color.BLACK);
            rect.setFill(Color.BLUE);
            boardTile[yIndex][xIndex] = rect;
            gameTile.add(boardTile[yIndex][xIndex], yIndex, xIndex);
            score.incrementBy(10);
        }
    }


}
