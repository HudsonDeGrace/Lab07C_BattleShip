import java.util.ArrayList;
import java.util.Random;

public class Ship {
    private int size;

    public Ship(int size) {
        this.size = size;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    BSBoard bsBoard = BSBoard.getInstance();
    BSTile[][] board = bsBoard.getBoard();

    public void placeShip(){
        boolean placed = false;
        Random gen = new Random();

        // Determined if ship is placed vertically or horizontally
        // If true -> ship is placed vertically
        if(gen.nextBoolean()){
            do {
                int startRow = gen.nextInt(0, 11 - size);
                int startCol = gen.nextInt(10);
                if(checkEligibility(startRow, startCol, true)) {
                    placed = true;
                    for(int i = 0; i < size; i++){
                        board[startRow + i][startCol].setIsEmpty(false);
                        board[startRow + i][startCol].setText("Ship");
                    }
                }
            }while(!placed);
        }else{
            do {
                int startRow = gen.nextInt(10);
                int startCol = gen.nextInt(0, 11 - size);
                if(checkEligibility(startRow, startCol, false)) {
                    placed = true;
                    for(int i = 0; i < size; i++){
                        board[startRow][startCol + i].setIsEmpty(false);
                        board[startRow][startCol + i].setText("Ship");
                    }
                }
            }while(!placed);
        }
    }

    public boolean checkEligibility(int startRow, int startCol, boolean isVertical){
        boolean eligible = false;
        int[] rows = new int[size];
        int[] cols = new int[size];
        int[][] position =  new int[size][2];

        for(int i = 0; i < size; i++){
            if(isVertical){
                eligible = board[startRow + i][startCol].getIsEmpty();
                rows[i] =  startRow + i;
                cols[i] = startCol;
            }else{
                eligible = board[startRow][startCol + i].getIsEmpty();
                rows[i] =  startRow;
                cols[i] =  startCol + i;
            }
        }
        return eligible;
    }
}
