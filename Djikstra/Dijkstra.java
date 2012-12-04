/**
 *Dijkstra CSCI 3070U
 *The main purpose of this project is to show an implementation of the Convex Hull problem
 *<ul>
 *<li> Dijkstra - This class is used to create the GUI of the project.
 *<ul>
  *<li> Dijkstra - This is a constructor for the Dijkstra.
  *<li> Panel -  draws the points coded into the source.
  *<li> mousePressed - This collects points from the user.
*</ul>
  *<li> setPoints - sets all points on the image
  *<li> pathBetweenPoints - finds the paths between the points
  *<li> shortestPath - finds shortest path between the points
  *<li> Vertex - Class to hold the vertex information
  *<li> Edge - Class to hold the Edge information.
 *</ul>
 *@author Matthew Clark, Ryan Crawford, Mehedi Wahid
 *@version 1.0
 */
import javax.swing.*;
import java.awt.*;
import javax.imageio.*;
import java.io.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.lang.Math;

public class Dijkstra extends JFrame implements MouseListener {
 Image background;
 public Point[] point = new Point[45];
 public boolean flagA = false;
 public boolean flagB = false;
 public boolean flagC = false;
 public Vertex ver;
 public Point closePointA = new Point();
 public Point closePointB = new Point();
 public List<Vertex> selectedPath = new ArrayList<Vertex>();

   
 public Dijkstra () {
   setPoints();
   setContentPane(new Panel());
   setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   setSize(800,500);
   setBackground();
   addMouseListener(this);
   setResizable(false);
   setVisible(true);
 }
 	public void mouseClicked(MouseEvent evt){};

	public void mousePressed(MouseEvent evt){

			
      Point temp = new Point();
      temp.setLocation(evt.getX(), evt.getY());


      if(flagA == false && flagB == false){
        flagA = true;
        double closest = 999999;
        for(int i = 0; i < 45; i++){
          if(Math.sqrt(((temp.getX() - point[i].getX())*(temp.getX() - point[i].getX())) + (temp.getY() - point[i].getY())*(temp.getY() - point[i].getY())) < closest){
            closest = Math.sqrt(((temp.getX() - point[i].getX())*(temp.getX() - point[i].getX())) + (temp.getY() - point[i].getY())*(temp.getY() - point[i].getY()));
            closePointA = point[i];
          }
        }
        repaint();
        return;
      }
      if(flagB == false && flagA == true){
        flagB = true;
        double closest = 999999;
          for(int i = 0; i < 45; i++){
            if(Math.sqrt(((temp.getX() - point[i].getX())*(temp.getX() - point[i].getX())) + (temp.getY() - point[i].getY())*(temp.getY() - point[i].getY())) < closest){
              closest = Math.sqrt(((temp.getX() - point[i].getX())*(temp.getX() - point[i].getX())) + (temp.getY() - point[i].getY())*(temp.getY() - point[i].getY()));
              closePointB = point[i];
            }
          }
        repaint();
        return;
      }
}
	public void mouseReleased(MouseEvent evt){};
	public void mouseEntered(MouseEvent evt){};
	public void mouseExited(MouseEvent evt){};

double Average(Point p1, Point p2){
  if(Math.abs(p1.getX() - p2.getX()) < 10){
    double average = Math.abs(p2.getY() - p1.getY());
    return average;
  }else{
    double average = Math.abs(p2.getX() - p1.getX());
    return average;
    }       
         
  } 
 class Panel extends JPanel {   

