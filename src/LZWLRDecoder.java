import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
public class LZWLRDecoder {
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
		String currentOutput = "";
		for(int i = 0; i<encodedInts.size();i++)
		{
			int currentInt = encodedInts.get(i);
			if(tableOfCodes.get(currentInt)!=null)
			{
				pw.print(tableOfCodes.get(currentInt));
				previousOutput = tableOfCodes.get(currentInt);
			}
			else
			{
				
			}
		}
		br.close();
		pw.close();
	}

}
