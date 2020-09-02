import java.util.*;
import java.awt.List;

public class LZWCodeGenerator {

	public static ArrayList compressText(String originalText) {
		HashMap<String, Integer> dict = new HashMap<String, Integer>();
		int dictSize = 256;
		int maxSize = 2048;
		ArrayList<Integer> compressedText = new ArrayList<Integer>();
		
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
		
		
		return compressedText;
	}
}
