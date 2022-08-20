/*
 * CS310 Assignment 9 - Hash Tables - Open Addressing and Linear Probing
 */
package cs310datastructures;

import java.io.File;
import java.lang.NumberFormatException;
import java.util.Scanner;

/**
 * This program provides the ability to use the Caesar Cipher algorithm to
 * encrypt and decrypt files based on a given shift value. There is also the 
 * ability to test the cipher table that is being generated. 
 *
 * (Do Not Modify, except for playground method)
 * 
 * @author Jeffrey LaMarche
 * @version 1.0 2020-09-16 - Initial Version
 * @version 1.1 2021-Jan-15 - Added in playground method for student testing
 */
public class CS310Assignment9
{

    // defines constants for menu item choices, would probably be better as
    //  an enumerated type
    private static final int ENCRYPT_FILE = 1;
    private static final int DECRYPT_FILE = 2;
    private static final int TEST_CIPHER_TABLE = 3;
    private static final int PLAYGROUND = 4;
    private static final int QUIT_PROGRAM = 5;

    // private constant values
    private static final int LOW_MENU_CHOICE = 1;
    private static final int HIGH_MENU_CHOICE = QUIT_PROGRAM;

    /*
    Relative directory path where files will be loaded
     */
    private static final String inputDirectoryPath = "input/";

    /*
    Relative directory path where files will be saved
     */
    private static final String outputDirectoryPath = "output/";

    /**
     * The main method for the entire program. This makes everything work. In
     * this case this displays the main menu, gets user input, and runs
     * different parts of the program based on what the user selected.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {        
        int userChoice = -1;
        int shiftValue = -1;
        String filename = null;
        String inputFile = null;
        String outputFile = null;
        boolean cipherSuccessful = false;

        Scanner input = new Scanner(System.in);

        System.out.println("Welcome to the Caesar Cipher Program 1.0!");

        System.out.println();

        // keep running until the user decides to quit
        while (userChoice != QUIT_PROGRAM)
        {
            displayMenu();

            userChoice = getUserMenuInput(input);

            System.out.println();

            switch (userChoice)
            {
                case ENCRYPT_FILE:
                    filename = getFileName(input);
                    shiftValue = getCipherShiftValue(input);
                    inputFile = inputDirectoryPath + filename;
                    outputFile = outputDirectoryPath + filename;
                    
                    cipherSuccessful = CaesarCipher.encryptFile(
                            inputFile, outputFile, shiftValue
                    );

                    if(cipherSuccessful)
                    {
                        System.out.println();
                        System.out.printf("The file %s was successfully", 
                                filename);
                        System.out.printf(" encrypted and is\nstored in the");
                        System.out.printf(" output directory with the same");
                        System.out.printf(" file name.\n");
                    }
                    else
                    {
                        System.out.printf("ERROR - The file %s was "
                                + "not encrypted!\n", filename);
                    }
                    
                    break;
                case DECRYPT_FILE:
                    filename = getFileName(input);
                    shiftValue = getCipherShiftValue(input);
                    inputFile = inputDirectoryPath + filename;
                    outputFile = outputDirectoryPath + filename;
                    
                    cipherSuccessful = CaesarCipher.decryptFile(
                            inputFile, outputFile, shiftValue
                    );
                    
                    if(cipherSuccessful)
                    {
                        System.out.println();
                        System.out.printf("The file %s was successfully",
                                filename);
                        System.out.printf(" decrypted and is\nstored in the");
                        System.out.printf(" output directory with the same");
                        System.out.printf(" file name.\n");
                    }
                    else
                    {
                        System.out.printf("ERROR - The file %s was "
                                + "not decrypted!\n", filename);
                    }
                    
                    break;
                case TEST_CIPHER_TABLE:
                    shiftValue = getCipherShiftValue(input);
                    System.out.println();
                    
                    CaesarCipher.testCipherHashTable(shiftValue);
                    
                    break;
                case PLAYGROUND:
                    playground();
                    
                    break;
                case QUIT_PROGRAM:
                    System.out.print("Thanks for using the Caesar Cipher");
                    System.out.println(" Program 1.0!");
                    System.exit(0);

                    break;
                default:
                    System.out.println("ERROR: This should not be possible!");
                    System.out.println("Why do humans always break things?");
                    System.out.println("The robot overlords are displeased...");

                    break;
            }

            System.out.println();
        }

    }

    /**
     * Displays the menu interface to standard output
     */
    private static void displayMenu()
    {
        System.out.println("Caesar Cipher Main Menu: ");
        System.out.println("--------------------------------------");
        System.out.println(" " + ENCRYPT_FILE + ". Encrypt File");
        System.out.println(" " + DECRYPT_FILE + ". Decrypt File");
        System.out.println(" " + TEST_CIPHER_TABLE + ". Test Cipher Hash Table");
        System.out.println(" " + PLAYGROUND + ". Run Playground");
        System.out.println(" " + QUIT_PROGRAM + ". Quit Program");
        System.out.println("--------------------------------------");
    }

