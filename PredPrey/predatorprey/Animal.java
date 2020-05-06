package predatorprey;

public class Animal{

	// instance variables
	
	public int energy;
	public int energyRepThresh;
	
	// when to reproduce
		// energy reproduction threshold
		
	// position
	public int x, y;
	public Ecosystem eco;
	

	// constructor
	
	public Animal(){
		x = 0;
		y = 0;
		energy = 0;
		energyRepThresh = 20;
	}
	
	public Animal(int x, int y){
		this.x = x;
		this.y = y;
		energy = 0;
		energyRepThresh = 20;
	}
	// methods
	
	// eat
	
	
	//	Which class knows what?  (Where instance variables are stored.)
	//  Which class controls what? (Where the methods are stored.)
	//  Which method constrols how much? (What arguments are passed, what parameters are generated.)
	
	public void move(int dx, int dy){
		x += dx;  	//  -1, 0, 1
		y += dy;	//	-1, 0, 1
		
		x = Math.floorMod(x, eco.size);
		y = Math.floorMod(y, eco.size);
	}
	
	public void act(){
		int dx =  (int) ( Math.random()*(1+1+1) ) -1;
		int dy =  (int) ( Math.random()*(1+1+1) ) -1;
		
		move(dx, dy);
		if (!(dx ==0 && dy ==0))
			energy --;
		//~ System.out.printf("act with energy = %d\n", energy);
		checkDeath();
		reproduce();
	}
	
	public void checkDeath(){
		//~ System.out.printf("checkdeath with energy = %d\n", energy);
		if(energy <= 0)
			die();
	}
	
	public void die(){
		eco.deathList.add(this);
	}
	
	// (int) [0, 0.0001, 0.1, 0.2, ... , 2.7, 2.8, 2.9, 2.999999)
	// 0, 1, 2 
	// -1, 0, -1
	
	
	// asynchronous updating  vs synchronous updating
	//     randomize the order
	
	
	// getters and setters
	public void setPos(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public int[] getPos(){
		int[] pos = {x,y};
		return pos;
	}
	
	public void checkReproduce(){
		if (energy > energyRepThresh)
			reproduce();
	}
	
	public void reproduce(){
		energy -= energyRepThresh;
		Animal a = new Animal(x, y);
		a.eco = this.eco;
		a.energy = 5;
		eco.birthList.add(a);
	}

	

}
