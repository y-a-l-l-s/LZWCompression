import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
public class LZWLRDecoder {
	static int tableOfCodesSize= 128;
	final static int CHARDIGITS = 16;
	final static int NUMOFBINDIGITS = 12;
	private HashMap<Integer,String>tableOfCodes;
	public LZWLRDecoder()
	{

	}
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
		BufferedReader br = new BufferedReader(new FileReader (inputFile));
		PrintWriter pw = new PrintWriter("decodedfile.txt");
		StringBuffer str = new StringBuffer();
		while (br.ready())
		{
			String current = Integer.toBinaryString(br.read());
			while (current.length()<CHARDIGITS)
			{
				current = "0" + current;
			}
			str.append(current);
		}
		ArrayList<Integer> encodedInts = new ArrayList<Integer>();
		while(str.length()>0)
		{
			int convertedChunk = (Integer.parseInt(str.substring(0,NUMOFBINDIGITS)));
			encodedInts.add(convertedChunk);
			str.delete(0, NUMOFBINDIGITS);
		}
		String previousOutput = "";
		for(int i = 0; i<encodedInts.size()-1;i++)
		{
			int currentInt = encodedInts.get(i);
			String currentStr = tableOfCodes.get(currentInt);
			pw.print(currentStr);
			previousOutput = currentStr;
			if(tableOfCodes.get(encodedInts.get(i+1)).substring(0,1)!=null)
			{
				tableOfCodes.put(tableOfCodesSize, previousOutput+tableOfCodes.get(encodedInts.get(i+1)).substring(0,1));
				tableOfCodesSize++;
			}
			else
			{
				tableOfCodes.put(tableOfCodesSize, currentStr+currentStr.substring(0,1));
				pw.print(tableOfCodes.get(currentInt));
			}

		}
		br.close();
		pw.close();
	}

}
