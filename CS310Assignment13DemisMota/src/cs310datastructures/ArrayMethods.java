/*
 * CS310 Assignment 13 & 14 Sorting Experimentation
 */
package cs310datastructures;

import java.util.Random;

/**
 * Utility class for an integer array, provides various utility methods
 *
 * @author Jeffrey LaMarche
 * 
 * @version 1.0 2020-Nov-11
 * Template Version
 *
 * @author Demis Mota
 * @version 1.1 2022-Aug-06 - Initial Implementation
 */
public class ArrayMethods
{
    /**
     * Private default constructor, which means the class cannot be instantiated
     */
    private ArrayMethods()
    {
        // empty constructor, so that ArrayMethods cannnot be instantiated
    }

    public static void fillArraySortedInts(int[] array)
    {
		if (array == null)
			throw new IllegalArgumentException("ERROR: null array");
		for (int i=0; i < array.length; i++)
			array[i] = i+1;
    }

    public static void fillArrayRandomInts(int[] array)
    {
		if (array == null)
			throw new IllegalArgumentException("ERROR: null array");

		Random rand = new Random();
		int randomNum = rand.nextInt();

		for (int i=0; i < array.length; i++)
		{
			array[i] = randomNum;
			randomNum = rand.nextInt();
		}
    }

    public static void copyArray(int[] arraySource, int[] arrayDestination)
    {
		if (arraySource == null)
			throw new IllegalArgumentException("ERROR: null array source");
		if (arrayDestination == null)
			throw new IllegalArgumentException("ERROR: null array destination");
		if (arraySource.length != arrayDestination.length)
			throw new IllegalArgumentException("ERROR: array sizes do not match"); 

		for (int i=0; i<arraySource.length; i++)
			arrayDestination[i] = arraySource[i];

    }

    public static void swapTwoArrayValues(int[] array, int indexOne, int indexTwo)
    {
		if ( array == null)
			throw new IllegalArgumentException("ERROR: null array!");
		if ( indexOne > 0 || indexOne >= array.length )
			throw new IllegalArgumentException("ERROR: Value indexOne"
					+ " is out of bounds"); 
		if ( indexTwo > 0 || indexTwo >= array.length )
			throw new IllegalArgumentException("ERROR: Value indexTwo"
					+ " is out of bounds"); 

		int temp = array[indexOne];
		array[indexOne] = array[indexTwo];
		array[indexTwo] = temp;
    }

    public static boolean verifySortedArray(int[] array)
    {
		if ( array == null)
			throw new IllegalArgumentException("ERROR: null array!");

		for ( int i=1; i < array.length; i++ )
			if ( array[i] < array[i-1])
				return false;
        return true;
    }

    /**
     * Displays the contents of the array from low index to high index, each
     * value separated by a comma. <br><br>
     * 
     * Fun Fact: You can create this array, which is empty with length zero. 
     * <br><br>
     * 
     * int[] emptyArray = new int[0]; <br><br>
     * 
     * It is not the same as the array reference being null. <br><br>
     *
     * @param array the array of integers to display
     * @param lowIndex the low index in the array being displayed (inclusive)
     * @param highIndex the high index in the array being displayed (inclusive)
     */
    public static void displayArray(int[] array, int lowIndex, int highIndex)
    {
        final String HEADING = "displayArray: ";
        String msg;

        if (array == null)
        {
            msg = String.format("%sArray is null", HEADING);
            throw new IllegalArgumentException(msg);
        }

        if (array.length > 0)   // allows an empty array to be printed
        {
            if (lowIndex < 0 || lowIndex >= array.length)
            {
                msg = String.format("%s", HEADING);
                msg = msg + String.format("Low index %d ", lowIndex);
                msg = msg + String.format("is out of bounds");
                throw new IllegalArgumentException(msg);
            }

            if (highIndex < 0 || highIndex >= array.length)
            {
                msg = String.format("%s", HEADING);
                msg = msg + String.format("High index %d ", highIndex);
                msg = msg + String.format("is out of bounds");
                throw new IllegalArgumentException(msg);
            }

            if (lowIndex > highIndex)
            {
                msg = String.format("%s", HEADING);
                msg = msg + String.format("Low index %d is greater ", lowIndex);
                msg = msg + String.format("than high index %d", highIndex);
                throw new IllegalArgumentException(msg);
            }
        }

        System.out.print("[");

        // if the array is not empty, display the contents
        if (array.length > 0)  
        {
            for (int i = lowIndex; i <= highIndex; i++)
            {
                // display everything but last value
                if (i < highIndex)
                {
                    System.out.print(array[i] + ", ");
                }
                // display the last value
                else
                {
                    System.out.print(array[i]);
                }
            }
        }

        System.out.println("]");
    }

}
