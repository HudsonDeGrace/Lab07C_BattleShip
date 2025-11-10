import java.util.Random;

public class Ship {
    private int size;
    int healthLeft = size;

    public Ship(int size) {
        this.size = size;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getHealthLeft() {
        return healthLeft;
    }
    public void setHealthLeft(int healthLeft) {
        this.healthLeft = healthLeft;
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
