
public class Correlator {
	private static final double FREQ_MIN = 0.0001;
    private static final double FREQ_MAX = 0.01;
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void main(String[] args)
	{
		if(args.length != (3))
		{
			 System.err.println("java Correlator [-b | -a | -h] <String File1> <String File2");
			 System.exit(1);
		}
			DataCounter<String> d1 = null;
			DataCounter<String> d2 = null;
		if(args[0].equals("-b"))
		{
			d1 = new BinarySearchTree();
			d2 = new BinarySearchTree();
		}
		else if(args[0].equals("-a"))
		{
			d1 = new AVLTree();
			d2 = new AVLTree();
		}
		else if(args[0].equals("-h"))
		{
			d1 = new HashTable();
			d2 = new HashTable();
		}
		
		correlate(d1, d2, args[1], args[2] );
		
	}
	/**
	 * Method to correlate the two files
	 * @param d1 data structure type 1
	 * @param d2 data structure type 2
	 * @param a first text file 
	 * @param b the second text file
	 */
	@SuppressWarnings("rawtypes")
	public static void correlate(DataCounter<String> d1, DataCounter<String> d2, String a, String b)
	{
		int s1 = size(a, d1); //total number of words  of text file 1
		int s2 = size(b, d2); // total number of words of text file 2
		DataCount[] count1 = d1.getCounts(); // data count of file 1
        DataCount[] count2 = d2.getCounts(); // data count of file 2

		double sum = 0;
		
			
		 for ( DataCount sc : count1 ) {
			 for(DataCount fc: count2)
           
			 {  
                    if(sc.data.equals(fc.data))
                    {
                     double f1 = ((double)sc.count) / s1; // normalized frequency of word from file 1
                     double f2 = ((double)fc.count ) / s2; // normalized frequency of word from file 2
                     
                     if ( f1 >= FREQ_MIN && f1 <= FREQ_MAX && f2 >= FREQ_MIN && f2 <= FREQ_MAX ) // to remove the words with relative frequency too high or too low
                     {
                    	 double difference =  Math.abs( f1 - f2 );
                    	 sum +=  Math.pow(  difference , 2.0 ); // adds the squared difference of frequency to the running sum 
                     }
                    }
             }
     }
     
     System.out.println("The correlation between " + a + " and " + b + " is: " + sum );
     
             
		
	}
	/**
     * Helper method to calculate the total number of words in a file 
     * @param file the file to be checked words for
     * @param d the data structure to be used
     * @return the total number of words
     */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private static int size( String file, DataCounter d ) {
		int totalWords = 0;
		try {

			FileWordReader reader = new FileWordReader(file);

			String word = reader.nextWord();

			while (word != null) {

				d.incCount(word);
				totalWords++;
				word = reader.nextWord();

			}
		} catch (Throwable error) {
			System.err.println("Error processing \n" + file + error);
			System.exit(1);
		}
		return totalWords;

	}
}

	
    
