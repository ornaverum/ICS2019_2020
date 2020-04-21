import java.lang.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.ArrayList.*;
import java.util.Random;

import java.util.Scanner;

import preditorprey.*;

// test git

public class Program{

		public static final int ECO_SIZE = 100;
		public static final int SQ_SIZE = 10;

		public static void main(String[] args){
				JFrame frame = new JFrame("Example");
        MyPanel panel=new MyPanel();

				ecosystem eco = new ecosystem(ECO_SIZE, ECO_SIZE);
				panel.setEco(eco);
				panel.setSqSize(SQ_SIZE);
        panel.setPreferredSize(new Dimension(ECO_SIZE*SQ_SIZE,ECO_SIZE*SQ_SIZE));
				frame.add(panel);
        panel.addMouseMotionListener(panel);
        panel.addMouseListener(panel);
        // panel.addKeyListener(panel);
        frame.pack();
				frame.setVisible(true);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

				while(true)
        {
						eco.updateGrid();
						// eco.drawGrid();
						eco.nextCycle();
            panel.repaint();
            try{Thread.sleep(100);}catch(Exception e){}
        }

		}
}

class MyPanel extends JPanel implements MouseMotionListener, MouseListener{

		ecosystem eco;
		public int sqSize;

		public void setEco(ecosystem eco) {this.eco = eco;}
		public void setSqSize(int size) {sqSize = size;}

    public void paint(Graphics g){

				Graphics2D g2=(Graphics2D)g;
				g2.setColor(Color.WHITE);
				g2.fillRect(0, 0, getWidth(), getHeight());
        for (int i = 0; i < eco.maxX; i++){
						for (int j = 0; j < eco.maxY; j++){
								if(eco.displayGrid[i][j] == 1){
										g2.setColor(Color.GREEN);
										g2.fillRect(i*sqSize, j*sqSize, sqSize, sqSize);
								} else if(eco.displayGrid[i][j] == 2){
										g2.setColor(Color.RED);
										g2.fillRect(i*sqSize, j*sqSize, sqSize, sqSize);
								}
						}
				}
	}

    public void mouseDragged(MouseEvent e){}
    public void mouseMoved(MouseEvent e)    {
    }

    public void mouseClicked(MouseEvent e){}
    public void mouseEntered(MouseEvent e){}
    public void mouseExited(MouseEvent e){}
    public void mousePressed(MouseEvent e)    {
				eco.nextCycle();
				eco.updateGrid();
				// eco.drawGrid();
				repaint();
    }
    public void mouseReleased(MouseEvent e){}

}
