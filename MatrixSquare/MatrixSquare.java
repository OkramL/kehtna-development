public class MatrixSquare {
	public static void main(String[] args) {
		int size = 3;
		if(args.length == 1) {
			size = Integer.parseInt(args[0]);
		}
		MatrixSquare app = new MatrixSquare(size);
	}
	
	public MatrixSquare(int size) {
		int[][] matrix = genMatrix(size);
		showMatrix(matrix);
	}
	
	private int[][] genMatrix(int size) {
		int[][] matrix = new int[size][size];
		for(int i = 0; i < size; i++) {
			for(int j = 0; j < size; j++) {
				if(i == j) {
					matrix[i][j] = 1;
				} else if(i > j) {
					matrix[i][j] = 0;
				} else {
					matrix[i][j] = 2;
				}
			}
		}
		return matrix;
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
}