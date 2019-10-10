import java.util.Random; 
public class Practice {
	public static void main(String[] args){
		
		//printTable(17, 17);
		
		int[] nums = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
		
		for(int i: nums){
			System.out.println(i);
		}
		
	}
	
	public static void printTable(int row, int col){
		int m = 1;
		while(m <= row){
			printRow(m, col);
			m++;
			System.out.println();
		}
	}
	
	public static void printRow(int m, int col){
		int n = 1;
		while(n <= m){
			System.out.printf("%6d", m*n);
			n++;
		}
	}
}



