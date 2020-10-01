public class EncoderQueue {
	private EncoderNode head;
	public EncoderQueue () {
	}

	public void addNode (String key) {
		EncoderNode node = new EncoderNode (key);
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
	
	public String pop () {
		String key = head.getKey();
		head.getNext().setLast(head.getLast());
		head.getLast().setNext(head.getNext());
		head = head.getNext();
		return key;
	}
	
	
	public void removeNode (String key) {
		EncoderNode node = head;
		while (node.getKey() != key) {
			node = node.getNext();
			if (node == head) {
				System.out.println("uhohsauce");
				System.out.println(key);
				System.out.println(key.length());
				throw new NullPointerException ("bad");
			}
			
		}
		if (node == head) {
			head = head.getNext();
		}
		node.getNext().setLast(node.getLast());
		node.getLast().setNext(node.getNext());
	}
	
}
