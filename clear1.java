
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

class clear extends JFrame{
    ImageIcon i = new ImageIcon("C:\\Users\\namhee\\Desktop\\clear1.jpg");
    Image im=i.getImage();
    clear(){
        this.setTitle("LEVEL1 CLEAR");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        MyPanel panel = new MyPanel();
        panel.setLayout(null);
        JButton button1 = new JButton(new ImageIcon("C:\\Users\\namhee\\Desktop\\next_button.png"));
        button1.setBorderPainted(false);
        button1.setContentAreaFilled(false);
        button1.setFocusPainted(false);
        panel.add(button1);
        button1.setBounds(100, 117, 120, 30);

        this.add(panel);
        this.setSize(320,200);
        this.setVisible(true);
    }
    class MyPanel extends JPanel{
        public void paintComponent(Graphics g){
            super.paintComponent(g);
            g.drawImage(im,0,0,getWidth(),getHeight(),this);
        }
    }
}
public class clear1 {
    public static void main(String[] args) {
         new clear();
    } 
}
