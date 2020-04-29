package preditorprey;
// import java.util.Random;

public class Animal{

	// instance variables
	int speed;
	int energy;

	// how hungry it is -
		// energy

	// when to reproduce
		// energy reproduction threshold

	public static int randInt(int min, int max){
			double r = Math.random();
			int ro = (int) (r*(max-min+1)) + min;
			// System.out.printf("r=%f, %d, %f, ro=%d\n", r, max-min+1, r*(max-min+1), ro);
			return ro;
	}
	// position
	public int x, y;

	Ecosystem eco;


	// constructor

	public Animal(){
		x = 0;
		y = 0;
	}

	public Animal(int x, int y){
		this.x = x;
		this.y = y;
	}


	// methods
	// eat
	// die
	// reproduce

	// move

	public void move(int dx, int dy){
		x += dx;
		y += dy;

		x = Math.floorMod(x, eco.size);
		y = Math.floorMod(y, eco.size);
		
	}

	// getters and setters
	public void setPos(int x, int y){
		this.x = x;
		this.y = y;
	}

	public int[] getPos(){
		int[] pos = {x,y};
		return pos;
	}

	public void act(){
		int dx, dy;
		dx = randInt(-1, 1);
		dy = randInt(-1, 1);
		move(dx, dy);
	}
}
