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
/*	public Vector<Integer> xclicks = new Vector<Integer>();
	public Vector<Integer> yclicks = new Vector<Integer>();*/
	
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
     	g.setColor(Color.BLUE);
     for(int y = 0; y < hull.size()-1; y++){
    	g.drawLine((int)(hull.get(y).getX())-3,(int)(hull.get(y).getY())-26,(int)(hull.get(y+1).getX()-3),(int)(hull.get(y+1).getY()-26));
     	}
     	g.drawLine((int)(hull.get(hull.size()-1).getX()-3),(int)(hull.get(hull.size()-1).getY()-26),(int)(hull.get(0).getX()-3),(int)(hull.get(0).getY()-26));
     }
  	}
  
  }

	/* When clicking on the white rectangle a red dot will appear */
	public void mouseClicked(MouseEvent evt){};

	public void mousePressed(MouseEvent evt){
    if(flag == false){
		Point temp = new Point();
  	  if(evt.getX() > 0 && evt.getX() <700 && evt.getY() > 0 && evt.getY() <425){ 
  		    temp.setLocation(evt.getX(), evt.getY());
  		    clicks.add(temp);
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
				frame.hull = giftWrap(frame.clicks);
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

  public Point minX(Vector<Point> S){
    double minX = S.get(0).getY();
    int loc = 0;
    Point minPoint = S.get(0);
    //If the calculate button is pushed
    for(int i =0; i < S.size(); i++){
        if(S.get(i).getX() < minX){
          loc = i;
          minX = S.get(i).getX();
          minPoint = S.get(i);
        }
    }
    /*Switches minimum Point with point 0*/
    swapPoints(0, loc);
    return minPoint;    
  }
  public Vector<Point> giftWrap(Vector<Point> S){
    Vector<Point> wrap = new Vector<Point>();
    Point minimum = minX(S);
    Point pointOnHull = minimum;
    Point endPoint = new Point();
    int i = 0;
    int small;

    do{
      wrap.add(pointOnHull);
      endPoint = S.get(0);
      for(int j = 1; j < S.size(); j++){
        if(endPoint == pointOnHull  || ccw(pointOnHull, endPoint, S.get(j)) == -1){
          endPoint = S.get(j);
        }
      }
      pointOnHull = endPoint;
      i++;
    }while(endPoint != minimum);
    return wrap;
  }
		/*
	# Three points are a counter-clockwise turn if ccw > 0, clockwise if
# ccw < 0, and collinear if ccw = 0 because ccw is a determinant that
# gives the signed area of the triangle formed by p1, p2 and p3.
function ccw(p1, p2, p3):
    return (p2.x - p1.x)*(p3.y - p1.y) - (p2.y - p1.y)*(p3.x - p1.x)*/
    public double ccw(Point p1, Point p2, Point p3){
        double orien = ((p2.getX() - p1.getX())*(p3.getY() - p1.getY()) - (p2.getY() - p1.getY())*(p3.getX() - p1.getX()));

        if(orien > 0){
          return -1;
        }
        if(orien < 0){
          return 1;
        }
    	return 0;
    }
 }
