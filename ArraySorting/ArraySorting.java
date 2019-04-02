/**
 * Random integer generator result into array 
 * Sort array
 * show min and max number after sorting
 * Sorting both side (ASC DESC?)
 *
*/
import java.util.Random;

public class ArraySorting{
	static int min, max, howMany;	
	
	public static void main(String[] args) {
		if(args.length == 3) {
			if(isInteger(args[0]) && isInteger(args[1]) && isInteger(args[2])) {
				min = Integer.parseInt(args[0]);
				max = Integer.parseInt(args[1]);
				howMany = Integer.parseInt(args[2]);				
				int[] rndNumbers = new int[howMany];
				for(int i = 0; i < howMany; i++){
					rndNumbers[i] = getRandomBetween(min, max);
				}
				
			} else {
				System.out.println("One or multiple parameter(s) is not integer.");	
			}
		} else {
			System.out.println("Must be three integer numbers. MIN MAX HOWMANY");
		}
	}
	
	private static boolean isInteger(String str) {
		try {
			int a = Integer.parseInt(str);
			return true;
		} catch(NumberFormatException nfe) {
			return false;
		}
	}
	
	private static int getRandomBetween(int min, int max) {
		Random random = new Random();
		int rndNr = random.nextInt((max - min) + 1) + min;
		return rndNr;
	}
	
	private static int[] sortArray(int[] rndNumbers) {
		for(int i = 0; i < rndNumbers.length()-1; i++) {
			for(int j = i+1; j = ) {
				
			}
		}
	}
}