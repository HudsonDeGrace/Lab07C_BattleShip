import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BSFrame extends JFrame {
    JPanel MainPnl, TitlePnl, BSPnl, StatsPnl, BtnPnl, SouthPnl;
    JLabel TitleLbl;
    JTextField MissTF, StrikeTF, TotalMissTF, TotalHitTF;
    JButton ResetBtn, QuitBtn;

    BSBoard bsBoard = BSBoard.getInstance();
    BSTile[][] board = bsBoard.getBoard();
    int miss = 0, hits = 0, strikes = 0, totalMiss = 0;

    Ship ship1 = new Ship(5, 5);
    Ship ship2 = new Ship(4, 4);
    Ship ship3 = new Ship(3, 3);
    Ship ship4 = new Ship(3, 3);
    Ship ship5 = new Ship(2, 2);

    int shipsLeft = 5;


    public BSFrame() {
        super("Battleship");

        MainPnl = new JPanel();
        MainPnl.setLayout(new BorderLayout());
        add(MainPnl);
        createSouthPnl();
        createBtnPnl();
        createBSPnl();
        createTitlePnl();
        createStatsPnl();


        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(750, 750);
        setVisible(true);
    }

    private void createSouthPnl() {
        SouthPnl = new JPanel();
        SouthPnl.setLayout(new GridLayout(1, 2));
        MainPnl.add(SouthPnl, BorderLayout.SOUTH);
    }

    private void createStatsPnl() {
        StatsPnl = new JPanel();

        StatsPnl.setLayout(new GridLayout(2, 2));
        MissTF = new JTextField();
        StrikeTF = new JTextField();
        TotalMissTF = new JTextField();
        TotalHitTF = new JTextField();
        MissTF.setBorder(BorderFactory.createTitledBorder("Misses"));
        StrikeTF.setBorder(BorderFactory.createTitledBorder("Strikes"));
        TotalMissTF.setBorder(BorderFactory.createTitledBorder("Total Misses"));
        TotalHitTF.setBorder(BorderFactory.createTitledBorder("Total Hits"));
        MissTF.setEditable(false);
        StrikeTF.setEditable(false);
        TotalMissTF.setEditable(false);
        TotalHitTF.setEditable(false);
        StatsPnl.add(MissTF);
        StatsPnl.add(StrikeTF);
        StatsPnl.add(TotalMissTF);
        StatsPnl.add(TotalHitTF);

        SouthPnl.add(StatsPnl, BorderLayout.CENTER);
    }

    private void createTitlePnl() {
        TitlePnl = new JPanel();

        TitleLbl = new JLabel("Single Player Battleship");
        TitleLbl.setFont(new Font("Arial", Font.BOLD, 24));
        TitleLbl.setHorizontalAlignment(JLabel.CENTER);
        TitleLbl.setVerticalAlignment(JLabel.CENTER);

        TitlePnl.add(TitleLbl);
        MainPnl.add(TitlePnl, BorderLayout.NORTH);
    }

    private void createBSPnl() {
        BSPnl = new JPanel();
        BSPnl.setLayout(new GridLayout(10, 10));
        for(BSTile[] row : board){
            for(BSTile tile : row){
                BSPnl.add(tile);
                tile.addActionListener(new buttonListener());
            }
        }
        fillBoard();

        MainPnl.add(BSPnl, BorderLayout.CENTER);
    }

    private void createBtnPnl() {
        BtnPnl = new JPanel();
        BtnPnl.setLayout(new GridLayout(1,2));
        ResetBtn = new JButton("Reset");
        QuitBtn = new JButton("Quit");
        BtnPnl.add(ResetBtn);
        BtnPnl.add(QuitBtn);
        QuitBtn.addActionListener(_ -> {
            if(JOptionPane.showConfirmDialog(MainPnl,"Are you sure you want to quit?","Quit", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) ==  JOptionPane.YES_OPTION){
                System.exit(0);
            }
        });
        ResetBtn.addActionListener(_ -> {
            if(JOptionPane.showConfirmDialog(MainPnl,"Are you sure you want to reset?","Reset", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) ==  JOptionPane.YES_OPTION) {
                resetGame();
            }
        });
        SouthPnl.add(BtnPnl, BorderLayout.SOUTH);
    }

    private void resetGame() {
        bsBoard.resetBoard();
        fillBoard();
        shipsLeft = 5;
        miss = 0;
        hits = 0;
        totalMiss = 0;
        strikes = 0;
        MissTF.setText("");
        StrikeTF.setText("");
        TotalMissTF.setText("");
        TotalHitTF.setText("");
        for(BSTile[] row : board){
            for(BSTile tile : row){
                if(tile.getIsHit()){
                    tile.addActionListener(new buttonListener());
                    tile.setIsHit(false);
                }
            }
        }
    }

    private void fillBoard(){
        ship1.placeShip();
        ship2.placeShip();
        ship3.placeShip();
        ship4.placeShip();
        ship5.placeShip();
    }

    boolean onTile(int row, int col, Ship ship){
        if((row >= ship.getStartRow() && row <= ship.getEndRow()) && (col >= ship.getStartCol() && col <= ship.getEndCol())){
            ship.setHealthLeft(ship.getHealthLeft() - 1);
            System.out.println(ship.getHealthLeft());
            return true;
        }
        return false;
    }

    Ship getShip(int row, int col){
        Ship ret = null;
        if(onTile(row, col, ship1)){
            ret = ship1;
        }else if(onTile(row, col, ship2)){
            ret = ship2;
        }else if(onTile(row, col, ship3)){
            ret = ship3;
        }else if(onTile(row, col, ship4)){
            ret = ship4;
        }else if(onTile(row, col, ship5)){
            ret = ship5;
        }
        return ret;
    }

    class buttonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            for(BSTile[] row : bsBoard.getBoard()){
                for(BSTile tile : row){
                    if(tile == e.getSource()){
                        if(tile.getIsEmpty()){
                            tile.setText("M");
                            tile.setForeground(Color.YELLOW);
                            tile.setIsHit(true);
                            tile.removeActionListener(this);
                            miss++;
                            totalMiss++;
                        }else{
                            tile.setText("X");
                            tile.setForeground(Color.RED);
                            tile.setIsHit(true);
                            tile.removeActionListener(this);
                            miss = 0;
                            hits++;

                            if(getShip(tile.getRow(), tile.getCol()).getHealthLeft() == 0){
                                shipsLeft--;
                                System.out.println("Ships left " + shipsLeft);
                                if(shipsLeft == 0){
                                    if(JOptionPane.showConfirmDialog(MainPnl,"You win! Do you want to play again?", "You Win!",  JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) ==  JOptionPane.YES_OPTION){
                                        shipsLeft = 5;
                                        resetGame();
                                    }
                                }else{
                                    JOptionPane.showMessageDialog(MainPnl, "You sunk a ship!", "Ship Sunk", JOptionPane.INFORMATION_MESSAGE);
                                }
                            }
                        }
                    }
                }
            }
            if(miss == 5){
                strikes++;
                miss = 0;
            }
            if(strikes == 3){
                if(JOptionPane.showConfirmDialog(MainPnl, "You gained 3 strikes and lost! Would you like to try again?", "You Lose!", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
                    resetGame();
                }else{
                    System.exit(0);
                }
            }
            MissTF.setText(String.valueOf(miss));
            TotalMissTF.setText(String.valueOf(totalMiss));
            StrikeTF.setText(String.valueOf(strikes));
            TotalHitTF.setText(String.valueOf(hits));
        }
    }
}
