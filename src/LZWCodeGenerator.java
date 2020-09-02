import java.util.*;
import java.awt.List;

public class LZWCodeGenerator {

	public static void main(String [] args) {
		
	}
	
	public static ArrayList compressText(String originalText) {
		// all starting characters
		int dictSize = 256;
		// max length for the hashmap
		int maxSize = 2048;
		
		HashMap<String, Integer> dict = new HashMap<String, Integer>();
		ArrayList<Integer> compressedText = new ArrayList<Integer>();
		
		// adds all 256 characters to the hashmap
		for (int i = 0; i < 256; i++) {
			dict.put("" + (char)i, i);
		}
		
		String current = "";
		for (char character: originalText.toCharArray()) {
			current += character;
			if (!dict.containsKey(current)) {
				dict.put(current, dictSize);
				dictSize++;
				compressedText.add(dict.get(current.substring(0,current.length()-1)));
				current = current.substring(current.length()-1);
			}
		}
		
		if (current.equals("")) {
			// we r good
		}
		else {
			compressedText.add(dict.get(current));
		}
		
		// returns the arraylist to be converted to .txt file
		return compressedText;
	}
}
