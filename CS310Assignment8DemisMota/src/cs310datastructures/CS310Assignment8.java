/*
 * CS310 Assignment 8 - Queues and Simulation
 */
package cs310datastructures;

import java.util.Scanner;

/**
 * This class runs simulations of the card game War and also tests the
 * PlayingCardDeck class.
 *
 * @author Jeffrey LaMarche
 * @version 1.0 2020-09-11 - Initial Template Version
 * @version 1.1 2021-01-15 - Added playground method for student testing
 *
 * @author Demis Mota
 * @version 1.2 2022-06-24 - Implemented SimulateGameOfWar method 
 */
public class CS310Assignment8
{

    // defines constants for menu item choices, would probably be better as
    //  an enumerated type
    private static final int RUN_WAR_SIM_STATIC = 1;
    private static final int RUN_WAR_SIM_SHUFFLE = 2;
    private static final int TEST_DECK = 3;
    private static final int PLAYGROUND = 4;
    private static final int QUIT_PROGRAM = 5;

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
        int userChoice = -1;
        Scanner input = new Scanner(System.in);

        System.out.println("Welcome to the Game of War Simulator 1.0!");

        System.out.println();

        // keep running until the user decides to quit
        while (userChoice != QUIT_PROGRAM)
        {
            displayMenu();

            userChoice = getUserMenuInput(input);

            System.out.println();

            switch (userChoice)
            {
                case RUN_WAR_SIM_STATIC:
                    simulateGameOfWar(false);

                    break;
                case RUN_WAR_SIM_SHUFFLE:
                    simulateGameOfWar(true);

                    break;
                case TEST_DECK:
                    testPlayingCardDeck();

                    break;
                case PLAYGROUND:
                    playground();
                    
                    break;
                case QUIT_PROGRAM:
                    System.out.print("Thanks for using the Game of War");
                    System.out.println(" Simulator 1.0!");
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
	 * Simulates a game of war with cards either shuffled or not.
	 * @param shuffleDeck determines whether to shuffle deck before simulation.
	 */
    private static void simulateGameOfWar(boolean shuffleDeck)
    {
		PlayingCardDeck deck = new PlayingCardDeck();
		PlayingCardQueue player1 = new PlayingCardQueue();
		PlayingCardQueue player2 = new PlayingCardQueue();
		int player1Wins = 0;
		int player2Wins = 0;
		int numTies = 0;

		int round = 1;

		if ( shuffleDeck )
			deck.shuffleDeck();
		while (!deck.isDeckEmpty())
		{
			player1.enqueueCard(deck.dealCard());
			player2.enqueueCard(deck.dealCard());
		}
		while( !player1.isQueueEmpty() && !player2.isQueueEmpty() && round < 1000)
		{

			System.out.printf("Round %d:  Player 1 (%d)   Player 2 (%d)\n",
			   round++, player1.getNumberOfCards(), player2.getNumberOfCards());
			System.out.println("-------------------------------------------");
			System.out.println("Player 1 Card        Player 2 Card");
			
			PlayingCard p1 = player1.dequeueCard();
			PlayingCard p2 = player2.dequeueCard();
			
			if ( !p1.isFaceUp()) 
				p1.flipCard();
			if ( !p2.isFaceUp())
				p2.flipCard();
			
			System.out.printf("%-20s %-9s\n", p1.toString(), p2.toString());
			System.out.println("-------------------------------------------");
			System.out.println();

			if (p1.getPoints() == p2.getPoints())
			{
				System.out.println("WAR! Both players lose!");
				numTies++;
			}
			else if (p1.getPoints() > p2.getPoints())
			{
				System.out.println("Player 1 won the round!");
				player1.enqueueCard(p1);
				player1.enqueueCard(p2);
				player1Wins++;
			}
			else 
			{
				System.out.println("Player 2 won the round!");
				player2.enqueueCard(p2);
				player2.enqueueCard(p1);
				player2Wins++;
			}	
			System.out.println();
			System.out.println();
		}

		if (player1.getNumberOfCards() < player2.getNumberOfCards() )
			System.out.println("** Player 2 Won the Game!! **");
		else if ( player1.getNumberOfCards() > player2.getNumberOfCards()) 
			System.out.println("** Player 1 Won the Game!! **");
		else
			System.out.println("** Player 1 and Player 2 Tied!! **");

		System.out.println();

		System.out.println("Remaining Cards:");
		System.out.println("-----------------");
		System.out.println("Player 1 (" + player1.getNumberOfCards()+ ")");
		System.out.println("Player 2 (" + player2.getNumberOfCards()+ ")");
		System.out.println("-----------------\n");
		
		double player1WinPct = (double)player1Wins/round*100;
		double player2WinPct = (double)player2Wins/round*100;
		double tiePct = (double)numTies/round*100;
		
		System.out.println("Game Statistics:");
		System.out.println("--------------------------------------");
		System.out.printf("%-22s %d\n","Total Rounds:", round);
		System.out.printf("%-22s %3d ( %5.2f%% )\n","Total Wars (Ties):",
				numTies, tiePct );
		System.out.printf("%-22s %3d ( %5.2f%% )\n", "Player 1 Wins:",
				player1Wins, player1WinPct); 
		System.out.printf("%-22s %3d ( %5.2f%% )\n", "Player 1 Loses:",
				player2Wins, player2WinPct); 
		System.out.printf("%-22s %3d ( %5.2f%% )\n", "Player 2 Wins:",
				player2Wins, player2WinPct); 
		System.out.printf("%-22s %3d ( %5.2f%% )\n", "Player 2 Loses:",
				player1Wins, player1WinPct); 
    }

    /**
     * Displays the image manipulation menu interface to standard output
     */
    private static void displayMenu()
    {
        System.out.println("Game of War Main Menu: ");
        System.out.println("--------------------------------------");
        System.out.println(" " + RUN_WAR_SIM_STATIC
                + ". Simulate a Game of War (Static)");
        System.out.println(" " + RUN_WAR_SIM_SHUFFLE
                + ". Simulate a Game of War (Random)");
        System.out.println(" " + TEST_DECK + ". Test the Playing Card Deck");
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
     * Provides some minimal visual testing of the PlayingCardDeck class.
     *
     * This is a bit like unit testing.
     */
    private static void testPlayingCardDeck()
    {
        PlayingCardDeck deck = new PlayingCardDeck();

        System.out.println("-- Displaying Deck Face Down --");
        deck.displayDeck(false);
        System.out.println();

        // ensure cards are face up
        System.out.println("-- Displaying Deck Face Up --");
        deck.displayDeck(true);
        System.out.println();

        // ensure that cards return to face down
        System.out.println("-- Displaying Deck Face Down --");
        deck.displayDeck(false);
        System.out.println();

        PlayingCard card = deck.dealCard();

        if (card != null)
        {
            System.out.println("-- Displaying Dealt Card --");
            card.flipCard();
            System.out.println("Card dealt: " + card);
            card.flipCard();
            System.out.println();

            System.out.println("-- Displaying Deck With Front Card Removed --");
            deck.displayDeck(true);
            System.out.println();

            deck.addCardToBottom(card);

            System.out.println("-- Displaying Deck With Card Returned to Bottom --");
            deck.displayDeck(true);
            System.out.println();
        }
        else
        {
            System.out.println("ERROR: Card should not have been null!");
            System.out.println();
        }

        deck.shuffleDeck();

        System.out.println("-- Displaying a Shuffled Deck --");
        deck.displayDeck(true);
        System.out.println();

        // exhausting the deck 
        while (!deck.isDeckEmpty())
        {
            deck.dealCard();
        }

        System.out.println("-- Displaying an Empty Deck --");
        deck.displayDeck(true);
        System.out.println();

        System.out.println("-- Verifying Deck Size is Zero --");
        System.out.println("Deck Size: " + deck.getNumberOfCards());
        System.out.println();

        card = deck.dealCard();

        // make sure null is returned
        System.out.println("-- Card Value Returned From Empty Deck --");
        System.out.println(card);
        System.out.println();

        System.out.println("-- Attempting to Add a Null Card to the Deck --");
        // ensure error message is displayed
        deck.addCardToBottom(card);
        System.out.println();

        // ensure that the null value was not added to the deck
        System.out.println("-- Verifying Deck Size is Still Zero --");
        System.out.println("Deck Size: " + deck.getNumberOfCards());
    }
    
    /**
     * Playground for exercising your PlayingCardDeck and PlayingCardQueue 
     * classes (i.e., this means testing)
     */
    private static void playground()
    {
        // TODO or Not TODO, that is the question
        // Professor Note: I recommend the TODO option
        
        // except that it must compile, follow the course coding standards,
        // not cause exceptions...
		PlayingCardDeck newDeck = new PlayingCardDeck();
		newDeck.displayDeck(true);
		newDeck.shuffleDeck();
		PlayingCardQueue newQ = new PlayingCardQueue();
		while(!newDeck.isDeckEmpty())
		{
			newQ.enqueueCard(newDeck.dealCard());
		}
    }
}
