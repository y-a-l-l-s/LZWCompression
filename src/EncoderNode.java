public class EncoderNode {
	private String key;
	private EncoderNode next;
	private EncoderNode last;
	
	public EncoderNode (String key) {
		this.key = key;
	}
	
	public void setNext (EncoderNode node) {
		next = node;
	}
	
	public void setLast (EncoderNode node) {
		last = node;
	}
}
