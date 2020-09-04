import java.util.*;
import java.awt.List;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class LZWCodeGenerator {

	public static void main(String [] args) throws IOException {
		compressText("fileToCompress.txt");
	}

	public static void compressText(String inputFile) throws IOException {
		// all starting characters
		int dictSize = 256;
		// max size for the hashmap
		int maxSize = 4096;
		
		HashMap<String, Integer> dict = new HashMap<String, Integer>();
		ArrayList<Integer> compressedText = new ArrayList<Integer>();
		
		// adds all 256 characters to the hashmap
		for (int i = 0; i < 256; i++) {
			dict.put("" + (char)i, i);
		}
		
		BufferedReader br = new BufferedReader(new FileReader(inputFile)); // initalize
		
		StringBuffer current = new StringBuffer();
		while(br.ready()) { // RETRIEVE NUMBERS TO ENCODE
			current.append((char)br.read());
			if (!dict.containsKey(current.toString())) { // check if new string
				if(dictSize < maxSize) { // check if hashmap has space left
					// adds to the hashmap
					dict.put(current.toString(), dictSize);
					dictSize++;
				}
				compressedText.add(dict.get(current.substring(0,current.length()-1))); // add number to arraylist
				current.delete(0, current.length()-1);
			}
		}
		br.close();
		
		// final encoded character
		if (!current.toString().equals("")) {
			compressedText.add(dict.get(current.toString()));
		}
		
		// ENCODE TEXT
		PrintWriter pw = new PrintWriter(inputFile + ".lzw");
		StringBuffer str = new StringBuffer();
		
		for (int i = 0; i < compressedText.size(); i++) { // convert array list to binary and add to string
			String num = Integer.toBinaryString(compressedText.get(i)); 
			while(num.length() < 8) {
				num = "0" + num;
			}
			str.append(num);
			
			while(str.length() > 8) { // convert binary to encoded character and add to .txt
				pw.print((char)Integer.parseInt(str.substring(0,8), 2));
				str.delete(0, 8);
			}
		}
		int left = 8 - (str.length() % 8); // add extra zeroes for padding
		if(left != 8) {
			for(int j = 0; j < left; j++) {
				str.append("0");
			}
		}
		for(int i = 0; i < str.length(); i = i + 8) { // add padded ending to .txt
			pw.print((char)Integer.parseInt(str.substring(i,i+8), 2));
		}
		pw.print((char)left); // add character for number of extra zeroes
		pw.close();
	}
}
