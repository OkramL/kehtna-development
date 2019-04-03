import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class NavalBattle implements Runnable {
	
	private int boardSize = 4;
	private int shipsCount = (int) ((boardSize * boardSize) / 2);
	private int[][] userBoard;	// User see
	private int[][] gameBoard;	// PC known
	private char[] topLine = new char[]{'A','B','C','D','E','F','G','H','I','J'};
	private int points = 0;
	private long c = 0;
	private boolean wait = true;
	private long start = System.currentTimeMillis();
		
	public static void main(String[] args) {
		if(args.length == 2) { // Board size and ships count
			NavalBattle app = new NavalBattle(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
			Thread thread = new Thread(app);
			thread.start();
		} else if(args.length == 1) { // Board size but ships half board size
			if(Integer.parseInt(args[0]) > 10) {
				System.out.println("Max size 10 x 10. Use default value!\n");
				NavalBattle app = new NavalBattle(4, 8);
				Thread thread = new Thread(app);
				thread.start();
			} else {
				NavalBattle app = new NavalBattle(Integer.parseInt(args[0]), (int)((Integer.parseInt(args[0])*Integer.parseInt(args[0]))/2));
				Thread thread = new Thread(app);
				thread.start();
			}
		} else { // Default 4x4 and 8 ships
			NavalBattle app = new NavalBattle(4, 8);
			Thread thread = new Thread(app);
			thread.start();
		}		
	}
	
	@Override
	public void run() {
		try {
			System.out.println("Starting " + getTime());
			playGame();
			Thread.sleep(1000);
		} catch (InterruptedException ie) {
			ie.printStackTrace();
		}		
	}
	
	public NavalBattle(int boardSize, int shipsCount) {		
		userBoard = new int[boardSize][boardSize];
		gameBoard = new int[boardSize][boardSize];
		for (int[] row: userBoard) { // UserBoard all sea (zeros)
			Arrays.fill(row, 0);
		}			
		genBoard();
		showBoard(userBoard); // Test
		//showBoard(gameBoard); // Test
		//playGame();
	}
	
	private void playGame() {
		Scanner scanner = new Scanner(System.in);
		while(wait) {
			System.out.print("Your move (like B2) > ");
			String userInput = scanner.next();
			if(userInput.equalsIgnoreCase("exit")) {
				System.out.println("Bye, bye");
				System.exit(0);
			} else {
				
				if(userInput.length() == 2) {// get user input
					char ch = Character.toUpperCase(userInput.charAt(0));
					int nr = Integer.parseInt(userInput.substring(1, 2));
					wait = shooting(ch, nr);
					showBoard(userBoard); // Test
					//showBoard(gameBoard); // Test
					
				} else {
					System.out.println("Try again!");	
				}
			}
		}
		System.out.println("Game over! Total points: " + points);
		System.out.println("Stopping... " + getTime());
	}
	
	private boolean shooting(char ch, int nr) {
		boolean charExists = false;
		boolean lineNrExists = false;		
		int colNr = 0;
		
		for(int i = 0; i < topLine.length; i++) {
			if(ch == topLine[i]) {
				charExists = true;
				colNr = i;
				break;
			}
		}
		
		if(nr < boardSize) {
			lineNrExists = true;
		}
		
		if(charExists && lineNrExists) {
			if(gameBoard[nr][colNr] == 1 && userBoard[nr][colNr] != 1) {				
				userBoard[nr][colNr] = 1;
				points++;
				System.out.println("Hit! Points: " + points);
				System.out.println("Time " + getTime());
				boolean ok = compareArrays(userBoard, gameBoard);
				if(ok) {					
					return false; // Game Over
				} 
			} else {
				System.out.println("Missed! Points: " + points);
				System.out.println("Time " + getTime());
			}
		} else {
			System.out.println("Shooting over sea :)");
			System.out.println("Time " + getTime());
		}
		return true;		
	}
	
	private boolean compareArrays(int[][] one, int[][] two) {
		boolean different = true;
		for(int i = 0; i < one.length; i++) {
			for(int j = 0; j < one[0].length; j++) {
				if(one[i][j] != two[i][j]) {
					different = false;
					break;
				}
			}	
		}
		return different;
	}
	
	/**
	 * https://stackoverflow.com/questions/3491027/java-console-code-for-stopwatch-timer
	 * 
	*/
	private String getTime() {
		long elapsedTime = System.currentTimeMillis() - start;
		elapsedTime = elapsedTime / 1000;

		String seconds = Integer.toString((int) (elapsedTime % 60));
		String minutes = Integer.toString((int) ((elapsedTime % 3600) / 60));
		String hours = Integer.toString((int) (elapsedTime / 3600));

		if (seconds.length() < 2)
			seconds = "0" + seconds;

		if (minutes.length() < 2)
			minutes = "0" + minutes;

		if (hours.length() < 2)
			hours = "0" + hours;

		return minutes + ":" + seconds;
	}
	
	private void genBoard() {		
		int shipsCounter = 0;
		for(int i = 0; i < boardSize; i++) {
			for(int j = 0; j < boardSize; j++) {
				if((getRandomBetween(1, (boardSize/2)) % 2) == 0) {
					if(shipsCounter < shipsCount) {
						gameBoard[i][j] = 1; // Ship	
						shipsCounter++;	
					}					
				} else {
					gameBoard[i][j] = 0; // Empty aka sea
				}				
			}	
		}		
	}
	
	private void showBoard(int[][] board) {
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