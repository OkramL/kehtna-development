import java.util.Random;

public class MatrixApp2 {
	/**
	 * Done
	*/
	public static void main(String[] args) {
		int rows = 3;
		int cols = 2;
		if(args.length == 2) {
			rows = Integer.parseInt(args[0]);
			cols = Integer.parseInt(args[1]);
		}
		MatrixApp2 app = new MatrixApp2(rows, cols);		
	}
	
	/**
	 * Constructor
	 * 
	*/
	public MatrixApp2(int rows, int cols) {		
		int[][] matrix = new int[rows][cols];
		matrix = genMyList(rows, cols);
		// Test SHOW
		showMatrix(matrix);
		// LETS sort matrix
		matrix = sortMatrix(matrix);
		
	}
	
	private int[][] sortMatrix(int[][] matrix) {
		// Get all Matrix numbers into 1D Array :)
		int rows = matrix.length;
		int cols = matrix[0].length;
		int[] allNumbers = get2DInto1D(matrix);
		allNumbers = bubbleSort(allNumbers); // sort 1D array
		// Show array
		showArray(allNumbers); // show 1D array
		matrix = put1DInto2D(allNumbers, rows, cols); // 
		showMatrix(matrix);
		return matrix;
	}
	
	private int[][] genMyList(int rows, int cols) {
		int[][] mylist = new int[rows][cols];
		for(int i = 0; i < rows; i++) {
			for(int j = 0; j < cols;  j++) {
				mylist[i][j] = getRandomBetween(10, 99);
			}
		}
		return mylist;
	}
	
	private int getRandomBetween(int min, int max) {
		Random random = new Random();
		int rndNr = random.nextInt((max - min) + 1) + min;
		return rndNr;
	}
	
	private int[] bubbleSort(int[] arr) {
		int n = arr.length;
		int temp = 0;

		for(int i = 0; i < n; i++) {
			for(int j = 1; j < (n-i); j++) {				
				if(arr[j-1] > arr[j]) {
					temp = arr[j-1];
					arr[j-1] = arr[j];
					arr[j] = temp;
				}							
			}
		}
		return arr;
	}
	
	private void showMatrix(int[][] matrix) {
		int rows = matrix.length;
		int cols = matrix[0].length;
		for(int i = 0; i < rows; i++) {
			for(int j = 0; j < cols;  j++) {
				System.out.print(matrix[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}
	
	private void showArray(int[] oneDimensionArray) {
		for(int i = 0; i < oneDimensionArray.length; i++) {
			System.out.print(oneDimensionArray[i] + " ");
		}
		System.out.println("\n");
	}
	
	/**
	 * 2D into 1D array
	 * 
	*/
	private int[] get2DInto1D(int[][] matrix) {
		int[] allNumbers = new int[(matrix.length * matrix[0].length)];
		int rows = matrix.length;
		int cols = matrix[0].length;
		int k = 0;
		for(int i = 0; i < rows; i++) {
			for(int j = 0; j < cols;  j++) {
				allNumbers[k] = matrix[i][j];				
				//allNumbers[(rows * i) + j] = matrix[i][j]; // wierd not working
				k++;
			}			
		}
		return allNumbers;
	}
	
	/**
	 * 1D array into 2D array
	 *
	*/
	private int[][] put1DInto2D(int[] oneDimensionArray, int rows, int cols) {
		int[][] matrix = new int[rows][cols];		
		int k = 0;
		for(int i = 0; i < rows; i++) {
			for(int j = 0; j < cols;  j++) {
				//allNumbers[k] = matrix[i][j];				
				matrix[i][j] = oneDimensionArray[k];
				k++;
			}			
		}
		return matrix;
	}
}