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
	public int[][] worldGrid;


	// Constructor
	public Ecosystem(){
		size = 0;
		animalList = new ArrayList<>();
		birthList = new ArrayList<>();
		killList = new ArrayList<>();
		drawGrid = new int[size][size];
		animalDensity = 0;
	}

	public Ecosystem(int size){
		this.size = size;
		animalList = new ArrayList<>();
		birthList = new ArrayList<>();
		killList = new ArrayList<>();
		animalDensity = 0;
		drawGrid = new int[size][size];
		populate();
		// updateGrid();

	}

	public Ecosystem(int size, double rho){
		this.size = size;
		animalList = new ArrayList<>();
		birthList = new ArrayList<>();
		killList = new ArrayList<>();
		drawGrid = new int[size][size];
		animalDensity = rho;
		populate();
		// updateGrid();
	}

	//Methods
	public void populate(){
		double r;
		for (int i = 0; i < size; i++){
			for (int j = 0; j< size; j++){
				r = Math.random();
				//~ System.out.printf("%f, %f\n", r, animalDensity);
				if(r < animalDensity/2.0){
					Preditor a = new Preditor(i,j);
					a.eco = this;
					a.speed = 1;
					a.energy = 10;
					animalList.add(a);
				} else if (r < animalDensity ){
					Prey a = new Prey(i,j);
					a.eco = this;
					a.speed = 1;
					a.energy = 10;
					animalList.add(a);
				}
			}
		}
	}


	public void update(){
			Collections.shuffle(animalList);
			for (Animal a: animalList){
					if (a instanceof Preditor)
						((Preditor) a).act();
					else if (a instanceof Prey)
						((Prey) a).act();
			}
			for (Animal a: killList){
				animalList.remove(a);
			}
			animalList.addAll(birthList);
			birthList.clear();
			killList.clear();
	}



	// public void updateGrid(){
	// 	int[] pos;
	// 	for (int i = 0; i < size; i++){
	// 		for (int j = 0; j< size; j++){
	// 			drawGrid[i][j]=0;
	// 		}
	// 	}
	//
	// 	for(Animal a:animalList){
	// 		pos = a.getPos();
	// 		drawGrid[pos[0]][pos[1]] = 1;
	// 	}
	// }

	public void setAnimalDensity(double p){this.animalDensity = p;}



}
