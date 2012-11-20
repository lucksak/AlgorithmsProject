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
import java.util.Stack;
import java.lang.Math;


public class GrahamSchmidt extends JFrame implements MouseListener{

	int x=0, y=0;
	String events ="";
	//public Vector<Integer> xclicks = new Vector<Integer>();
	//public Vector<Integer> yclicks = new Vector<Integer>();
	
	public Vector<Point> clicks = new Vector<Point>();//A vector of Point
	
	public JButton calculate;
	public boolean flag = false;
   
	
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
   
   if(clicks.size() > 0 && flag == false){
      for(int i = 0; i < clicks.size(); i++){
            g.setColor(Color.RED);
  			   g.fillRect((int)(clicks.get(i).getX())-7,(int)(clicks.get(i).getY())-30,7,7);    
         }
        
     }
     if(flag == true){
     	//drawlines
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
  			frame.flag = true;
  			double minY = frame.clicks.get(0).getY();
  			int loc = 0;
  			Point minPoint = frame.clicks.get(0);
  			System.out.println("CALCULATE PUSH");
  				//If the calculate button is pushed
  				for(int i =0; i < frame.clicks.size(); i++){
  					 System.out.println(" Points = " + frame.clicks.get(i).getX() + " , " + frame.clicks.get(i).getY());
  					 if(frame.clicks.get(i).getY() < minY){
  					 	loc = i;
  					 	minY = frame.clicks.get(i).getY();
  					 	minPoint = frame.clicks.get(i);
  					 }
  				}
  				/*Switches minimum Point with point 0*/
  				swapPoints(0, loc);
  				System.out.println(" Switched zero with " + loc);
  				
  				for(int i =0; i < frame.clicks.size(); i++){
  					 System.out.println(" Points = " + frame.clicks.get(i).getX() + " , " + frame.clicks.get(i).getY());
  				 }
  				System.out.println("minY = " + minY);
					Point[] cross = new Point[frame.clicks.size()];
					cross[0] = frame.clicks.get(0);
					cross[1] = frame.clicks.get(1);
					cross[2] = frame.clicks.get(2);
					double a1 = angleFind(cross[0], cross[1]);
					//double theta = Math.asin(crossProd(cross[0], cross[1])/((Math.sqrt(
					double a2 = polarAngle(cross[0], cross[1]);
					System.out.println(" Angle of point 0 and 1 = " + a1);
					System.out.println(" Polar Angle of point 0 and 1 = " + Math.toDegrees(a2));
					
  		}
  }
  /*Returns the cross product of two points in R2*/
  public double angleFind(Point A, Point B){
  	double prod = (A.getX()*B.getY()) - (A.getY()*B.getX());
		double theta = Math.asin((prod)/(Math.sqrt((A.getX()*A.getX())+
			(A.getY()*A.getY()))*Math.sqrt((B.getX()*B.getX())+(B.getY()*B.getY()))));
  	return theta;
  }
  /*Polar angle as defined in text on page 1020*/
  public double polarAngle(Point A, Point B){
  	Point polVect = new Point();
  	double theta = 0.0;
  	polVect.setLocation(A.getX()-B.getX(),A.getY()-B.getY());
  	//+x +y
  	if(polVect.getX() >= 0 && polVect.getY() >= 0){
  		theta = Math.atan(polVect.getY()/polVect.getX());
  	}
  	//-x +y
  	if(polVect.getX() < 0 && polVect.getY() >= 0){
  		theta = Math.atan((-1*polVect.getX())/polVect.getY()) + (Math.PI/2);
  	}
  	//-x -y
  	if(polVect.getX() < 0 && polVect.getY() < 0){
  		theta = Math.atan(((-1*polVect.getY())/(-1*polVect.getX()))) + Math.PI;
  	}
  	//+x, -y
  	if(polVect.getX() >= 0 && polVect.getY() < 0){
  		theta = Math.atan(polVect.getX()/(-1*polVect.getY())) + ((3*Math.PI)/2);
  	}
  	
  	System.out.println(" polVect = " + polVect.getX() + " , " + polVect.getY());
  	
  	return theta;
  }
	public void swapPoints(int A, int B){
		Point tempB = frame.clicks.get(B);
		frame.clicks.setElementAt(frame.clicks.get(A), B);
		frame.clicks.setElementAt(tempB, A);
	}
 }
