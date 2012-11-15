/**
  *Version 0.1
  *Authors Matthew Clark, Ryan Crawford, Mehedi Wahid

  *This is for the Algorithms course!
*/
import javax.swing.*;
import java.awt.*;

public class GrahamSchmidt extends JFrame {

 public GrahamSchmidt () {
   setContentPane(new Panel());
   setLayout(new GridLayout(100,200));
   setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   setSize(700,500);   
   setVisible(true);
 }
 
 class Panel extends JPanel {
  @Override
  public void paintComponent(Graphics g) {
   g.drawRect(0,0,500,500);
  }
 }
 public void paint(Graphics g){
   int Width = getSize().width;
   int Height = getSize().height;
   int temp = 0;

   while(temp < 500){
      
      g.drawLine(temp+5,0,temp+5,500);
      g.drawLine(0,temp-7,500,temp-7);
      temp += 15;
   }
   
}

 public static void main (String args[]) {
  JFrame frame = new GrahamSchmidt();
 }
}
