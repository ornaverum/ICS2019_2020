package preditorprey;
import java.util.ArrayList;
import java.util.ArrayList.*;
import java.util.Random;
import java.util.*;



public class Ecosystem{

	// Instance variables
	public int size;  // Square
	public double animalDensity;
	public ArrayList<Animal> animalList;
	public ArrayList<Animal> killList;
	public ArrayList<Animal> birthList;
	public ArrayList<Animal> updateList;
	public int[][] typeGrid;
	public int[][] idGrid;
	public int lastID;


	// Constructor
	public Ecosystem(){
		size = 0;
		animalList = new ArrayList<>();
		birthList = new ArrayList<>();
		killList = new ArrayList<>();
		updateList = new ArrayList<>();
		typeGrid = new int[size][size];
		idGrid = new int[size][size];
		animalDensity = 0;
		lastID = 0;
	}

	public Ecosystem(int size){
		this.size = size;
		animalList = new ArrayList<>();
		birthList = new ArrayList<>();
		killList = new ArrayList<>();
		updateList = new ArrayList<>();
		animalDensity = 0;
		typeGrid = new int[size][size];
		lastID = 0;
		populate();
		updateGrid();

	}

	public Ecosystem(int size, double rho){
		this.size = size;
		animalList = new ArrayList<>();
		birthList = new ArrayList<>();
		killList = new ArrayList<>();
		updateList = new ArrayList<>();
		typeGrid = new int[size][size];
		idGrid = new int[size][size];
		animalDensity = rho;
		lastID = 0;
		populate();
		updateGrid();
	}

	//Methods
	public void populate(){
		double r;
		for (int i = 0; i < size; i++){
			for (int j = 0; j< size; j++){
				r = Math.random();
				//~ System.out.printf("%f, %f\n", r, animalDensity);
				if(r < animalDensity/2.0){
					Preditor a = new Preditor(i, j, lastID, 1);
					lastID ++;
					a.eco = this;
					a.speed = 1;
					a.energy = 10;
					a.energyThreshold = 25;
					animalList.add(a);
				} else if (r < animalDensity ){
					Prey a = new Prey(i, j, lastID, 2);
					lastID ++;
					a.eco = this;
					a.speed = 1;
					a.energy = 10;
					a.energyThreshold = 25;
					animalList.add(a);
				}
			}
		}
	}


	public void update(){
			// Collections.shuffle(animalList);
			int numPred, numPrey;
			numPred = 0;
			numPrey = 0;
			for (Animal a: animalList){
					if (a instanceof Preditor){
						((Preditor) a).act(); numPred++;}
					else if (a instanceof Prey){
						((Prey) a).act(); numPrey++;}
			}
			System.out.printf("%d pred and %d prey\n", numPred, numPrey);
			for (Animal a: killList){
				animalList.remove(a);
			}
			// System.out.printf("There are %d animals. %d animals are being born. ", animalList.size(), birthList.size());
			animalList.addAll(birthList);
			// System.out.printf("Then there are %d animals.\n", animalList.size());
			for (Animal a: birthList){
					int[] pos = a.getPos();
					typeGrid[pos[0]][pos[1]] = a.type;
					idGrid[pos[0]][pos[1]] = animalList.indexOf(a);
			}

			for (Animal a: killList){
					int[] pos = a.getPos();
					typeGrid[pos[0]][pos[1]] = 0;
					idGrid[pos[0]][pos[1]] = 0;
			}
			birthList.clear();
			killList.clear();
			// updateGrid();

	}


	public void updateGrid(){
		int[] pos;
		for (int i = 0; i < size; i++){
			for (int j = 0; j< size; j++){
				typeGrid[i][j] = 0;
				idGrid[i][j] = -1;
			}
		}

		Animal c;
		for (int i = 0; i < animalList.size(); i++){
				c = animalList.get(i);
				pos = c.getPos();
				idGrid[pos[0]][pos[1]] = i;
		}

		for(Animal a:animalList){
			pos = a.getPos();
			typeGrid[pos[0]][pos[0]] = a.type;
		}
	}

	public void setAnimalDensity(double p){this.animalDensity = p;}

	public int[] getRandomNeighbor(int x0, int y0, int type){
			ArrayList<int[]> potentials = new ArrayList<>();
			int x, y;
			System.out.printf("Getting neighbors of type %d:\n", type);
			for (int i = -1; i <= 1; i++){
					for (int j = -1; j <= 1; j++){
						x = Math.floorMod(x0 + i, size);
						y = Math.floorMod(y0 + j, size);
						System.out.printf("%d ", typeGrid[x][y]);
							if (!(i == 0 && j == 0)){
								if (typeGrid[x][y] == type)
										potentials.add(new int[] {x, y});
							}
					}
					System.out.println("");
			}

			for (int i = -1; i <= 1; i++){
					for (int j = -1; j <= 1; j++){
						x = Math.floorMod(x0 + i, size);
						y = Math.floorMod(y0 + j, size);
						System.out.printf("%d ", idGrid[x][y]);
					}
					System.out.println("");
			}

			if (potentials.isEmpty())
					return null;
			else{
					Collections.shuffle(potentials);
					if (true){
							int[] testpos = potentials.get(0);
							int xt = testpos[0];
							int yt = testpos[1];
							int t = typeGrid[xt][yt];
							System.out.printf("Returning %d at (%d, %d) for %d cantered at (%d, %d). ID = %d\n", t, xt, yt, type, x0, y0, idGrid[xt][yt]);
					}
					return potentials.get(0);
			}

	}

}
