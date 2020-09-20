import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
public class LZWLRDecoder {
	static int tableOfCodesSize= 128;
	final static int CHARDIGITS = 16; //number of digits assigned to each char
	final static int NUMOFBINDIGITS = 12; //number of digits split into each binary chunk
	private HashMap<Integer,String>tableOfCodes;
	public LZWLRDecoder() //initializes empty decoder
	{

	}
	//fills in hashmap with the 127 ascii values and their corresponding characters
	public HashMap<Integer, String> fillInAsciiValues()
	{
		tableOfCodes = new HashMap<Integer, String>();
		for (int i = 0; i < 128; i++)
		{
			tableOfCodes.put((Integer)i, Character.toString((char)i));
		}
		return tableOfCodes;
	}

	public void decode(String inputFile) throws IOException
	{
		tableOfCodes = this.fillInAsciiValues();
		BufferedReader br = new BufferedReader(new FileReader (inputFile));
		PrintWriter pw = new PrintWriter("decodedfile.txt");
		StringBuffer str = new StringBuffer();
		while (br.ready())
		{
			String current = Integer.toBinaryString(br.read()); //converts char to a binary string
			while (current.length()<CHARDIGITS) //pads converted char binary strings with zeros until they are all length 16
			{
				current = "0" + current;
			}
			if(br.ready())//accounting for extra char from encoder
			{
				str.append(current);
			}
		}
		ArrayList<Integer> encodedInts = new ArrayList<Integer>();
		//reading through stringbuffer str and taking 12 digits chunks to convert to single integers
		while(str.length()>0)
		{
			int convertedChunk = (Integer.parseInt(str.substring(0,NUMOFBINDIGITS)));
			encodedInts.add(convertedChunk);
			str.delete(0, NUMOFBINDIGITS);
		}
		String previousOutput = "";
		for(int i = 0; i<encodedInts.size()-1;i++) //convert arraylist of ints into chars and building tableOfCodes
		{
			int currentInt = encodedInts.get(i);
			String currentStr = tableOfCodes.get(currentInt);
			pw.print(currentStr);
			previousOutput = currentStr;
			if(tableOfCodes.get(encodedInts.get(i+1))!=null) //check if code for next int in arraylist exists
			{
				String add = tableOfCodes.get(encodedInts.get(i+1)).substring(0,1);
				tableOfCodes.put(tableOfCodesSize, previousOutput+add);
			}
			else//build tableOfCodes so that the next int in arraylist has a code
			{
				tableOfCodes.put(tableOfCodesSize, currentStr+currentStr.substring(0,1));
			}
			tableOfCodesSize++;
		}
		br.close();
		pw.close();
	}

}
