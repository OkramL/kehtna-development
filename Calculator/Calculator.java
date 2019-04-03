public class Calculator {
	public static void main(String[] args) {
				
		if(args.length == 3) {
			if(isInt(args[0]) && isInt(args[2])) {
				int a = Integer.parseInt(args[0]);
				int b = Integer.parseInt(args[2]);
				switch(args[1]) {
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
		} else {
			System.out.println("Need three arguments");
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