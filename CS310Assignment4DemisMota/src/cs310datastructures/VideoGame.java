/*
 * CS310 Assignment 2 - Abstract Classes
 */
package cs310datastructures;

import java.util.Scanner;

/**
 * A class representing a basic Video Game object.
 * @author Demis Mota
 * @version 1.0 10-May-2022 Implementation 
 * @version 1.1 29-May-2022 added toFileFormat() method 
 */
public class VideoGame extends ShelfItem
{
    private String gameSystem;  // the system the game is played on
    private int timesFinished;  // the number of times the game has been 
                                // finished
    private boolean gamePlayed; // whether the game has ever been played

    /**
     * Default Constructor
     */
    VideoGame() {
        super(); // gameTitle
        gameSystem = "";
        timesFinished = 0;
        gamePlayed = false;
    }
    
    /**
     * A constructor that allows creating a video game with a game title,
     * the game system the game is for, the amount of times the game
     * has been finished, and whether the game has been played or not.
     * 
     * @param gameTitle the title of the video game
     * @param gameSystem the name of of the game system
     * @param timesFinished the number of times the title was finished
     * @param gamePlayed whether the game has been played or not
     */
    VideoGame(String gameTitle, String gameSystem, 
            int timesFinished, Boolean gamePlayed){
        super(gameTitle);
        this.gameSystem = gameSystem;
        this.timesFinished = timesFinished;
        this.gamePlayed = gamePlayed;
    }
    
    /**
     * Get the game title
     * 
     * @return a string reference to the game title
     */
    public String getGameTitle() {
        return super.getItemName();
    }
    
    /**
     * Sets the game title to a new value
     * 
     * @param gameTitle the games title
     */
    public void setGameTitle(String gameTitle){
        super.setItemName(gameTitle);
    }
    
    /**
     * Get the name of the game system
     * 
     * @return a string reference to the game system's name 
     */
    public String getGameSystem() {
        return gameSystem;
    }

    /**
     * Sets the game system name to a new title
     * 
     * @param gameSystem the new title for the game system
     */
    public void setGameSystem(String gameSystem) {
        this.gameSystem = gameSystem;
    }

    /**
     * Gets the number of times the game was finished
     * 
     * @return an integer to the amount of times game was finished
     */
    public int getTimesFinished() {
        return timesFinished;
    }

    /**
     * Sets the value for the number of times the game was finished
     * 
     * @param timesFinished an integer for the number of times the game was
     * finished
     */
    public void setTimesFinished(int timesFinished) {
        this.timesFinished = timesFinished;
    }

    /**
     * Gets whether the game was played or not using true or false
     * 
     * @return a boolean value representing whether the game was played or not
     */
    public boolean isGamePlayed() {
        return gamePlayed;
    }
    
    /**
     * Sets the whether the game was played to true or false
     * 
     * @param gamePlayed value representing whether game was played or not
     */
    public void setGamePlayed(boolean gamePlayed) {
        this.gamePlayed = gamePlayed;
    }
    
    /**
     * Increases the number of times the game was finished by one
     */
    public void increaseTimesFinished() {
        timesFinished++;
    }
    
    /**
     * Decreases the number of times the game was finished by one, unless 
     * the value is less than 0
     */
    public void decreaseTimesFinished() {
        if( timesFinished > 0)
            timesFinished--;
    }
    
    /**
     * Allow a user to enter the information for a video game
     * 
     * @param input the Scanner object used to enter a video game
     */
    @Override
    public void enterItem ( Scanner input ) {
        enterGameTitle(input);
        enterGameSystem(input);
        enterTimesFinished(input);
        enterGamePlayed(input);
        System.out.println();
    }
    
    /**
     * Allow a user to input a video game's title
     * 
     * @param input the Scanner object used to enter video game title
     */
    public void enterGameTitle ( Scanner input ) {
        final String BAD_INPUT = "";
        String userInput;
        
        // attempt to get the game title
        System.out.print("Enter the game title: ");
        userInput = input.nextLine();
        
        // while the game title is an empty string, get the game title again
        while(userInput.equals(BAD_INPUT))
        {
            System.out.println("ERROR: Game title cannot be empty!");
            System.out.print("Enter the game title: ");

            userInput = input.nextLine();
        }
        
        // once valid, set the title entered as the item name
        super.setItemName(userInput);
    }
    
    /**
     * Allow a user to input a video game's game system
     * 
     * @param input the Scanner object used to enter a video game's game system
     */
    public void enterGameSystem ( Scanner input ) {
        final String BAD_INPUT = "";
        String userInput;
        
        // attempt to get the game system
        System.out.print("Enter the game system: ");
        userInput = input.nextLine();
        
        // while the game system is an empty string, get the game system again
        while(userInput.equals(BAD_INPUT))
        {
            System.out.println("ERROR: Game system(s) cannot be empty!");
            System.out.print("Enter game system(s): ");

            userInput = input.nextLine();
        }
        
        // once valid, set the user input as the author name
        setGameSystem(userInput);
    
    }
    
    /**
     * Allow a user to enter the number of times a video game was finished
     * 
     * @param input the Scanner object use to enter the number of times the
     * game was finished
     */
    public void enterTimesFinished ( Scanner input ) {
        String userInput;
        
        // attempt to get the number of pages
        System.out.print("Enter number of times game was finished: ");
        userInput = input.nextLine();
        
        // while the times game was finished is not a positive integer value, 
        //  get the times game was finished again
        while(!super.isPositiveInteger(userInput))
        {
            System.out.println("ERROR: Number of times game was finished "
                    + "must be a positive integer value!");
            System.out.print("Enter number of times game was finished: ");

            userInput = input.nextLine();
        }
        
        // once valid, convert user input to an integer and set as the 
        //   number of times game was finished
        setTimesFinished(Integer.parseInt(userInput));
    }
    
    /**
     * Allow a user to enter whether a game was played or not
     * 
     * @param input the Scanner object used to tell whether the game was played
     */
    public void enterGamePlayed( Scanner input ) {
        final String BAD_INPUT = "";
        String userInput;
        
        // attempt to find out whether the game has been played
        System.out.print("Has the game been played(y/n): ");
        userInput = input.nextLine();
        
        // while the game played is an empty string, prompt again
        while(userInput.equals(BAD_INPUT))
        {
            System.out.println("ERROR: Whether the game has been played "
                    + "cannot be empty!");
            System.out.print("Has the game been played(y/n): ");

            userInput = input.nextLine();
        }
        
        // once valid, set whether the game has been played or not
        if (userInput.charAt(0) == 'y' || userInput.charAt(0) == 'Y')
            setGamePlayed(true);
        else
            setGamePlayed(false);
    }
    
    /**
     * Displays information for a video game to standard output
     */
    @Override
    public void displayItem() {
        System.out.println("** Video Game Information **");
        System.out.println("Title: " + super.getItemName());
        System.out.println("System: " + getGameSystem());
        System.out.println("Times Finished: " + getTimesFinished());
        System.out.println("Game played: " + (isGamePlayed()?"yes":"no"));
    }
    
    /**
     * Converts a video game object into a string representation. The format
     * for the string is: GAME: GameTitle for GameSystem
     * 
     * @return a reference to a string representing a video game
     */
    @Override
    public String toString() {
        return "GAME: " + super.getItemName() + " for " + getGameSystem();
    }

	@Override
	public String toFileFormat() {
		final String delimiter = "###";
        
        return "GAME" + delimiter + 
                getGameTitle() + delimiter + 
                getGameSystem() + delimiter +
                getTimesFinished() + delimiter +
				isGamePlayed();
	}
    
}
