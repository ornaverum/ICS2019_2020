import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.lang.*;

public class Program
{
	public static void main(String[] args)
    {
		JFrame frame = new JFrame("Example");
        MyPanel panel=new MyPanel();
        panel.setPreferredSize(new Dimension(800,600));
		frame.add(panel);
        panel.addMouseMotionListener(panel);
        panel.addMouseListener(panel);
        panel.addKeyListener(panel);
        frame.pack();
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}

class MyPanel extends JPanel implements MouseMotionListener, MouseListener, KeyListener
{
    public int x=0;
    public int y=0;
    public int yBox = 60;
    public int xBox = 60;
    public int w=10;
    public int h = 50;
    
    public double xMinBound = 0.1;
    public double xMaxBound = 0.9;
    public int boundBoxBuffer = 3;
    
    public double circMinBound = 0.2;
    public double circBoundFactor = (1.00-2.0*circMinBound);
    
    public int xmin, xmax;
    public int ymin, ymax;
    public int xMinCircBound, widthCircBound;
    public int yMinCircBound, heightCircBound;
    
    public int xCirc, yCirc, widthCirc, heightCirc, rCirc;
    
    public double xScaleFrac;
    
    public boolean traceBox = false;
    
    public boolean firstFlag = true;
    
    public void paint(Graphics g)
    {
        super.paint(g);
		Graphics2D g2 = (Graphics2D) g;
		
        xmin = (int) Math.round(xMinBound*getWidth());
        xmax = (int) Math.round(xMaxBound*getWidth());
        
        xMinCircBound = (int) Math.round(circMinBound*getWidth());
        widthCircBound = (int) Math.round(circBoundFactor*getWidth());
        
        yMinCircBound = (int) Math.round(circMinBound*getHeight());
        heightCircBound = (int) Math.round(circBoundFactor*getHeight());
        
        xCirc = (int) Math.round(0.5*getWidth());
        yCirc = (int) Math.round((circMinBound + 0.5*circBoundFactor)*getHeight());
        
        xScaleFrac = ((double) x - (double) xmin)/((double) xmax - (double) xmin);
        
        widthCirc = (int) Math.round(xScaleFrac*widthCircBound);
        heightCirc = (int) Math.round(xScaleFrac*heightCircBound);
        rCirc = Math.min(widthCirc, heightCirc);
        
        ymin = yBox - h/2;
        ymax = yBox + h/2;
        
        // This wasn't painting the first run because the values weren't initialized
        // so this condition wasn't met.
		if (xmin < xBox && xBox < xmax && ymin < y && y < ymax){
			
			if (!traceBox)
			{
				g2.setColor(Color.WHITE);
				g2.fillRect(0, 0, getWidth(),getHeight());
			}	
			g2.setColor(Color.LIGHT_GRAY);
			g2.fillRect(xmin - boundBoxBuffer, ymin + h/2 - boundBoxBuffer, (xmax-xmin)+2*boundBoxBuffer + w, (ymax-ymin)+2*boundBoxBuffer);
			g2.setColor(Color.PINK);
			g2.fillRect(xMinCircBound, yMinCircBound, widthCircBound, heightCircBound);
			g2.setColor(new Color(100, 0, 100));
			g2.fillRect(xBox, yBox, w, h);
			
			g2.setColor(Color.BLUE);
			g2.fillOval(xCirc-rCirc/2, yCirc-rCirc/2, rCirc, rCirc);
		}
	}
	
	public void clear(Graphics g)
	{
		Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.WHITE);
        g2.fillRect(0, 0, getWidth(),getHeight());
	}	
    
    public void mouseDragged(MouseEvent e)
    {
		x = e.getX();
		y = e.getY();
		xBox = x - w/2;
        yBox = (int) Math.round(0.9*getHeight()) - h/2;// e.getY();
        repaint();
	}
    public void mouseMoved(MouseEvent e)
    {

    }
    
    public void mouseClicked(MouseEvent e){
		
	}
    public void mouseEntered(MouseEvent e){}
    public void mouseExited(MouseEvent e){}
    public void mousePressed(MouseEvent e)
    {
        //~ if(e.getButton()==1)w+=50;
        //~ else if(e.getButton()==3)w-=50;

    }
    public void mouseReleased(MouseEvent e)
    {

	}
	
	public void keyPressed(KeyEvent e)
	{
		//~ traceBox = false;
        //~ repaint();
	}
	public void keyReleased(KeyEvent e)
	{
		//~ traceBox = true;
		//~ repaint();
	}
	public void keyTyped(KeyEvent e)
	{
		//~ traceBox = !traceBox;
		//~ repaint();
	}
	
}
