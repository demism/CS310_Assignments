/*
 * CS310 Assignment 9 - Hash Tables - Open Addressing and Linear Probing
 */
package cs310datastructures;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * TODO Class Description
 * 
 * @author Jeffrey LaMarche
 * @version 1.0 2020-09-19 - Template Version
 * 
 * @author Demis Mota
 * @version 1.1 2022-07-08 - Initial Implementation
 */
public class CaesarCipher
{   
    /**
     * Restricted default constructor
     */
    private CaesarCipher()
    {
        // Given private access to prevent someone from being able to 
        // instantiate an CaesarCipher object
    }
    
	/**
	 * Encrypts a file. 
	 * @param inputFile file to be encrypted
	 * @param outputFile the encrypted file
	 * @param shiftValue the shift value
	 * @return true if file was encrypted 
	 */
    public static boolean encryptFile(String inputFile, 
            String outputFile, int shiftValue)
    {
        boolean cipherSuccess = false;

		CipherHashTable encTable = new CipherHashTable();
		cipherSuccess = buildCipher(encTable,shiftValue,true);
		if(cipherSuccess)
		{
			encodeFile(inputFile, outputFile, encTable);
		}

        return cipherSuccess;
    }

   /**
	* Decrypts a file. 
	* @param inputFile file to be decrypted
	* @param outputFile decrypted file
	* @param shiftValue the shift value
	* @return true if file was decrypted 
	*/ 
    public static boolean decryptFile(String inputFile, 
            String outputFile, int shiftValue)
    {
        boolean cipherSuccess = false;

		CipherHashTable encTable = new CipherHashTable();
		cipherSuccess = buildCipher(encTable,shiftValue,false);
		if(cipherSuccess)
		{
			encodeFile(inputFile, outputFile, encTable);
		}

        return cipherSuccess;
    }

    /**
	 * Applies the cipher created 
	 * @param inputFile file to be encoded
	 * @param outputFile completely encoded file
	 * @param cipher the cipher created using either encrypt/decrypt
	 * @return true if file is correctly encoded with cipher 
	 */ 
    private static boolean encodeFile(String inputFile, 
            String outputFile, CipherHashTable cipher)
    {
        boolean cipherSuccess = false;

		File infile = new File(inputFile);
		File outfile =  new File(outputFile);
		Scanner inputScan;
		PrintWriter pwOutput;

		try {
			inputScan = new Scanner(infile);
			pwOutput = new PrintWriter(outfile);
			while(inputScan.hasNext())
			{
				String line = inputScan.nextLine();
				line = line.toUpperCase();
				for (Character c : line.toCharArray())
				{
					Character tmp = cipher.findCipherValue(c);
					if (tmp != null)
					{
						pwOutput.print(tmp);
						pwOutput.print(" ");
					}
				}pwOutput.println();
			}
			inputScan.close();
			pwOutput.close();
        
			cipherSuccess = true;
		}
		catch (Exception e)
		{
			System.out.println("ERROR!" + e.getMessage());
		}
        return cipherSuccess;
    }

    /**
	 * Builds the cyper, either encrypted or decrypted
	 * @param cipher holds the cipher
	 * @param shiftValue the amount to shift value by
	 * @param encrypt true if you'd like to encrypt, or false for decrypt
	 * @return true if cipher was created successfully. 
	 */ 
    private static boolean buildCipher(CipherHashTable cipher,
            int shiftValue, boolean encrypt)
    {
        /*
        Some helpful constants
        */
        final int NUM_DIGITS = 10;
        final int NUM_LETTERS = 26;
        final char START_DIGIT = '0';
        final char END_DIGIT = '9';
        final char START_LETTER = 'A';
        final char END_LETTER = 'Z';
        
        boolean buildSuccess = false;

		int digitShiftValue = shiftValue % NUM_DIGITS;
		int charShiftValue = shiftValue % NUM_LETTERS;

		char digitIter = START_DIGIT; 
		while(digitIter <= END_DIGIT)
		{
			int digitVal = (int)(digitIter - START_DIGIT);  
			digitVal = (digitVal+shiftValue) % NUM_DIGITS ;
			char itemValue = (char)(digitVal+'0');

			if (encrypt == true)
			{
				cipher.addCipherEntry(digitIter, itemValue);
				digitIter++;
			}
			else
			{
				cipher.addCipherEntry(itemValue, digitIter);
				digitIter++;
			}
		}
		
		char alphaIter = START_LETTER; 
		while(alphaIter <= END_LETTER)
		{
			int digitVal = (int)(alphaIter - START_LETTER);  
			digitVal = (digitVal+shiftValue) % NUM_LETTERS;
			char itemValue = (char)(digitVal+'A');

			if (encrypt == true)
			{
				cipher.addCipherEntry(alphaIter, itemValue);
				alphaIter++;
			}
			else
			{
				cipher.addCipherEntry(itemValue, alphaIter);
				alphaIter++;
			}
		}
		if ( cipher.getNumberOfEntries() == 36 )
			buildSuccess = true;
        return buildSuccess;
    }

