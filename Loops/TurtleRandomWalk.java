import java.util.Scanner;
//~ import java.util.Math;

import java.util.Random; 
import java.awt.Color;

public class TurtleRandomWalk{
	public static void main(String[] args){
		Turtle[] tmnt = new Turtle[4];
		Random rand = new Random();
		Double dir;
		
		//~ for (int i = 0; i<tmnt.length; i++){
			//~ tmnt[i] = new Turtle();
		//~ }
		
		//~ for (Turtle i: tmnt){
			//~ System.out.println(i);
		//~ }
		
		
		
		for (Turtle t: tmnt){
			int r, g, b;
			r = rand.nextInt(255);
			g = rand.nextInt(255);
			b = rand.nextInt(255);
			t.penColor(new Color(r,g,b));
		}
		
		for (int i = 0; i < 50; i++){
			for(Turtle t: tmnt){
				dir = 360*rand.nextDouble();
				t.left(dir);
				t.forward(100);
			}
			tmnt[0].zoomFit();
		}
		
	}
}
