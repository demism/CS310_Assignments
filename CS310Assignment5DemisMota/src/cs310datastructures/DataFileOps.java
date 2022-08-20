/*
 * CS310 Assignment 5 - Singly Linked Lists
 */
package cs310datastructures;

/**
 * An interface specifying file operations that can be used on a collection
 *  of data (a data structure)
 * 
 * @author Jeffrey LaMarche
 * @version 1.0 2020-Aug-20 Initial Version
 */
public interface DataFileOps
{
    /**
     * Will use the contents specified by the file name to populate
     *  the data structure
     * 
     * @param filename the name of the file to attempt loading data from
     * 
     * @return true if the file data could be loaded into the data structure,
     *      false otherwise
     */
    public boolean loadData(String filename);
    
    /**
     * Will use the specified file to save the contents of the collection in
     *  a useful format for loading later
     * 
     * @param filename the name of the file to attempt saving data to
     * 
     * @return true if the collection data could be saved to the file,
     *      false otherwise
     */
    public boolean saveData(String filename);
}
