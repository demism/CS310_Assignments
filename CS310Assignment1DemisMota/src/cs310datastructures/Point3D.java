/*
 * CS310 Assignment 1 - Inheritance
 */
package cs310datastructures;

/**
 * This class represents a point in a 3D space
 * 
 * @author Demis Mota
 * @version 1.0  2022-05-05 Initial Version
 */

public class Point3D extends Point2D
{
    private int z;

     /**
     * Default constructor for a 3D point
     */
    
    public Point3D() {
        // calls super class constructor Point2D(int x, int y)
        // and sets x and y's default value to 0.
        super(0,0);
        
        z = 0; 
    }
    /**
     * Constructor that allows assigning the x, y, and z values for the 3D point
     * 
     * @param x the 3D point x coordinate value
     * @param y the 3D point y coordinate value
     * @param z the 3D point z coordinate value
     */
    public Point3D(int x, int y, int z){
        // Calls the super class constructor Point2D(int x, int y) 
        // to set their x and y values respectively
        super(x,y);
        
        this.z = z;
    }
    // Getter and Setter
    /**
     * Allows access to the 3D point z coordinate value
     * 
     * @return the 3D point z coordinate value
     */
    public int getZ() {
        return z;
    }
    
    /**
     * Allows setting the z coordinate value
     * 
     * @param z the updated 3D point z coordinate value
     */
    public void setZ(int z) {
        this.z = z;
    }
    /**
     * Tests two 3D point objects for equality
     * 
     * @param obj the right operand object ("this" is the left operand)
     * 
     * @return true if the two 3D points are equal, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Point3D other = (Point3D) obj;
        
        if ( this.getX() != other.getX() ) {
            return false;
        }
        if ( this.getY() != other.getY() ){
            return false;
        }
        return other.z == this.z;
    }
    /**
     * Converts a 3D point object into a string representation in this format:
     * (x, y, z)
     * 
     * @return a string reference for a 3D point object
     */    
    @Override
    public String toString()
    {
        return "(" + getX() + ", " + getY() + ", " + this.z + ")";
    }
}
