/*
 * CS310 Assignment 9 - Hash Tables - Open Addressing and Linear Probing
 */
package cs310datastructures;

/**
 * This class creates and entry object for a hash table application that
 * will be storing key / value pairs where each contains character data.
 * 
 * (Do Not Modify)
 * 
 * @author Jeffrey LaMarche
 * @version 1.0 2020-09-16 - Initial Version
 */
public class CipherEntry
{
    /*
    The key character data
    */
    private Character key;
    
    /*
    The value character data
    */
    private Character value;

    /**
     * The default constructor for a CipherEntry object. 
     * <br><br>
     * The key and value will both be null.
     */
    public CipherEntry()
    {
        key = null;
        value = null;
    }

    /**
     * A constructor that allows setting the key and value data members 
     * when a new CipherEntry object is created.
     * 
     * @param key the Character key to set in the CipherEntry object
     * @param value the Character value to set in the CipherEntry object
     */
    public CipherEntry(Character key, Character value)
    {
        this.key = key;
        this.value = value;
    }

    /**
     * Allows obtaining the key from the CipherEntry object
     * 
     * @return the Character value that is assigned to the key
     */
    public Character getKey()
    {
        return key;
    }

    /**
     * Allows obtaining the value from the CipherEntry object
     * 
     * @return the Character value that is assigned to the value
     */
    public Character getValue()
    {
        return value;
    }

    /**
     * Allows setting the CipherEntry key to a new value
     * 
     * @param key the new Character value to assign to the CipherEntry key
     */
    public void setKey(Character key)
    {
        this.key = key;
    }

    /**
     * Allows setting the CipherEntry value to a new value
     * 
     * @param value the new Character value to assign to the CipherEntry value
     */
    public void setValue(Character value)
    {
        this.value = value;
    }

    /**
     * Converts a CipherEntry object 1into a string representation
     * 
     * @return the string representing the CipherEntry object
     */
    @Override
    public String toString()
    {
        return "Key = " + key + ", Value = " + value;
    }
        
    
}
