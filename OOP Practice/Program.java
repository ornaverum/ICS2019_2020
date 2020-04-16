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
		ecosystem eco = new ecosystem(20, 20, 0.1, 0.05);
	}
	
	
}

class ecosystem{
	public int maxX, maxY;
	public double grassDensity, herbDensity;
	
	public String[][] displayGrid;
	
	ArrayList<food> masterList = new ArrayList<>();

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
					grass g = new grass(i, j);
					masterList.add(g);
				} else if ( r < grassDensity + herbDensity ){
					herbovore h = new herbovore(i, j);
					masterList.add(h);
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
		for (food f:masterList){
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
		ArrayList<food> nextList = (ArrayList<food>)masterList.clone();
		for(food f:masterList){
			if (f.foodType !="grass"){
				((critter) f).see();
			}
		}
		for(food f:masterList){
			if (f.foodType !="grass"){
				((critter) f).chasePrey(1);
			}
		}
		
		
	}
	
}
	
	



class food{
	public int x, y;
	public int energyGained = 10;
	public String foodType = "";
	
	food(){
		x = 0;
		y = 0;
	}
	
	food(int x, int y){
		this.x = x;
		this.y = y;
		//~ System.out.printf("in Food: %d, %d, %d, %d\n", x, y, this.x, this.y);
	}
	
	food(int x, int y, int E){
		this.x = x;
		this.y = y;
		energyGained = E;
	}
	
	public void setPos(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public int[] getPos(){
		int[] pos = {x, y};
		return pos;
	}
	
	public static double distance (food f1, food f2){
		int[] pos1 = f1.getPos();
		int[] pos2 = f2.getPos();
		
		double d = Math.sqrt(Math.pow(pos1[0]-pos2[0],2) + Math.pow(pos1[1]-pos2[1], 2));
		return d;
	}
	
	public String toString(){
		String s = String.format("Type: %s at pos = (%d, %d)", this.getClass(), x, y);
		return s;
	}
}



class critter extends food implements eater{
	int energy = 10;
	int speed = 5;
	food prey = null;
	String preyType = "";
	
	
	critter(){
		super();
	}
	
	critter(int x, int y){
		super(x, y);
		this.x = x;
		this.y = y;
			
	}
	
	critter(int x, int y, int Eg, int E){
		super(x, y, Eg);
		this.x = x;
		this.y = y;
		energyGained = Eg;
		energy = E;
	}
	
	public void move(int vx, int vy){
		double eC = energyCost(vx, vy);
		//~ System.out.printf("Entering move method.\n");
		x += vx;
		y += vy;
		energy -= eC;
	}
	
	public double energyCost(int vx, int vy){
		return Math.pow(Math.pow(vx,2) + Math.pow(vy,2), 0.8);
	}
	
	
	public void eatPrey()
	{
		
	}
	
	public void chasePrey(double dt)
	{
		if (prey != null){
			int[] pos = prey.getPos();
			
			int xp = pos[0];
			int yp = pos[1];
			
			int signX0 = Math.signum(x-xp);
			int signY0 = Math.signum(x-yp);
			
			double theta = Math.atan2( (double)(yp-y), (double) (xp-x));
			int vx = (int) Math.round(Math.cos(theta)*speed*dt);
			int vy = (int) Math.round(Math.sin(theta)*speed*dt);
			
			move(vx, vy);
			
			int signX1 = Math.signum(x-xp);
			int signY1 = Math.signum(y-yp);
			
			boolean b1 = signX0!=signX1 || signX0 == 0;
			boolean b2 = signY0!=signY1 || signY0 == 0;
			
			if ( (b1 && b2) || (signX1 == 0 && signY1 == 0) ){
				eatPrey();
			}
			
		}
	}
	
	public void see(double r, ArrayList<food> FoodList){
		double closest = 1000000.0;
		double distance = 0;
		String foodName = "";
		
		for (food F: FoodList){
			foodName = String.format("%s", F.getClass());
			if (foodName.contains(preyType)){
				
				distance = food.distance(this, F);
				if (distance < closest && distance <= r){
					closest = distance;
					prey = F;
				}
			}
		}
	}
}

class grass extends food{

	

	grass(){
		super();
		foodType = "grass";
	}
	
	grass(int x, int y){
		super(x, y);
		this.x = x;
		this.y = y;
		foodType = "grass";
	}
}

interface eater{
	void eatPrey();
	
	void chasePrey(double dt);
	
	void see(double r, ArrayList<food> foodList);
}

class herbovore extends critter{
	
	
	
	herbovore(){
		super();
		prey = null;
		speed = 5;
		energy = 10;
		preyType = "grass";
		foodType = "herbovore";
	}
	
	herbovore(int x, int y){
		super(x, y);
		prey = null;
		speed = 5;
		energy = 10;
		preyType = "grass";
		foodType = "herbovore";
	}
	
}


