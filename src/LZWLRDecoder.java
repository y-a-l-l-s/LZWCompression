import java.util.HashMap;

public class LZWLRDecoder {
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
	
	public void decode(String inputFile)
	{
		
	}
	
}
