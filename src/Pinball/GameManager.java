package Pinball;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;


public class GameManager extends Application {


    private Stage window;
    private Display display = new Display(271, 400, 8, 5);

    private Tile[][] board = new Tile[display.getBoardRows()][display.getBoardColumns()];
    private Rectangle[][] boardTile = new Rectangle[display.getBoardRows()][display.getBoardColumns()];

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
        Scene gameScene;
        GridPane rootPane = new GridPane();
        GridPane gameTile = new GridPane();
        Circle ball = new Circle(10);
        ball.setFill(Color.RED);
        setBoard(display.getBoardRows(), display.getBoardColumns());
        fillBoard(display.getBoardRows(), display.getBoardColumns());
        boardTile = buildBoard(display.getBoardRows(), display.getBoardColumns());

        window.setMaxWidth(display.getBoardWidth());

        Rectangle grayRect = new Rectangle(window.getMaxWidth(), 20);
        grayRect.setStroke(Color.BLACK);
        grayRect.setFill(Color.GRAY);

        Score score = new Score(0);
        Label totalscore = new Label();
        totalscore.setText("" + score.getCurrentValue());

        HBox buttonsbois = new HBox(3);
        Button reset = new Button("RESET");
        Button play = new Button("PLAY");

        play.setStyle("-fx-background-color: yellow;" + "-fx-text-fill: black;" + "-fx-border-color: black;");
        reset.setStyle("-fx-background-color: gray;" + "-fx-text-fill: black;" + "-fx-border-color: black;");

        for (int i = 0; i < display.getBoardRows(); i++) {
            for (int j = 0; j < display.getBoardColumns(); j++) {
                gameTile.add(boardTile[i][j], j, i);
            }
        }

        buttonsbois.getChildren().addAll(reset, totalscore, play);

        rootPane.add(gameTile, 0, 0);
        rootPane.add(grayRect, 0, 1);
        buttonsbois.setAlignment(Pos.CENTER);
        rootPane.add(buttonsbois, 0, 2);



        gameScene = new Scene(rootPane);
        window.setScene(gameScene);
        window.setFullScreen(false);
        window.show();
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
}
