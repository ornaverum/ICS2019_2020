/**
 * @author Jack Waddell
 * 
 */
import java.util.Scanner; 
public class Program{
	public static void main(String[] args){
		int count = 0;
		//~ printHi();
		
		Scanner in = new Scanner(System.in);
		System.out.println("What Fib do you want? ");
		int fibNum = in.nextInt();
		
		System.out.println("The fib is: " + fib(fibNum));
	}
	
	public static void printHi(){
		System.out.println("Hi!");
		printHi();
	}
	
	public static void printHi(int count){
		count++;
		System.out.println("Hi! :" + count);
		printHi(count);
	}
	
	
	public static int fib(int num){
		if (num <= 0){
			throw new IllegalArgumentException();
		} else if (num == 1 || num ==2){
			return 1;
		} else {
			int sum = fib(num-1)+fib(num-2);
			return sum;
		}
	}
	
	// ==, !=, <, >, <=, >=
	
	
	
}
