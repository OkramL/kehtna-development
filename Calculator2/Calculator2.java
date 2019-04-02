import java.util.Scanner;

public class Calculator2 {
	
	public static void main(String[] args) {
		String[] myTexts = new String[]{"Input first number: ", "Input operator (/ x - +): ", "Input second number: "};
		String[] data = new String[3];
		Scanner scan = new Scanner(System.in);
		
		for(int i = 0; i < 3; i++){
			System.out.print(myTexts[i]);
			data[i] = scan.next();
		}
		System.out.println(data[0]);
		if(isInt(data[0]) && isInt(data[2])) {
			a = Integer.parseInt(data[0]);
			b = Integer.parseInt(data[2]);
			switch(data[1]) {
				case "+":
					System.out.println(sum(a, b));
					break;
				case "-":
					System.out.println(substract(a, b));
					break;
				case "/":
					System.out.println(divide(a, b));
					break;
				case "x":				
					System.out.println(multiply(a, b));
					break;				
				default:
					System.out.println("Wrong operator!");
					break;
			}
		} else {
			System.out.println("Error with numbers");	
		}
	}
	
	public static boolean isInt(String str) {
		try {
			int a = Integer.parseInt(str);
			return true;
		} catch (NumberFormatException nfe){
			return false;
		}
	}
	
	public static int sum(int a, int b) {
		return a + b;
	}
	public static int substract(int a, int b) {
		return a - b;
	}
	public static float divide(int a, int b) {
		return (float) a / b;
	}
	public static int multiply(int a, int b) {
		return a * b;
	}
}