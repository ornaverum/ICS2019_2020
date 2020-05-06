import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import predatorprey.*;

public class Program{

	// I'm setting this as constants for now
	// Some of these could later be refactored as model or GUI parameters.

	public static final int ECO_SIZE = 20;
	public static final int SQ_SIZE = 20;
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



		while(true)
        {
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


        
        for(int i = 0; i < eco.size; i++){
			for(int j = 0; j<eco.size; j++){
				if (eco.drawGrid[i][j]==1){
					g2.setColor(Color.GRAY);
					g2.fillRect(i*sqSize, j*sqSize, sqSize, sqSize);
				} else if (eco.drawGrid[i][j]==2){
					g2.setColor(Color.BLACK);
					g2.fillRect(i*sqSize, j*sqSize, sqSize, sqSize);
				}
			}
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
