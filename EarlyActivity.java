import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

class Early extends JFrame{
    ImageIcon bg = new ImageIcon("C:\\java_study2\\Kangtris\\src\\images\\kangtris_first.png");
    Image bg_img = bg.getImage();
    Early(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
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
        
        this.add(panel);
        this.setSize(340,500);
        this.setVisible(true);
        setResizable(false);
    }
    class MyPanel extends JPanel{
        public void paintComponent(Graphics g){
            super.paintComponent(g);
            g.drawImage(bg_img,0,0,getWidth(),getHeight(),this);
        }
    }
}
public class EarlyActivity {
    public static void main(String[] args) {
         new Early();
    } 
}
