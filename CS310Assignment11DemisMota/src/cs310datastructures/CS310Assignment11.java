/*
 * CS310 Assignment 11 - Java TreeSet
 */
package cs310datastructures;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.TreeSet;
import java.util.Scanner;

/**
 * This class provides the driver for the Sudoku solution helper program. This
 * allows a user to load a puzzle file, display the puzzle, and generate a
 * help file that explains what moves are remaining in the puzzle. There is also
 * a testing feature.
 *
 * @author Jeffrey LaMarche
 * 
 * @version 1.0 2020-10-03
 * Initial Version
 * 
 * @version 1.1 2020-10-08
 * Menu value input method made more generic so that any range bound positive 
 * integer values could be obtained from a user with a custom message prompt.
 * 
 * @version 1.2 2021-03-21
 * Added playground method and functionality into menu system
 */
public class CS310Assignment11
{   
    // defines constants for menu item choices, would probably be better as
    //  an enumerated type
    private static final int DISPLAY_PUZZLE = 1;
    private static final int REMAINING_MOVES = 2;
    private static final int GENERATE_HELP = 3;
    private static final int LOAD_PUZZLE = 4;
    private static final int TEST_SUDOKU_CLASSES = 5;
    private static final int PLAYGROUND = 6;
    private static final int QUIT_PROGRAM = 7;

    // private constant values
    private static final int LOW_MENU_CHOICE = 1;
    private static final int HIGH_MENU_CHOICE = QUIT_PROGRAM;
    
    /*
    Relative directory path where files will be loaded
     */
    private static final String INPUT_DIRECTORY_PATH = "input/";

    /*
    Relative directory path where files will be saved
     */
    private static final String OUTPUT_DIRECTORY_PATH = "output/";

