/*
 * CS310 Assignment 13 & 14 Sorting Experimentation
 */
package cs310datastructures;

import java.util.Arrays;    // used in assignment 14

/**
 * Functional Class containing various Sorting Methods
 *
 * @author Jeffrey LaMarche
 * 
 * @version 1.0 2020-Nov-11
 * Template Version
 * 
 * @author Demis Mota 
 * @version 1.1 2022-Aug-06 UML Translation requires implementation
 */
public class SortingMethods
{
	private SortingMethods() {
		// Empty Constructor 
	}
	
	/**
	 * Bubble Sort Implementation
	 * @param numbers the array to be sorted
	 */
	public static void bubbleSort(int[] numbers) {
		
		if ( numbers == null )
			throw new IllegalArgumentException("ERROR! Null Array Value");
		
		int n = numbers.length;
		
		for (int j=0; j < n; j++){
			for (int i=0; i < n-1; i++)   
			{
				if (numbers[i] > numbers[j])
				{                     
					ArrayMethods.swapTwoArrayValues(numbers, i, j);
				}
			}
		}
	}
	
	/**
	 * Selection Sort Implementation
	 * @param numbers the array to be sorted
	 */
	public static void selectionSort(int[] numbers){
		if ( numbers == null )
			throw new IllegalArgumentException("ERROR! Null Array Value");


		int n = numbers.length;
 
        for (int i = 0; i < n-1; i++)
        {
            int minVal = i;
            for (int j = i+1; j < n; j++) {
                if (numbers[j] < numbers[minVal])
                    minVal = j;
			}
			ArrayMethods.swapTwoArrayValues(numbers, minVal, i);
        }
	}
	
	/**
	 * Insertion Sort Implementation
	 * @param numbers the array to be sorted
	 */
	public static void insertionSort(int[] numbers){
		if ( numbers == null )
			throw new IllegalArgumentException("ERROR! Null Array Value");
		
		int n = numbers.length;

        for (int i = 1; i < n; ++i) {
            int key = numbers[i];
            int j = i - 1;
  
            while (j >= 0 && numbers[j] > key) {
                numbers[j + 1] = numbers[j];
                j = j - 1;
            }
            numbers[j + 1] = key;
		}
	}
	
	/**
	 * Shell Sort Implementation
	 * @param numbers the array to be sorted
	 */
	public static void shellSort(int[] numbers){
		if ( numbers == null )
			throw new IllegalArgumentException("ERROR! Null Array Value");

		int n = numbers.length;
  
        for (int gap = n/2; gap > 0; gap /= 2)
        {
            for (int i = gap; i < n; i += 1)
            {
                int temp = numbers[i];
  
                int j;
                for (j = i; j >= gap && numbers[j - gap] > temp; j -= gap)
                    numbers[j] = numbers[j - gap];
  
                numbers[j] = temp;
            }
        }
	}

	
	/**
	 * Merge Sort Implementation outward facing
	 * @param numbers the array to be sorted
	 */
	public static void mergeSort(int[] numbers){
		if ( numbers == null )
			throw new IllegalArgumentException("ERROR! Null Array Value");

		if (numbers.length > 1) 
		{
			int[] temp = new int[numbers.length]; 
			mergeSort(numbers, temp, 0, numbers.length - 1);
		}
	}
	
	/**
	 * Private Merge Sort helper function does the recursive handling
	 * @param numbers the array to be sorted
	 * @param temp a temporary array to hold a subarray
	 * @param leftIndex the left target for the subarray
	 * @param rightIndex the right target for the subarray
	 */
	private static void mergeSort(int[] numbers, int[] temp, 
							int leftIndex, int rightIndex){
		if (leftIndex != rightIndex) // leftIndex == rightIndex is the base case 
		{
    		// select midpoint
    		int midIndex = (leftIndex + rightIndex) / 2;
			// recursively merge sort first half 
			mergeSort(numbers, temp, leftIndex, midIndex);
			// recursively merge sort second half 
			mergeSort(numbers, temp, midIndex + 1, rightIndex);
			// copy subnumbersay to temp numbersay
			for (int i = leftIndex; i <= rightIndex; i++) {
				temp[i] = numbers[i];
			}
			// Do the merge operation back to numbers 
			int leftCounter = leftIndex;
			int rightCounter = midIndex + 1;
			for (int curr = leftIndex; curr <= rightIndex; curr++) {
			// left partition exhausted
				if (leftCounter == midIndex + 1) {
					numbers[curr] = temp[rightCounter]; 
					rightCounter++; 
				}
				else if (rightCounter > rightIndex) {
					numbers[curr] = temp[leftCounter]; 
					leftCounter++;
				}
				else if (temp[leftCounter] < temp[rightCounter])
				{
					numbers[curr] = temp[leftCounter]; 
					leftCounter++;
				}
				else
				{
					numbers[curr] = temp[rightCounter]; 
					rightCounter++;
				} 
			}
		}
	}
	
	/**
	 * Quick Sort Implementation: Outward facing
	 * @param numbers the array to be sorted
	 */
	public static void quickSort(int[] numbers){
		if ( numbers == null )
			throw new IllegalArgumentException("ERROR! Null Array Value");	
		
		int n = numbers.length;
		
		quickSort(numbers,0, n-1);
	}

	/**
	 * The recursive private helper function for implementing quickSort
	 * @param numbers the array to be sorted
	 * @param lowIndex the low side of the array partition
	 * @param highIndex the high side of the array partition also the pivot index
	 */
	private static void quickSort(int[] numbers, int lowIndex, int highIndex){
		if (lowIndex < highIndex) 
		{
			
			int partIndex = quickSortPartition(numbers, lowIndex, highIndex);

			quickSort(numbers, lowIndex, partIndex - 1);
			quickSort(numbers, partIndex + 1, highIndex);
		}
	}
	/**
	 * The partition to be sorted
	 * @param numbers the subarray to be sorted
	 * @param lowIndex the low index of the subArray
	 * @param highIndex the pivot value and the last element in the numbers array
	 * @return the new pivot
	 */
	private static int quickSortPartition(int[] numbers, int lowIndex, int highIndex){
		     
		int pivot = numbers[highIndex]; 

		int i = (lowIndex - 1); 

		for(int j = lowIndex; j <= highIndex - 1; j++)
		{
			if (numbers[j] < pivot) 
			{
				i++; 
				ArrayMethods.swapTwoArrayValues(numbers, i, j);
			}
		}
		ArrayMethods.swapTwoArrayValues(numbers, i + 1, highIndex);
		return (i + 1);
	}

	/**
	 * The java.util.Arrays sort method 
	 * @param numbers the array to be sorted
	 */
	public  static void javaSort(int[] numbers){
		if ( numbers == null )
			throw new IllegalArgumentException("ERROR! Null Array Value");
		
		Arrays.sort(numbers);
	}
}