    /**
     * Obtains the user input for the menu selection. Ensures that the user
     * choice is a valid value based on the current menu options.
     *
     * @param input the scanner object used for input
     *
     * @return an integer corresponding to the user selection
     */
    private static int getUserMenuInput(Scanner input)
    {
        String userChoice = "";
        int intChoice = -1;

        // keep looping until the choice is valid
        while (intChoice < LOW_MENU_CHOICE || intChoice > HIGH_MENU_CHOICE)
        {
            System.out.print("Enter selection (" + LOW_MENU_CHOICE + " - "
                    + HIGH_MENU_CHOICE + "): ");
            userChoice = input.nextLine();

            // if the choice is not an positive integer, ask again
            while (!isPositiveInteger(userChoice))
            {
                System.out.println("ERROR: Invalid choice!");
                System.out.print("Enter selection (" + LOW_MENU_CHOICE + " - "
                        + HIGH_MENU_CHOICE + "): ");
                userChoice = input.nextLine();
            }

            // parse the integer when it is safe to do so
            intChoice = Integer.parseInt(userChoice);

            // display an error if the choice is not valid
            if (intChoice < LOW_MENU_CHOICE || intChoice > HIGH_MENU_CHOICE)
            {
                System.out.println("ERROR: Invalid choice!");
            }
        }

        return intChoice;
    }

    /**
     * Determines whether a given string is a positive integer value
     *
     * @param strNum string containing value to check for being a positive
     * integer
     *
     * @return true is the string contains a positive integer, false otherwise
     */
    private static boolean isPositiveInteger(String strNum)
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
     * Determines whether a give string contains an integer value
     *
     * @param strNum string containing value to check for being an integer
     *
     * @return true if the string contains an integer value, false otherwise
     */
    private static boolean isInteger(String strNum)
    {
        try
        {
            Integer.parseInt(strNum);

            return true;
        }
        catch (NumberFormatException nfe)
        {
            return false;
        }
    }

    /**
     * Obtains the name of the file to encrypt,decrypt, or crack from the user
     *
     * @param input a Scanner object for obtaining user input
     * @param path the directory path for listing the files available
     *
     * @return string containing the file name
     */
    private static String getFileName(Scanner input)
    {
        final String BAD_INPUT = "";
        final String BUFFER = "    ";

        File inputDirectory = new File(inputDirectoryPath);
        String[] directoryContents = inputDirectory.list();

        String filename = null;

        // display the contents of the inputDirectory for helping a user with
        // selecting an file to manipulate
        System.out.println("Listing Input Directory Contents");
        System.out.println("--------------------------------");

        if (directoryContents.length > 0)
        {
            int fileCount = 1;

            for (String file : directoryContents)
            {
                System.out.printf("%s%d. %s\n", BUFFER, fileCount, file);

                fileCount++;
            }
        }
        else
        {
            System.out.println(BUFFER + "Directory Contains No Files");
        }

        System.out.println();

        // attempt to get the file name
        System.out.print("Enter the file name with extension: ");
        filename = input.nextLine();

        // while the file name is an empty string, get the file name again
        while (filename.equals(BAD_INPUT))
        {
            System.out.println("ERROR: File name cannot be empty!");
            System.out.print("Enter the file name with extension: ");

            filename = input.nextLine();
        }

        return filename;
    }

    /**
     * Obtains a positive Caesar Cipher shift value from the user
     *
     * @param input a Scanner object for obtaining user input
     *
     * @return a positive integer value
     */
    private static int getCipherShiftValue(Scanner input)
    {
        String userShiftValue = "";
        int intShiftValue = -1;

        System.out.print("Enter a positive shift value: ");
        userShiftValue = input.nextLine();

        // if the choice is not an positive integer, ask again
        while (!isPositiveInteger(userShiftValue))
        {
            System.out.println("ERROR: Invalid shift value!");
            System.out.print("Enter a positive shift value: ");
            userShiftValue = input.nextLine();
        }

        // parse the integer when it is safe to do so
        intShiftValue = Integer.parseInt(userShiftValue);

        return intShiftValue;
    }

    /**
     * Playground for exercising your CipherHashTable and CaesarCipher classes 
     * (i.e., this means testing)
     */
    private static void playground()
    {
        // TODO or Not TODO, that is the question
        // Professor Note: I recommend the TODO option
        
        // except that it must compile, follow the course coding standards,
        // not cause exceptions...
        System.out.println("There ain't no rulez here!");

    }
    
}
