/*
 * CS310 Assignment 4 - ArrayLists, Data Persistence, and Refactoring
 */
package cs310datastructures;

/**
 * Provides the interface specification for the operations of a shelving
 *  system. 
 * 
 * @author Jeffrey LaMarche
 * @version 1.0 2020-Aug-17 Initial Version 
 */
public interface ShelvingOperations
{
    /**
     * Adds an item to the shelving system
     * 
     * @param item the item to add to the shelves
     * @return true if the item was successfully added, false otherwise
     */
    public boolean addItem(ShelfItem item);
    
    /**
     * Adds an item to the shelving system at the specified position
     * 
     * @param item the item to add to the shelves
     * @return true if the item was successfully added, false otherwise
     */
    public boolean addItem(ShelfItem item, int position);
    
    /**
     * Removes an item from the shelving system based on the name provided
     * 
     * @param itemName the name of the particular item to look for to remove
     * @return a reference to the item removed or null if the item does not exist
     */
    public ShelfItem removeItem(String itemName);
    
    /**
     * Locates an item in the shelving system based on the name provided
     * 
     * @param itemName the name of the particular item to look for to remove
     * @return a reference to the item or null if the item does not exist
     */
    public ShelfItem findItem(String itemName);
    
    /**
     * Displays the items on the shelves
     */
    public void displayShelves();
}
