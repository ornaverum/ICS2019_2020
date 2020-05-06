package predatorprey;
import java.util.ArrayList;
import java.util.ArrayList.*;
import java.util.Random;
import java.util.Collections;



public class Ecosystem{

	// Instance variables
	public int size;  // Square
	public double animalDensity;
	public ArrayList<Animal> animalList;
	public ArrayList<Animal> deathList;
	public ArrayList<Animal> birthList;
	public int[][] drawGrid;  	// 0 for no Animal, 1 for Animal.
	
	
	// Constructor
	public Ecosystem(){
		size = 0;
		animalList = new ArrayList<>();
		birthList = new ArrayList<>();
		deathList = new ArrayList<>();
		drawGrid = new int[size][size];
		animalDensity = 0;
	}
	
	public Ecosystem(int size){
		this.size = size;
		animalList = new ArrayList<>();
		birthList = new ArrayList<>();
		deathList = new ArrayList<>();
		animalDensity = 0;
		drawGrid = new int[size][size];
		populate();
		updateGrid();
		
	}
	
	public Ecosystem(int size, double rho){
		this.size = size;
		animalList = new ArrayList<>();
		birthList = new ArrayList<>();
		deathList = new ArrayList<>();
		drawGrid = new int[size][size];
		animalDensity = rho;
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
					Prey a = new Prey(i,j);
					a.eco = this;
					a.energy = 10;
					animalList.add(a);
				} else if (r < animalDensity){
					Predator a = new Predator(i,j);
					a.eco = this;
					a.energy = 10;
					animalList.add(a);
				}
			}
		}
	}
	
	
	
	public void update(){
		Collections.shuffle(animalList);
		for(Animal a:animalList){
			a.act();
		}
		for(Animal a:deathList){
			animalList.remove(a);
		}
		animalList.addAll(birthList);
		birthList.clear();
		deathList.clear();
		
		updateGrid();
	}
	
	
	public void updateGrid(){
		int[] pos;
		for (int i = 0; i < size; i++){
			for (int j = 0; j< size; j++){
				drawGrid[i][j]=0;
			}
		}
		for(Animal a:animalList){
			pos = a.getPos();
			if (a instanceof Prey)					// if Prey
				drawGrid[pos[0]][pos[1]] = 1;		// if Prey

			else if (a instanceof Predator) 		// if Predator
				drawGrid[pos[0]][pos[1]] = 2;		// if Predator
			else
				continue;
		}
	}
	
	public void setAnimalDensity(double p){this.animalDensity = p;}
	
	public ArrayList<Prey> getAdjacentPrey(int x, int y){
		// Adjacent coords
		// xn = x -1, 0, +1
		// yn = y -1, 0, +1
		// account for wrapping?
		
		// one predator at, say (10, 11)
		
		ArrayList<Prey> targets = new ArrayList<>();
		
		int[] pos;
		for(Animal a:animalList){
			if (a instanceof Prey){
				pos = a.getPos();
				xn = a.x; yn = a.y;
				// pos[0] = xn; pos[1] = yn;
				// check to see if it's a neighbor of Predator at (x,y)
				// if it is...
				targets.add(a);
			}
		}
		
		return targets;
		
	}
	
	
	

}