  @Override
  public void paintComponent(Graphics g) {
   
   g.drawImage(background,100,0,null);
//print out the innitial nodes
   for(int i = 0; i < 45; i++){
      g.fillOval((int)(point[i].getX())-10,(int)(point[i].getY())-30,20,20);
   }
   //print out the starting point node when clicked
   if(flagA == true){
    g.setColor(Color.RED);
    g.fillOval((int)(closePointA.getX())-10,(int)(closePointA.getY())-30,20,20);
   }
   //print out the finish point node when clicked
    if(flagB == true){
      g.setColor(Color.BLUE);
      g.fillOval((int)(closePointB.getX())-10,(int)(closePointB.getY())-30,20,20);
   }

    if(flagA == true && flagB == true && flagC == true){
      g.setColor(Color.BLACK);
      int p = -1;
      for(int i = 0; i < selectedPath.size(); i++){
        if(selectedPath.get(i).point == closePointB){
          
          p = i;
        }
      }

      //print out the line connecting all the point
      if(p > 0){
        for(int t =0; t < p; t++){
            g.drawLine((int)(selectedPath.get(t).point.getX()),(int)(selectedPath.get(t).point.getY()-20),(int)(selectedPath.get(t+1).point.getX()),(int)(selectedPath.get(t+1).point.getY()-20));
        }
      }
     
    }
    }
 }
 public void setBackground(){
   //add the background image
   try{
   background = ImageIO.read(new File("Screenshot-1.png"));
}
catch(IOException e){
   System.out.println("error " + e);
   }
}
 public void setPoints(){

   //create point objects
   for(int i = 0; i < 45; i++){
      point[i] = new Point();
}
   //manually setting all points UGH!
   point[0].setLocation(166,30);
   point[1].setLocation(150,153);
   point[2].setLocation(125,326);
   point[3].setLocation(235,30);
   point[4].setLocation(320,30);
   point[5].setLocation(206,269);
   point[6].setLocation(180,269);
   point[7].setLocation(207,327);
   point[8].setLocation(208,388);
   point[9].setLocation(220,150);
   point[10].setLocation(215,435);
   point[11].setLocation(220,485);
   point[12].setLocation(316,152);
   point[13].setLocation(315,265);
   point[14].setLocation(325,390);
   point[15].setLocation(309,433);
   point[16].setLocation(377,30);
   point[17].setLocation(373,153);
   point[18].setLocation(375,267);
   point[19].setLocation(375,327);
   point[20].setLocation(434,444);
   point[21].setLocation(375,390);
   point[22].setLocation(378,454);
   point[23].setLocation(436,30);
   point[24].setLocation(436,153);
   point[25].setLocation(434,270);
   point[26].setLocation(467,390);
   point[27].setLocation(523,30);
   point[28].setLocation(520,152);
   point[29].setLocation(558,152);
   point[30].setLocation(520,270);
   point[31].setLocation(514,327);
   point[32].setLocation(504,382);
   point[33].setLocation(610,30); 
   point[34].setLocation(606,150);
   point[35].setLocation(658,154);
   point[36].setLocation(650,215);
   point[37].setLocation(607,217);
   point[38].setLocation(607,270);
   point[39].setLocation(656,270);
   point[40].setLocation(607,332);
   point[41].setLocation(572,333);
   point[42].setLocation(573,385);
   point[43].setLocation(603,408);
   point[44].setLocation(605,384);
   
}
  
