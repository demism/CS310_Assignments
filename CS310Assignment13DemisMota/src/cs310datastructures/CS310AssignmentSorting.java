/*
 * CS310 Assignment 13 & 14 Sorting Experimentation
 */
package cs310datastructures;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

/**
 * TODO Class Description
 *
 * @author Jeffrey LaMarche
 * 
 * @version 1.0 2021-Mar-31  
 * Template Version
 * 
 * @author TODO
 * @version TODO
 */
public class CS310AssignmentSorting
{

    /*
    The number of tests to perform per sort
     */
    private static final int NUM_TESTS = 10;

    /*
    The size of the arrays that will be sorted
     */
    private static final int[] ARRAY_SIZES =
    {
        10, 100, 1000, 10000, 25000, 50000, 100000
    };

    /*
    The number of nanoseconds in a second
     */
    private static final double NANO_IN_SEC = 1000000000.0;

    /*
    The filename where to store the generated CSV file
     */
    private static final String OUTPUT_FILENAME = "output/sortResults.csv";
    
    /*
    Whether the arrays in the tests contain random integer values
    */
    private static final boolean RANDOM_ARRAYS = true;

	/*
	The amount of sorting methods 
	*/
	private static final int NUM_SORTS = 7;
    
    /*
    Labels for the sorting methods
    */
    private static final String BUBBLE = "Bubble";
    private static final String SELECTION = "Selection";
    private static final String INSERTION = "Insertion";
    private static final String SHELL = "Shell";
    private static final String MERGE = "Merge";
    private static final String QUICK = "Quick";
    private static final String JAVA = "Java Arrays";

    
    public static void main(String[] args)
    {
		boolean verified = true;
		boolean[] verify = new boolean[NUM_SORTS];
		int testCount = 0; // Looping Variable
		int arrayCount = 0;

		long[][] bubbleSort2DArray = new long[NUM_TESTS][ARRAY_SIZES.length];
		long[][] selectionSort2DArray = new long[NUM_TESTS][ARRAY_SIZES.length];
		long[][] insertionSort2DArray = new long[NUM_TESTS][ARRAY_SIZES.length];
		long[][] shellSort2DArray = new long[NUM_TESTS][ARRAY_SIZES.length];
		long[][] mergeSort2DArray = new long[NUM_TESTS][ARRAY_SIZES.length];
		long[][] quickSort2DArray = new long[NUM_TESTS][ARRAY_SIZES.length];
		long[][] javaSort2DArray = new long[NUM_TESTS][ARRAY_SIZES.length];

		while( testCount < NUM_TESTS && verified )
		{
			System.out.println("Starting sorting test " + (testCount+1) + ":");
			while ( arrayCount < ARRAY_SIZES.length && verified )
			{
				System.out.println("    Starting test of size " + ARRAY_SIZES[arrayCount] + ":");
				int[] bubbleSortArray = new int[ARRAY_SIZES[arrayCount]];
				int[] selectionSortArray = new int[ARRAY_SIZES[arrayCount]];
				int[] insertionSortArray = new int[ARRAY_SIZES[arrayCount]];
				int[] shellSortArray = new int[ARRAY_SIZES[arrayCount]];
				int[] mergeSortArray = new int[ARRAY_SIZES[arrayCount]];
				int[] quickSortArray = new int[ARRAY_SIZES[arrayCount]];
				int[] javaSortArray = new int[ARRAY_SIZES[arrayCount]];

				if ( RANDOM_ARRAYS )
				{
					ArrayMethods.fillArrayRandomInts(bubbleSortArray);
				}
				else
				{
					ArrayMethods.fillArraySortedInts(bubbleSortArray);
				}
				ArrayMethods.copyArray(bubbleSortArray, selectionSortArray); 
				ArrayMethods.copyArray(bubbleSortArray, insertionSortArray); 
				ArrayMethods.copyArray(bubbleSortArray, shellSortArray); 
				ArrayMethods.copyArray(bubbleSortArray, mergeSortArray); 
				ArrayMethods.copyArray(bubbleSortArray, quickSortArray); 
				ArrayMethods.copyArray(bubbleSortArray, javaSortArray); 
				verify[0] = runSortTest(BUBBLE, testCount, arrayCount, 
										bubbleSortArray, bubbleSort2DArray);
				verify[1] = runSortTest(SELECTION, testCount, arrayCount, 
										selectionSortArray, selectionSort2DArray);
				verify[2] = runSortTest(INSERTION, testCount, arrayCount, 
										insertionSortArray, insertionSort2DArray);
				verify[3] = runSortTest(SHELL, testCount, arrayCount, 
										shellSortArray, shellSort2DArray);
				verify[4] = runSortTest(MERGE, testCount, arrayCount, 
										mergeSortArray, mergeSort2DArray);
				verify[5] = runSortTest(QUICK, testCount, arrayCount, 
										quickSortArray, quickSort2DArray);
				verify[6] = runSortTest(JAVA, testCount, arrayCount, 
										javaSortArray, javaSort2DArray);

				for (int i=0; i<NUM_SORTS; i++){
					verified = verified && verify[i];
				}
				arrayCount++;
			}
			testCount++;
			arrayCount=0;
			System.out.println();
		}
		if (!verified) {
			System.out.println("ERROR: Sorting tests not successful!");
			System.out.println();
			for (int i = 0; i < NUM_SORTS; i++) {
				if (verify[i] == false) {
					switch (i) {
						case 0:
							System.out.println("  Bubble Sort Failed!");
							break;
						case 1:
							System.out.println("  Selection Sort Failed!");
							break;
						case 2:
							System.out.println("  Insertion Sort Failed!");
							break;
						case 3:
							System.out.println("  Shell Sort Failed!");
							break;
						case 4:
							System.out.println("  Merge Sort Failed!");
							break;
						case 5:
							System.out.println("  Quick Sort Failed!");
							break;
						case 6:
							System.out.println("  Java Arrays Sort Failed!");
							break;
					}
				}
			}
			System.out.println();
		}
		else
		{
			displayTable(BUBBLE, bubbleSort2DArray);
			displayTable(SELECTION, selectionSort2DArray);
			displayTable(INSERTION, insertionSort2DArray);
			displayTable(SHELL, shellSort2DArray);
			displayTable(MERGE, mergeSort2DArray);
			displayTable(QUICK, quickSort2DArray);
			displayTable(JAVA, javaSort2DArray);
			createCSVFile(OUTPUT_FILENAME, bubbleSort2DArray, selectionSort2DArray,
					insertionSort2DArray, shellSort2DArray, mergeSort2DArray,
					quickSort2DArray, javaSort2DArray);
		}
        
    }

    
    public static boolean runSortTest(String sortName, int testNum, int sizeNum, 
            int[] arrayToSort, long[][] sortResults)
    {
		boolean arraySorted = false;
		long startTime = System.nanoTime();
		long endTime = 0; 
		switch(sortName) {
			case BUBBLE:
//				System.out.println("Unsorted? ");
//				ArrayMethods.displayArray(arrayToSort, 0, arrayToSort.length-1);
				SortingMethods.bubbleSort(arrayToSort);
				
//				System.out.println("Sorted? " );
//				ArrayMethods.displayArray(arrayToSort, 0, arrayToSort.length-1);
				endTime = System.nanoTime();
				System.out.printf("%30s","Bubble Sort Time:");
				break;
			case SELECTION: 
				SortingMethods.selectionSort(arrayToSort);
				endTime = System.nanoTime();
				System.out.printf("%30s","Selection Sort Time:");
				break;
			case INSERTION: 
				SortingMethods.insertionSort(arrayToSort);
				endTime = System.nanoTime();
				System.out.printf("%30s","Insertion Sort Time:");
				break;
			case MERGE: 
				SortingMethods.mergeSort(arrayToSort);
				endTime = System.nanoTime();
				System.out.printf("%30s","Merge Sort Time:");
				break;
			case SHELL: 
				SortingMethods.shellSort(arrayToSort);
				endTime = System.nanoTime();
				System.out.printf("%30s","Shell Sort Time:");
				break;
			case QUICK: 
				SortingMethods.quickSort(arrayToSort);
				endTime = System.nanoTime();
				System.out.printf("%30s","Quick Sort Time:");
				break;
			case JAVA: 
				SortingMethods.javaSort(arrayToSort);
				endTime = System.nanoTime();
				System.out.printf("%30s","Java Sort Time:");
				break;
		}
		arraySorted = ArrayMethods.verifySortedArray(arrayToSort);
		
		sortResults[testNum][sizeNum] = endTime - startTime;
		
		if ( arraySorted )
			System.out.printf("%12.8f (Validation passed)\n", (double)sortResults[testNum][sizeNum]/NANO_IN_SEC );
		else
			System.out.printf("%12.8f (Validation failed)\n", (double)sortResults[testNum][sizeNum]/NANO_IN_SEC );
		
		return arraySorted;
		
    }
    
