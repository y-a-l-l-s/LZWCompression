public class EncoderNode {
	private String key;
	private EncoderNode next;
	private EncoderNode last;
	
	public EncoderNode (String key) {
		this.key = key;
	}
	
	public void setNext (EncoderNode node) {
		this.next = node;
	}
	
	public void setLast (EncoderNode node) {
		this.last = node;
	}
	
	public EncoderNode getNext (EncoderNode node) {
		return next;
	}
	
	public EncoderNode getLast (EncoderNode node) {
		return last;
	}
}
