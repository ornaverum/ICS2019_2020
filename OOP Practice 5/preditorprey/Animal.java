package preditorprey;

public class Animal{

	// instance variables
	
	// how hungry it is - 
		// energy
		
	// when to reproduce
		// energy reproduction threshold
		
	// position
	public int x, y;
	

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

	

}
