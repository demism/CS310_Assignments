/*
 * CS310 Assignment 8 - Queues and Simulation
 */
package cs310datastructures;

/**
 * A playing card class that can be used in card games.
 * <br>
 * Each card has three data members:<br>
 * - Suit: 1 to 4 representing Spades, Diamonds, Clubs, and Hearts<br>
 * - Rank: 1 to 13 representing Ace to King<br>
 * - Points: the points value for a card, which is variable and game dependent<br>
 *
 * @author Jeffrey LaMarche
 * @version 1.0 2020-Sept-04 Initial Version
 */
public class PlayingCard extends Card
{    
    /*
    Whether to display the cards using unicode characters
     */
    private static final boolean UNICODE_DISPLAY = true;

    // contant values respresenting the card suits
    public static final int SPADES = 1;
    public static final int DIAMONDS = 2;
    public static final int CLUBS = 3;
    public static final int HEARTS = 4;
    
    /*
    The suit of the card
     */
    private int suit;

    /*
    The rank of the card
     */
    private int rank;

    /*
    The points value of the card
     */
    private int points;

    /**
     * A playing card constructor that allows setting the suit, rank,
     * and points values to what is passed in as parameters
     * 
     * @param suit the suit of the card
     * @param rank the rank of the card
     * @param points the point value of the card
     */
    public PlayingCard(int suit, int rank, int points)
    {
        this.suit = suit;
        this.rank = rank;
        this.points = points;
    }
    
    /**
     * Allows access to the playing card suit value
     *
     * @return an integer representing the playing card suit value
     */
    public int getSuit()
    {
        return suit;
    }

    /**
     * Allows access to the playing card rank value
     *
     * @return an integer representing the playing card rank value
     */
    public int getRank()
    {
        return rank;
    }

    /**
     * Allows access to the playing card points value
     *
     * @return an integer representing the playing card points value
     */
    public int getPoints()
    {
        return points;
    }

    /**
     * Allows setting the playing card points value to a new value
     *
     * @param points an integer containing the new points value for the playing
     * card
     */
    public void setPoints(int points)
    {
        this.points = points;
    }

    /**
     * Displays the playing card based on whether it is face up or face down.
     * <br><br>
     * The toString() value is merely printed without a newline on the end.
     */
    @Override
    public void displayCard()
    {   
        System.out.print(this.toString());
    }

    /**
     * Provides a string representation of a playing card object
     *
     * @return a string representing the PlayingCard object data values
     */
    @Override
    public String toString()
    {
        StringBuilder output = new StringBuilder();
 
        if (super.isFaceUp())
        {
            if (rank == 1)
            {
                output.append("Ace");
            }
            else if (rank == 11)
            {
                output.append("Jack");
            }
            else if (rank == 12)
            {
                output.append("Queen");
            }
            else if (rank == 13)
            {
                output.append("King");
            }
            else
            {
                output.append(rank);
            }

            output.append(" of ");

            if (UNICODE_DISPLAY)
            {
                if (suit == SPADES)
                {
                    output.append("\u2660");
                }
                else if (suit == DIAMONDS)
                {
                    output.append("\u2666");
                }
                else if (suit == CLUBS)
                {
                    output.append("\u2663");
                }
                else
                {
                    output.append("\u2665");
                }
            }
            else
            {
                if (suit == SPADES)
                {
                    output.append("Spades");
                }
                else if (suit == DIAMONDS)
                {
                    output.append("Diamonds");
                }
                else if (suit == CLUBS)
                {
                    output.append("Clubs");
                }
                else
                {
                    output.append("Hearts");
                }
            }
        }
        else
        {
            output.append("Card is Face Down");
        }
        
        return output.toString();
    }

}
