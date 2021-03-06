import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import preditorprey.*;

public class Program{

	// I'm setting this as constants for now
	// Some of these could later be refactored as model or GUI parameters.

	public static final int ECO_SIZE = 100;
	public static final int SQ_SIZE = 10;
	public static final double ANIMAL_DENSITY = 0.05;

	public static void main(String[] args){

		int dim = ECO_SIZE*SQ_SIZE;

		Ecosystem eco = new Ecosystem(ECO_SIZE, ANIMAL_DENSITY);
		JFrame frame = new JFrame("Example");
        MyPanel panel=new MyPanel();
        panel.setPreferredSize(new Dimension(dim, dim));
        panel.setEcosystem(eco);
        panel.setSqSize(SQ_SIZE);
		frame.add(panel);
        panel.addMouseMotionListener(panel);
        panel.addMouseListener(panel);
        frame.pack();
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);



		while(true){
						eco.update();
            panel.repaint();
            try{Thread.sleep(100);}catch(Exception e){}
        }
	}
}

class MyPanel extends JPanel implements MouseMotionListener, MouseListener{
    public int sqSize;
    Ecosystem eco;

    public void paint(Graphics g){
        //super.paint(g);
				Graphics2D g2=(Graphics2D)g;
        g2.setColor(Color.WHITE);
        g2.fillRect(0,0,getWidth(),getHeight());


        g2.setColor(Color.GREEN);
				int[] pos;
				int i, j;
				for(Animal a: eco.animalList){

					if (a instanceof Prey) g2.setColor(Color.GREEN);
					else if (a instanceof Preditor) g2.setColor(Color.RED);
					pos = a.getPos();
					i = pos[0];
					j = pos[1];
					g2.fillRect(i*sqSize, j*sqSize, sqSize, sqSize);
				}
			}

	public void setSqSize(int s){sqSize = s;}
	public void setEcosystem(Ecosystem eco){this.eco = eco;}
    public void mouseDragged(MouseEvent e){}
    public void mouseMoved(MouseEvent e){}
    public void mouseClicked(MouseEvent e){}
    public void mouseEntered(MouseEvent e){}
    public void mouseExited(MouseEvent e){}
    public void mousePressed(MouseEvent e){}
    public void mouseReleased(MouseEvent e){}

}