    public static void displayTable(String sortName, long[][] results)
    {	
		if (results == null)
			throw new IllegalArgumentException("ERROR: results has null value");

		String DIVIDER = "---------------------------------------"
							+ "-----------------------------------------";

		//Heading
		System.out.println(sortName + " Sort Results (In Seconds)");

		// Column Titles 
		System.out.printf("%10s"," ");
		for (int i=0;i<ARRAY_SIZES.length; i++)
			System.out.printf("%10d",ARRAY_SIZES[i]);
		System.out.println();

		// Divider
		System.out.println(DIVIDER);

		// Values
		for (int i=0; i<NUM_TESTS; i++) {
			System.out.printf("%4s%4d  ", "Test", (i+1));
			for (int j=0; j<ARRAY_SIZES.length; j++)
			{
				System.out.printf("%10.4f", (double)results[i][j]/NANO_IN_SEC);
			}
			System.out.println();
		}

		// Averages
		double averageTimes = 0.0;
		System.out.println(DIVIDER);
		System.out.printf("Averages  ");
		for (int i=0; i<ARRAY_SIZES.length; i++) {
			for (int j=0; j<NUM_TESTS; j++)
			{
				averageTimes += (double)results[j][i]/NANO_IN_SEC;
			}
			System.out.printf("%10.4f", averageTimes/NUM_TESTS);
			averageTimes = 0.0;
		}
		System.out.println("\n");
	}
    
