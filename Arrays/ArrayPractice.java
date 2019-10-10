import java.util.Arrays;

public class ArrayPractice{
	public static void main(String[] args){
		
		int len = 10;
		int[] a = new int[len]; 
		
		for (int i: a){
			System.out.println(i);
		}
		
		//~ int[] a = {1, 2, 3, 4, 5};
		//~ printArray(a, "a");
		
		//~ int[] b = a;
		//~ printArray(b, "b");
		
		//~ //b[3] = 10;
		
		//~ b = Arrays.copyOf(a, a.length);
		//~ b[3] = 10;
		
		//~ printArray(b, "b again");
		//~ printArray(a, "a again");
		

	}
	
	public static void printArray (int[] arr, String name){
		System.out.print(name + " = ");
		for (int i : arr){
			System.out.print(i + " ");
		}
		System.out.println();
	}
	
	//~ public static void main(String[] args){
		//~ for(String arg:args){
			//~ System.out.println(arg);
		//~ }
	//~ }
}



