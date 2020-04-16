import java.lang.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.ArrayList.*;
import java.util.Random;

public class Program
{
	public static void main(String[] args)
    {
		ecosystem eco = new ecosystem(10, 10, 0.1, 0);
	}
}

interface being{
	being reproduce();

	int[] getPos();

	void setPos(int x, int y);

	void feed(int dE);

	int getEnergy();

	void setEnergy(int E);

	int die();
}


class grass implements being{
	int x, y;
	int energy;
	public static final int REPRODUCTION_ENERGY_COST = 2;

	grass(){
		x = 0;
		y = 0;
		energy = 0;
	}

	grass(int x, int y, int e){
		this.x = x;
		this.y = y;
		energy = e;
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
		String s = String.format("Type: %s at pos = (%d, %d)", this.getClass(), x, y);
		return s;
	}

	public int die(){return 0;}

	public void feed(int dE){energy += dE;}

	public grass reproduce(){
		if (energy > REPRODUCTION_ENERGY_COST){
			energy -= REPRODUCTION_ENERGY_COST;
			grass g = new grass();
			return g;
		} else { return null; }
	}
}

class critter implements being{
	int x, y;
	int energy;

	critter(){
		x = 0;
		y = 0;
		energy = 0;
	}

	critter(int x, int y, int e){
		this.x = x;
		this.y = y;
		energy = e;
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

	public critter reproduce(){}

	public int die(){return 0;}

	public void feed(int dE){energy += dE;}

	public String toString(){
		String s = String.format("Type: %s at pos = (%d, %d)", this.getClass(), x, y);
		return s;
	}
}


class ecosystem{
	public int maxX, maxY;
	public double grassDensity, herbDensity;

	public String[][] displayGrid;

	ArrayList<being> masterList = new ArrayList<>();

	ecosystem(){
	}

	ecosystem(int maxX, int maxY, double rhoG, double rhoH){
		this.maxX = maxX;
		this.maxY = maxY;
		grassDensity = rhoG;
		herbDensity = rhoH;
		populate();
		displayGrid = new String[maxX][maxY];
		updateGrid();
		drawGrid();
	}

	public String toString(){
		for (int i = 0; i < maxX; i ++){
			for (int j = 0; j < maxY; j ++){

			}
		}
		return "";
	}

	public void populate(){
		Random rand = new Random();
		double r;
		for (int i = 0; i < maxX; i ++){
			for (int j = 0; j < maxY; j ++){
				r = rand.nextDouble();
				if (r < grassDensity){
					grass g = new grass(i, j, 1);
					masterList.add(g);
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
		for (being f:masterList){
			int[] pos = f.getPos();
			int i = pos[0];
			int j = pos[1];
			String type = String.format("%s", f.getClass());
			if(type.contains("grass")){
				displayGrid[i][j] = "g";
			} else {
				displayGrid[i][j] = "X";
			}
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

	public void updateCycle(){
	}
}
