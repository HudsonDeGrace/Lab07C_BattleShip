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
        BSTile[][] board = bsBoard.getBoard();

        BSPnl = new JPanel();
        BSPnl.setLayout(new GridLayout(10, 10));
        for(BSTile[] row : board){
            for(BSTile tile : row){
                BSPnl.add(tile);
            }
        }

        MainPnl.add(BSPnl, BorderLayout.CENTER);
    }

    private void createBtnPnl() {
        BtnPnl = new JPanel();
        BtnPnl.setLayout(new GridLayout(1,2));
        ResetBtn = new JButton("Reset");
        QuitBtn = new JButton("Quit");
        BtnPnl.add(ResetBtn);
        BtnPnl.add(QuitBtn);
        QuitBtn.addActionListener(_ -> System.exit(0));
        ResetBtn.addActionListener(_ -> bsBoard.resetBoard());
        SouthPnl.add(BtnPnl, BorderLayout.SOUTH);
    }

//    class buttonListener implements ActionListener {
//        @Override
//        public void actionPerformed(ActionEvent e) {
//            if(e.getSource() == ResetBtn){}
//        }
//    }
}
