package predatorprey;

public class Predator extends Animal{

	public boolean graze;
	public static int predatorFeedEnergy;
	// public static int predatorRepThresh;


	public Predator(){
		super();
	}

	public Predator(int x, int y){
		super(x, y);
	}

	public Predator(int x, int y, Ecosystem eco){
			super(x, y);
			this.eco = eco;
			energy = Ecosystem.predatorEnergyInit;
			energyRepThresh = Ecosystem.predatorRepThresh;
	}

	public void eat(){
		// energy += 2;
		Prey n = eco.getPreyNeighbor(x, y);
			if (n != null){
				eco.deathList.add(n);
				energy+= predatorFeedEnergy;
			}
	}

	public void checkReproduce(){
		if (energy > energyRepThresh)
			reproduce();
	}

	public void reproduce(){
		energy -= energyRepThresh;
		Predator a = new Predator(x, y, this.eco);
		eco.birthList.add(a);
	}

	public void act(){

		// System.out.printf("Pred: My energy is %d\n", energy);

		eat();

		int dx =  (int) ( Math.random()*(1+1+1) ) -1;
		int dy =  (int) ( Math.random()*(1+1+1) ) -1;

		move(dx, dy);
		if (!(dx ==0 && dy ==0))
			energy --;

		checkDeath();
		// checkReproduce();
	}

}
