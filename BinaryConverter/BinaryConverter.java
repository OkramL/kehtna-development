import java.text.DecimalFormat;
public class BinaryConverter {
	public static void main(String[] args) {
		
		if(args.length == 1 && isInteger(args[0])) {
			int decimal = Integer.parseInt(args[0]);
			String result = "";
			while (decimal > 0) {
				if((decimal % 2) == 0) {
					result = "0" + result;
				} else {
					result = "1" + result;
				}
				decimal = decimal / 2;
			}			
			System.out.println(args[0] + " => " + niceFormat(result));
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
	
	private static String niceFormat(String original) {
		String result = "";
		int more = 4 - (original.length() % 4);
		String spaces = "";
		for(int i = original.length(); i > 0; i-- ) {
			if((i % 4) == 0) {
				result += " " + original.substring(i-1, i);
			} else {
				result += original.substring(i-1, i);
			}
		}		
		
		for(int i = 0; i < more; i++) {
			spaces += "0";
		}		
		
		return original + " => " + spaces + result;
	}
}