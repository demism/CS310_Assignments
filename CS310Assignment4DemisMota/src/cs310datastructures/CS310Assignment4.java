/*
 * CS310 Assignment 4 - ArrayLists, Data Persistence, and Refactoring
 */
package cs310datastructures;

import java.util.Scanner;

/**
 * The main class for the entire program. This makes everything work.
 * (Complete, do not modify except for the playground method for your testing.)
 *
 * @author Jeffrey LaMarche
 * @version 1.0  2020-Aug-19 - Initial Version
 * @version 1.1  2021-Jan-14 - Added playground method for student testing
 */
public class CS310Assignment4
{

    // defines constants for menu item choices, would probably be better as
    //  an enumerated type
    private static final int ADD_BOOK = 1;
    private static final int ADD_BOOK_POSITION = 2;
    private static final int ADD_GAME = 3;
    private static final int ADD_GAME_POSITION = 4;
    private static final int REMOVE_ITEM = 5;
    private static final int FIND_ITEM = 6;
    private static final int DISPLAY_SHELVES = 7;
    private static final int PLAYGROUND = 8;
    private static final int QUIT_PROGRAM = 9;

    // private constant values
    private static final int LOW_MENU_CHOICE = 1;
    private static final int HIGH_MENU_CHOICE = QUIT_PROGRAM;
    private static final int ADD_AT_BACK = -1;

    // constant for data file location
    private static final String DATA_FILE_NAME = "data/shelvingdata.txt";

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

        ShelvingSystem shelves = new ShelvingSystem();

        // load the data from the file
        System.out.println("\nLoading shelf data...\n");
        loadSuccess = shelves.loadData(DATA_FILE_NAME);

        if (loadSuccess)
        {
            System.out.println("SUCCESS: Data was loaded!");
        }
        else
        {
            System.out.println("FAILURE: Data was not loaded!");
        }
        
        System.out.println();

        System.out.println("Welcome to the Shelving System 2.0");
        
        System.out.println();

