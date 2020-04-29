package preditorprey;
import java.util.ArrayList;
import java.util.ArrayList.*;
import java.util.Random;



public class Ecosystem{

	// Instance variables
	public int size;  // Square
	public double animalDensity;
	public ArrayList<Animal> animalList;
	public int[][] drawGrid;  	// 0 for no Animal, 1 for Animal.


	// Constructor
	public Ecosystem(){
		size = 0;
		animalList = new ArrayList<>();
		drawGrid = new int[size][size];
		animalDensity = 0;
	}

	public Ecosystem(int size){
		this.size = size;
		animalList = new ArrayList<>();
		animalDensity = 0;
		drawGrid = new int[size][size];
		populate();
		// updateGrid();

	}

	public Ecosystem(int size, double rho){
		this.size = size;
		animalList = new ArrayList<>();
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
				if(r < animalDensity){
					Animal a = new Animal(i,j);
					a.eco = this;
					animalList.add(a);
				}
			}
		}
	}


	public void update(){
			for (Animal a: animalList){
					a.act();
			}
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
