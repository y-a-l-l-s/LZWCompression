public class EncoderQueue {
	private EncoderNode head;
	
	public EncoderQueue () {
		
	}
	
	public void addNode (EncoderNode node) {
		if (head == null) { 
			head = node;
		}
		
	}
}
