import java.awt.*;

public class BSBoard {
    BSTile[][] board = new BSTile[10][10];

    private static final BSBoard INSTANCE = new BSBoard();
    private BSBoard() {
        createBoard();
    }

    public BSTile[][] getBoard(){
        return board;
    }

    public static BSBoard getInstance() {
        return INSTANCE;
    }

    public void createBoard(){
        for(int row = 0; row < board.length; row++) {
            for(int col = 0; col < board[row].length; col++) {
                board[row][col] = new BSTile(row, col, false, true);
                board[row][col].setText("~");
                board[row][col].setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
                board[row][col].setForeground(Color.CYAN);
            }
        }
    }

    public void resetBoard(){
        for(int row = 0; row < board.length; row++) {
            for(int col = 0; col < board[row].length; col++) {
                INSTANCE.getBoard()[row][col].setText("~");
                INSTANCE.getBoard()[row][col].setForeground(Color.CYAN);
                INSTANCE.getBoard()[row][col].setIsEmpty(true);
            }
        }
    }
}
