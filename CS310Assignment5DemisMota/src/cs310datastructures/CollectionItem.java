/*
 * CS310 Assignment 5 - Singly Linked Lists
 */
package cs310datastructures;

import java.util.Scanner;

/**
 * An abstract class that defines the top-level hierarchy for items
 *  that someone might collect.  
 *
 * @author Jeffrey LaMarche
 * @version 1.0 2020-July-09 Initial Version
 * @version 1.1 2020-Aug-15 Added isPositiveInteger method for input validation
 * @version 1.2 2020-Aug-20 Added toFileFormat method for read/write file ops
 * @version 1.3 2020-Aug-23 Updated to CollectionItem
 */
public abstract class CollectionItem
{

    private String itemName;    // the name of the item

    /**
     * The default constructor, which sets the item name to an empty string.
     */
    public CollectionItem()
    {
        itemName = "";
    }

    /**
     * Constructor that allows setting the item name to a specific value.
     *
     * @param itemName a string containing the item name for the object
     */
    public CollectionItem(String itemName)
    {
        this.itemName = itemName;
    }

    /**
     * Allows retrieving the name of the item from the object.
     *
     * @return a string reference containing the item name. Could be the empty
     * string.
     */
    public String getItemName()
    {
        return itemName;
    }

    /**
     * Allows setting the name of the item. If null is passed to the method, no
     * change is made to the item name.
     *
     * @param itemName the string to set as the item name
     */
    public void setItemName(String itemName)
    {
        if (itemName != null)
        {
            this.itemName = itemName;
        }
    }

    /**
     * Determines whether a given string is a positive integer value
     *
     * @param strNum string containing value to check for being a positive
     * integer
     *
     * @return true is the string contains a positive integer, false otherwise
     */
    public static boolean isPositiveInteger(String strNum)
    {
        final char LOW_INT_VALUE = '0';
        final char HIGH_INT_VALUE = '9';
        final String BAD_STRING = "";

        // if the string is null or empty, it is not valid
        if (strNum == null || strNum.equals(BAD_STRING))
        {
            return false;
        }

        // check each character, making sure it is between 0 and 9 inclusive
        for (int i = 0; i < strNum.length(); i++)
        {
            if (strNum.charAt(i) < LOW_INT_VALUE
                    || strNum.charAt(i) > HIGH_INT_VALUE)
            {
                return false;
            }
        }

        // all tests passed, so string contains a positive integer
        return true;
    }

    /**
     * Allows entering information for an item. The implementation is left to
     * the sub-classes that inherit from this class.
     *
     * @param input The Scanner object used for obtaining input
     */
    public abstract void enterItem(Scanner input);

    /**
     * Displays information about the item. The implementation is left to
     * sub-classes that inherit this class.
     */
    public abstract void displayItem();
    
    /**
     * Converts the item into a format needed for saving/loading from a file.
     *  The specific format is left to the sub-classes that inherit from 
     *  this class. 
     * 
     * @return a string representation of the item suitable for use in 
     *      writing or reading from a text file
     */
    public abstract String toFileFormat();
}
