/*
 * CS310 Assignment 1 - Inheritance
 */
package cs310datastructures;

/**
 * This class represents a point in a 2D space
 * (This class requires no changes. Do not modify!)
 * 
 * @author Jeffrey LaMarche
 * @version 1.0  2020-Aug-07 Initial Version
 * @version 1.01 2021-Jan-14 Added alternate default constructor line and 
 *      comment to help with learning. Also corrected typo in later comment. 
 */
public class Point2D
{
    private int x;
    private int y;

    /**
     * Default constructor for a 2D point
     */
    public Point2D()
    {
        // The commented out line is a better solution, as it reuses existing
        //  code. It is also more complicated to understand when first learning
        //  about inheritance. What is happening is that a specific constructor
        //  is called that already does the work correctly. If using this
        //  alternate solution, the latter two lines are no longer needed.
        
        /* this(0, 0); */
        
        x = 0;
        y = 0;
    }

    /**
     * Constructor that allows assigning the x and y values for the 2D point
     * 
     * @param x the 2D point x coordinate value
     * @param y the 2D point y coordinate value
     */
    public Point2D(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    /**
     * Allows access to the 2D point x coordinate value
     * 
     * @return the 2D point x coordinate value
     */
    public int getX()
    {
        return x;
    }

    /**
     * Allows access to the 2D point y coordinate value
     * 
     * @return the 2D point y coordinate value
     */
    public int getY()
    {
        return y;
    }

    /**
     * Allows setting the 2D point x coordinate value
     * 
     * @param x the updated 2D point x coordinate value
     */
    public void setX(int x)
    {
        this.x = x;
    }

    /**
     * Allows setting the 2D point y coordinate value
     * 
     * @param y the updated 2D point y coordinate value
     */
    public void setY(int y)
    {
        this.y = y;
    }

    /**
     * Converts a 2D point object into a string representation in this format:
     * (x, y)
     * 
     * @return a string reference for a 2D point object
     */
    @Override
    public String toString()
    {
        return "(" + x + ", " + y + ")";
    }

    /**
     * Tests two 2D point objects for equality
     * 
     * @param obj the right operand object ("this" is the left operand)
     * 
     * @return true if the two 2D points are equal, false otherwise
     */
    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
        {
            return true;
        }
        
        if (obj == null)
        {
            return false;
        }
        
        if (getClass() != obj.getClass())
        {
            return false;
        }
        
        final Point2D other = (Point2D) obj;
        
        if (this.x != other.x)
        {
            return false;
        }
        
        if (this.y != other.y)
        {
            return false;
        }
        
        return true;
    }
    
}
