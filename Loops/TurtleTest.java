
/**
 * 
 * @author 	Jack Waddell
 */

import java.util.Scanner;
public class TurtleTest{
		
		public static void main(String[] args){
			double size;	
			Scanner input = new Scanner(System.in);
			System.out.println("How big a square would you like?");
			size = input.nextDouble();
			turtleSquare(size);
		}
		
		/**
		 * Draws a square using the Turtle class
		 * 
		 * @param 	turtleSize: the length of a side of the square the turtle draws
		 * @return	null
		 */
		public static void turtleSquare(double turtleSize){
			Turtle don;
			don = new Turtle();
			don.forward(turtleSize);
			don.left(90);
			don.forward(turtleSize);
			don.left(90);
			don.forward(turtleSize);
			don.left(90);
			don.forward(turtleSize);
		}	
}
