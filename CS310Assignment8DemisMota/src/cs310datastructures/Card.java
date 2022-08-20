/*
 * CS310 Assignment 8 - Queues and Simulation
 */
package cs310datastructures;

/**
 * An abstract class for helping to represent different types of cards.
 * 
 * @author Jeffrey LaMarche
 * @version 1.0 2020-Sept-04 Initial Version
 */
public abstract class Card
{
    /*
    Whether the card is face up or face down 
    */
    private boolean faceUp;     

    /**
     * Allows access to determining if the card is face up. 
     * 
     * @return true if the card is face up, false if the card is face down
     */
    public boolean isFaceUp()
    {
        return faceUp;
    }

    /**
     * Allows setting the card face up value to a different value
     * 
     * @param faceUp true if the card is face up, false otherwise
     */
    public void setFaceUp(boolean faceUp)
    {
        this.faceUp = faceUp;
    }
    
    /**
     * Flips a card from one side to the other
     */
    public void flipCard()
    {
        faceUp = !faceUp;
    }
    
    /**
     * Displays a card based on which side is showing
     */
    public abstract void displayCard();

}
