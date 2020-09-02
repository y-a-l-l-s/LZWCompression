import java.util.*;
import java.awt.List;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class LZWCodeGenerator {

	public static void main(String [] args) {
		
	}

	public static ArrayList compressText(String inputFile) throws IOException {
		// all starting characters
		int dictSize = 256;
		// max length for the hashmap
		int maxSize = 4096;
		
		HashMap<String, Integer> dict = new HashMap<String, Integer>();
		ArrayList<Integer> compressedText = new ArrayList<Integer>();
		
		// adds all 256 characters to the hashmap
		for (int i = 0; i < 256; i++) {
			dict.put("" + (char)i, i);
		}
		
		BufferedReader br = new BufferedReader(new FileReader(inputFile));
		
		String current = "";
		while(br.ready()) {
			current += (char)br.read();
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
