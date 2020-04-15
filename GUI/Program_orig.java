import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Program
{
	public static void main(String[] args)
    {
		JFrame frame = new JFrame("Example");
        MyPanel panel=new MyPanel();
        panel.setPreferredSize(new Dimension(400,400));
		frame.add(panel);
        panel.addMouseMotionListener(panel);
        panel.addMouseListener(panel);
        frame.pack();
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}

class MyPanel extends JPanel implements MouseMotionListener, MouseListener
{
    public int x=0;
    public int y=0;
    public int w=10;
    
    public void paint(Graphics g)
    {
        //super.paint(g);
		Graphics2D g2=(Graphics2D)g;
        g2.setColor(Color.WHITE);
        g2.fillRect(0,0,getWidth(),getHeight());
        g2.setColor(new Color(100,y/2,x/2));
        g2.fillRect(x,y,w,50);
	}
    
    public void mouseDragged(MouseEvent e){}
    public void mouseMoved(MouseEvent e)
    {
        x=e.getX();
        y=e.getY();
        repaint();
    }
    
    public void mouseClicked(MouseEvent e){}
    public void mouseEntered(MouseEvent e){}
    public void mouseExited(MouseEvent e){}
    public void mousePressed(MouseEvent e)
    {
        if(e.getButton()==1)w+=50;
        else if(e.getButton()==3)w-=50;
        repaint();
    }
    public void mouseReleased(MouseEvent e){}
    
}
