
import java.util.Arrays;

public class Str{
	public static void main(String[] args){
		String name1 = new String("Bob");
		String name2 = new String("Bob");
		
		if (name1 == name2){
			System.out.printf("%s and %s are the same.", name1, name2);
		} else {
			System.out.printf("%s and %s are different.", name1, name2);
		}
		
		
		
		//~ char letter;
		
		//~ letter = name1.charAt(1);
		
		//~ System.out.println(letter);
		
		//~ for(int i=0; i<name1.length(); i++){
			//~ System.out.println(name1.charAt(i));
		//~ }
		
		
		//~ for(char c: name2.toCharArray()){
			//~ System.out.println(c);
		//~ }
		
		//~ System.out.print("Roman alphabet: ");
		//~ for (char c = 'a'; c <= 'z'; c++) {
			//~ System.out.print(c);
		//~ }
		
		//~ for (char c = 'A'; c <= 'Z'; c++) {
			//~ System.out.print(c);
		//~ }
		//~ System.out.println();
		
		//~ System.out.print("Greek alphabet: ");
		//~ for (int i = 913; i <= 937; i++) {
			//~ System.out.print((char) i);
		//~ }
		//~ System.out.println();
		
		
		//~ if(name1.equals(name2)){
			//~ System.out.printf("%s and %s are the same\n", name1, name2);
		//~ } else {
			//~ System.out.printf("%s and %s are not the same\n", name1, name2);
		//~ }
		
		
		//~ if(name1.compareTo(name2)<0){
			//~ System.out.printf("%s comes before %s", name1, name2);
		//~ } else if(name1.compareTo(name2)>0){
			//~ System.out.printf("%s comes before %s", name2, name1);
		//~ } else {
			//~ System.out.printf("%s and %s are the same\n", name1, name2);
		//~ }
		
		//~ String name = "Nicolai Copernicus";
		//~ System.out.println(name.toUpperCase());
		//~ System.out.println(name);
		
		//~ String warning = "The robot apocalypse is coming.";
		//~ System.out.println(warning);
		//~ warning = warning.replace("robot apocalypse", "robopocalypse");
		//~ System.out.println(warning);
		
		//~ String book = "Harry Potter and the Unbearable Lightness of Being";
		//~ System.out.println(book.substring(0));
		
		//~ char let = 'a';
		//~ System.out.println(book.indexOf(let));
		//~ System.out.println(book.indexOf(let,2));
		
		
		//~ System.out.println(Arrays.toString(args));

		//~ int sum = 0;
		//~ for(String arg:args){
			//~ sum += Integer.parseInt(arg);
		//~ }
		//~ System.out.printf("The total was %d\n", sum);
		
	}
}
