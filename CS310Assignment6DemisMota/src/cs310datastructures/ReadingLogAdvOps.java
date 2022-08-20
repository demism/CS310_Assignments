/*
 * CS310 Assignment 6 - Java Collected LinkedList and Iterators
 */
package cs310datastructures;

import java.util.ArrayList;

/**
 * Provides the interface specification for the basic operations performed on a 
 *  finished book reading log
 * 
 * @author Jeffrey LaMarche
 * @version 1.0 2020-Aug-29 Initial Version 
 */
public interface ReadingLogAdvOps
{
    /**
     * Removes all books from the reading log that have the same title provided
     * 
     * @param bookTitle the name of the particular book to remove
     * 
     * @return a reference to an ArrayList containing all of the book entries 
     * removed or null if the item does not exist
     */
    public ArrayList<BookReadEntry> removeAllBooks(String bookTitle);
    
    /**
     * Locates all books in the reading log that have the same title provided
     * 
     * @param bookTitle the title of the particular book to look for
     * 
     * @return a reference to an ArrayList containing all of the book entries 
     * located or null if the item does not exist
     */
    public ArrayList<BookReadEntry> findAllBooks(String bookTitle);
    
    /**
     * Displays the data in the reading log in reverse order
     */
    public void displayReadingLogReverse();
}
