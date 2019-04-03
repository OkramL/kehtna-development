import java.util.Random;

public class MatrixApp {
	public int[][] matrix = new int[3][2]; // Look main method numbers to!
	
	public static void main(String[] args) {
		MatrixApp app = new MatrixApp(3, 2);		
	}
	
	public MatrixApp(int rows, int cols) {		
		matrix = getMyList(rows, cols);
		for(int i = 0; i < rows; i++) {
			for(int j = 0; j < cols;  j++) {
				System.out.print(matrix[i][j] + " ");
			}
			System.out.println();
		}
		sortMatrix(matrix);
	}
	
	private void sortMatrix(int[][] matrix) {
		int[] allNumbers = new int[(matrix.length * matrix[0].length)];
		int rows = matrix.length;
		int cols = matrix[0].length;
		int k = 0;
		for(int i = 0; i < rows; i++) {
			for(int j = 0; j < cols;  j++) {
				allNumbers[k] = matrix[i][j];				
				//System.out.print(allNumbers[k] + " ");
				k++;
			}			
		}
		int[] oneMatrix = bubbleSort(allNumbers);
		matrix = new int[rows][cols];
		k = 0;
		for(int i = 0; i < rows; i++) {
			for(int j = 0; j < cols;  j++) {
				matrix[i][j] = allNumbers[k];		
				k++;
			}			
		}
		System.out.println();
		for(int i = 0; i < rows; i++) {
			for(int j = 0; j < cols;  j++) {
				System.out.print(matrix[i][j] + " ");
			}
			System.out.println();
		}
		
	}
	
	private int[][] getMyList(int rows, int cols) {
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
	private static int[] bubbleSort(int[] arr) {
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
}