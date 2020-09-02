import java.util.*;
import java.awt.List;

public class LZWCodeGenerator {

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
		
		String old = "";
		for (char character: originalText.toCharArray()) {
			String current = old + character;
			
			if (dict.containsKey(current)) {
				old = current;
			}
			// have to also add something here to make sure that it doesn't exceed the max size
			else {
				dict.put(current, dictSize++);
				compressedText.add(dict.get(old));
				old = Character.toString(character);
			}
		}
		
		if (old.equals("")) {
			// we r good
		}
		else {
			compressedText.add(dict.get(old));
		}
		
		// returns the arraylist to be converted to .txt file
		return compressedText;
	}
}
