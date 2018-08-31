package Pinball;

public class Display {

    private int boardWidth;
    private int boardHeight;
    private int boardRows;
    private int boardColumns;

    public Display (int boardWidth, int boardHeight, int boardRows, int boardColumns) {
        this.boardWidth = boardWidth;
        this.boardHeight = boardHeight;
        this.boardRows = boardRows;
        this.boardColumns = boardColumns;
    }

    public int getBoardWidth() {
        return this.boardWidth;
    }

    public int getBoardHeight() {
        return this.boardHeight;
    }

    public int getBoardRows() {
        return this.boardRows;
    }

    public int getBoardColumns() {
        return this.boardColumns;
    }
}
