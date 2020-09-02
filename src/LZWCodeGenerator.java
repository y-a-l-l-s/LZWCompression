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
		
		if (old.equals("")) {
			// we r good
		}
		else {
			compressedText.add(dict.get(old));
		}
		
		return compressedText;
	}
}
