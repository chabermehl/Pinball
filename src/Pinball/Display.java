/*
 * @author Charles Habermehl
 * CS 351
 * 8/31/18
 * Pinball
 */
package Pinball;

public class Display {

    private int boardWidth;
    private int boardHeight;
    private int boardRows;
    private int boardColumns;

    /**
     * constructor htat hold board size
     *
     * @param boardWidth   pixel width of board
     * @param boardHeight  pixel height of board
     * @param boardRows    number of rows in board
     * @param boardColumns number of columns in board
     */
    public Display(int boardWidth, int boardHeight, int boardRows, int boardColumns) {
        this.boardWidth = boardWidth;
        this.boardHeight = boardHeight;
        this.boardRows = boardRows;
        this.boardColumns = boardColumns;
    }

    /**
     * gets board width
     *
     * @return integer value
     */
    public int getBoardWidth() {
        return this.boardWidth;
    }

    /**
     * gets board height
     *
     * @return integer value
     */
    public int getBoardHeight() {
        return this.boardHeight;
    }

    /**
     * gets number of rows the board has
     *
     * @return integer value
     */
    public int getBoardRows() {
        return this.boardRows;
    }

    /**
     * returns number of columns the board has
     *
     * @return integer value
     */
    public int getBoardColumns() {
        return this.boardColumns;
    }
}
