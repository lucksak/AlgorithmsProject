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
import java.util.ArrayList;
import java.lang.Math;


public class GrahamSchmidt extends JFrame implements MouseListener{

	int x=0, y=0;
	String events ="";
	//public Vector<Integer> xclicks = new Vector<Integer>();
	//public Vector<Integer> yclicks = new Vector<Integer>();
	
	public Vector<Point> clicks = new Vector<Point>();//A vector of Point
	public Vector<Point> hull = new Vector<Point>();
	public Stack<Point> S = new Stack<Point>();
	
	public JButton calculate;
	public boolean flag = false;
   
	
 public GrahamSchmidt () {
         
   setContentPane(new Panel());
   addMouseListener(this);
   setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   setSize(700,500);
   setResizable(false);
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
  			   g.fillOval((int)(clicks.get(i).getX())-7,(int)(clicks.get(i).getY())-30,8,8);    
         }
        
     
     }
     if(flag == true){
/*
     	g.setColor(Color.BLUE);
     for(int i = 0; i < hull.size()-1; i++){
    	g.drawLine((int)(hull.get(i).getX())-3,(int)(hull.get(i).getY())-26,(int)(clicks.get(i+1).getX()-3),(int)(clicks.get(i+1).getY()-26));
     	}
     	g.drawLine((int)(hull.get(hull.size()-1).getX()-3),(int)(hull.get(hull.size()-1).getY()-26),(int)(clicks.get(0).getX()-3),(int)(clicks.get(0).getY()-26));
   */  
     g.setColor(Color.GREEN);
     Point First = S.pop();
     Point Second =  S.pop();
     	
     while(S.empty() == false){
     	g.drawLine((int)(First.getX())-3,(int)(First.getY())-26,(int)(Second.getX()-3),(int)(Second.getY()-26));
     	First = Second;
     	Second = S.pop();
     }
     g.drawLine((int)(First.getX())-3,(int)(First.getY())-26,(int)(Second.getX()-3),(int)(Second.getY()-26));
     
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
	public void mouseClicked(MouseEvent evt){};

	public void mousePressed(MouseEvent evt){
        if(flag == false){
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
}
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
					
					sort();
					System.out.println("Sorted");
					// Sorts by polar angle
					
					//This is where we fix the convex hull problem!
					
				
					System.out.println("---------------------------------------------------------");
					for(int i =0; i < frame.clicks.size(); i++){
  					 System.out.println(" Points = " + frame.clicks.get(i).getX() + " , " + frame.clicks.get(i).getY());
  					 }
  				System.out.println("---------------------------------------------------------");
					
				//	int N = frame.clicks.size();
								
					
					frame.S.push(frame.clicks.get(0));
					frame.S.push(frame.clicks.get(1));
					frame.S.push(frame.clicks.get(2));
					
					Point firstIsAlsoLast = frame.clicks.get(0);
					frame.clicks.add(firstIsAlsoLast);
					
					Point top;
					Point nextToTop;
					int m=3;
					
					for(int i =3; i < frame.clicks.size(); i++){
					
						 top = frame.S.pop();
						 nextToTop = frame.S.pop();
						 frame.S.push(nextToTop);
						 frame.S.push(top);
						 
  					 Point min = frame.clicks.get(i);
  					 double low = polarAngle(min, frame.clicks.get(i));
  					 
  					 for(int j =0; j < frame.clicks.size(); j++){
  					 		if(ccw(nextToTop, top, frame.clicks.get(i))>= 0) {
  					 			if(frame.S.size() > 3){
  					 				frame.S.pop();
  					 				}
  					 			if((polarAngle(min, frame.clicks.get(j)) > low)){
  					 				low = polarAngle(min, frame.clicks.get(j));
  					 				min = frame.clicks.get(j);
  					 				
  					 				
  					 			}else{
                        Point temp = frame.S.pop();
                        frame.S.pop();
                        frame.S.push(temp);
                     }
  					 		}
  					 		m++;
  					 		//swapPoints(m,j);
  					 }
  					 frame.S.push(frame.clicks.get(i));
						 System.out.println("Pushed = " + frame.clicks.get(i));
						 
  					 frame.hull.add(min);
  				 }
  				 System.out.println("Stack = " + frame.S.toString());
  				 
  				 for(int i =0; i < frame.hull.size(); i++){
  					 System.out.println("  Hull Points = " + frame.hull.get(i).getX() + " , " + frame.hull.get(i).getY());
  				 }
  				 frame.repaint();
  				 frame.calculate.setEnabled(false);
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
     // System.out.println("1");
  	}
  	//-x +y
  	if(polVect.getX() < 0 && polVect.getY() >= 0){
  		theta = Math.atan((-1*polVect.getX())/polVect.getY()) + (Math.PI/2);
   // System.out.println("2");
  	}
  	//-x -y
  	if(polVect.getX() < 0 && polVect.getY() < 0){
  		theta = Math.atan(((-1*polVect.getY())/(-1*polVect.getX()))) + Math.PI;
    //System.out.println("3");
  	}
  	//+x, -y
  	if(polVect.getX() >= 0 && polVect.getY() < 0){
  		theta = Math.atan(polVect.getX()/(-1*polVect.getY())) + ((3*Math.PI)/2);
 //System.out.println("4");
  	}  	
  	return theta;
  }
  /* Swaps points */
	public void swapPoints(int A, int B){
		Point tempB = frame.clicks.get(B);
		frame.clicks.setElementAt(frame.clicks.get(A), B);
		frame.clicks.setElementAt(tempB, A);
	}
	
	/*Super awesome bubble sort!  Sort by polarAngle*/
	public void sort(){
		int position, scan;
			for(position = (frame.clicks.size()); position > 0; position--)
			{
				for(scan = 0; scan < (position - 1); scan++)
				{
					if(polarAngle(frame.clicks.get(0), frame.clicks.get(scan)) < polarAngle(frame.clicks.get(0), frame.clicks.get(scan+1)))
						{
							swapPoints(scan, scan+1);
						}
				}
			}
			return;
	}
	
	
	/*
	# Three points are a counter-clockwise turn if ccw > 0, clockwise if
# ccw < 0, and collinear if ccw = 0 because ccw is a determinant that
# gives the signed area of the triangle formed by p1, p2 and p3.
function ccw(p1, p2, p3):
    return (p2.x - p1.x)*(p3.y - p1.y) - (p2.y - p1.y)*(p3.x - p1.x)*/
    public double ccw(Point p1, Point p2, Point p3){
    	return ((p2.getX() - p1.getX())*(p3.getY() - p1.getY()) - (p2.getY() - p1.getY())*(p3.getX() - p1.getX()));
    }
 }