 public static void main (String args[]) {
    Dijkstra frame = new Dijkstra();
    Vertex[] vertices = new Vertex[45];//create an array of vertex type
    frame.setPoints();
    //iterate through the array making each on a vertex and giving it a point value
    for(int i = 0; i <45 ; i++){

      vertices[i] = new Vertex();
      vertices[i].point = frame.point[i];
    }
    //Created a vertex object for each and defining its edges
    vertices[0].adjacencies = new Edge[]{new Edge(vertices[1],123.0),
                                         new Edge(vertices[3],69.0)};
    vertices[1].adjacencies = new Edge[]{new Edge(vertices[0],123.0),
                                         new Edge(vertices[9],70.0),
                                         new Edge(vertices[2],173.0)};
    vertices[2].adjacencies = new Edge[]{new Edge(vertices[1],173.0),
                                         new Edge(vertices[7],82.0)};  
    vertices[3].adjacencies = new Edge[]{new Edge(vertices[0],69.0),
                                         new Edge(vertices[4],85.0),
                                         new Edge(vertices[9],120.0)}; 
    vertices[4].adjacencies = new Edge[]{new Edge(vertices[3],85.0),
                                         new Edge(vertices[16],57.0),
                                         new Edge(vertices[12],122.0)};
    vertices[5].adjacencies = new Edge[]{new Edge(vertices[9],119.0),
                                         new Edge(vertices[6],26.0),
                                         new Edge(vertices[13],109.0),
                                         new Edge(vertices[7],58.0)}; 
    vertices[6].adjacencies = new Edge[]{new Edge(vertices[5],26.0)};
    vertices[7].adjacencies = new Edge[]{new Edge(vertices[5],58.0),
                                         new Edge(vertices[2],82.0),
                                         new Edge(vertices[8],61.0),
                                         new Edge(vertices[19],168.0)};
    vertices[8].adjacencies = new Edge[]{new Edge(vertices[7],61.0),
                                         new Edge(vertices[14],117.0),
                                         new Edge(vertices[10],47.0)};
    vertices[9].adjacencies = new Edge[]{new Edge(vertices[3],120.0),
                                         new Edge(vertices[1],70.0),
                                         new Edge(vertices[12],120.0),
                                         new Edge(vertices[5],119.0)};
    vertices[10].adjacencies = new Edge[]{new Edge(vertices[8],61.0),
                                          new Edge(vertices[11],50.0),
                                          new Edge(vertices[15],94.0)};
    vertices[11].adjacencies = new Edge[]{new Edge(vertices[10],50.0),
                                          new Edge(vertices[22],158.0)};
    vertices[12].adjacencies = new Edge[]{new Edge(vertices[4],122.0),
                                          new Edge(vertices[9],120.0),
                                          new Edge(vertices[13],113.0),
                                          new Edge(vertices[17],57.0)};
    vertices[13].adjacencies = new Edge[]{new Edge(vertices[5],109.0),
                                          new Edge(vertices[12],113.0),
                                          new Edge(vertices[18],60.0)} ;                                  
    vertices[14].adjacencies = new Edge[]{new Edge(vertices[8],117.0),
                                          new Edge(vertices[15],43.0),
                                          new Edge(vertices[21],50.0)};
    vertices[15].adjacencies = new Edge[]{new Edge(vertices[10],94.0),
                                          new Edge(vertices[14],43.0)};
    vertices[16].adjacencies = new Edge[]{new Edge(vertices[4],57),
                                          new Edge(vertices[17],123.0),
                                          new Edge(vertices[23],59.0)};                                      
    vertices[17].adjacencies = new Edge[]{new Edge(vertices[12],57.0),
                                          new Edge(vertices[16],123.0),
                                          new Edge(vertices[18],114.0 ),
                                          new Edge(vertices[24],63.0)};
    vertices[18].adjacencies = new Edge[]{new Edge(vertices[13],60.0),
                                          new Edge(vertices[17],114.0),
                                          new Edge(vertices[19],60.0),
                                          new Edge(vertices[25],59.0)};
    vertices[19].adjacencies = new Edge[]{new Edge(vertices[7],168.0),
                                         new Edge(vertices[18],60.0),
                                         new Edge(vertices[21],63.0),
                                         new Edge(vertices[31],139.0)};
    vertices[20].adjacencies = new Edge[]{new Edge(vertices[22],61.0)};
    vertices[21].adjacencies = new Edge[]{new Edge(vertices[19],63.0),
                                          new Edge(vertices[14],50.0),
                                          new Edge(vertices[22],64.0),
                                          new Edge(vertices[26],101.0)};
    vertices[22].adjacencies = new Edge[]{new Edge(vertices[11],158.0),
                                          new Edge(vertices[20],61.0)};
    vertices[23].adjacencies = new Edge[]{new Edge(vertices[16],59.0),
                                          new Edge(vertices[24],123.0),
                                          new Edge(vertices[27],87.0)};
    vertices[24].adjacencies = new Edge[]{new Edge(vertices[17],63.0),
                                          new Edge(vertices[23],123.0),
                                          new Edge(vertices[25],117.0),
                                          new Edge(vertices[28],84.0)};
    vertices[25].adjacencies = new Edge[]{new Edge(vertices[18],59.0),
                                          new Edge(vertices[24],117.0),
                                          new Edge(vertices[30],86.0)};
    vertices[26].adjacencies = new Edge[]{new Edge(vertices[21],101.0)};
    vertices[27].adjacencies = new Edge[]{new Edge(vertices[23],59.0),
                                          new Edge(vertices[28],122.0),
                                          new Edge(vertices[33],87.0)};
    vertices[28].adjacencies = new Edge[]{new Edge(vertices[24],84.0),
                                          new Edge(vertices[27],122.0),
                                          new Edge(vertices[29],38.0),
                                          new Edge(vertices[30],118.0)};
    vertices[29].adjacencies = new Edge[]{new Edge(vertices[28],38.0)};
    vertices[30].adjacencies = new Edge[]{new Edge(vertices[25],86.0),
                                          new Edge(vertices[28],118.0),
                                          new Edge(vertices[31],57.0),
                                          new Edge(vertices[38],87.0)};
    vertices[31].adjacencies = new Edge[]{new Edge(vertices[19],139.0),
                                          new Edge(vertices[30],57.0),
                                          new Edge(vertices[32],55.0)};
    vertices[32].adjacencies = new Edge[]{new Edge(vertices[31],55.0)};
    vertices[33].adjacencies = new Edge[]{new Edge(vertices[27],87.0),
                                          new Edge(vertices[34],120.0)};
    vertices[34].adjacencies = new Edge[]{new Edge(vertices[33],120.0),
                                          new Edge(vertices[35],120.0),
                                          new Edge(vertices[37],67.0)};
    vertices[35].adjacencies = new Edge[]{new Edge(vertices[34],120.0),
                                          new Edge(vertices[36],61.0)};
    vertices[36].adjacencies = new Edge[]{new Edge(vertices[35],61.0),
                                          new Edge(vertices[37],43.0)};
    vertices[37].adjacencies = new Edge[]{new Edge(vertices[34],67.0),
                                          new Edge(vertices[36],43.0),
                                          new Edge(vertices[38],53.0)};
    vertices[38].adjacencies = new Edge[]{new Edge(vertices[30],87.0),
                                          new Edge(vertices[37],53.0),
                                          new Edge(vertices[39],49.0),
                                          new Edge(vertices[40],62.0)};
    vertices[39].adjacencies = new Edge[]{new Edge(vertices[38],49.0)};
    vertices[40].adjacencies = new Edge[]{new Edge(vertices[38],62.0),
                                          new Edge(vertices[41],52.0),
                                          new Edge(vertices[44],52.0)};
    vertices[41].adjacencies = new Edge[]{new Edge(vertices[41],52.0)};
    vertices[42].adjacencies = new Edge[]{new Edge(vertices[44],32.0)};
    vertices[43].adjacencies = new Edge[]{new Edge(vertices[44],24.0)};
    vertices[44].adjacencies = new Edge[]{new Edge(vertices[40],52.0),
                                          new Edge(vertices[42],32.0),
                                          new Edge(vertices[43],24.0)};
    //Defining the starting point 
    boolean temp = false ;
    while(temp != true){
      
      System.out.print("");
      if(frame.flagA == true && frame.flagB == true){
        int p = -1;
        for(int k = 0; k < 45; k++){
          if(frame.point[k] == frame.closePointA){
            p = k;
          }
        }  
        if(p>0){
          //setting the closepoints and slected paths
          pathBetweenPoints(vertices[p]);
          for(Vertex ver : vertices){
            if(ver.point == frame.closePointB){
              frame.ver = ver;
              frame.selectedPath = shortestPath(ver);
            }
          
          }
          frame.flagC = true;
          temp = true;
          
        }                   

      }
    }
    frame.repaint();

    //
  }
  public static void pathBetweenPoints(Vertex S){
    S.minDistance = 0.0;
    PriorityQueue<Vertex> vQueue = new PriorityQueue<Vertex>();
    vQueue.add(S);//add 0 to the queue so its not empty

    while(!vQueue.isEmpty()){//whilke queue is not empty
      Vertex u = vQueue.poll();//remove the head
      for(Edge edge : u.adjacencies){//a for each loop to iterate through each edges adjacent values
        Vertex vTemp = edge.target;
        double weight = edge.weight;
        double disTU = u.minDistance + weight;//get min distance
          if(disTU < vTemp.minDistance){//if its less than previous min distance
            vQueue.remove(vTemp);//remove the previos smallest
            vTemp.minDistance = disTU;
            vTemp.previous = u;
            vQueue.add(vTemp);//add new smallest
          }
      }
    }

  }
  //
  public static List<Vertex> shortestPath(Vertex target){
    List<Vertex> shortPath = new ArrayList<Vertex>();
    for(Vertex ver = target; ver != null; ver = ver.previous){
      shortPath.add(ver);//add the vertex to the shortest path list
    }
    //reverss the path
    Collections.reverse(shortPath);
    return shortPath;
  }
}
class Vertex implements Comparable<Vertex>{
    
    public Edge[] adjacencies;//array of edges
    public Point point;//give each vertex a point value
    public double minDistance = 9999999;//Some really high initial number
    public Vertex previous;

      public int compareTo(Vertex other)
   {
       return Double.compare(minDistance, other.minDistance);//compare vertex
   }

}
class Edge{
    //each edge has an associated vertex and a weight
    public Vertex target;
    public double weight;

    public Edge(Vertex argTarget, double argWeight){
         target = argTarget;
         weight = argWeight; 
    }

}



