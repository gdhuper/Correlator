

/**
	 * Helper class for linked list nodes storing generic types
	 * 
	 * @param <E>
	 */
	public class Node extends DataCount<String> {
		private Node next;

		/**
		 * Creates a LinkedNode object with element d of type E and an int count
		 */
		public Node(String data, int count) {
			super(data, count);
			
		}

		/**
		 * Creates a LinkedNode object with element d of type E, an int count,
		 * and a next node
		 */
		public Node(String data, int count, Node next) {
			super(data, count);
			this.next = next;
		}

		public Node getNext() {
			return next;
		}

		public void setNext(Node next) {
			this.next = next;
		}

		
	}