    /**
     * The main method for the entire program. This makes everything work. In
     * this case this displays the main menu, gets user input, and runs
     * different parts of the program based on what the user selected.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {   
        boolean solutionGenerated = false;
        int userChoice = -1;
        int userRow = -1;
        int userCol = -1;
        String filename = null;
        String inputFile = null;
        String outputFile = null;
        String msgPrompt = null;

        TreeSet<Integer> remainingMoves = null;
        
        SudokuBoard board = null;
        SudokuSolutionHelper solutionHelper = null;

        Scanner input = new Scanner(System.in);

        System.out.println("Welcome to the Sudoku Solution Helper Program 1.0!");

        System.out.println();
                
        while (userChoice != QUIT_PROGRAM)
        {
            displayMenu();

            msgPrompt = "Enter menu selection";
            userChoice = getUserIntegerValue(input, msgPrompt, 
                    LOW_MENU_CHOICE, HIGH_MENU_CHOICE + 1);

            System.out.println();

            switch (userChoice)
            {
                case DISPLAY_PUZZLE:
                    if (board != null)
                    {
                        System.out.print(board);
                    }
                    else
                    {
                        System.out.print("ERROR: No File Loaded!\n");
                    }

                    break;
                case REMAINING_MOVES:
                    if (board != null && solutionHelper != null)
                    {
                        // display the board so it is easier for the user to
                        // remember what they might want
                        System.out.println(board);
                        
                        // get the user row and col for determining remaining moves
                        msgPrompt = "Enter row selection";
                        userRow = getUserIntegerValue(input, msgPrompt, 0, board.getBoardSize());
                        
                        msgPrompt = "Enter col selection";
                        userCol = getUserIntegerValue(input, msgPrompt, 0, board.getBoardSize());
                        
                        remainingMoves = solutionHelper.getRemainingCellMoves(userRow, userCol);
                        
                        System.out.printf("Remaining moves at ");
                        System.out.printf("(%d, %d): ", userRow, userCol);
                        
                        if(remainingMoves.isEmpty())
                        {
                            System.out.printf("%s\n", "No Moves Remaining");
                        }
                        else
                        {
                            System.out.printf("%s\n", remainingMoves);
                        }
                    }
                    else
                    {
                        System.out.print("ERROR: No File Loaded!\n");
                    }
                    
                    break;
                case GENERATE_HELP:
                    if(solutionHelper != null)
                    {
                        // create a filename based on loaded filename
                        outputFile = OUTPUT_DIRECTORY_PATH + "help-" + filename;
                        
                        // generate the help file
                        solutionGenerated = solutionHelper.generateSolutionHelpFile(outputFile);
                        
                        if (solutionGenerated)
                        {
                            System.out.println("Solution help saved to: " + outputFile);
                        }
                        else
                        {
                            System.out.print("FILE ERROR: Solution help was");
                            System.out.println(" not successfully saved!");
                        }
                    }
                    else
                    {
                        System.out.print("ERROR: No File Loaded!\n");
                    }
                    
                    break;
                case LOAD_PUZZLE:
                    // delete the SudokuBoard
                    board = null;
                    
                    // delete the SolutionHelper
                    solutionHelper = null;
                    
                    // get the filename from the user
                    filename = getInputFileName(input);
                    inputFile = INPUT_DIRECTORY_PATH + filename;
                    
                    try
                    {
                        // create a new board based on the file
                        board = new SudokuBoard(inputFile);
                        System.out.println("SUCCESS: " + filename + " was loaded!");
                        
                        // create the helper object and load the board into it
                        solutionHelper = new SudokuSolutionHelper(board);
                    }
                    catch(FileNotFoundException fnfe)
                    {
                        System.out.println("ERROR: File Not Loaded");
                        System.out.println(fnfe);
                    }
                    catch(NumberFormatException nfe)
                    {
                        System.out.println("ERROR: File Not Loaded");
                        System.out.println("ERROR: A Value Was Not An Integer");
                        System.out.println(nfe);
                    }
                    catch(IllegalArgumentException iae)
                    {
                        System.out.println("ERROR: File Not Loaded");
                        System.out.println("ERROR: Illegal Argument Detected");
                        System.out.println(iae);
                    }

                    break;
                case TEST_SUDOKU_CLASSES:
                    // run the hash table testing
                    sudokuTesting();

                    break;
                case PLAYGROUND:
                    // run the testing playground
                    playground();

                    break;
                case QUIT_PROGRAM:
                    System.out.print("Thanks for using the Sudoku Solution");
                    System.out.println(" Helper Program 1.0!");
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
        System.out.println("Sudoku Solution Helper Main Menu: ");
        System.out.println("-----------------------------------------");
        System.out.println(" " + DISPLAY_PUZZLE + ". Display Puzzle");
        System.out.println(" " + REMAINING_MOVES 
                + ". Display Remaining Moves For A Cell");
        System.out.println(" " + GENERATE_HELP + ". Generate Solution Help");
        System.out.println(" " + LOAD_PUZZLE + ". Load Puzzle");
        System.out.println(" " + TEST_SUDOKU_CLASSES
                + ". Test Sudoku Board and Solution Helper");
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
     * @param lowValue the low integer in the range for obtaining input (inclusive)
     * @param highValue the high integer in the range for obtaining input (exclusive)
     * 
     * @return a positive integer value from the user in range [lowValue, highValue)
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
     * Obtains the name of the file to load into the word count table
     *
     * @param input a Scanner object for obtaining user input
     * @param path the directory path for listing the files available
     *
     * @return string containing the file name
     */
    private static String getInputFileName(Scanner input)
    {
        final String BAD_INPUT = "";
        final String BUFFER = "    ";

        File inputDirectory = new File(INPUT_DIRECTORY_PATH);
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
                System.out.printf("%s%3d. %s\n", BUFFER, fileCount, file);

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
     * Provides minimal testing of the Sudoku Board and Solution Helper classes
     */
    private static void sudokuTesting()
    {   
        System.out.println("-- Testing Invalid Boards --");
        System.out.println();
        
        SudokuBoard invalidBoard =  null;
        
        try
        {
            invalidBoard = new SudokuBoard("input/puzzleInvalidSize.txt");
        }
        catch (FileNotFoundException fnfe)
        {
            System.out.println(fnfe);
        }
        catch (IllegalArgumentException iae)
        {
            System.out.println(iae);
            System.out.printf("Correct: Board size is not a perfect square\n");
        }
        
        if(invalidBoard == null)
        {
            System.out.println("Correct: puzzleInvalidSize.txt Board Not Loaded");
        }
        
        System.out.println();
        
        try
        {
            invalidBoard = new SudokuBoard("input/puzzleInvalidCell.txt");
        }
        catch (FileNotFoundException fnfe)
        {
            System.out.println(fnfe);
        }
        catch (IllegalArgumentException iae)
        {
            System.out.println(iae);
            System.out.printf("Correct: Value is not an integer\n");
        }
        
        if(invalidBoard == null)
        {
            System.out.println("Correct: puzzleInvalidCell.txt Board Not Loaded");
        }
        
        System.out.println();
        System.out.println("-- Testing Valid Board Loading --");
        System.out.println();
        
        SudokuBoard board = null;
        
        try
        {
            board = new SudokuBoard("input/testingPuzzle4x4.txt");
        }
        catch (FileNotFoundException fnfe)
        {
            System.out.println(fnfe);
        }
          
        if(board != null)
        {
            System.out.println(board);
        }
        else
        {
            System.out.println("Incorrect: testingPuzzle4x4.txt Should have displayed!");
            System.exit(1);
        }
        
        int invalid = -1;
        int compareValue = 2;
        
        System.out.println("-- Testing Get Cell Method --");
        System.out.println();
        
        try
        {
            board.getCell(invalid, 0);
        }
        catch(IndexOutOfBoundsException iobe)
        {
            System.out.println(iobe);
            System.out.printf("Correct: Row index %d was out of bounds\n",
                    invalid);
        }
        
        System.out.println();
        
        try
        {
            board.getCell(0, invalid);
        }
        catch(IndexOutOfBoundsException iobe)
        {
            System.out.println(iobe);
            System.out.printf("Correct: Column index %d was out of bounds\n",
                    invalid);
        }
        
        invalid = board.getBoardSize();
        System.out.println();
        
        try
        {
            board.getCell(invalid, 0);
        }
        catch(IndexOutOfBoundsException iobe)
        {
            System.out.println(iobe);
            System.out.printf("Correct: Row index %d was out of bounds\n",
                    invalid);
        }
        
        System.out.println();
        
        try
        {
            board.getCell(0, invalid);
        }
        catch(IndexOutOfBoundsException iobe)
        {
            System.out.println(iobe);
            System.out.printf("Correct: Column index %d was out of bounds\n",
                    invalid);
        }
        
        System.out.println();
        
        try
        {
            int cellValue = board.getCell(0, 0);
            
            System.out.printf("Retrieved value of %d equals %d: %b\n", cellValue,
                    compareValue, (cellValue == compareValue));
        }
        catch(IndexOutOfBoundsException iobe)
        {
            System.out.println(iobe);
            System.out.printf("Incorrect: Column index %d is in bounds\n",
                    invalid);
        }
        
        System.out.println();   // end testing get cell methods
        System.out.println("-- Testing Set Cell Method --");
        System.out.println();
        
        invalid = -1;
        compareValue = 100;
        
        try
        {
            board.setCell(invalid, 0, compareValue);
        }
        catch(IndexOutOfBoundsException iobe)
        {
            System.out.println(iobe);
            System.out.printf("Correct: Row index %d was out of bounds\n",
                    invalid);
        }
        
        System.out.println();
        
        try
        {
            board.setCell(0, invalid, compareValue);
        }
        catch(IndexOutOfBoundsException iobe)
        {
            System.out.println(iobe);
            System.out.printf("Correct: Column index %d was out of bounds\n",
                    invalid);
        }
        
        invalid = board.getBoardSize();
        System.out.println();
        
        try
        {
            board.setCell(invalid, 0, compareValue);
        }
        catch(IndexOutOfBoundsException iobe)
        {
            System.out.println(iobe);
            System.out.printf("Correct: Row index %d was out of bounds\n",
                    invalid);
        }
        
        System.out.println();
        
        try
        {
            board.setCell(0, invalid, compareValue);
        }
        catch(IndexOutOfBoundsException iobe)
        {
            System.out.println(iobe);
            System.out.printf("Correct: Column index %d was out of bounds\n",
                    invalid);
        }
        
        System.out.println();
         
        try
        {
            board.setCell(0, 0, null);
        }
        catch(IllegalArgumentException iae)
        {
            System.out.println(iae);
            System.out.printf("Correct: Null value in invalid\n");
        }
        
        System.out.println();
        
        try
        {
            board.setCell(0, 0, compareValue);
            
            int cellValue = board.getCell(0, 0);
            System.out.printf("Retrieved value of %d equals %d: %b\n", cellValue,
                    compareValue, (cellValue == compareValue));
        }
        catch(IndexOutOfBoundsException iobe)
        {
            System.out.println(iobe);
            System.out.printf("Incorrect: Retrieved value should equal 100\n",
                    invalid);
        }
        
        // return board back to original state
        board.setCell(0, 0, 2);

        System.out.println();
        System.out.println("-- Testing Solution Helper Methods --");
        System.out.println();
        
        SudokuSolutionHelper helper = new SudokuSolutionHelper(board);
        
        TreeSet<Integer> master, row, col, grid;
        
        master = helper.getMasterSet();
        row = helper.getFilledRow(0);
        col = helper.getFilledColumn(0);
        grid = helper.getFilledMiniGrid(0, 0);
        
        // should be a set containing 1, 2, 3, 4
        System.out.println("Master Set Values: " + master);
        System.out.println();
        
        // should be a set containing 1, 2, 3, 4
        System.out.println("Row Filled Values: " + row);
        
        // should be a set containing 1, 2, 3, 4
        System.out.println("Col Filled Values: " + col);
        
        // should be a set containing 1, 2, 4
        System.out.println("Grid Filled Values: " + grid);
        
        // should be the set containing 1, 2, 3, 4
        TreeSet combine = helper.getFilledCellMoves(0, 0);
        System.out.println("Filled moves at (0, 0]): " + combine);
                
        System.out.println();
        
        TreeSet remaining = helper.getRemainingRow(0);
        // should be the empty set
        System.out.println("Remaining moves for Row 0: " + remaining);
        
        // should be the empty set
        remaining = helper.getRemainingColumn(0);
        System.out.println("Remaining moves for Col 0: " + remaining);
        
        // should be a set containing 3
        remaining = helper.getRemainingMiniGrid(0, 0);
        System.out.println("Remaining moves for Mini-Grid(0,0): " + remaining);
        
        // should be the empty set
        remaining = helper.getRemainingCellMoves(0, 0);
        System.out.println("Remaining moves at cell (0, 0): " + remaining);
        
        System.out.println();
        System.out.println("-- Testing Board Solved Method --");
        
        System.out.println();
        System.out.println(board);
        
        // this should print false
        System.out.println("Puzzle Finished: " + helper.isBoardSolved());
        System.out.println();
        
        SudokuBoard boardFinished = null;
        
        try
        {
            boardFinished = new SudokuBoard("input/puzzleSolved.txt");
        }
        catch (FileNotFoundException fnfe)
        {
            System.out.println(fnfe);
        }
                
        System.out.println(boardFinished);
        
        helper = null;
        helper = new SudokuSolutionHelper(boardFinished);
        
        // this should print true
        System.out.println("Puzzle Finished: " + helper.isBoardSolved());
    }
    
    /**
     * Playground for exercising your project classes (i.e., this means
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
