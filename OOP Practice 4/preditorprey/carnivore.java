package preditorprey;


public class carnivore extends agent implements critter{
		agent prey = null;
		// speed = 2;

		public carnivore(int x, int y, int e){
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

		public void eat(){}

		public void reproduce(){}
}
