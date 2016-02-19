
import java.io.IOException;

/**
 * 
 * An executable that counts the words in a files and prints out the counts in
 * 
 * descending order. You will need to modify this file.
 */

public class WordCount {
	public static void main(String[] args) {
		DataCounter<String> d = null;
		if (args.length < 4) {
			System.err.println("java WordCount [ -b | -a | -h ] [ -frequency | -num_unique ] [-is|-qs|-ms] <filename>");
			System.exit(1);
		}
		if (args[0].equals("-b"))
		{
			d = new BinarySearchTree<String>();
		}
		else if (args[0].equals("-a"))
		{
			d = new AVLTree<String>();
		}
		else if (args[0].equals("-h"))
		{
			d = new HashTable();
		}
		if(args[1].equals("-frequency"))
		{
			countWords(d, args[1], args[2], args[3]);
		}
		else if(args[1].equals("-num_unique"))
		{
			countWords(d, args[1], args[2], args[3]);
		}
	}
	public static void countWords(DataCounter<String> d, String a, String b, String file) {
		long e1 = 0; long e2 =0; long e3 = 0; long s =0; long s1 =0; long s2 = 0;
		long memory = 0; long memory1 = 0; long memory2 = 0;
		
		try {
			FileWordReader reader = new FileWordReader(file);
			String word = reader.nextWord();
			while (word != null) {
				d.incCount(word);
				word = reader.nextWord();
			}

		} catch (IOException e) {
			System.err.println("Error processing " + file + e);
			System.exit(1);
		}
		DataCount<String>[] counts = d.getCounts();
        if(b.equals("-is"))
        {
        	 s = System.nanoTime();
        	sortByDescendingCount(counts);
        	 e1 = System.nanoTime();
        	 Runtime runtime = Runtime.getRuntime();
             runtime.gc();
             memory = runtime.totalMemory() - runtime.freeMemory();
        }
        else if(b.equals("-qs"))
        {
        	s1 = System.nanoTime();
		QuickSort(counts, 0, counts.length);
			e2 = System.nanoTime();
			Runtime runtime = Runtime.getRuntime();
            runtime.gc();
             memory1 = runtime.totalMemory() - runtime.freeMemory();
        }
        else if(b.equals("-ms"))
        {
        	s2 = System.nanoTime();
        	mergeSort(counts);
        	e3 = System.nanoTime();
        	Runtime runtime = Runtime.getRuntime();
            runtime.gc();
            memory2 = runtime.totalMemory() - runtime.freeMemory();
        }
		if(a.equals("-frequency"))
		{
		for (DataCount<String> c : counts){

			 System.out.println(c.count + " \t" + c.data);
		}
		 if(b.equals("-is")){
			 System.out.println("Time taken by Insertion Sort for sorting words : "+ Math.abs(e1-s) / 1000000000.0 + " nS");
			 System.out.println("Totally memory used by Insertion sort: " + memory + " bytes");
		 }
		 if(b.equals("-qs")){
			 System.out.println("Time taken by QuickSort for sorting words : "+ Math.abs(e2-s1) / 1000000000.0 + " nS");
			 System.out.println("Totally memory used by QuickSort :" + memory1 + " bytes");
				 }
		 if(b.equals("-ms")){
			 System.out.println("Time taken by MergeSort for sorting words : "+ Math.abs(e3-s2) / 1000000000.0 + " nS");
			 System.out.println("Totally memory used by MergeSort :" + memory2 + " bytes");
				 }
		}
		else if(a.equals("-num_unique"))
		{
			int totalUnique = 0;
			for(DataCount<String> c : counts) // to calculate total number of unique words in the text file
			{
				totalUnique++;
			}
			System.out.println("Total number of unique words in this file: " + totalUnique);
		}

	}

	/**
	 * 
	 * QuickSort
	 * Sort the count array in descending order of count. If two elements have
	 * the same count, they should be in alphabetical order (for Strings, that
	 * is. In general, use the compareTo method for the DataCount.data field).
	 * @param counts array to be sorted.
	 * @param low the lowest index to start from
	 * @param high the end index
	 * 
	 */

	private static <E extends Comparable<E>> void QuickSort(
			DataCount<E>[] counts, int low, int high)
	{
		if (high - low > 1)
		{
			int pivot = partition(counts, low, high);
			QuickSort(counts, low, pivot);
			QuickSort(counts, pivot + 1, high);
		}

	}
    /**
     * Recursive helper method for quick sort method
     */
	private static <E extends Comparable<E>> int partition(
			DataCount<E>[] counts, int low, int high)
	{
		int left = low;
		int right = high - 1;
		int pivot = (low + high) / 2;
		DataCount<E> piv = counts[pivot];
		DataCount<E> temp = counts[low];
		counts[low] = piv;
		counts[pivot] = temp;
		while (left < right)
		{
			if (counts[right].compareTo(piv) < 0)
			{
				right--;
			}
			else if (counts[left].compareTo(piv) >= 0)
			{
				left++;
			}
			else
			{
				DataCount<E> swap = counts[left];
				counts[left] = counts[right];
				counts[right] = swap;
			}
		}
		counts[low] = counts[left];
		counts[left] = piv;
		return left;
	}
	
	public static <E extends Comparable<E>>void sortByDescendingCount( DataCount<E>[] a )
	 {
	 int j;
	
	 for( int p = 1; p < a.length; p++ )
	 {
	 DataCount<E> tmp = a[p];
	 for(j = p; j > 0 && tmp.compareTo(a[j-1]) > 0; j-- )
	 a[j] = a[j-1];
	 a[j] = tmp;
	 }
	 }


	 public static <E extends Comparable<E>>void mergeSort(DataCount<E>[] a)
		{
			@SuppressWarnings("unchecked")
			DataCount<E>[] tmp = new DataCount[a.length];
			mergeSort(a, tmp,  0,  a.length - 1);
		}

 
		private static <E extends Comparable<E>>void mergeSort(DataCount<E>[] a, DataCount<E>[] tmp, int left, int right)
		{
			if( left < right )
			{
				int center = (left + right) / 2;
				mergeSort(a, tmp, left, center);
				mergeSort(a, tmp, center + 1, right);
				merge(a, tmp, left, center + 1, right);
			}
		}


		private static <E extends Comparable<E>> void merge(DataCount<E>[] a, DataCount<E>[] tmp, int left, int right, int rightEnd )
	    {
	        int leftEnd = right - 1;
	        int k = left;
	        int num = rightEnd - left + 1;

	        while(left <= leftEnd && right <= rightEnd)
	            if(a[left].compareTo(a[right]) >= 0){
	                tmp[k++] = a[left++];
	            }
	            else{
	                tmp[k++] = a[right++];
	            }

	        while(left <= leftEnd){    
	            tmp[k++] = a[left++];
	            }

	        while(right <= rightEnd)  {
	            tmp[k++] = a[right++];
	            }

	        for(int i = 0; i < num; i++, rightEnd--){
	            a[rightEnd] = tmp[rightEnd];
	            }
	    }

	}




	  
	  