    public static boolean createCSVFile(String filename, 
            long[][] bubbleResults, long[][] selectionResults, 
            long[][] insertionResults, long[][] shellResults,
            long[][] mergeResults, long[][] quickResults, long[][] javaResults)
    {
		if ( filename == null || bubbleResults == null || selectionResults == null
				|| insertionResults == null || shellResults == null ||
				mergeResults == null || quickResults == null || javaResults == null)
			throw new IllegalArgumentException("ERROR: createCSVFile illegal parameter");
		
		boolean results = true;
		try {
			File file = new File(filename);
			PrintWriter fileOutput = new PrintWriter(file);
			createCSVTable(fileOutput, BUBBLE, bubbleResults);
			createCSVTable(fileOutput, SELECTION, selectionResults);
			createCSVTable(fileOutput, INSERTION, insertionResults);
			createCSVTable(fileOutput, SHELL, shellResults);
			createCSVTable(fileOutput, MERGE, mergeResults);
			createCSVTable(fileOutput, QUICK, quickResults);
			createCSVTable(fileOutput, JAVA, javaResults);
			fileOutput.close();
			System.out.println("Creating CSV file...");
			System.out.println("CSV file created -- located in file:" + filename);
		}
		catch (FileNotFoundException fnfe) {
			System.out.println("ERROR: FILE " + filename + "NOT FOUND");
			results = false;
		}
        return results;
    }

    
    private static void createCSVTable(PrintWriter writer, String sortName, 
            long[][] sortResults)
    {
		if ( writer == null || sortName == null || sortResults == null)
			throw new IllegalArgumentException("ERROR: createCSVTable illegal parameter");

		writer.println(sortName + " Sort Results (In Seconds)");
		writer.println(",10,100,1000,10000,25000,50000,100000");
		for (int i=0; i<NUM_TESTS; i++) {
			writer.print("Test " + (i+1) + ",");
			for (int j=0; j<ARRAY_SIZES.length; j++)
			{
				if ( j == ARRAY_SIZES.length - 1)
					writer.printf("%.8f", (double)sortResults[i][j]/NANO_IN_SEC);
				else
					writer.printf("%.8f,", (double)sortResults[i][j]/NANO_IN_SEC);
			}
			writer.println();
		}

		double averageTimes = 0.0;
		writer.print("Averages,");
		for (int i=0; i<ARRAY_SIZES.length; i++) {
			for (int j=0; j<NUM_TESTS; j++)
			{
				averageTimes += (double)sortResults[j][i]/NANO_IN_SEC;
			}
			if ( i == ARRAY_SIZES.length - 1)
				writer.printf("%.8f",(averageTimes/NUM_TESTS));
			else
				writer.printf("%.8f,",(averageTimes/NUM_TESTS));
			averageTimes = 0.0;
		}
		writer.println("\n");
		writer.flush();
    }
    
    /**
     * Playground for exercising your FlutteroidFarm class (i.e., this means
     * testing)
     */
    public static void playground()
    {
		
        System.out.println("There ain't no rulez here!");
    }
}
