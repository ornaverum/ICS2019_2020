package preditorprey;

public class herbovore extends agent implements critter{

		agent predator = null;

		public herbovore(int x, int y, int e){
				super(x,  y,  e);
		}

		public void look(){
		}

		public void move(){
				if (predator == null){
						int[] dr = getRandomMove(speed);
						step(dr[0], dr[1]);
				}
		}

		public void act(){
				eat();
				look();
				move();
				if (energy >= reproductionThresholdEnergy){
					reproduce();
					energy -= reproductionThresholdEnergy;
				}
		}

		public void eat(){
			energy++;
		}

		public void reproduce(){
			System.out.printf("In %s reproduce: E = %d\n", this.getClass(), energy);
			herbovore c = eco.addNewHerbovore(x,y);
			int[] dr;
			dr = c.getRandomMove(1);
			c.step(dr[0], dr[1]);
			c.updatePos();
		}
}
