/*
 * CS310 Assignment 12 - Binary Search Trees
 */
package cs310datastructures;

/**
 * An entry for when a flutteroid escapes from the farm. This includes the 
 * Flutteroid object and also the time it will take to capture the flutteroid. 
 * 
 * @author Jeffrey LaMarche
 * @version 1.0 2020-10-21 Initial Version of Gloriousness
 */
public class FlutteroidEscapeEntry
{
    /*
    The Flutteroid object
    */
    private Flutteroid escapedFlutteroid;
    
    /*
    How long it will take to capture the flutteroid
    */
    private int remainingCaptureTime;

    /**
     * Constructor to make an entry for an escaped Flutteroid
     * 
     * @param escapedFlutteroid reference to a node containing a Flutteroid
     * @param remainingCaptureTime time remaining before flutteroid is captured
     */
    public FlutteroidEscapeEntry(Flutteroid escapedFlutteroid, 
            int remainingCaptureTime)
    {
        this.escapedFlutteroid = escapedFlutteroid;
        this.remainingCaptureTime = remainingCaptureTime;
    }

    /**
     * Allows access to the escaped Flutteroid object
     * 
     * @return a reference to the node containing the escaped flutteroid
     */
    public Flutteroid getEscapedFlutteroid()
    {
        return escapedFlutteroid;
    }

    /**
     * Allows access to the remaining time to capture an escaped flutteroid
     * 
     * @return the time units remaining until a flutteroid can be captured
     */
    public int getRemainingCaptureTime()
    {
        return remainingCaptureTime;
    }
    
    /**
     * Decreases the remaining capture time for the flutteroid by one unit
     */
    public void decressRemainingCaptureTime()
    {
        if(remainingCaptureTime > 0)
        {
            remainingCaptureTime--;
        }
    }

    /**
     * Provides a string representation for an escaped flutteroid entry
     * 
     * @return a string representing an escaped flutteroid entry object
     */
    @Override
    public String toString()
    {
        return "{" + escapedFlutteroid + " CTime: " + remainingCaptureTime + "}";
    }
    
    
}
