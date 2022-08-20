/*
 * CS310 Assignment 5 - Singly Linked Lists
 */
package cs310datastructures;

import java.util.Scanner;
import java.util.Calendar;

/**
 * This class makes the Books Reading Log function for the user. This log
 * keeps track of the books someone finishes reading. Each entry in the log 
 * contains book information, a time stamp, and whether a book is owned. 
 *
 * @author Jeffrey LaMarche
 * @version 1.0  2020-Aug-27 - Initial Version
 * @version 1.01 2020-Aug-28 - Name changes and updated documentation
 * @version 1.1  2021-Jan-15 - Added in playground method for student testing
 */
public class CS310Assignment5
{

    // defines constants for menu item choices, would probably be better as
    //  an enumerated type
    private static final int ADD_ITEM = 1;
    private static final int REMOVE_ITEM = 2;
    private static final int FIND_ITEM = 3;
    private static final int DISPLAY_LOG = 4;
    private static final int PLAYGROUND = 5;
    private static final int QUIT_PROGRAM = 6;

    // private constant values
    private static final int LOW_MENU_CHOICE = 1;
    private static final int HIGH_MENU_CHOICE = QUIT_PROGRAM;

    // constant for data file location
    private static final String DATA_FILE_NAME = "data/logdata.txt";

    /**
     * The driver of the entire program
     *
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);
        int userChoice = -1;
        boolean loadSuccess;
        boolean saveSuccess;

        BookReadingLog bookReadingLog = new BookReadingLog();

        // load the data from the file
        System.out.println("\nLoading reading log data...\n");
        loadSuccess = bookReadingLog.loadData(DATA_FILE_NAME);

        if (loadSuccess)
        {
            System.out.println("SUCCESS: Data was loaded!");
        }
        else
        {
            System.out.println("FAILURE: Data was not loaded!");
        }

        System.out.println();

        System.out.println("Welcome to the book reading log 1.0");

        System.out.println();

        // keep running until the user decides to quit
        while (userChoice != QUIT_PROGRAM)
        {
            displayMenu(bookReadingLog);

            userChoice = getUserMenuInput(input);

            System.out.println();

            switch (userChoice)
            {
                // adds a new book to the reading log
                case ADD_ITEM:
                    addBookToLog(bookReadingLog, input);
                    break;
                // removes an item from the reading log based on name
                case REMOVE_ITEM:
                    removeBookByName(bookReadingLog, input);
                    break;
                // finds an item in the reading log based on name
                case FIND_ITEM:
                    findBookByName(bookReadingLog, input);
                    break;
                // displays the contents of the reading log
                case DISPLAY_LOG:
                    bookReadingLog.displayReadingLog();
                    break;
                // runs the testing playground method
                case PLAYGROUND:
                    playground();
                    System.out.println();
                    break;
                // quits out of the program and cleans things up if needed
                case QUIT_PROGRAM:
                    System.out.println("Quitting program...\n");

                    System.out.println("Saving book reading log data...\n");
                    saveSuccess = bookReadingLog.saveData(DATA_FILE_NAME);

                    if (saveSuccess)
                    {
                        System.out.println("\nSUCCESS: Data was saved!");
                    }
                    else
                    {
                        System.out.println("\nFAILURE: Data was not saved!");
                    }

                    System.out.println("\nThanks for using the "
                            + "book reading log 1.0!\n");
                    break;
                default:
                    System.out.println("ERROR: This should not be possible!");
                    break;
            }
        }

    }

    /**
     * Displays the books reading log menu interface to standard output
     */
    private static void displayMenu(BookReadingLog booksReadLog)
    {
        System.out.println("Book Reading Log Menu - Books Read: " +
                booksReadLog.getNumberOfBooksRead());
        System.out.println("--------------------------------------");
        System.out.println(" " + ADD_ITEM + ". Add Book Information");
        System.out.println(" " + REMOVE_ITEM + ". Remove Book by Title");
        System.out.println(" " + FIND_ITEM + ". Find Book by Title");
        System.out.println(" " + DISPLAY_LOG + 
                ". Display Reading Log (Old -> New)");
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

        while (intChoice < LOW_MENU_CHOICE || intChoice > HIGH_MENU_CHOICE)
        {
            System.out.print("Enter selection (" + LOW_MENU_CHOICE + " - "
                    + HIGH_MENU_CHOICE + "): ");
            userChoice = input.nextLine();

            while (!CollectionItem.isPositiveInteger(userChoice))
            {
                System.out.println("ERROR: Invalid choice!");
                System.out.print("Enter selection (" + LOW_MENU_CHOICE + " - "
                        + HIGH_MENU_CHOICE + "): ");
                userChoice = input.nextLine();
            }

            intChoice = Integer.parseInt(userChoice);

            if (intChoice < LOW_MENU_CHOICE || intChoice > HIGH_MENU_CHOICE)
            {
                System.out.println("ERROR: Invalid choice!");

            }
        }

        return intChoice;
    }

