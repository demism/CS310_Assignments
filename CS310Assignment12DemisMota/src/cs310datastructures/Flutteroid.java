/*
 * CS310 Assignment 12 - Binary Search Trees
 */
package cs310datastructures;

import java.util.Random;

/**
 * This class represents the Flutteroid creature. It looks a bit like a 
 * jellyfish and can fly. It is dangerous and can suck the life energy
 * out of something. Flutteroids produce the highly sought spice melange, 
 * so their dangerous nature is often ignored and instead they are farmed. 
 * 
 * @author Jeffrey LaMarche
 * @version 1.0 2020-10-20 Initial Version
 */
public class Flutteroid
{
    /*
    The name for the Flutteroid 
    */
    private String nameID;
    
    /*
    The x and y location for the Flutteroid
    */
    private Point2D location;

    /*
    The range that a Flutteroid can move in a time unit. A value of 10 would
    indicate that a Flutteroid can move anywhere between [-10, 10] x and/or y
    positions during a single time unit. 
    */
    private int movementRange;
    
    /*
    How many times a Flutteroid has esacped
    */
    private int escapes;
    
    /**
     * Random number generator object for the Flutteroid movement
     */
    private Random rand;
    
    /**
     * The default constructor for a Flutteroid
     * <br><br>
     * The default name is invalid and needs to be changed. <br>
     * The default location is at position (0, 0)<br>
     * The default movement range is 1<br>
     * The default escapes is 0
     */
    public Flutteroid()
    {
        nameID = "INVALID";
        location = new Point2D();
        movementRange = 1;
        escapes = 0;
        rand = new Random();
    }

    /**
     * This constructor allows creating a Flutteroid object with the given name
     * and starting at the default location of (0, 0) and a movement range of 1. 
     * If the name is null, an invalid name is assigned. 
     * 
     * @param nameID the new String reference containing the name for the Flutteroid
     */
    public Flutteroid(String nameID)
    {
        if(nameID != null)
        {
            this.nameID = nameID;
        }
        else
        {
            this.nameID = "INVALID";
        }
        
        location = new Point2D();
        
        movementRange = 1;
        
        escapes = 0;
        
        rand = new Random();
    }
    
    /**
     * This constructor allows creating a Flutteroid object with the given name
     * and starting at the default location of (0, 0) and a specified movement
     * range. If the name is null, an invalid name is assigned.
     * 
     * @param nameID the new String reference containing the name for the Flutteroid
     * @param movementRange the movement range integer value for the Flutteroid
     */
    public Flutteroid(String nameID, int movementRange)
    {
        if(nameID != null)
        {
            this.nameID = nameID;
        }
        else
        {
            this.nameID = "INVALID";
        }
        
        location = new Point2D();
        
        this.movementRange = Math.abs(movementRange);
        
        escapes = 0;
        
        rand = new Random();
    }
    
    /**
     * This constructor creates a Flutteroid object with the given name,
     * location values, and movement range. If the name is null, an invalid
     * name is assigned. 
     * 
     * @param nameID the String reference containing the name for the Flutteroid
     * @param xLocation the x location integer value for the Flutteroid
     * @param yLocation the y location integer value for the Flutteroid
     * @param movementRange the movement range integer value for the Flutteroid
     */
    public Flutteroid(String nameID, int xLocation, int yLocation, int movementRange)
    {
        if(nameID != null)
        {
            this.nameID = nameID;
        }
        else
        {
            this.nameID = "INVALID";
        }
        
        location = new Point2D(xLocation, yLocation);
        
        this.movementRange = Math.abs(movementRange);
        
        escapes = 0;
        
        rand = new Random();
    }
    
    /**
     * Allows access to the Flutteroid name/ID value. 
     * 
     * @return a string containing the Flutteroid name string reference
     */
    public String getNameID()
    {
        return nameID;
    }

    /**
     * Allows access to the location of the Flutteroid in 2D space
     * 
     * @return a Point object containing the X and Y coordinates 
     * for the Flutteroid 
     */
    public Point2D getLocation()
    {
        return location;
    }

    /**
     * Allows access to the movement range of the Flutteroid object
     * 
     * @return an integer value for the Flutteroid movement range
     */
    public int getMovementRange()
    {
        return movementRange;
    }

    /**
     * Allows access to the number of times a Flutteroid has escaped
     * 
     * @return an integer value for the number of escapes
     */
    public int getEscapes()
    {
        return escapes;
    }

    /**
     * Assigns the Flutteroid name reference to a new value. If the new value
     * is null, no change is made. 
     * 
     * @param nameID the new String reference containing the name for the Flutteroid
     */
    public void setNameID(String nameID)
    {
        if(nameID != null)
        {
            this.nameID = nameID;
        }
    }

    /**
     * Assigns the X coordinate location for a Flutteroid
     * 
     * @param xLocation the new x location integer value for the Flutteroid
     */
    public void setXLocation(int xLocation)
    {
        location.setX(xLocation);
    }
    
    /**
     * Assigns the Y coordinate location for a Flutteroid
     * 
     * @param yLocation the new y location integer value for the Flutteroid
     */
    public void setYLocation(int yLocation)
    {
        location.setY(yLocation);
    }

    /** 
     * Assigns the Flutteroid movement range to a new value
     * 
     * @param movementRange the new movement range to set in the Flutteroid
     */
    public void setMovementRange(int movementRange)
    {
        this.movementRange = Math.abs(movementRange);
    }
    
    /**
     * Increases the times a Flutteroid has escaped by one
     */
    public void increaseEscapes()
    {
        escapes++;
    }
    
    /**
     * Moves the Flutteroid in a random manner based on its movement range
     */
    public void move()
    {
        // get the min and max values in the movement range
        int max = getMovementRange();
        int min = -getMovementRange();
        
        // compute a random number within this range
        int deltaX = rand.nextInt((max - min) + 1) + min;
        int deltaY = rand.nextInt((max - min) + 1) + min;
        
        // get the current location
        Point2D current = getLocation();
        
        // update the current location to update by the changes in X and Y
        setXLocation(current.getX() + deltaX);
        setYLocation(current.getY() + deltaY);
    }

    /**
     * Displays the Flutteroid data to standard output
     */
    public void display()
    {
        System.out.printf("-=: Flutteroid Info :=-\n");
        System.out.printf("--------------------------\n");
        System.out.printf("    Name ID:  %s\n", nameID);
        System.out.printf("    Location: %s\n", location);
        System.out.printf("    Movement: %d\n", movementRange);
        System.out.printf("    Escapes:  %d\n", escapes);
    }
    
    /**
     * Creates a string representation of a Flutteroid object
     * 
     * @return a reference to the string representation of the Flutteroid object
     */
    @Override
    public String toString()
    {
        return "{" + nameID + ": " + location + "}";
    }
    
}
