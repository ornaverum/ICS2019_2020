package preditorprey;

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