        // keep running until the user decides to quit
        while (userChoice != QUIT_PROGRAM)
        {
            displayMenu();

            userChoice = getUserMenuInput(input);

            System.out.println();

            switch (userChoice)
            {
                // adds a new book to the shelves
                case ADD_BOOK:
                    addBook(shelves, input);
                    break;
                // adds a book at a specific position
                case ADD_BOOK_POSITION:
                    addBookAtPosition(shelves, input);
                    break;
                // adds a new video game to the shelves
                case ADD_GAME:
                    addVideoGame(shelves, input);
                    break;
                // adds a video game at a specific position
                case ADD_GAME_POSITION:
                    addGameAtPosition(shelves, input);
                    break;
                // removes an item from the shelves based on name
                case REMOVE_ITEM:
                    removeItemByName(shelves, input);
                    break;
                // finds an item in the shelves based on name
                case FIND_ITEM:
                    findItemByName(shelves, input);
                    break;
                // displays the contents of the shelves
                case DISPLAY_SHELVES:
                    shelves.displayShelves();
                    break;
                // runs the playground testing method
                case PLAYGROUND:
                    playground();
                    System.out.println();
                    break;
                // quits out of the program and cleans things up if needed
                case QUIT_PROGRAM:
                    System.out.println("Quitting program...\n");
                    
                    System.out.println("Saving shelf data...\n");
                    saveSuccess = shelves.saveData(DATA_FILE_NAME);

                    if (saveSuccess)
                    {
                        System.out.println("\nSUCCESS: Data was saved!");
                    }
                    else
                    {
                        System.out.println("\nFAILURE: Data was not saved!");
                    }

                    System.out.println("\nThanks for using the "
                            + "Shelving System 2.0!");
                    break;
                default:
                    // cannot actually get here
                    break;
            }
        }

    }

    /**
     * Displays the shelving system menu interface to standard output
     */
    private static void displayMenu()
    {
        System.out.println("*** Shelving System MENU ***");
        System.out.println("------------------------------");
        System.out.println(" " + ADD_BOOK + ". Add Book");
        System.out.println(" " + ADD_BOOK_POSITION + ". Add Book at Position");
        System.out.println(" " + ADD_GAME + ". Add Video Game");
        System.out.println(" " + ADD_GAME_POSITION + ". Add Video Game "
                + "at Position");
        System.out.println(" " + REMOVE_ITEM + ". Remove Item");
        System.out.println(" " + FIND_ITEM + ". Find Item");
        System.out.println(" " + DISPLAY_SHELVES + ". Display Shelves");
        System.out.println(" " + PLAYGROUND + ". Run Playground");
        System.out.println(" " + QUIT_PROGRAM + ". Quit Program");
        System.out.println("------------------------------");
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

            while (!ShelfItem.isPositiveInteger(userChoice))
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
     * Helper method to add an item into the shelves, either at the back or at a
     * specific position, depending on what position is passed to the method.
     *
     * @param shelves the ShelfSystem object for adding items to
     * @param item the specific item to add to the shelves
     * @param position the index for where to add the item
     */
    private static void addItemToShelves(ShelvingSystem shelves, ShelfItem item,
            int position)
    {
        boolean wasAdded = false;

        if (position == ADD_AT_BACK)
        {
            wasAdded = shelves.addItem(item);
        }
        else
        {
            wasAdded = shelves.addItem(item, position);
        }

        if (wasAdded)
        {
            System.out.println("ADD SUCCESS - " + item + " was successfully"
                    + " added to the shelves!");
        }
        else
        {
            System.out.println("ADD FAILURE -  " + item + " was not added"
                    + " to the shelves!");
        }

        System.out.println();
    }

    /**
     * Adds a new book to the shelves at the first empty location, which is
     * always at the end.
     *
     * @param shelves the ShelvingSystem object to add the book to
     * @param input the Scanner object for user input
     */
    private static void addBook(ShelvingSystem shelves, Scanner input)
    {
        Book book = new Book();
        book.enterItem(input);

        addItemToShelves(shelves, book, ADD_AT_BACK);
    }

    /**
     * Adds a new video game to the shelves at the first empty location, which
     * is always at the end.
     *
     * @param shelves the ShelvingSystem object to add the book to
     * @param input the Scanner object for user input
     */
    private static void addVideoGame(ShelvingSystem shelves, Scanner input)
    {
        VideoGame game = new VideoGame();
        game.enterItem(input);

        addItemToShelves(shelves, game, ADD_AT_BACK);
    }

    /**
     * Adds a new book to the shelves at the specific position. All books after
     * the newly inserted item will be shifted over.
     *
     * @param shelves the ShelvingSystem object to add the book to
     * @param input the Scanner object for user input
     */
    private static void addBookAtPosition(ShelvingSystem shelves, Scanner input)
    {
        int position = -1;

        Book book = new Book();
        book.enterItem(input);

        if (shelves.getNumberOfShelfItems() == 0)
        {
            System.out.println("Shelf contains no items. Placing new item as"
                    + " first item on the shelves.");

            addItemToShelves(shelves, book, ADD_AT_BACK);
        }
        else
        {
            position = getPosition(shelves, input);

            addItemToShelves(shelves, book, position);
        }
    }

    /**
     * Adds a new video game to the shelves at the specific position. All games
     * after the newly inserted item will be shifted over.
     *
     * @param shelves the ShelvingSystem object to add the game to
     * @param input the Scanner object for user input
     */
    private static void addGameAtPosition(ShelvingSystem shelves, Scanner input)
    {
        int position = -1;

        VideoGame game = new VideoGame();
        game.enterItem(input);

        if (shelves.getNumberOfShelfItems() == 0)
        {
            System.out.println("Shelf contains no items. Placing new item as"
                    + " first item on the shelves.");

            addItemToShelves(shelves, game, ADD_AT_BACK);
        }
        else
        {
            position = getPosition(shelves, input);

            addItemToShelves(shelves, game, position);
        }
    }

    /**
     * Gets the position to insert a new item onto the shelves. Ensures that the
     * position is in the appropriate range, which will be one higher than the
     * corresponding array index. Subtracts one from the user entered position
     * to ensure the index is valid.
     *
     * @param shelves the ShelvingSystem object
     * @param input the Scanner object for user input
     *
     * @return an integer with a valid index on the shelves
     */
    private static int getPosition(ShelvingSystem shelves, Scanner input)
    {
        int position = -1;
        String userInput = "";

        while (position < 1 || position > shelves.getNumberOfShelfItems())
        {
            System.out.print("Enter position (" + 1 + " - "
                    + (shelves.getNumberOfShelfItems()) + "): ");
            userInput = input.nextLine();

            while (!ShelfItem.isPositiveInteger(userInput))
            {
                System.out.println("ERROR: Invalid position!");
                System.out.print("Enter position (" + 1 + " - "
                        + (shelves.getNumberOfShelfItems()) + "): ");
                userInput = input.nextLine();
            }

            position = Integer.parseInt(userInput);

            if (position < 1 || position > shelves.getNumberOfShelfItems())
            {
                System.out.println("ERROR: Invalid position!");
            }
        }

        return position - 1;
    }

    /**
     * Attempts to remove an item from the shelves based on the title
     *
     * @param shelves the ShelvingSystem object to remove the item from
     * @param input the Scanner object for user input
     */
    private static void removeItemByName(ShelvingSystem shelves, Scanner input)
    {
        final String BAD_INPUT = "";
        String userInput;
        ShelfItem removedObj = null;

        // attempt to get the item name
        System.out.print("Enter the item name to remove: ");
        userInput = input.nextLine();

        // while the item name is an empty string, get the item name again
        while (userInput.equals(BAD_INPUT))
        {
            System.out.println("ERROR: Item name cannot be empty!");
            System.out.print("Enter the item name: ");

            userInput = input.nextLine();
        }

        // attempt to remove what the user entered from the shelves
        removedObj = shelves.removeItem(userInput);

        if (removedObj != null)
        {
            System.out.println("REMOVE SUCCESS - " + removedObj + " was "
                    + "successfully removed from the shelves!");
        }
        else
        {
            System.out.println("REMOVE FAILURE - \"" + userInput + "\" could"
                    + " not be found on the shelves.");
        }

        System.out.println();
    }

    /**
     * Attempts to find an item on the shelves based on the title
     *
     * @param shelves the ShelvingSystem object to find the item in
     * @param input the Scanner object for user input
     */
    private static void findItemByName(ShelvingSystem shelves, Scanner input)
    {
        final String BAD_INPUT = "";
        String userInput;
        ShelfItem foundObj = null;

        // attempt to get the item name
        System.out.print("Enter the item name to find: ");
        userInput = input.nextLine();

        // while the item name is an empty string, get the item name again
        while (userInput.equals(BAD_INPUT))
        {
            System.out.println("ERROR: Item name cannot be empty!");
            System.out.print("Enter the item name: ");

            userInput = input.nextLine();
        }

        // attempt to find what the user entered on the shelves
        foundObj = shelves.findItem(userInput);

        if (foundObj != null)
        {
            foundObj.displayItem();
        }
        else
        {
            System.out.println("FIND FAILURE - \"" + userInput + "\" could not"
                    + " be found on the shelves!");
            System.out.println();
        }
    }
    
    /**
     * Playground for exercising your ShelvingSystem class 
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
