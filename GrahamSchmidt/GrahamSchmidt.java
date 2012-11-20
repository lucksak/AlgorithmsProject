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
import java.util.Vector;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Collections;


public class GrahamSchmidt extends JFrame implements MouseListener{

	int x=0, y=0;
	String events ="";
	//public Vector<Integer> xclicks = new Vector<Integer>();
	//public Vector<Integer> yclicks = new Vector<Integer>();
	
	public Vector<Point> clicks = new Vector<Point>();//A vector of Point
	
	public JButton calculate;
   
	
 public GrahamSchmidt () {
         
   setContentPane(new Panel());
   addMouseListener(this);
   setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   setSize(700,500);
   FlowLayout layout = new FlowLayout(FlowLayout.CENTER,200,420);
   setLayout(layout);
  
   calculate = new JButton("Calculate");
   
   add(calculate);
   setVisible(true);
 }
 
 class Panel extends JPanel {
  @Override
  public void paintComponent(Graphics g) {
  	g.setColor(Color.WHITE);
  	g.fillRect(0,0,700,400);
   
   if(clicks.size() > 0){
      for(int i = 0; i < clicks.size(); i++){
            g.setColor(Color.RED);
  			   g.fillRect((int)(clicks.get(i).getX())-7,(int)(clicks.get(i).getY())-30,7,7);    
         }
        
     }
  	}
  
  }

/*
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
*/

	/* When clicking on the white rectangle a red dot will appear */
	public void mouseClicked(MouseEvent evt){
		Point temp = new Point();
		System.out.println(" MOUSE CLICKED" + " x= " + evt.getX() + " y= " + evt.getY());
      if(evt.getX() > 0 && evt.getX() <700 && evt.getY() > 0 && evt.getY() <425){
		      //xclicks.add(evt.getX());
		      //yclicks.add(evt.getY());
		      
		      temp.setLocation(evt.getX(), evt.getY());
		      clicks.add(temp);
		      System.out.println(" Points = " + clicks.size());
		      System.out.println(" Points x = " + clicks.get(clicks.size()-1).getX());
		      System.out.println(" Points y = " + clicks.get(clicks.size()-1).getY());
		      
		      //System.out.println(" X Vector size = " + xclicks.size());
					//System.out.println(" Y Vector size = " + yclicks.size());
            repaint();
     }
	}

	public void mousePressed(MouseEvent evt){};
	public void mouseReleased(MouseEvent evt){};
	public void mouseEntered(MouseEvent evt){};
	public void mouseExited(MouseEvent evt){};


 public static void main (String args[]) {
  GrahamSchmidt frame = new GrahamSchmidt();
   
  Actions action = new Actions(frame);
  

 }
}
class Actions implements ActionListener {
  	GrahamSchmidt frame;
  	public Actions(GrahamSchmidt frame) {
  		this.frame = frame;  		
		this.frame.calculate.addActionListener(this);
  	}
  	@Override
		public void actionPerformed(ActionEvent e) {
			
  		if(e.getSource() == frame.calculate) {
  			double minY = frame.clicks.get(0).getY();
  			Point minPoint = frame.clicks.get(0);
  			System.out.println("CALCULATE PUSH");
  				//If the calculate button is pushed
  				for(int i =0; i < frame.clicks.size(); i++){
  					 System.out.println(" Points = " + frame.clicks.get(i).getX() + " , " + frame.clicks.get(i).getY());
  					 if(frame.clicks.get(i).getY() < minY){
  					 	minY = frame.clicks.get(i).getY();
  					 	minPoint = frame.clicks.get(i);
  					 }
  				}	
  				System.out.println("minY = " + minY);
  		}
  }
 }
