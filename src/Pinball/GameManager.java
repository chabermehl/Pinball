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

    private boolean inStart = true;


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        window = primaryStage;
        window.setTitle("Pinball");
        gameController();
    }

    private void gameController() {
        Circle ball = new Circle(10);
        ball.setFill(Color.RED);

        Scene gameScene;

        window.setMaxWidth(display.getBoardWidth());

        Rectangle grayRect = new Rectangle(window.getMaxWidth(), 20);
        grayRect.setStroke(Color.BLACK);
        grayRect.setFill(Color.GRAY);

        Label totalscore = new Label();
        totalscore.setText("" + score.getCurrentValue());

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
                checkCollision(ball, display.getBoardRows(), display.getBoardColumns());
            }
        };
        animationTimer.start();
    }

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

    private void setBoard(int rows, int cols) {
        for (int i = 0; i < cols; i++) {
            for (int j = 0; j < rows; j++) {
                Tile tile = new Tile(false);
                tile.setState(false);
                board[j][i] = tile;
            }
        }
    }

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

    private void reset(Circle ball) {
        hitWall = 0;

        dx = 0;
        dy = 0;
        //setStartLocation(ball);
        ball.setCenterX(100);
        ball.setCenterY(100);
        play.setStyle("-fx-background-color: yellow;" + "-fx-text-fill: black;" + "-fx-border-color: black;");
        reset.setStyle("-fx-background-color: gray;" + "-fx-text-fill: black;" + "-fx-border-color: black;");

        fillGrid(display.getBoardRows(), display.getBoardColumns());
    }

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
        System.out.println(ball.getCenterX() + "," + ball.getCenterY());
    }

    private void setInPlay() {
        double velocity = 5;
        Random rand = new Random();
        double angle = rand.nextDouble();
        dx = velocity * Math.cos(Math.PI * angle);
        dy = velocity * -Math.sin(Math.PI * angle);
    }

    private void setStartLocation(Circle ball) {
        ball.setCenterY(display.getBoardHeight() + 10);
        ball.setCenterX(127);
    }

    private void checkCollision(Circle ball, int rows, int cols) {
        boolean pointScore = false;
        int xIndex = (int)(ball.getCenterX() / 50);
        int yIndex = (int)(ball.getCenterY() / 50);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if(board[i][j].getState() && xIndex == i && yIndex == j) {
                    pointScore = true;
                }
                if (pointScore) {
                    Rectangle rect = new Rectangle(50, 50);
                    rect.setFill(Color.BLUE);
                    rect.setStroke(Color.BLACK);
                    boardTile[i][j] = rect;
                    score.incrementBy(10);
                    pointScore = false;
                }
            }
        }
    }
}
