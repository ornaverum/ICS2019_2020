import java.lang.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.ArrayList.*;
import java.util.Random;

import java.util.Scanner;

// test git

public class Program
{
		public static void main(String[] args)
	    {
				Scanner scan = new Scanner(System.in);

				System.out.printf("Enter to continue, Q to quit : ");
				String strIn = scan.nextLine();
					ecosystem eco = new ecosystem(10, 10);
				while (!strIn.toUpperCase().contains("Q")){
					eco.nextCycle();
					eco.updateGrid();
					eco.drawGrid();
					strIn = scan.nextLine();
					// System.out.printf("\n\n%s", eco);
				}
		}
}

class MyPanel extends JPanel implements MouseMotionListener, MouseListener
{
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



class agent {
	int x, y;
	int energy;
	int reproductionThresholdEnergy;


	agent(){
		x = 0;
		y = 0;
		energy = 0;
		reproductionThresholdEnergy = 0;
	}

	agent(int x, int y, int e, int rTE){
		this.x = x;
		this.y = y;
		energy = e;
		reproductionThresholdEnergy = rTE;
	}

	public void setPos(int x, int y){
		this.x = x;
		this.y = y;
	}

	public int[] getPos(){
		int[] pos = {x, y};
		return pos;
	}

	public int getEnergy(){	return energy;}

	public void setEnergy(int e) { energy = e; }

	public String toString(){
		String s = String.format("Type: %s at pos = (%d, %d) with energy = %d.", this.getClass(), x, y, energy);
		return s;
	}
}


class ecosystem{
	public int maxX, maxY;
	public double preyDensity, predDensity;
	public ArrayList<agent> masterList = new ArrayList<>();
	public String[][] displayGrid;

	ecosystem(){
	}

	ecosystem(int maxX, int maxY){
		this.maxX = maxX;
		preyDensity = 0.1;
		predDensity = 0;
		this.maxY = maxY;
		populate();
		displayGrid = new String[maxX][maxY];
		updateGrid();
		drawGrid();
	}

	public String toString(){
		String s = "";
		for(agent c: masterList){
			s += String.format("%s\n", c.toString());
		}
		return s;
	}

	public void populate(){
		Random rand = new Random();
		double r;
		for (int i = 0; i < maxX; i ++){
			for (int j = 0; j < maxY; j ++){
				r = rand.nextDouble();
				if (r < preyDensity){
					agent c = new agent(i, j, 1, 2);
					masterList.add(c);
				}
			}
		}
	}

	public void updateGrid(){
		for (int i = 0; i < maxX; i ++){
			for (int j = 0; j < maxY; j ++){
				displayGrid[i][j] = ".";
			}
		}
		for (agent c:masterList){
			int[] pos = c.getPos();
			int i = pos[0];
			int j = pos[1];
			displayGrid[i][j]="x";
			// String type = String.format("%s", f.getClass());
			// if(type.contains("grass")){
			// 	displayGrid[i][j] = "g";
			// } else {
			// 	displayGrid[i][j] = "X";
			// }
		}
	}

	public void drawGrid(){
		for (int i = 0; i < maxX; i ++){
			for (int j = 0; j < maxY; j ++){
				System.out.printf( "%s", displayGrid[i][j] );
			}
			System.out.printf("\n");
		}
	}

	public void nextCycle(){
		Random rand = new Random();
		int rx, ry;
		for(agent c: masterList){
				rx = randIntRange(-1, 1);
				ry = randIntRange(-1, 1);
				move(c, rx, ry);
		}
	}

	public static int randIntRange(int min, int max){
		Random rand = new Random();
		int r = rand.nextInt( (max-min) + 1) + min;
		return r;
	}

	public void move(agent c, int dx, int dy){
		int x0, x1, y0, y1;
		int[] pos = c.getPos();
		x0 = pos[0];
		y0 = pos[1];
		if (x0 + dx >= maxX){
			x1 = x0 + dx - maxX;
		} else if (x0 + dx < 0) {
			x1 = x0 + dx + maxX;
		} else {
			x1 = x0 + dx;
		}

		if (y0 + dy >= maxY){
			y1 = y0 + dy - maxY;
		} else if (y0 + dy < 0) {
			y1 = y0 + dy + maxY;
		} else {
			y1 = y0 + dy;
		}

		c.setPos(x1, y1);

	}

}