    /**
     * Performs some basic tests on the cipher hash table
     * (Do Not Modify - Return to original state if you do)
     * 
     * @param shiftValue the shift value to use when testing the cipher table
     */
    public static void testCipherHashTable(int shiftValue)
    {
        /*
        With a shift value of 0, the key and value will be the same. 
        
        When the value is a multiple of 10, the digit key/value pair is equal
        
        When the value is a multiple of 26, the letter key/value pair is equal
        
        When a multiple of both, digit/letter key/value pairs are equal
        */
        
        boolean addSuccess = false;
        CipherHashTable emptyCipher = new CipherHashTable();
        
        System.out.println("-- Displaying Empty Cipher Table -- ");
        System.out.println();
        
        // display the table and entries
        emptyCipher.displayCipherHashTable();
        emptyCipher.displayCipherEntries();
        
        System.out.println("-- Checking Find Value Method -- ");
        System.out.println();
        
        Character value = emptyCipher.findCipherValue('A');
        
        System.out.println("Value == null : " + (value == null));
        System.out.println();
        
        System.out.println("-- Displaying Filling Table Results -- ");
        System.out.println();
        
        // exhaust the cipher hash table space by filling it
        for(int i = '0'; i <= 'Z'; i++)
        {
            addSuccess = emptyCipher.addCipherEntry((char)i, (char)i);
            
            if(addSuccess)
            {
                System.out.printf("ADD SUCCESS");
            }
            else
            {
                System.out.printf("ADD FAILURE");
            }
            
            System.out.printf(" (%2d): key = %c, value = %c\n", 
                    (i - '0' + 1), (char)i, (char)i);
        }
        
        System.out.println();
        
        System.out.println("-- Checking Find Value Method -- ");
        System.out.println();
        
        value = emptyCipher.findCipherValue('A');
        
        if(value != null)
        {
            System.out.println("Value == 'A' : " + (value.equals('A')));
        }
        else
        {
            System.out.println("ERROR: Value is null!");
        }
        
        System.out.println();
        
        System.out.println("-- Displaying Completely Full Cipher Table -- ");
        System.out.println();
        
        // display the table and entries
        emptyCipher.displayCipherHashTable();
        emptyCipher.displayCipherEntries();
        
        boolean cipherSuccess = false;
        
        CipherHashTable encryptCipher = new CipherHashTable();
        
        // build the cipher for encrypting
        cipherSuccess = buildCipher(encryptCipher, shiftValue, true);

        System.out.printf("-- Displaying Encryption Cipher Table -- "
                + "Shift: %d --\n", shiftValue);
        System.out.println();
        
        System.out.printf("Table Creation Status: %s", 
                cipherSuccess ? "SUCCESS\n" : "FAILURE\n");
        System.out.println();
        
        // display the table and entries
        encryptCipher.displayCipherHashTable();
        encryptCipher.displayCipherEntries();
        
        CipherHashTable decryptCipher = new CipherHashTable();
        
        // build the cipher for decrypting
        cipherSuccess = buildCipher(decryptCipher, shiftValue, false);

        System.out.printf("-- Displaying Decryption Cipher Table -- "
                + "Shift: %d --\n", shiftValue);
        System.out.println();
        
        System.out.printf("Table Creation Status: %s", 
                cipherSuccess ? "SUCCESS\n" : "FAILURE\n");
        System.out.println();
        
        // display the table and entries
        decryptCipher.displayCipherHashTable();
        decryptCipher.displayCipherEntries();
    }
}
