/*
 * CS310 Assignment 5 - Singly Linked Lists
 */
package cs310datastructures;

/**
 * A class the represents a node in a singly linked list that keeps track
 *  of books that have been finished being read.
 * 
 * @author Jeffrey LaMarche
 * @version 1.0 2020-Aug-23 Initial Version
 */
public class BookReadNode
{
    private BookReadEntry bookReadEntryObj;
    private BookReadNode nextNode;

    /**
     * The default constructor for a BookReadNode. 
     * 
     * A default node has a null book read entry and a null next node link.
     */
    public BookReadNode()
    {
        bookReadEntryObj = null;
        nextNode = null;
    }

    /**
     * A constructor that allows creating a new node with a book read
     *  entry already defined. The next link of this node will be null. 
     * 
     * @param bookReadEntryObj a reference to a BookReadEntry object
     */
    public BookReadNode(BookReadEntry bookReadEntryObj)
    {
        this.bookReadEntryObj = bookReadEntryObj;
        nextNode = null;
    }

    /**
     * Allows access to the BookReadEntry object inside the node.
     * 
     * @return a BookReadEntry object reference, which could be null
     */
    public BookReadEntry getBookReadEntryObj()
    {
        return bookReadEntryObj;
    }

    /**
     * Allows access to the next node link within the current node. (Provides
     *  access to what the node is pointing to.)
     * 
     * @return a BookReadNode reference to the next node, which could be null
     */
    public BookReadNode getNextNode()
    {
        return nextNode;
    }

    /**
     * Allows setting the BookReadEntry object to a new value. 
     * 
     * @param bookReadEntryObj a BookReadEntry reference, which could be null
     */
    public void setBookReadEntryObj(BookReadEntry bookReadEntryObj)
    {
        this.bookReadEntryObj = bookReadEntryObj;
    }

    /**
     * Allows setting the nextNode link to a new value in a BookReadNode object.
     * 
     * @param nextNode a BookReadNode reference, which could be null
     */
    public void setNextNode(BookReadNode nextNode)
    {
        this.nextNode = nextNode;
    }
    
}
