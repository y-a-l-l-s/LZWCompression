import java.io.IOException;

public class LRDecoderTester {
	public static void main(String[]args) throws IOException
	{
		LZWLRDecoder tester = new LZWLRDecoder();
		tester.decode("big.txt.lzw");
	}
}
