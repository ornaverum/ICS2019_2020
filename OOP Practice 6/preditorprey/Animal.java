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
		if (x >= eco.size) x-=eco.size;
		if (x < 0) x += eco.size;
		if (y >= eco.size) y-=eco.size;
		if (y < 0) y += eco.size;
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
		dx = randInt(-speed, speed);
		dy = randInt(-speed, speed);
		move(dx, dy);
	}

	public void die(){
		if (energy <= 0){
			eco.killList.add(this);
		}
	}

}
