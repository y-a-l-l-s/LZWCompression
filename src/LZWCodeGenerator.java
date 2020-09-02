import java.util.*;
import java.awt.List;

public class LZWCodeGenerator {

	public static ArrayList compressText(String originalText) {
		HashMap<String, Integer> dict = new HashMap<String, Integer>();
		int dictSize = 2048;
		ArrayList<Integer> compressedText = new ArrayList<Integer>();
		
		for (int i = 0; i < 256; i++) {
			dict.put("" + (char)i, i);
		}
		
		
		
		return compressedText;
	}
}
