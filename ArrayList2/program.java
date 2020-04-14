import java.util.*;
import java.lang.Math;

public class program{
	public static void main(String[] args){

		int[] list = new int[0];
		for (int i = 0; i < 100; i++) {
			list = append(list, (int) Math.random()*100);
		}
		
		
		
	}
	
	public static int[] append( int[] list, int value)
	{
		int[] newList = new int[list.length+1];
		for(int i = 0; i < list.length; i++){
			newList[i] = list[i];
		}
		newList[list.length+1] = value;
		
		return newList;
	}
	
	
}


class Square
{
	// instance variables
	double length;
	
	// methods
	
	public double area(){return this.length*this.length;}
	
	public void setLength(double length){this.length = length;}
	
	public double getLength() {return this.length;}
	
	// constructor;
	public Square(double length){
		this.length = length;
	}
	
	public Square(){
		this.length = 0;
	}
	
	public String toString(){
		return String.format("Area = %.2f\n", area());
	}
	
}
