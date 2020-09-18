import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
public class LZWLRDecoder {
	final static int CHARDIGITS = 16;
	final static int NUMOFBINDIGITS = 12;
	private HashMap<String,Integer>tableOfCodes;
	public LZWLRDecoder()
	{

	}
	public HashMap<String, Integer> fillInAsciiValues()
	{
		tableOfCodes = new HashMap<String, Integer>();
		for (int i = 0; i < 128; i++)
		{
			tableOfCodes.put(Character.toString((char)i), (Integer)i);
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
		while(str.length()>0)
		{
			int convertedChunk = (Integer.parseInt(str.substring(0,NUMOFBINDIGITS)));
			
		}
	}

}
