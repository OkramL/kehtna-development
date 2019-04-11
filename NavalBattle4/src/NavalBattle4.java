import java.io.*;
import java.util.*;


/**
 * See ei läinud algul ülesse
 * @author Marko Livental
 */
public class NavalBattle4 implements Runnable {

    private int boardSize = 4;
    private int shipsCount = ((boardSize * boardSize) / 2);
    private final int[][] userBoard;    // User see
    private int[][] gameBoard;    // PC known
    private final char[] topLine = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J'};
    private final long start = System.currentTimeMillis();
    private boolean wait = true;
    private int points = 0;
    private String hitOrMissed = "";
    private static final String PROGRESS_FILENAME = "progress.txt";
    private char ch;

    private NavalBattle4(String[] args) {
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
        for (int[] row : userBoard) { // userBoard all sea (zeros)
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
        for (int i = 0; i < boardSize; i++) { // GameBoard all sea (zeros)
            for (int j = 0; j < boardSize; j++) {
                if (shipsCounter < shipsCount) {
                    gameBoard[i][j] = 1;
                    shipsCounter++;
                } else {
                    gameBoard[i][j] = 0;
                }
            }
        }
        shuffle(gameBoard);
        //showBoard(gameBoard); // Testing
    }

    /**
     * https://stackoverflow.com/questions/20190110/2d-int-array-shuffle
     *
     * @param a 2D Array
     * @return return shuffled int 2D array
     */
    private int[][] shuffle(int[][] a) {
        Random random = new Random();

        for (int i = a.length - 1; i > 0; i--) {
            for (int j = a[i].length - 1; j > 0; j--) {
                int m = random.nextInt(i + 1);
                int n = random.nextInt(j + 1);

                int temp = a[i][j];
                a[i][j] = a[m][n];
                a[m][n] = temp;
            }
        }
        return a;
    }

    /*
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
    */
    private void showBoard(int[][] board) {
        int rows = board.length;
        int cols = board[0].length;

        // Draw A B C etc
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
        return random.nextInt((max - min) + 1) + min;
    }

    /**
     * https://stackoverflow.com/questions/3491027/java-console-code-for-stopwatch-timer
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

    private void playGame() throws IOException, InterruptedException {
        Scanner scanner = new Scanner(System.in);
        while (wait) {
            clearScreen();
            showInfo();
            showBoard(userBoard);
            System.out.print("Your move (like B2) > ");
            String userInput = scanner.next();
            if (userInput.equalsIgnoreCase("exit")) {
                System.out.println("Game saved ...");
                saveBoard();
                System.out.println("Stopping... Playing time: " + getTime());
                System.out.println("Bye, bye");
                System.exit(0);
            } else if (userInput.equalsIgnoreCase("backdoor")) {
                showBoard(gameBoard);
                System.exit(0);
            } else {
                if (userInput.length() == 2) {// check user input
                    if (checkUserInput(userInput)) {
                        char ch = Character.toUpperCase(userInput.charAt(0));
                        int nr = Integer.parseInt(userInput.substring(1, 2));
                        wait = shooting(ch, nr);
                    } else {
                        hitOrMissed = "Try again!";
                    }
                } else {
                    hitOrMissed = "Try again!";
                }
            }
        }
        File f = new File(PROGRESS_FILENAME);
        if (f.exists() && !f.isDirectory()) {
            f.delete(); // Kustutame faili
        }
        System.out.println("Game over! Total points: " + points);
        System.out.println("Stopping... Playing time: " + getTime());
        System.out.println("Game board was");
        showBoard(gameBoard);
    }

    private boolean checkUserInput(String userInput) {
        // Input must be A1 or a1 not a or not 1 or A12 or 123A and so on
        char ch = userInput.charAt(0); // a, b, c, d, e, f
        char nr = userInput.charAt(1); // 1, 2, 3, 4, 5, 6
        if(Character.isAlphabetic(ch) && Character.isDigit(nr)) {
            return true;
        } else {
            return false;
        }
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
                return !ok; // Game Over
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
        System.out.print("| Points: " + points + "/" + shipsCount);
        for (int i = 0; i < (10 - len); i++) {
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
    public static void main(String[] args) throws IOException, InterruptedException {
        NavalBattle4 nb4 = new NavalBattle4(args);
        //nb4.saveBoard();
        //nb4.loadBoard();
        File f = new File(PROGRESS_FILENAME);
        if (f.exists() && !f.isDirectory()) {
            nb4.loadBoard();
        }
        nb4.playGame();
    }

    private void clearScreen() throws IOException, InterruptedException {
        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
    }

    @Override
    public void run() {
        try {
            //System.out.println("Starting " + getTime());
            playGame();

            Thread.sleep(1000);
        } catch (InterruptedException | IOException ie) {
            ie.printStackTrace();
        }
    }

    private void saveBoard() {
        try {
            FileWriter fw = new FileWriter(PROGRESS_FILENAME);
            BufferedWriter bw = new BufferedWriter(fw);
            for (int i = 0; i < gameBoard.length; i++) {
                for (int j = 0; j < gameBoard[0].length; j++) {
                    bw.write(String.valueOf(gameBoard[i][j]));
                }
                if (i < gameBoard.length - 1) {
                    bw.newLine();
                }
            }
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadBoard() {
        List<String> lines = new ArrayList<>();
        FileReader fr = null;
        try {
            fr = new FileReader(PROGRESS_FILENAME);
            BufferedReader br = new BufferedReader(fr);
            String line = null;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);
            for (int j = 0; j < line.length(); j++) {
                char ch = line.charAt(j);
                gameBoard[i][j] = Character.getNumericValue(ch);
            }
        }
        showBoard(gameBoard);
    }
}
