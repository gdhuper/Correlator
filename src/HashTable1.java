import java.util.ArrayList;
import java.util.List;




public class HashTable1 implements DataCounter<String> {

	private Node[] array;
	private int size;

	public HashTable1() {
		size = nextPrime(10007);
		array = new Node[size];
	}

	public void incCount(String data) {
		
		int h = hash(data) % this.size;
		boolean dataExist = false;
		if(h > array.length)
		{
			rehash(array);
		}else
		{
		if(array[h] == null)
		{
			array[h] = new Node(data, 1);
			dataExist = true;
		}
		else if(array[h] != null)
		{
			Node current = array[h];
			while(current != null)
			{
				if(current.data.equalsIgnoreCase(data))
				{
					current.count++;
					dataExist = true;
				    break;
				}
				current = current.next;
			}
		  
		}
		if(!dataExist)
		{
			Node current = array[h];
			while(current != null)
			{
				current= current.next;
			}
			current = new Node(data, 1);
			
		}
		}
		

	}
	
	 private void rehash(Node[] array)
	    {
	    	int newSize = nextPrime(size * 2);
	    	
	    	Node[] newArray = new Node[newSize];
	    	
	    	for(int i = 0; i < array.length; i++)
	    	{
	    		Node front = array[i];
	    		if(front != null)
	    		{
	    		while(front != null)
	    		{
	    			int index = hash(front.data) % newArray.length;
	    			Node temp = front;
	    			front = front.next;
	    			temp.next = newArray[index];
	    			newArray[index] = temp;
	    		}
	    		}
	    		else
	    		{
	    			continue;
	    		}
	    	}
	    	this.size = newSize;
	    	
	    }

	@Override
	public int getSize() {
		return array.length;
	}

	@Override
	public DataCount<String>[] getCounts() {

		DataCount<String>[] counts = new DataCount[size];

		for (int i = 0; i < array.length; i++) {
			Node current = array[i];
			if(current == null)
			{
				continue;
			}
			else
			{
			while (current != null) {
				
				String d = current.data;
				int count = current.count;
				counts[i] = new DataCount<String>(d, count);
				current = current.next;

			}
		}
		}

		return counts;
	
	}

		/**List<DataCount<String>> counts = new ArrayList<DataCount<String>>();

		for (int i = 0; i < array.length; i++) {

			if (array[i] != null) {
				counts.add(new DataCount<String>(array[i].getData(),
						array[i].count));
				
			}
		
		}

		@SuppressWarnings("unchecked")
		DataCount<String>[] countsArray = new DataCount[counts.size()];

		for (int i = 0; i < counts.size(); i++) {
			countsArray[i] = counts.get(i);
		}

		
		return countsArray;
	
	}*/


	public int hash(String data) {
		int h = 0;
		for (int i = 0; i < data.length(); i++) {
			char c = data.charAt(i);
			h += Math.pow(37, data.length()-i) * (int) c;
		}
		if (h <= 0) {
			h += array.length;
		}
		return h;

	}

	/**
	 * Internal method to find a prime number at least as large as n.
	 * 
	 * @param n
	 *            the starting number (must be positive).
	 * @return a prime number larger than or equal to n.
	 */
	private static int nextPrime(int n) {
		if (n % 2 == 0)
			n++;

		for (; !isPrime(n); n += 2)
			;

		return n;
	}

	/**
	 * Internal method to test if a number is prime. Not an efficient algorithm.
	 * 
	 * @param n
	 *            the number to test.
	 * @return the result of the test.
	 */
	private static boolean isPrime(int n) {
		if (n == 2 || n == 3)
			return true;

		if (n == 1 || n % 2 == 0)
			return false;

		for (int i = 3; i * i <= n; i += 2)
			if (n % i == 0)
				return false;

		return true;
	}


private class Node {
	public Node next;
	public String data;
	public int count;

	/**
	 * Creates a LinkedNode object with element d of type E and an int count
	 */
	public Node(String data, int count) {
		//super(data, count);
		this.data = data;
		this.count = count;
		this.next = null;
		
	}

	/**
	 * Creates a LinkedNode object with element d of type E, an int count,
	 * and a next node
	 */
	public Node(String data, int count, Node next) {
		//super(data, count);
		this.data = data;
		this.count = count;
		
		this.next = next;
	}
}
}
