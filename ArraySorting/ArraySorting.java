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
	static String how;
	
	public static void main(String[] args) {
		if(args.length == 4) {
			if(isInteger(args[0]) && isInteger(args[1]) && isInteger(args[2])) {
				min = Integer.parseInt(args[0]);
				max = Integer.parseInt(args[1]);
				howMany = Integer.parseInt(args[2]);				
				how = args[3];
				int[] rndNumbers = new int[howMany];
				System.out.print("Original: ");
				for(int i = 0; i < howMany; i++){
					rndNumbers[i] = getRandomBetween(min, max);
					System.out.print(rndNumbers[i] + " ");
				}
				System.out.println();
				System.out.print("Sorted:   ");
				rndNumbers = bubbleSort(rndNumbers, how);
				
				for(int i = 0; i < rndNumbers.length; i++){
					System.out.print(rndNumbers[i] + " ");
				}
			} else {
				System.out.println("One or multiple parameter(s) is not integer.");	
			}
		} else {
			System.out.println("Must be three integer numbers. MIN MAX HOWMANY and one string ASC/DESC");
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
	
	/**
	* https://www.tutorialspoint.com/java-program-to-implement-bubble-sort
	*
	*/
	private static int[] bubbleSort(int[] arr, String howSort) {
		int n = arr.length;
		int temp = 0;

		for(int i = 0; i < n; i++) {
			for(int j = 1; j < (n-i); j++) {
				if(howSort.equalsIgnoreCase("asc")) {
					if(arr[j-1] > arr[j]) {
						temp = arr[j-1];
						arr[j-1] = arr[j];
						arr[j] = temp;
					}
				} else if(howSort.equalsIgnoreCase("desc")) {
					if(arr[j-1] < arr[j]) {
						temp = arr[j-1];
						arr[j-1] = arr[j];
						arr[j] = temp;
					}
				}				
			}
		}
		return arr;
	}
}