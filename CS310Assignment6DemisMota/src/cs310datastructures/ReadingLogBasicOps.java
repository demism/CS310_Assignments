/*
 * CS310 Assignment 6 - Java Collected LinkedList and Iterators
 */
package cs310datastructures;

/**
 * Provides the interface specification for the basic operations performed on a 
 *  finished book reading log
 * 
 * @author Jeffrey LaMarche
 * @version 1.0 2020-Aug-17 Initial Version 
 * @version 1.1 2020-Aug-23 Updated for assignment five
 */
public interface ReadingLogBasicOps
{
    /**
     * Adds a book finished entry to the reading log
     * 
     * @param BookReadEntry the book entry to add to the reading log
     * 
     * @return true if the book entry was successfully added, false otherwise
     */
    public boolean addBook(BookReadEntry bookEntryObj);
    
    /**
     * Removes a book from the reading log based on the title provided
     * 
     * @param bookTitle the name of the particular book to remove
     * 
     * @return a reference to the book finished entry that was removed, 
     * null if the item does not exist
     */
    public BookReadEntry removeBook(String bookTitle);
    
    /**
     * Locates a book in the reading log based on the name provided
     * 
     * @param bookTitle the title of the particular book to look for
     * 
     * @return a reference to the book finished entry or 
     * null if the item does not exist
     */
    public BookReadEntry findBook(String bookTitle);
    
    /**
     * Displays the data in the reading log
     */
    public void displayReadingLog();
}
