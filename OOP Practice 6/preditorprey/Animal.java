package preditorprey;
// import java.util.Random;

public class Animal{

	// instance variables
	int speed;
	int energy;
	int energyThreshold;
	int type;
	int id;

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
		id = 0;
		type = -1;
	}

	public Animal(int id){
		x = 0;
		y = 0;
		this.id = id;
		type = -1;
	}

	public Animal(int x, int y, int id){
		this.x = x;
		this.y = y;
		this.id = id;
		type = -1;
	}

	public Animal(int x, int y, int id, int type){
		this.x = x;
		this.y = y;
		this.id = id;
		this.type = type;
	}

	// methods
	// eat
	// die
	// reproduce

	// move

	// public void move(int dx, int dy){
	//
	// 		int x0 = x;
	// 		int y0 = y;
	//
	// 		int xf = x+dx;
	// 		int yf = y+dy;
	//
	// 		xf = Math.floorMod(xf, eco.size);
	// 		yf = Math.floorMod(yf, eco.size);
	//
	// 		// System.out.printf("%d, %d, %d, %d, %d, %d, %d\n", x, y, x0, y0, xf, yf, eco.typeGrid[xf][yf]);
	// 		if (eco.typeGrid[xf][yf]==0){
	// 				// System.out.printf("%d, %d, %d, %d, %d, %d, %d\n", x, y, x0, y0, xf, yf, eco.typeGrid[xf][yf]);
	// 				eco.typeGrid[x0][y0] = 0;
	// 				eco.idGrid[x0][y0] = -1;
	// 				eco.typeGrid[xf][yf] = type;
	// 				eco.idGrid[xf][yf] = eco.animalList.indexOf(this);
	// 				x = xf;
	// 				y = yf;
	// 		}
	// }

	public void move(){
			int[] pos = eco.getRandomNeighbor(x, y, 0);
			if (pos != null){
					int xf = pos[0];
					int yf = pos[1];
					eco.typeGrid[x][y] = 0;
					eco.idGrid[x][y] = -1;
					eco.typeGrid[xf][yf] = type;
					eco.idGrid[xf][yf] = eco.animalList.indexOf(this);
					x = xf;
					y = yf;
			}

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
			// move();
	}

	public void die(){
		if (energy <= 0){
			eco.killList.add(this);
		}
	}

	public void reproduce(){
			if (energy > energyThreshold){
					// System.out.println("Reproduction.");
					int[] pos = eco.getRandomNeighbor(x, y, 0);
					if (pos != null){
						energy -= energyThreshold;
						Prey a = new Prey(pos[0], pos[1], eco.lastID);
						eco.lastID ++;
						a.speed = 1;
						a.energy = 3;
						a.energyThreshold = 25;
						a.eco = this.eco;
						a.type = this.type;
						eco.birthList.add(a);
					}
			}
	}

}