    /**
     * Adds a new book to the books finished reading log.
     *
     * @param booksReadLog a reference to the BookReadingLog object where the
     * new finished book entry will be added
     * @param input the Scanner object for user input
     */
    private static void addBookToLog(BookReadingLog booksReadLog, 
            Scanner input)
    {
        boolean wasAdded = false;
        boolean bookOwned = false;
        Book bookObj = null;
        BookReadEntry foundBookReadEntryObj = null;
        BookReadEntry readEntryObj = null;
        Calendar timestamp;
        String userInput;

        bookObj = new Book();

        bookObj.enterBookTitle(input);

        // after getting the title, see if the book is already in the log
        foundBookReadEntryObj = booksReadLog.findBook(bookObj.getBookTitle());

        // if the book is not in the log, go ahead and get the remaining 
        // information from the user
        if (foundBookReadEntryObj == null)
        {
            bookObj.enterBookAuthor(input);
            bookObj.enterNumberPages(input);
            bookObj.enterTimesRead(input);
        }
        // if the book is in the log, use a reference to it instead
        else
        {
            bookObj = foundBookReadEntryObj.getBookObj();
            
            System.out.println("FOUND - " + bookObj);
            System.out.println("  Increasing number of times read "
                    + "and adding to log as new entry.");
            
            // increase the number of times the book has been read
            bookObj.increaseTimesRead();
        }

        // attempt to get the owned status
        System.out.print("Do you own the book (y/n): ");
        userInput = input.nextLine();

        // while the item name is an empty string, get the item name again
        while (!userInput.toLowerCase().equals("y")
                && !userInput.toLowerCase().equals("n"))
        {
            System.out.println("ERROR: Ownership must be a 'y' or 'n' value!");
            System.out.print("Do you own the book (y/n): ");

            userInput = input.nextLine();
        }

        if (userInput.toLowerCase().equals("y"))
        {
            bookOwned = true;
        }

        timestamp = Calendar.getInstance();

        readEntryObj = new BookReadEntry(bookObj, timestamp, bookOwned);

        wasAdded = booksReadLog.addBook(readEntryObj);

        if (wasAdded)
        {
            System.out.println("ADD SUCCESS - " + bookObj + " was successfully"
                    + " added to the reading log!");
        }
        else
        {
            System.out.println("ADD FAILURE -  " + bookObj + " was not added"
                    + " to the reading log!");
        }

        System.out.println();
    }

    /**
     * Attempts to remove an item from the books finished reading log based
     * on the title provided by the user
     *
     * @param booksReadLog the BookReadingLog object to remove the item from
     * @param input the Scanner object for user input
     */
    private static void removeBookByName(BookReadingLog booksReadLog,
            Scanner input)
    {
        final String BAD_INPUT = "";
        String userInput;
        BookReadEntry removedObj = null;

        // attempt to get the item name
        System.out.print("Enter the book title to remove: ");
        userInput = input.nextLine();

        // while the item name is an empty string, get the item name again
        while (userInput.equals(BAD_INPUT))
        {
            System.out.println("ERROR: Book title cannot be empty!");
            System.out.print("Enter the book title to remove: ");

            userInput = input.nextLine();
        }

        // attempt to remove what the user entered from the reading log
        removedObj = booksReadLog.removeBook(userInput);

        if (removedObj != null)
        {
            System.out.println("REMOVE SUCCESS - " + removedObj.getBookObj()
                    + " was successfully removed from the reading log!");

            // decrease the number of times the book has been read
            removedObj.getBookObj().decreaseTimesRead();
        }
        else
        {
            System.out.println("REMOVE FAILURE - \"" + userInput + "\" could"
                    + " not be found in the reading log.");
        }

        System.out.println();
    }

    /**
     * Attempts to find an item in the books finished reading log based on 
     * the title entered by the user
     *
     * @param booksReadLog the BookReadingLog object to remove the item from
     * @param input the Scanner object for user input
     */
    private static void findBookByName(BookReadingLog booksReadLog,
            Scanner input)
    {
        final String BAD_INPUT = "";
        String userInput;
        BookReadEntry foundObj = null;

        // attempt to get the item name
        System.out.print("Enter the book title to find: ");
        userInput = input.nextLine();

        // while the item name is an empty string, get the item name again
        while (userInput.equals(BAD_INPUT))
        {
            System.out.println("ERROR: Book title cannot be empty!");
            System.out.print("Enter the book title to find: ");

            userInput = input.nextLine();
        }

        // attempt to find what the user entered in the reading log
        foundObj = booksReadLog.findBook(userInput);

        if (foundObj != null)
        {
            foundObj.getBookObj().displayItem();
        }
        else
        {
            System.out.println("FIND FAILURE - \"" + userInput + "\" could not"
                    + " be found in the reading log!");
            System.out.println();
        }
    }
    
    /**
     * Playground for exercising your BooksReadingLog class (i.e., this means
     * testing)
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
