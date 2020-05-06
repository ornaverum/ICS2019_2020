package predatorprey;

import java.util.Collections;

public class Predator extends Animal{
	
	public boolean graze;
	
	
	public Predator(){
		super();
	}
	
	public Predator(int x, int y){
		super(x, y);
	}
	
	public void eat(){
		ArrayList<Prey> targets;
		targets = eco.getAdjacentPrey(x, y);
		
		
		if ( targets.isEmpty() ){
			// no prey
		} else {
			Collections.shuffle(targets);
			Prey p = targets.get(0);
			// at least 1 prey
		}
		
		
		// 	check to see if Prey is adjacent
		//		???
		//	If one or more adjacent prey animals
		//		Select 1
		//			???
		//		That Prey target dies
		//			target.die()
		//		Predator energy increases
		//			energy += SomeAmount
		
	}
	
	public void checkReproduce(){
		if (energy > energyRepThresh)
			reproduce();
	}
	
	public void reproduce(){
		energy -= energyRepThresh;
		Predator a = new Predator(x, y);
		a.eco = this.eco;
		a.energy = 5;
		eco.birthList.add(a);
	}
	
	public void act(){
		
		eat();
		
	
		int dx =  (int) ( Math.random()*(1+1+1) ) -1;
		int dy =  (int) ( Math.random()*(1+1+1) ) -1;
		
		move(dx, dy);
		if (!(dx ==0 && dy ==0))
			energy --;
			
		checkDeath();
		checkReproduce();
	}
	
	public void sayHi(){
		System.out.printf("%s says hi!\n", this);
	}
	
}



