
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class Tetris extends JFrame {

    JLabel statusbar;
    Board board;

    public Tetris() {
        statusbar = new JLabel(" 0");
        add(statusbar, BorderLayout.SOUTH);
        Board board = new Board(this);
        add(board);
        board.start();
        	
        board.setBackground(Color.BLACK);

        setSize(360, 550);
        setTitle("Tetris");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
   }

   public JLabel getStatusBar() {
       return statusbar;
   }

    public static void main(String[] args) {
        Tetris game = new Tetris();
        game.setLocationRelativeTo(null);
        game.setVisible(true);

    } 
}
