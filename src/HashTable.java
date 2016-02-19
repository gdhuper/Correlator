

import java.util.ArrayList;

import java.util.List;

/**
 * Hashtable implementation of the data counter interface 
 */

public class HashTable implements DataCounter<String> {

	private Node[] array;
	private int size;
	private int numEntries;

	public HashTable() {

		size = nextPrime(50000);
        array = new Node[size];

		numEntries = 0;

	}

	public int hash(String data, int count) {

		int hashVal = 0;

		for (int i = 0; data != null && i < data.length(); i++) {

			char c = data.charAt(i);

			hashVal = hashVal + (int) c; 
		}
		if (hashVal <= 0) {

			hashVal += array.length;

		}

		return hashVal;

	}

	/** {@inheritDoc} */

	public void incCount(String d) {

		if (numEntries + 1 >= size)

		{

			resize();

		}

		Node entry = contains(d);

		if (entry == null)

		{

			int hash = hash(d, size);

			if (array[hash] == null)

			{

				array[hash] = new Node(d, 1);

			}

			else

			{

				for (int i = hash + 1; i != hash; i++)

				{

					if (i == size)

						i = 0;

					if (array[i] == null)

					{

						array[i] = new Node(d, 1);

						hash = i;

						break;

					}

				}

				numEntries++;

			}

		}

		else

		{

			entry.count++;

		}

	}

	/**
	 * int h = hash(d) % size;
	 * 
	 * boolean dataExists = false;
	 * 
	 * //double loadFactor = nextPrime(size / array.length);
	 * 
	 * 
	 * Node current = null;
	 * 
	 * 
	 * 
	 * //if (loadFactor <= 1) {
	 * 
	 * /// resize(array);
	 * 
	 * //}
	 * 
	 * 
	 * if(h >= array.length) {
	 * 
	 * resize(array);
	 * 
	 * }
	 * 
	 * if (array[h] == null) {
	 * 
	 * 
	 * array[h] = new Node(d, 1);
	 * 
	 * }
	 * 
	 * else {
	 * 
	 * current = array[h];
	 * 
	 * }
	 * 
	 * 
	 * 
	 * while (current != null) {
	 * 
	 * System.out.println("dddd");
	 * 
	 * if (current.data == null) {
	 * 
	 * 
	 * }
	 * 
	 * if (current.data.equalsIgnoreCase(d)) {
	 * 
	 * current.count++;
	 * 
	 * dataExists = true;
	 * 
	 * break;
	 * 
	 * }
	 * 
	 * else if (current.getNext() != null) {
	 * 
	 * current = current.getNext();
	 * 
	 * }
	 * 
	 * //System.out.println(x);
	 * 
	 * current = current.getNext();
	 * 
	 * }
	 * 
	 * 
	 * 
	 * }
	 * 
	 * // if (!dataExists) {
	 * 
	 * // current.next = new Node(d, 1);
	 * 
	 * // Node current = new Node(d, 1, array[h]);
	 * 
	 * // current.next = array[h];
	 * 
	 * // array[h] = current;
	 * 
	 * // array[h].count++;
	 * 
	 * //
	 */

	private Node contains(String s)

	{

		for (int i = 0; i < size; i++)

			if (array[i] != null && array[i].data.compareTo(s) == 0)

				return array[i];

		return null;

	}

	private void resize() {

		int newSize = nextPrime(size * 2);

		Node[] temp = new Node[newSize];

		Node[] swap = array;

		array = temp;

		temp = swap;

		for (int i = 0; i < array.length; i++) {

			// temp[i] = array[i];

			Node entry = temp[i];

			if (entry != null)

			{

				int index = hash(entry.data, entry.count);

				array[index].data = entry.data;

			}

			// Node front = array[i];

			// while (front != null) {

			// int index = hash(front.data) % newArray.length;

			// Node temp = front;

			// front = front.next;

			// temp.next = newArray[index];

			// newArray[index] = temp;

			// }

		}

		size = newSize;

	}

	/**
	 * 
	 * Internal method to find a prime number at least as large as n.
	 * 
	 * 
	 * 
	 * @param n
	 * 
	 *            the starting number (must be positive).
	 * 
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
	 * 
	 * Internal method to test if a number is prime. Not an efficient algorithm.
	 * 
	 * 
	 * 
	 * @param n
	 * 
	 *            the number to test.
	 * 
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

	/** {@inheritDoc} */

	public DataCount<String>[] getCounts() {

		List<DataCount<String>> counts = new ArrayList<DataCount<String>>();

		for (int i = 0; i < array.length; i++) {

			if (array[i] != null) {

				counts.add(new DataCount<String>(array[i].getData(),

				array[i].count));

				// System.out.println(array[i].count);

			}

		}

		DataCount<String>[] countsArray = new DataCount[counts.size()];

		for (int i = 0; i < counts.size(); i++) {

			countsArray[i] = counts.get(i);

		}

		// System.out.println("countsArray.length=" + countsArray.length);

		return countsArray;

		// DataCount<String>[] countsArray = new DataCount[array.length];

		//

		// for (int i = 0; i < array.length; i++) {

		//

		// if (array[i] != null) {

		// countsArray[i] = new DataCount<String>(array[i].getData(),

		// array[i].count);

		// }

		//

		// }

		//

		//

		// System.out.println("countsArray.length=" + countsArray.length);

		// return countsArray;

	}

	/** {@inheritDoc} */

	public int getSize() {

		return size;

	}

}