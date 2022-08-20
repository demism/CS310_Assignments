/*
 * CS310 Assignment 12 - Binary Search Trees
 */
package cs310datastructures;

import java.util.Scanner;

/**
 * Provides the driver program for the flutteroid farm simulation. There is 
 * also a playground method that can be used to test the farm functionality.
 *
 * @author Jeffrey LaMarche
 * @version 1.0 2020-10-20 - Initial Version
 *
 * @author TODO
 * @version TODO
 */
public class CS310Assignment12
{

    // defines constants for menu item choices, would probably be better as
    //  an enumerated type
    private static final int DISPLAY_FARM_ASC = 1;
    private static final int DISPLAY_FARM_DES = 2;
    private static final int DISPLAY_FARM_STR = 3;
    private static final int DISPLAY_ESCAPEES = 4;
    private static final int BUILD_FARM = 5;
    private static final int UPDATE_FARM = 6;
    private static final int FIND_FLUTTEROID = 7;
    private static final int PLAYGROUND = 8;
    private static final int QUIT_PROGRAM = 9;

    // private constant values
    private static final int LOW_MENU_CHOICE = 1;
    private static final int HIGH_MENU_CHOICE = QUIT_PROGRAM;

    /**
     * The main method for the entire program. This makes everything work. In
     * this case this displays the main menu, gets user input, and runs
     * different parts of the program based on what the user selected.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        boolean addSuccessful = false;
        int userChoice = -1;
        int farmRunTime = -1;
        int numberFlutteroid = -1;
        String msgPrompt = null;

        Scanner input = new Scanner(System.in);

        System.out.println("Welcome to the Flutteroid Farm Program 1.0!");

        System.out.println();

        FlutteroidFarm zebestationFarm = new FlutteroidFarm();
        FlutteroidGenerator generator = new FlutteroidGenerator();

        while (userChoice != QUIT_PROGRAM)
        {
            displayMenu();

            msgPrompt = "Enter menu selection";
            userChoice = getUserIntegerValue(input, msgPrompt,
                    LOW_MENU_CHOICE, HIGH_MENU_CHOICE + 1);

            System.out.println();

            switch (userChoice)
            {
                case DISPLAY_FARM_ASC:
                    zebestationFarm.displayFlutteroidFarmAscending();
                    System.out.println();
                    
                    break;
                case DISPLAY_FARM_DES:
                    zebestationFarm.displayFlutteroidFarmDescending();
                    System.out.println();
                    
                    break;
                case DISPLAY_FARM_STR:
                    zebestationFarm.displayFlutteroidFarmStructure();
                    System.out.println();
                    
                    break;
                case DISPLAY_ESCAPEES:
                    zebestationFarm.displayEscapedFlutteroids();
                    System.out.println();
                    
                    break;
                case BUILD_FARM:
                    zebestationFarm.clearFarm();
                    
                    msgPrompt = "Enter the number of flutteroids to start with";
                    numberFlutteroid = getPositiveIntegerValue(input, msgPrompt);
                    
                    for(int i = 0; i < numberFlutteroid; i++)
                    {
                        Flutteroid flutter = generator.getFlutteroidInstance();
                        
                        addSuccessful = zebestationFarm.addFlutteroid(flutter);
                        
                        if(addSuccessful)
                        {
                            System.out.printf("    Added to Farm: %s\n", flutter);
                        }
                        else
                        {
                            System.out.printf("    Add Failure: %s\n", flutter);
                        }
                    }
                    System.out.println();
                    
                    break;
                case UPDATE_FARM:
                    msgPrompt = "Enter number of time units to upadate the farm";
                    farmRunTime = getPositiveIntegerValue(input, msgPrompt);
                    
                    System.out.println();
                    for(int time = 0; time < farmRunTime; time++)
                    {
                        zebestationFarm.updateFarm();
                        System.out.println();
                    }
                    
                    break;
                case FIND_FLUTTEROID:
                    msgPrompt = "Enter a flutteroid name to locate";
                    String flutteroidName = getStringValue(input, msgPrompt);
                    
                    Flutteroid foundRoid = zebestationFarm.findFlutteroid(flutteroidName);
                    System.out.println();
                    
                    if(foundRoid == null)
                    {
                        System.out.printf("Flutteroid \"%s\" could not be located on the farm.\n", 
                                flutteroidName);
                    }
                    else
                    {
                        System.out.println("Flutteroid Located:\n");
                        foundRoid.display();
                    }
                    
                    System.out.println();
                    
                    break;
                case PLAYGROUND:
                    playground();
                    System.out.println();
                    
                    break;
                case QUIT_PROGRAM:
                    System.out.print("Thanks for using the Flutteroid Farm");
                    System.out.println(" Program 1.0!");
                    System.exit(0);

                    break;
                default:
                    System.out.println("ERROR: This should not be possible!");
                    System.out.println("Why do humans always break things?");
                    System.out.println("The Flutteroids are displeased...");

                    break;
            }
        }
    }

    /**
     * Displays the menu interface to standard output
     */
    private static void displayMenu()
    {
        System.out.println("Flutteroid Farm Main Menu: ");
        System.out.println("-----------------------------------------");
        System.out.println(" " + DISPLAY_FARM_ASC
                + ". Display Farm Flutteroids (Ascending)");
        System.out.println(" " + DISPLAY_FARM_DES
                + ". Display Farm Flutteroids (Descending)");
        System.out.println(" " + DISPLAY_FARM_STR
                + ". Display Farm Flutteroids (Stucture)");
        System.out.println(" " + DISPLAY_ESCAPEES + ". Display Flutteroid Escapees");
        System.out.println(" " + BUILD_FARM + ". Build Farm");
        System.out.println(" " + UPDATE_FARM
                + ". Update Farm");
        System.out.println(" " + FIND_FLUTTEROID + ". Find Flutteroid");
        System.out.println(" " + PLAYGROUND + ". Run Playground");
        System.out.println(" " + QUIT_PROGRAM + ". Quit Program");
        System.out.println("-----------------------------------------");
    }

