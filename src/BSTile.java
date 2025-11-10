import javax.swing.*;

public class BSTile extends JButton {
    private int row;
    private int col;
    private boolean isHit;
    private boolean isEmpty;

    public BSTile(int row, int col,  boolean isHit, boolean isEmpty) {
        super();
        this.row = row;
        this.col = col;
        this.isHit = isHit;
        this.isEmpty = isEmpty;
    }

    public int getRow() {
        return row;
    }
    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }
    public void setCol(int col) {
        this.col = col;
    }

    public boolean getIsHit() {
        return isHit;
    }
    public void setIsHit(boolean isHit) {
        this.isHit = isHit;
    }

    public boolean getIsEmpty() {
        return isEmpty;
    }
    public void setIsEmpty(boolean isEmpty) {
        this.isEmpty = isEmpty;
    }
}
