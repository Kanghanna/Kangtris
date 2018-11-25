import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class option extends JFrame {

	JRadioButton bg1, bg2;

	public option() {
		
    	JPanel option = new JPanel();
    	
    	JRadioButton bg1 = new JRadioButton("기본 배경");
		bg1.setSelected(false);
		JRadioButton bg2 = new JRadioButton("사용자 지정 배경:");
		bg2.setSelected(false);
		
		ButtonGroup group = new ButtonGroup();
	     group.add(bg1);
	     group.add(bg2);
		
		option.add(bg1);
		option.add(bg2);
		
        final TextField userText = new TextField(20);
        option.add(userText);
        
        String Route = userText.getText();

    	JButton background = new JButton("확인");
    	option.add(background);
    	background.setBounds(180, 120, 120, 30);
    	

    	background.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				/*
					if(bg2.isChecked() == true) {
						ImageIcon icon = new ImageIcon(Route);
						JPanel background = new JPanel() {
							public void paintComponent(Graphics g){
								g.drawImage(icon,getWidth(),getHeight(),this);
								setOpaque(false);
								super.paintComponent(g);
							}
						};
					}
					*/
					Tetris game = new Tetris();
				game.setLocationRelativeTo(null);
		        game.setVisible(true);
			}
			
    	});	
						
        this.add(option);
        this.setSize(300,120);
        this.setVisible(true);
        setResizable(false);        
	}

	public static void main(String[] args) {
		new option();
	}
}