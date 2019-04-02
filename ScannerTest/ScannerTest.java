import java.util.Scanner;

public class ScannerTest {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter your first name: ");
		String name = scanner.next();
		System.out.println("Your name is: " + name);
	}
}