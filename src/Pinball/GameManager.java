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
    private Display display = new Display(500, 800, 8, 5);

    private Tile[][] board = new Tile[display.getBoardRows()][display.getBoardColumns()];
    private Button[][] boardTile = new Button[display.getBoardRows()][display.getBoardColumns()];

    public static void main(String[] args) {
        launch(args);
    }


    public void start(Stage primaryStage) {
        window = primaryStage;
        window.setTitle("Pinball");
        gameController();
    }


    private void gameController() {
        Scene gameScene;
        BorderPane rootPane = new BorderPane();
        GridPane gameTile = new GridPane();
        timer = new Timer();
        int rowrow = display.getBoardRows();
        System.out.println(rowrow);
        setBoard(display.getBoardRows(), display.getBoardColumns());
        fillBoard(display.getBoardRows(), display.getBoardColumns());
        boardTile = buildBoard(display.getBoardRows(), display.getBoardColumns());


        Label totalscore = new Label();
        totalscore.setText("thanks wesley");

        HBox buttonsbois = new HBox(3);
        Button reset = new Button("RESET");
        Button play = new Button("PLAY");

        for (int i = 0; i < display.getBoardRows(); i++) {
            for (int j = 0; j < display.getBoardColumns(); j++) {
                gameTile.add(boardTile[i][j], j, i);
            }
        }

        buttonsbois.getChildren().add(reset);
        buttonsbois.getChildren().add(totalscore);
        buttonsbois.getChildren().add(play);
        buttonsbois.setAlignment(Pos.BOTTOM_CENTER);
        rootPane.setBottom(buttonsbois);

        gameTile.setAlignment(Pos.CENTER);
        rootPane.setCenter(gameTile);
        gameScene = new Scene(rootPane);
        window.setScene(gameScene);
        window.setFullScreen(false);
        window.show();

    }

    private void fillBoard(int rows, int cols) {
        for (int i = 0; i < 3; i++) {
            int randrows = (int)(Math.random()*(rows-1));
            int randcols = (int)(Math.random()*(cols-1));
            board[randrows][randcols].setState(true);
        }
    }

    private void setBoard(int rows, int cols) {
        System.out.println(rows);
        for (int i = 0; i < cols; i++) {
            for (int j = 0; j < rows; j++) {
                Tile tile = new Tile(false);
                tile.setState(false);
                board[j][i] = tile;
            }
        }
    }

    private Button[][] buildBoard(int rows, int cols) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                Button btn = new Button();
                btn.setPrefSize(50,50);
                if(board[i][j].getState()) {
                    btn.setStyle("-fx-background-color: yellow;" + "-fx-border-color: black;");
                } else {
                    btn.setStyle("-fx-background-color: blue;" + "-fx-border-color: black;");
                }
                boardTile[i][j] = btn;
            }
        }
        return boardTile;
    }
}
