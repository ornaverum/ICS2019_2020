package preditorprey;

public class Prey extends Animal{
	
	public boolean graze;
	
	
	public Prey(){
		super();
	}
	
	public Prey(int x, int y){
		super(x, y);
	}
	
	public void eat(){
		energy += 2;
	}
	
	public void checkReproduce(){
		if (energy > energyRepThresh)
			reproduce();
	}
	
	public void reproduce(){
		energy -= energyRepThresh;
		Prey a = new Prey(x, y);
		a.eco = this.eco;
		a.energy = 5;
		eco.birthList.add(a);
	}
	
	public void act(){
		
		graze = !graze;
		
		if (graze)
			eat();
		
		else{
			int dx =  (int) ( Math.random()*(1+1+1) ) -1;
			int dy =  (int) ( Math.random()*(1+1+1) ) -1;
			
			move(dx, dy);
			if (!(dx ==0 && dy ==0))
				energy --;
		}
			
		checkDeath();
		checkReproduce();
	}
	
}



