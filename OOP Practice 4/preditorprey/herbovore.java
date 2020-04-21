package preditorprey;

class herbovore extends agent implements critter{

		agent predator = null;

		herbovore(int x, int y, int e){
				super(x,  y,  e);
		}

		public void look(){

		}

		public void move(){

		}

		public void act(){
				look();
				move();
		}

		public critter reproduce(){return null;}
}
