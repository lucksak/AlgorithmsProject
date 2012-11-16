/**
  *Version 0.1
  *Authors Matthew Clark, Ryan Crawford, Mehedi Wahid
*/
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;



public class GrahamSchmidt extends JFrame{

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
  MouseHandle clicks = new MouseHandle("Words and stuff");
 }
}

/*
 This is some code used to detect mouse clicks, currently detects the clicks but not in  
 the same window, November 15, Matthew Clark
 
 Mouse detecting
*/
class MouseHandle extends JFrame implements MouseListener{
	int x=0, y=0;
	String events ="";
	
	MouseHandle(String title){
		super(title);
		//addWindowListener(new GrahamSchmidt());
		addMouseListener(this);
		setSize(700,500);  
		setVisible(true);
	}
	
	public void mouseClicked(MouseEvent evt){
		System.out.println(" MOUSE CLICKED" + " x= " + evt.getX() + " y= " + evt.getY());
	}

	public void mousePressed(MouseEvent evt){};
	public void mouseReleased(MouseEvent evt){};
	public void mouseEntered(MouseEvent evt){};
	public void mouseExited(MouseEvent evt){};
}
