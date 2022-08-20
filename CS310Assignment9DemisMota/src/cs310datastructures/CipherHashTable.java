/*
 * CS310 Assignment 9 - Hash Tables - Open Addressing and Linear Probing
 */
package cs310datastructures;

/**
 * TODO Class Description
 *
 * @author Jeffrey LaMarche
 * @version 1.0 2020-09-19 - Template Version
 * 
 * @author Demis Mota
 * @version 1.1 2022-07-08 - Initial Implementation
 */
public class CipherHashTable
{

    /*
    The default size for a cipher hash table.
    
    It is a prime number large enough to hold  
    digits 0 to 9 and letters A to Z
     */
    private static final int HASH_TABLE_SIZE = 37;

    /*
    The array used for the hash table containing CipherEntry objects
     */
    private CipherEntry[] cipherHashTable;

    /*
    The number of entries currently held in the hash table
     */
    private int numberOfEntries;

    
	/**
	 * Default constructor creates an empty CipherHashTable 
	 */
    public CipherHashTable()
    {
        cipherHashTable = new CipherEntry[HASH_TABLE_SIZE];
		numberOfEntries = 0;
    }

    /**
	 * Get the number of entries added to cipherHashTable
	 * @return integer variable with said number 
	 */ 
    public int getNumberOfEntries()
    {
        return numberOfEntries;
    }

    /**
     * Computes the hash code value for a specific item. If the item is invalid
     * in any way, then the method returns -1 to signal that it failed.
     * 
     * (Do Not Modify)
     *
     * @param item a Character object
     *
     * @return an integer value that represents the hash code value for the item
     */
    private int hashCodeValue(Character item)
    {
        int hashValue = -1;

        // this hash code value causes a specific sequence of collisions that
        // ensure that the arrays are being treated circularly
        hashValue = item.hashCode() - 16;

        return hashValue;
    }

    /**
     * Computes the compressed hash code value from the hash value passed into
     * the method. This ensures that the new value will fit within the hash
     * table array size.
     * 
     * (Do Not Modify)
     *
     * @param hashCodeValue the original hash code value computed
     *
     * @return a compressed hash code value that fits in the table size
     */
    private int compressHashCodeValue(int hashCodeValue)
    {
        return hashCodeValue % HASH_TABLE_SIZE;
    }

    /**
	 * Adds a key value pair to cipherHashTable
	 * @param key the key for the hash
	 * @param value the associated value for the key
	 * @return boolean of true if value was added successfully. 
	 */
    public boolean addCipherEntry(Character key, Character value)
    {
        boolean wasEntryAdded = false;
		int startIndex = 0;

		if ( numberOfEntries != cipherHashTable.length - 1 && key != null &&
				value != null )
		{
			if (findCipherValue(key) == null)
			{
				CipherEntry newEntry = new CipherEntry(key,value);
				int hashCode = hashCodeValue(key);
				int hashIndex = compressHashCodeValue(hashCode);
				startIndex = hashIndex;

				while (hashIndex != -1 && cipherHashTable[hashIndex] != null)
				{
					if (hashIndex == HASH_TABLE_SIZE  - 1)
						hashIndex = 0;
					else
						hashIndex++;
					if (hashIndex == startIndex)
						hashIndex = -1;
				}
				if ( cipherHashTable[hashIndex] == null )
				{
					cipherHashTable[hashIndex] = newEntry;
					numberOfEntries++;
					wasEntryAdded = true;
				}
			}
		}
        return wasEntryAdded;
    }

    /**
	 * Returns the value associated with the key 
	 * @param key a Character representing the key
	 * @return a Character with the value 
	 */ 
    public Character findCipherValue(Character key)
    {
        Character foundValue = null;
		
		if ( key != null )
		{
			int hashIndex = compressHashCodeValue(hashCodeValue(key));
			int startIndex = hashIndex;
			while ( hashIndex != -1 && cipherHashTable[hashIndex] != null )
			{
				Character tempKey = cipherHashTable[hashIndex].getKey();
				if ( tempKey == key )
				{
					foundValue = cipherHashTable[hashIndex].getValue();
					hashIndex = -1;
				}
				else
				{
					if (hashIndex == HASH_TABLE_SIZE  - 1)
						hashIndex = 0;
					else
						hashIndex++;
					if (hashIndex == startIndex)
						hashIndex = -1;
				}
			}
		}
        return foundValue;
    }

	/**
	 * Displays a CipherHashTable
	 * 	includes empty entries.
	 */
    public void displayCipherHashTable()
    {
		System.out.println("Displaying Entire Hash Table:");
		System.out.println("--------------------------------");
		for (int i = 0; i < 37; i++)
		{
			System.out.printf("  Index[%2d]: %s\n",i, 
				cipherHashTable[i] == null ?
						"Is Empty" : cipherHashTable[i].toString());
		}
		System.out.println();
    }

    /**
	 * Displays each key,value entry in a CipherHashTable
	 */ 
    public void displayCipherEntries()
    {
		int entryNum = 1;
		System.out.println("Displaying Entries in Hash Table:");
		System.out.println("---------------------------------");
		if ( numberOfEntries > 0 )
		{
			for (int i = 0; i < 37; i++)
			{
				if (cipherHashTable[i] != null)
					System.out.printf("  Entry %2d: %s\n", entryNum++,
						cipherHashTable[i].toString() );
			}
		}
		else
		{
			System.out.println("  Table Contains No Entries");
			System.out.println();
		}
		System.out.println();
    }

}
