public class TextCompressor {
	public static void main(String[] args) {		
		String str = "";
		if(args.length > 0) {
			for(int i = 0; i < args.length; i++) {
				str += args[i] + " ";
			}
			str = str.trim();			
			String newStr = "";
			int counter = 1;
			for(int i = 0; i < str.length(); i++) {
				if(i > 0) {
					char thisCh = Character.toLowerCase(str.charAt(i));
					char prevCh = Character.toLowerCase(str.charAt(i-1));
					if(thisCh == prevCh) {
						counter++;
					} else {
						if(counter > 1) {
							newStr += counter + "" + prevCh;
							counter = 1;
						} else {
							newStr += prevCh;
						}
					}
				} 
				if(i == str.length()-1) {
					if(counter > 1) {
						newStr += counter + "" + str.charAt(str.length()-1);
					} else {
						newStr += str.charAt(str.length()-1);				
					}
				}
			}
			System.out.println("Original:  " + str);
			System.out.println("My result: " + newStr);	
		} else {
				
		}
	}
}