    /**
     * Obtains a single positive integer value from the user in the range
     * lowValue to (highValue - 1). <br>
     * <br>
     * [lowValue, highValue)
     * <br>
     *
     * @param input a Scanner object for obtaining user input
     * @param msg message prompt to display to the user
     * @param lowValue the low integer in the range for obtaining input
     * (inclusive)
     * @param highValue the high integer in the range for obtaining input
     * (exclusive)
     *
     * @return a positive integer value from the user in range [lowValue,
     * highValue)
     */
    private static int getUserIntegerValue(Scanner input, String msg,
            int lowValue, int highValue)
    {
        final int LOW_CHOICE = lowValue;
        final int HIGH_CHOICE = highValue - 1;

        String userChoice = "";
        int intChoice = -1;

        // keep looping until the choice is valid
        while (intChoice < LOW_CHOICE || intChoice > HIGH_CHOICE)
        {
            System.out.printf("%s (%d - %d): ", msg, LOW_CHOICE, HIGH_CHOICE);
            userChoice = input.nextLine();

            // if the choice is not an positive integer, ask again
            while (!isPositiveInteger(userChoice))
            {
                System.out.println("ERROR: Invalid choice!");
                System.out.printf("%s (%d - %d): ", msg, LOW_CHOICE, HIGH_CHOICE);
                userChoice = input.nextLine();
            }

            // parse the integer when it is safe to do so
            intChoice = Integer.parseInt(userChoice);

            // display an error if the choice is not valid
            if (intChoice < LOW_CHOICE || intChoice > HIGH_CHOICE)
            {
                System.out.println("ERROR: Invalid choice!");
            }
        }

        return intChoice;
    }

    /**
     * Obtains a positive integer value from the user
     *
     * @param input a Scanner object for obtaining user input
     * @param msg message prompt to display to the user
     *
     * @return a positive integer value from the user
     */
    private static int getPositiveIntegerValue(Scanner input, String msg)
    {
        String userValue = "";
        int intValue = -1;

        System.out.printf("%s: ", msg);
        userValue = input.nextLine();

        // if the choice is not an positive integer, ask again
        while (!isPositiveInteger(userValue))
        {
            System.out.println("ERROR: Invalid value entered!");
            System.out.printf("%s: ", msg);
            userValue = input.nextLine();
        }

        // parse the integer when it is safe to do so
        intValue = Integer.parseInt(userValue);

        return intValue;
    }
    
    /**
     * Obtains a string from the user and returns what was entered
     * 
     * @param input a Scanner object for obtaining user input
     * @param msg message prompt to display to the user
     * 
     * @return a string reference to what the user entered
     */
    private static String getStringValue(Scanner input, String msg)
    {
        String userValue = "";

        System.out.printf("%s: ", msg);
        userValue = input.nextLine();

        return userValue;
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
     * Playground for exercising your FlutteroidFarm class (i.e., this means
     * testing)
     */
    private static void playground()
    {
        // TODO or Not TODO, that is the question
        // Professor Note: I recommend the TODO option
        
        // except that it must compile, follow the course coding standards,
        // not cause exceptions...
		FlutteroidGenerator myGenerator = new FlutteroidGenerator();
		FlutteroidFarm myFarm = new FlutteroidFarm();
		Flutteroid myFlutteroid = myGenerator.getFlutteroidInstance();
		Flutteroid myFlutteroid2 = myGenerator.getFlutteroidInstance();
		FlutteroidBSTNode myTree = new FlutteroidBSTNode();
		boolean wasAdded;


		wasAdded = myFarm.addFlutteroid(myFlutteroid);
		wasAdded = myFarm.addFlutteroid(myFlutteroid2);
		
		System.out.println(myFlutteroid.toString());
		myFarm.updateEscapees();
		myFarm.displayFlutteroidFarmAscending();
		myFarm.moveFlutteroids();
		

    }
}
