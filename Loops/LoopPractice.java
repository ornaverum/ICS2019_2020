import java.util.Scanner;
//~ import java.util.Math;

import java.util.Random; 
import java.awt.Color;
import java.util.Arrays;

public class LoopPractice{
	public static void main(String[] args){
		int[] a = {1, 2, 3, 4, 5};
		// int[] b = a;  // alias
		int[] b = Arrays.copyOf(a, 3);
		//int[] b = copy(a);
		
		printArr(a);
		printArr(b);
		
		System.out.println(a);
		
		a[2] = 7;
		printArr(a);
		printArr(b);
		System.out.println(b);
		
		
		
		
		//~ int A = 7;
		//~ int B = A;
		//~ System.out.printf("%d, %d\n", A, B);
		//~ A = 10;
		//~ System.out.printf("%d, %d", A, B);
	}
	
	public static void printArr(int[] arr){
		for (int i:arr){
			System.out.printf("%4d", i);
		}
		System.out.println();
	}
	
	public static int[] copy(int[] a){
		int len = a.length;
		int[] b = new int[len];
		for (int i = 0; i<len; i++){
			b[i] = a[i];
		}
		return b;
	}
	
}
