

/**
 * This class Bench marks the performance of the data structures used.
 */
public class BenchMark {
	
	public static void main(String[] args)
	{
		String dType = null;
		DataCounter<String> d = null;
		if (args.length < 3) {

			System.err.println("For checking WordCount's performance: java BenchMark [ -b | -a | -h ] [ -frequency | -num_unique ] <filename>");
			System.err.println("For checking Correlator's performance: java BenchMark [ -b | -a | -h ] [ -frequency | -num_unique ] <filename>");


			System.exit(1);
		}
		if (args[0].equals("-b"))
		{
			d = new BinarySearchTree<String>();
			dType = "BST";
		}
		else if (args[0].equals("-a"))
		{
			d = new AVLTree<String>();
			dType = "AVLTree";
		}
		else if (args[0].equals("-h"))

		{
			d = new HashTable();
			dType = "HashTable";
		}
		
		checkPerformanceWordCount(d, args[1], args[2]);
		
		//checkPerformanceCorrelator(d, d, args[1], args[2]); to check the performance of correlator. we commented out this part because it was giving us error on compilation but this code runs though

	}
	/**
	 * Calculates the memory usage and running time of the wordcount class methods.
	 * @param d the data structure to measure performance of 
	 * @param a the specific function to call for
	 * @param file the text file to count words for
	 */
	public static void checkPerformanceWordCount( DataCounter<String> d, String a, String file ){ 
        long sum=0, s, e;
                // to calculate the running time for wordcount class's countword method   
                s = System.nanoTime();
                WordCount.countWords(d, a, file );
                e = System.nanoTime();
                sum = (e- s);
                
                // to Calculate the memory usage of the method
                Runtime runtime = Runtime.getRuntime();
                runtime.gc();
                long memory = runtime.totalMemory() - runtime.freeMemory();
                System.out.println("Totally memory used in bytes for counting words :" + memory);
                System.out.println("Time taken for counting words : "+ sum / 1000000000.0);
                
                
              
                
               
        

                
}
	
	/**
	 * this method computes the running time of correlator  class . but we had to comment this part out to remove the error.
	 * We can still run this method by passing "java BenchMark [-a|-h|-b] textfile1 textFile2" in the command line
	 */
	/**
	 * public static void checkPerformanceCorrelator( DataCounter<String> d, DataCounter<String> d1, String file1, String file2 ){ 
        long timeSum=0, s1, e1;
                // to calculate the running time for correlator class   
        		s1 = System.nanoTime();
        		Correlator.correlate(d, d1, file1, file2 );
        		e1 = System.nanoTime();
        		timeSum = (e1- s1);
                
                // to Calculate the memory usage of the method
                Runtime runtime = Runtime.getRuntime();
                runtime.gc();
                long memory = runtime.totalMemory() - runtime.freeMemory();
                System.out.println("Totally memory used in bytes for correlating :" + memory);
                System.out.println("Time taken for correlating two files : "+ timeSum / 1000000000.0);
	}*/
}
