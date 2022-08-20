/*
 * CS310 Assignment 8 - Queues and Simulation
 */
package cs310datastructures;

/**
 * A queue structure that holds PlayingCards. The available operations are:
 * <br><br>
 * - Checking if the queue is empty<br>
 * - Getting the number of items in the queue (non-standard)<br>
 * - Enqueue a card (adding it to the queue)<br>
 * - Dequeue a card (removing it from the queue)<br>
 * <br>
 *
 * @author Jeffrey LaMarche
 * @version 0.9 2020-09-11 - Template Version
 * 
 * @author Demis Mota
 * @version 1.0 2022-06-26  - Initial Implementation
 */
public class PlayingCardQueue
{

    /*
    The head of the singly linked list
    */
    private PlayingCardNode queueFront;
    
    /*
    The tail of the singly linked list
    */
    private PlayingCardNode queueBack;
    
    /*
    The number of nodes stored in the singly linked list
    */
    private int numberOfCards;

    /**
	 * Default constructor creates an empty PlayingCardQueue
	 */ 
    public PlayingCardQueue()
    {
		queueFront = null;
		queueBack = null;
		numberOfCards = 0;
    }

    /**
	 * Gets the number of cards in the queue
	 * @return an integer which represents the number of cards in queue 
	 */ 
    public int getNumberOfCards()
    {
        return numberOfCards;
    }

    /**
	 * Checks whether the queue has any PlayingCards in it.
	 * @return a boolean variable 
	 */ 
    public boolean isQueueEmpty()
    {
        return (numberOfCards == 0); 
    }

    /**
	 * Removes a PlayingCard from the queue
	 * @return a PlayingCard object 
	 */ 
    public PlayingCard dequeueCard()
    {
		if ( queueFront == null)
			return null;
		PlayingCard card = queueFront.getCard();
		queueFront = queueFront.getNextNode();
		numberOfCards--;
		return card;
    }

	/**
	 * Adds a playing card to the queue
	 * @param card A PlayingCard object to be added to queue
	 * @return a boolean value of true if card is successfuly added. 
	 */    
    public boolean enqueueCard(PlayingCard card)
    {
		if (card == null)
			return false;

		if (queueFront == null)
		{
			queueFront = new PlayingCardNode(card);
			queueBack = queueFront;
			numberOfCards++;
		}	
		else {
			queueBack.setNextNode(new PlayingCardNode(card));
			queueBack = queueBack.getNextNode();
			numberOfCards++;
		}
        return true;
    }
}
