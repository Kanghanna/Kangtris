import javax.swing.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import java.awt.*;

public class game extends JFrame {
	JFrame g = new JFrame();
	game() {
		setTitle("��Ʈ���� ȭ��");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		c.setLayout(new FlowLayout());
		ImageIcon img = new ImageIcon("C:\\Users\\namhee\\Desktop\\game.jpg"); 
		JLabel imageLabel = new JLabel(img); 
 		c.add(imageLabel);
		setSize(360,550);
		setVisible(true);
	}
	public static void main(String [] args) {
		new game();
	}
}