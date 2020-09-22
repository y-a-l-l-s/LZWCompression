import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
//improvements
public class LZWCodeGenerator {
static int DICTINT = 128;
final static int NUMOFBINDIGITS = 12; //number of digits assigned to each char
final static int CHARDIGITS = 16; //number of digits split into binary chunks
final static int MAXHASHSIZE = (int)Math.pow(2, CHARDIGITS);
	public static void main(String [] args) throws IOException {
		compressText("big.txt");
	}

	public static void compressText(String inputFile) throws IOException {
		HashMap<String, Integer> dict = new HashMap<String, Integer>();
		ArrayList<Integer> compressedText = new ArrayList<Integer>();
		
		// adds dictInt characters to the hashmap
		for (int i = 0; i < DICTINT; i++) {
			dict.put("" + (char)i, i);
		}
		
		BufferedReader br = new BufferedReader(new FileReader(inputFile)); // initalize
		
		StringBuffer current = new StringBuffer();
		while(br.ready()) { // RETRIEVE NUMBERS TO ENCODE
			current.append((char)br.read());
			if (!dict.containsKey(current.toString())) { // check if new string
				if(DICTINT < MAXHASHSIZE) { // check if hashmap has space left
					// adds to the hashmap
					dict.put(current.toString(), dict.size());
					DICTINT++;
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
			// pads 0s to make it numofbindigits long
			while(num.length() < NUMOFBINDIGITS) {
				num = "0" + num;
			}
			str.append(num);
			
			while(str.length() > CHARDIGITS) { // convert binary to encoded character and add to .txt
				pw.print((char)Integer.parseInt(str.substring(0,CHARDIGITS), 2));
				str.delete(0, CHARDIGITS);
			}
		}
		int left = CHARDIGITS - (str.length() % CHARDIGITS); // add extra zeroes for padding
		if(left != CHARDIGITS) {
			for(int j = 0; j < left; j++) {
				str.append("0");
			}
		}
		for(int i = 0; i < str.length(); i = i + CHARDIGITS) { // add padded ending to .txt
			pw.print((char)Integer.parseInt(str.substring(i,i+CHARDIGITS), 2));
		}
		pw.print((char)left); // add character for number of extra zeroes
		pw.close();
	}
}
