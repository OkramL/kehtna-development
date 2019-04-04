
import java.io.IOException;
import 
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;


/**
 *
 * @author Marko
 */
public class NavalBattle3 implements Runnable {

    private int boardSize = 4;
    private int shipsCount = (int) ((boardSize * boardSize) / 2);
    private final int[][] userBoard;	// User see
    private final int[][] gameBoard;	// PC known
    private final char[] topLine = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J'};
    private final long start = System.currentTimeMillis();
    private boolean wait = true;
    private int points = 0;
    private String hitOrMissed = "";

    public NavalBattle3(String[] args) {
        if (args.length == 2) {
            if (isInteger(args[0]) && isInteger(args[1])) {
                boardSize = Integer.parseInt(args[0]);
                shipsCount = Integer.parseInt(args[1]);
            }
        } else if (args.length == 1) {
            if (isInteger(args[0])) {
                boardSize = Integer.parseInt(args[0]);
            }
        }
        userBoard = new int[boardSize][boardSize];
        gameBoard = new int[boardSize][boardSize];
        for (int[] row : userBoard) { // UserBoard all sea (zeros)
            Arrays.fill(row, 0);
        }
        genBoard(); // PC knows board
        //showBoard(userBoard); // Show User game board
    }

    private boolean isInteger(String str) {
        try {
            int a = Integer.parseInt(str);
            return true;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }

    private void genBoard() {
        int shipsCounter = 0;
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                if ((getRandomBetween(1, (boardSize / 2)) % 2) == 0) {
                    if (shipsCounter < shipsCount) {
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
        for (int i = 0; i < cols; i++) {
            if (i == 0) {
                System.out.print("x " + topLine[i] + " ");
            } else {
                System.out.print(topLine[i] + " ");
            }
        }
        System.out.println();
        for (int i = 0; i < rows; i++) {
            System.out.print(i + " "); // Line number left size of board			
            for (int j = 0; j < cols; j++) {
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

        if (seconds.length() < 2) {
            seconds = "0" + seconds;
        }

        if (minutes.length() < 2) {
            minutes = "0" + minutes;
        }

        if (hours.length() < 2) {
            hours = "0" + hours;
        }

        return hours + ":" + minutes + ":" + seconds;
    }

    private void playGame() {
        Scanner scanner = new Scanner(System.in);
        while (wait) {
			CLS();
            showInfo();
            showBoard(userBoard);
            System.out.print("Your move (like B2) > ");
            String userInput = scanner.next();
            if (userInput.equalsIgnoreCase("exit")) {
				System.out.println("Stopping... Playing time: " + getTime());
                System.out.println("Bye, bye");
                System.exit(0);
            } else if(userInput.equalsIgnoreCase("backdoor")) { 
				showBoard(gameBoard);
				System.exit(0);
			} else {
                if (userInput.length() == 2) {// check user input
                    char ch = Character.toUpperCase(userInput.charAt(0));
                    int nr = Integer.parseInt(userInput.substring(1, 2));
                    wait = shooting(ch, nr);
                    //showBoard(userBoard);   // Test
                    //showBoard(gameBoard); // Test
                } else {
                    hitOrMissed = "Try again!";
                }
            }
        }
        System.out.println("Game over! Total points: " + points);
        System.out.println("Stopping... Playing time: " + getTime());
		System.out.println("Game board was");
		showBoard(gameBoard);		
    }

    private boolean shooting(char ch, int nr) {
        boolean charExists = false;
        boolean lineNrExists = false;
        int colNr = 0;

        for (int i = 0; i < topLine.length; i++) {
            if (ch == topLine[i]) {
                charExists = true;
                colNr = i;
                break;
            }
        }

        if (nr < boardSize) {
            lineNrExists = true;
        }

        if (charExists && lineNrExists) {
            if (gameBoard[nr][colNr] == 1 && userBoard[nr][colNr] != 1) {
                userBoard[nr][colNr] = 1;
                points++;
                hitOrMissed = "Hit!";
                //System.out.println("Hit! Points: " + points);
                //System.out.println("Time " + getTime());
                boolean ok = compareArrays(userBoard, gameBoard);
                if (ok) {
                    return false; // Game Over
                }
            } else {
                hitOrMissed = "Missed!";
                //System.out.println("Missed! Points: " + points);
                //System.out.println("Time " + getTime());
            }
        } else {
            hitOrMissed = "Toooo long";
            //System.out.println("Shooting over sea :)");
            //System.out.println("Time " + getTime());
        }
        return true;
    }

    private boolean compareArrays(int[][] one, int[][] two) {
        boolean different = true;
        for (int i = 0; i < one.length; i++) {
            for (int j = 0; j < one[0].length; j++) {
                if (one[i][j] != two[i][j]) {
                    different = false;
                    break;
                }
            }
        }
        return different;
    }

    private void showInfo() {
        System.out.println("+---------------------+");
        int len = hitOrMissed.length();
        System.out.print("| Target: " + hitOrMissed);
        for (int i = 0; i < (12 - len); i++) {
            System.out.print(" ");
        }
        System.out.println("|");
        
        len = String.valueOf(len).length();
        System.out.print("| Points: " + points);
        for (int i = 0; i < (12 - len); i++) {
            System.out.print(" ");
        }
        System.out.println("|");
        
        len = getTime().length();
        System.out.print("|   Time: " + getTime());
        for (int i = 0; i < (12 - len); i++) {
            System.out.print(" ");
        }
        System.out.println("|");
        
        System.out.println("+---------------------+");
        //System.out.println("|                     |");
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        NavalBattle3 nb3 = new NavalBattle3(args);
        nb3.playGame();
    }

	private void CLS() {
		new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();		
	}
    @Override
    public void run() {
        try {
            //System.out.println("Starting " + getTime());            
            playGame();
            Thread.sleep(1000);
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }
    }
}
