import java.util.Random;
import java.util.Scanner;

public class BombingShips {
	private int boardSize = 4;
	private int shipsCount =(int) ((boardSize * boardSize) / 2);
	private int[][] board; 			// Real data
	private int[][] boardPlayer; 	// Player board
	private char[] topLine = new char[]{'A','B','C','D','E','F','G','H','I','J'};
	
	public static void main(String[] args) {		
		if(args.length == 2) { // Board size and ships count
			BombingShips app = new BombingShips(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
		} else if(args.length == 1) { // Board size but ships half board size
			BombingShips app = new BombingShips(Integer.parseInt(args[0]), (int)((Integer.parseInt(args[0])*Integer.parseInt(args[0]))/2));
		} else { // Default 4x4 and 8 ships
			BombingShips app = new BombingShips(4, 8);
		}		
	}
	
	public BombingShips(int boardSize, int shipsCount) {
		this.boardSize = boardSize;
		this.shipsCount = shipsCount;
		this.board = new int[boardSize][boardSize];
		genBoard();
		showBoard();
		playGame();
	}
	
	private void playGame() {
		boolean wait = true;
		Scanner scanner = new Scanner(System.in);
		while(wait) {
			System.out.print("Your move (like B2) > ");
			String user = scanner.next();
			if(user.equalsIgnoreCase("exit")) {
				System.out.println("Bye, bye");
				System.exit(0);
			} else {
				System.out.println("Let's shoot again");
			}
		}
	}
	
	private void genBoard() {
		int shipsCounter = 0;
		for(int i = 0; i < boardSize; i++) {
			for(int j = 0; j < boardSize; j++) {
				if((getRandomBetween(1, (boardSize/2)) % 2) == 0) {
					if(shipsCounter < shipsCount) {
						board[i][j] = 1; // Ship	
						shipsCounter++;	
					}					
				} else {
					board[i][j] = 0; // Empty aka sea
				}				
			}	
		}		
	}
	
	private void showBoard() {
		int rows = board.length;
		int cols = board[0].length;
		/**
		 * Draw A B C etc
		*/
		for(int i = 0; i < cols; i++) {
			if(i == 0) {
				System.out.print("  " + topLine[i] + " ");
			} else {
				System.out.print(topLine[i] + " ");
			}
		}
		System.out.println();
		for(int i = 0; i < rows; i++) {
			System.out.print(i + " "); // Line number left size of board			
			for(int j = 0; j < cols;  j++) {				
				System.out.print(board[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}
	
	private int getRandomBetween(int min, int max) {
		Random random = new Random();
		int rndNr = random.nextInt((max - min) + 1) + min;
		return rndNr;
	}
	
}