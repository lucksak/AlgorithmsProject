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



public class GrahamSchmidt extends JFrame implements MouseListener{

	int x=0, y=0;
	String events ="";
	public Vector<Integer> xclicks = new Vector<Integer>();
	public Vector<Integer> yclicks = new Vector<Integer>();
	
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
   
   if(yclicks.size() > 0){
      for(int i = 0; i < yclicks.size(); i++){
            g.setColor(Color.RED);
  			   g.fillRect(xclicks.get(i)-7,yclicks.get(i)-30,7,7);    
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
	public void mouseClicked(MouseEvent evt){
		
		System.out.println(" MOUSE CLICKED" + " x= " + evt.getX() + " y= " + evt.getY());
      if(evt.getX() > 0 && evt.getX() <700 && evt.getY() > 0 && evt.getY() <425){
		      xclicks.add(evt.getX());
		      yclicks.add(evt.getY());
		      System.out.println(" X Vector size = " + xclicks.size());
				System.out.println(" Y Vector size = " + yclicks.size());
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
  			System.out.println("CALCULATE PUSH");
  		}
  	}
  }
