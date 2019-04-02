public class BinaryConverter {
	public static void main(String[] args) {
		if(args.length > 0 && isInteger(args[0])) {
			
		} else {
			System.out.println("Sorry, not Integer");
		}
	}
	
	private static boolean isInteger(String str) {
		try {
			int a = Integer.parseInt(str);
			return true;
		} catch(NumberFormatException nfe) {
			return false;
		}
	}
}