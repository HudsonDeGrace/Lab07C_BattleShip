import java.util.Random;

public class Ship {
    private int size;
    int healthLeft;
    private boolean isVertical;
    int startRow;
    int startCol;
    int endRow;
    int endCol;

    public Ship(int size, int healthLeft) {
        this.size = size;
        this.healthLeft = healthLeft;
    }

    public int getSize(){
        return size;
    }

    public int getHealthLeft() {
        return healthLeft;
    }
    public void setHealthLeft(int healthLeft) {
        this.healthLeft =  healthLeft;
    }

    public boolean isVertical() {
        return isVertical;
    }

    public int getStartRow() {
        return startRow;
    }

    public int getStartCol() {
        return startCol;
    }

    public int getEndRow() {
        return endRow;
    }

    public int getEndCol() {
        return endCol;
    }

    BSBoard bsBoard = BSBoard.getInstance();
    BSTile[][] board = bsBoard.getBoard();

    public void placeShip(){
        boolean placed = false;
        Random gen = new Random();
        healthLeft = size;

        // Determined if ship is placed vertically or horizontally
        // If true -> ship is placed vertically
        isVertical = gen.nextBoolean();
        if(isVertical){
            do {
                startRow = gen.nextInt(0, 11 - size);
                startCol = gen.nextInt(10);
                if(checkEligibility(startRow, startCol, isVertical)) {
                    placed = true;
                    for(int i = 0; i < size; i++){
                        board[startRow + i][startCol].setIsEmpty(false);
                    }
                    endRow = startRow + size;
                    endCol = startCol;
                }
            }while(!placed);
        }else{
            do {
                startRow = gen.nextInt(10);
                startCol = gen.nextInt(0, 11 - size);
                if(checkEligibility(startRow, startCol, false)) {
                    placed = true;
                    for(int i = 0; i < size; i++){
                        board[startRow][startCol + i].setIsEmpty(false);
                    }
                    endRow = startRow;
                    endCol = startCol + size;
                }
            }while(!placed);
        }
    }

    public boolean checkEligibility(int startRow, int startCol, boolean isVertical){
        boolean eligible = false;
        int count = 0;

        if(isVertical){
            for (int i = 0; i < size; i++) {
                if(board[startRow + i][startCol].getIsEmpty()) {
                    count++;
                    if(count == size){
                        eligible = true;
                        for(int j = 0; j < size; j++){
                            board[startRow + j][startCol].setIsEmpty(false);
                        }
                    }
                }
            }
        }else{
            for (int i = 0; i < size; i++) {
                if(board[startRow][startCol + i].getIsEmpty()) {
                    count++;
                    if(count == size){
                        eligible = true;
                        for(int j = 0; j < size; j++){
                            board[startRow][startCol + j].setIsEmpty(false);
                        }
                    }
                }
            }
        }

        return eligible;
    }
}
