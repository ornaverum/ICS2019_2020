package predatorprey;

public class Prey extends Animal{

	public boolean graze;


	public Prey(){
		super();
	}

	public Prey(int x, int y){
		super(x, y);
	}

	public Prey(int x, int y, Ecosystem eco){
			super(x, y);
			this.eco = eco;
			energy = Ecosystem.preyEnergyInit;
			energyRepThresh = Ecosystem.preyRepThresh;
			if (energyRepThresh == 0)
				System.out.printf("My threshold is %d. preyRepThresh is %d.\n", energyRepThresh, Ecosystem.preyRepThresh);
	}

	public void eat(){
		energy += 2;
	}

	public void checkReproduce(){
		// System.out.printf("Prey: my energy is %d and my threshold is %d.\n", energy, energyRepThresh);
		if (energy > energyRepThresh)
			reproduce();
	}

	public void reproduce(){
		energy -= energyRepThresh;
		Prey a = new Prey(x, y, this.eco);
		eco.birthList.add(a);
	}


	public void act(){

		// System.out.printf("Prey: My energy is %d\n", energy);

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
