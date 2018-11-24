import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EarlyActivity extends JFrame {

	public EarlyActivity() {
		
		ImageIcon bg = new ImageIcon("C:\\java_study2\\Kangtris\\src\\images\\kangtris_first.jpg");
	    Image bg_img = bg.getImage();
	    
	    class MyPanel extends JPanel{
	        public void paintComponent(Graphics g){
	            super.paintComponent(g);
	            g.drawImage(bg_img,0,0,getWidth(),getHeight(),this);
	        }
	    }
	    
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        MyPanel panel = new MyPanel();
        panel.setLayout(null);
        JButton button1 = new JButton(new ImageIcon("C:\\java_study2\\Kangtris\\src\\images\\start_button.png"));
        button1.setBorderPainted(false);
        button1.setContentAreaFilled(false);
        button1.setFocusPainted(false);
        panel.add(button1);
        button1.setBounds(50, 400, 120, 30);

        JButton button2 = new JButton(new ImageIcon("C:\\java_study2\\Kangtris\\src\\images\\ways_button.png"));
        button2.setBorderPainted(false);
        button2.setContentAreaFilled(false);
        button2.setFocusPainted(false);
        panel.add(button2);
        button2.setBounds(180, 400, 120, 30);
        
        button1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//new game();
				Tetris game = new Tetris();
		        game.setLocationRelativeTo(null);
		        game.setVisible(true);
			}   	
        });
        
        button2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new ways();
			}   	
        });
        
        this.add(panel);
        this.setSize(360,550);
        this.setVisible(true);
        setResizable(false);          
        
	}
    public static void main(String[] args) {
         new EarlyActivity();
    }   
}

class ways extends JFrame{
    ways(){
    	setTitle("방법 소개");
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		c.setLayout(new FlowLayout());
		ImageIcon img = new ImageIcon("C:\\java_study2\\Kangtris\\src\\images\\ways.jpg"); 
		JLabel imageLabel = new JLabel(img); 
 		c.add(imageLabel);
		setSize(360,550);
		setVisible(true);  

		/*
		JPanel game = new JPanel();
    	setContentPane(game);
    	setSize(360,550);
    	setResizable(false);
    	setVisible(true);
    	*/
    }
}
