public class EncoderQueue {
	private EncoderNode head;
	public EncoderQueue () {

	}

	public void addNode (EncoderNode node) {
		if (head == null) {
			head = node;
		} else if(head.getNext() == null) {
			head.setNext(node);
			head.setLast(node);
			node.setLast(head);
			node.setNext(head);
		} else {
			EncoderNode oldLast = head.getLast();
			oldLast.setNext(node);
			node.setNext(head);
			head.setLast(node);
			node.setLast(oldLast);
		}
	}
	
	public void removeNode (String key) {
		EncoderNode node = head;
		while (node.getKey() != key) {
			node = node.getNext();
		}
		node.getNext().setLast(node.getLast());
		node.getLast().setNext(node.getNext());
	}
}
