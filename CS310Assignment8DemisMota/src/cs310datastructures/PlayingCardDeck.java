/*
 * CS310 Assignment 8 - Queues and Simulation
 */
package cs310datastructures;

import java.util.Collections;
import java.util.LinkedList;

/**
 * This class represents a deck of playing cards. These are the 
 * operations available in the limited deck:<br><br>
 * - Building a new deck in a consistent pattern<br>
 * - Getting the number of cards remaining in the deck<br>
 * - Shuffling the cards in the deck<br>
 * - Dealing a card from the top of the deck<br>
 * - Adding a card to the bottom of the deck<br>
 * - Displaying the contents of the deck<br>
 * 
 * @author Jeffrey LaMarche
 * @version 0.9 2020-09-11 - Template Version
 * 
 * @author DemisMota
 * @version 1.0 2022-06-24 - Initial Implementation
 */
public class PlayingCardDeck
{
    /*
    The rank of an ace card
    */
    private final int ACE_RANK = 1;
    
    /*
    The number of cards in a suit
    */
    private final int CARDS_IN_SUIT = 13;
    
    /*
    The maximum number of cards in a new deck
    */
    private final int CARDS_IN_DECK = 52;
    
    /*
    A linked list that holds the cards. 
    */
    private LinkedList<PlayingCard> deck;

   /**
	* Default Constructor creates a 52-card deck of cards
	*/ 
    public PlayingCardDeck()
    {
		deck = new LinkedList<>();
		PlayingCard card;
		for (int i=0; i<52; i++) {
			int cardSuit = i/13 + 1;
			int cardRank = i%13 + 1;
			int cardPoints = 14;
			if ( cardRank != 1)
				cardPoints = cardRank;
			card = new PlayingCard(cardSuit,cardRank,cardPoints);
			addCardToBottom(card);
		}
    }

   /**
	* Gets the number of cards in the deck
	* @return an integer with the total amount of cards in deck. 
	*/ 
    public int getNumberOfCards()
    {
        return deck.size();
    }
    
	/**
	 * Checks to see whether the deck of cards is empty.
	 * @return a boolean value representing whether the deck has no cards. 
	 */
    public boolean isDeckEmpty()
    {
        return deck.isEmpty();
    }
    
	/**
	 * Shuffles the deck of cards.
	 */
    public void shuffleDeck()
    {
		Collections.shuffle(deck);
    }

    /**
	 * Deals a card from the deck (removes it from deck)
	 * @return a PlayingCard object which is the card dealt. 
	 */ 
    public PlayingCard dealCard()
    {
        return deck.pollFirst();
    }

    /**
	 * Adds a playing card to the bottom of the deck.
	 * @param card a PlayingCard object to add to bottom of deck. 
	 */ 
    public void addCardToBottom(PlayingCard card)
    {
		if (card != null)
			deck.add(card);
    }

    /**
	 * Displays a deck of cards either face up or down.
	 * @param displayFaceUp a boolean value used to show deck face up/down. 
	 */ 
    public void displayDeck(boolean displayFaceUp)
    {
		for (int i=0; i < getNumberOfCards(); i++ )
		{
			if ( displayFaceUp ) {
				if ( deck.get(i).isFaceUp())
					System.out.println(deck.get(i).toString());
				else{
					deck.get(i).flipCard();
					System.out.println(deck.get(i).toString());
				}
			}
			else
			{
				if ( deck.get(i).isFaceUp())
				{
					deck.get(i).flipCard();
					System.out.println(deck.get(i).toString());
				}
				else
					System.out.println(deck.get(i).toString());
			}
		}
    }
}
