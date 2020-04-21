import java.lang.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.ArrayList.*;
import java.util.Random;

import java.util.Scanner;

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



class agent {
		int x, y;
		int energy;
		public int reproductionThresholdEnergy;
		public double visionRadius;
		public boolean seesTarget;
		public int speed;
		ecosystem eco;

		agent(){
			x = 0;
			y = 0;
			energy = 0;
			reproductionThresholdEnergy = 0;
			visionRadius = 0;
			speed = 1;
			seesTarget = false;
		}

		agent(int x, int y, int e){
			this.x = x;
			this.y = y;
			energy = e;
			reproductionThresholdEnergy = 1000;
			visionRadius = 5;
			this.speed = 1;
			seesTarget = false;
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

		public boolean seeAgent(agent target){
				int[] posTarget;
				int xT, yT;

				posTarget = target.getPos();
				xT = posTarget[0];
				yT = posTarget[1];
				double distance = Math.hypot(x-xT, y-yT);
				return (distance <= visionRadius);
		}

		public static int randIntRange(int min, int max){
			Random rand = new Random();
			int r = rand.nextInt( (max-min) + 1) + min;
			return r;
		}

		public int[] getRandomMove(int speed){
				int rx, ry;
				rx = randIntRange(-speed, speed);
				ry = randIntRange(-speed, speed);
				int[] dr = {rx, ry};
				return dr;
		}

		public void step(int dx, int dy){

			int x0 = x;
			int y0 = y;

			int y1, x1;

			int maxX = eco.maxX;
			int maxY = eco.maxY;

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
			setPos(x1, y1);
		}



}

interface critter{
		critter reproduce();
		void move();
		void look();
		void act();
}

class herbovore extends agent implements critter{

		agent predator = null;

		herbovore(int x, int y, int e){
				super(x,  y,  e);
		}

		public void look(){

		}

		public void move(){

		}

		public void act(){
				look();
				move();
		}

		public critter reproduce(){return null;}
}


class carnivore extends agent implements critter{
		agent prey = null;
		// speed = 2;

		carnivore(int x, int y, int e){
				super(x,  y,  e);
		}

		public void look(){

		}

		public void move(){
				if (prey == null){
						int[] dr = getRandomMove(speed);
						step(dr[0], dr[1]);
				}
		}

		public void act(){
			look();
			move();
		}

		public critter reproduce(){return null;}
}



class ecosystem{
	public int maxX, maxY;
	public double preyDensity, predDensity;
	public ArrayList<agent> masterList = new ArrayList<>();
	public int[][] displayGrid;

	ecosystem(){
	}

	ecosystem(int maxX, int maxY){
		this.maxX = maxX;
		preyDensity = 0.01;
		predDensity = 0.005;
		this.maxY = maxY;
		populate();
		displayGrid = new int[maxX][maxY];
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
						herbovore c = new herbovore(i, j, 1);//, 5, 5.0, 1);
						c.eco = this;
						c.speed = 1;
						c.reproductionThresholdEnergy = 5;
						c.visionRadius = 8;
						masterList.add(c);
				} else if (r < preyDensity + predDensity) {
						carnivore c = new carnivore(i, j, 1);//, 2, 5.0, 2);
						c.eco = this;
						c.speed = 2;
						c.reproductionThresholdEnergy = 2;
						c.visionRadius = 5;
						masterList.add(c);
				}
			}
		}
	}

	public void updateGrid(){
		String s;
		for (int i = 0; i < maxX; i ++){
			for (int j = 0; j < maxY; j ++){
				displayGrid[i][j] = 0;
			}
		}
		for (agent c:masterList){
			int[] pos = c.getPos();
			s = String.format("%s", c.getClass());
			// System.out.println(s);
			int i = pos[0];
			int j = pos[1];
			displayGrid[i][j]=s.contains("herbovore")?1:2;
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
				System.out.printf( "%d", displayGrid[i][j] );
			}
			System.out.printf("\n");
		}
	}

	// public void move(agent c, int dx, int dy){
	// 	int[] pos = c.getPos();
	// 	int x0 = pos[0];
	// 	int y0 = pos[1];
	//
	// 	int y1, x1;
	//
	// 	if (x0 + dx >= maxX){
	// 		x1 = x0 + dx - maxX;
	// 	} else if (x0 + dx < 0) {
	// 		x1 = x0 + dx + maxX;
	// 	} else {
	// 		x1 = x0 + dx;
	// 	}
	//
	// 	if (y0 + dy >= maxY){
	// 		y1 = y0 + dy - maxY;
	// 	} else if (y0 + dy < 0) {
	// 		y1 = y0 + dy + maxY;
	// 	} else {
	// 		y1 = y0 + dy;
	// 	}
	// 	c.setPos(x1, y1);
	// }


	public static int randIntRange(int min, int max){
		Random rand = new Random();
		int r = rand.nextInt( (max-min) + 1) + min;
		return r;
	}

	public void nextCycle(){
		Random rand = new Random();
		int rx, ry;
		//findTargets();
		for(agent c: masterList){
				// rx = randIntRange(-1, 1);
				// ry = randIntRange(-1, 1);
				// move(c, rx, ry);
				if (c instanceof carnivore){
					((carnivore) c).act();
				}
				else if (c instanceof herbovore){
					((herbovore) c).act();
				} else {

				}
		}
	}

	public void findTargets(){
			double dMin, d;
			double cx, cy;
			double tx, ty;
			int[] cpos;
			int[] tpos;

			for(agent c: masterList){
					if (c instanceof carnivore){
							dMin = Math.hypot(maxX, maxY);
							cpos = c.getPos();
							cx = cpos[0]; cy = cpos[1];
							for(agent t: masterList){
									if ( t instanceof herbovore ){
											tpos = t.getPos();
											tx = tpos[0]; ty = tpos[1];
											d = Math.hypot(cx - tx, cy-ty);
											if (d < dMin){
													d = dMin;
													( (carnivore) c ).prey = (herbovore) t;
											}

									}
							}
					}
			}

			for(agent c: masterList){
					if (c instanceof herbovore ){
							dMin = Math.hypot(maxX, maxY);
							cpos = c.getPos();
							cx = cpos[0]; cy = cpos[1];
							for(agent t: masterList){
									if ( t instanceof carnivore ){
											tpos = t.getPos();
											tx = tpos[0]; ty = tpos[1];
											d = Math.hypot(cx - tx, cy-ty);
											if (d < dMin){
													d = dMin;
													( (herbovore) c).predator = (carnivore) t;
											}

									}
							}
					}
			}
	}


}
