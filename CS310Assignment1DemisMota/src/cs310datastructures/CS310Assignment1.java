/*
 * CS310 Assignment 1 - Inheritance
 */
package cs310datastructures;

/**
 * The main class for the entire program. This makes everything work.
 *
 * @author Jeffrey LaMarche
 * @version 1.0 2020-Aug-11 Template Version
 * 
 * @author Demis Mota
 * @version 1.01 2022-May-02 Implementation Version
 */
public class CS310Assignment1
{

    /**
     * The main method for the entire program
     *
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        // Testing Point2D
        Point2D pointOne2D = new Point2D();
        Point2D pointTwo2D = new Point2D(0, 10);
        Point2D pointThree2D = new Point2D(0, 10);
        Point2D pointFour2D = new Point2D(-5, -10);

        System.out.println("Equality of 2D Points:");
        
        displayEquality(pointOne2D, pointOne2D);
        displayEquality(pointOne2D, pointTwo2D);
        displayEquality(pointTwo2D, pointThree2D);
        displayEquality(pointTwo2D, pointFour2D);
        
        System.out.println();
        
        System.out.println("Distance between 2D Points:");
        
        displayDistance(pointOne2D, pointOne2D);
        displayDistance(pointOne2D, pointTwo2D);
        displayDistance(pointTwo2D, pointThree2D);
        displayDistance(pointTwo2D, pointFour2D);
        displayDistance(pointFour2D, pointTwo2D);
        
        System.out.println();

        // Testing Point3D
        
        Point3D pointOne3D = new Point3D();
        Point3D pointTwo3D = new Point3D(0, 0, 5);
        Point3D pointThree3D = new Point3D(0, 0, 5);
        Point3D pointFour3D = new Point3D(-2, -2, -5);
        Point3D pointFive3D = new Point3D(5, 5, 5);

        System.out.println("Equality of 3D Points:");
        
        displayEquality(pointOne3D, pointOne3D);
        displayEquality(pointOne3D, pointTwo3D);
        displayEquality(pointTwo3D, pointThree3D);
        displayEquality(pointTwo3D, pointFour3D);
        displayEquality(pointTwo3D, pointFive3D);
        
        System.out.println();
        
        System.out.println("Distance between 3D Points:");
        
        displayDistance(pointOne3D, pointOne3D);
        displayDistance(pointOne3D, pointTwo3D);
        displayDistance(pointTwo3D, pointThree3D);
        displayDistance(pointTwo3D, pointFour3D);
        displayDistance(pointFour3D, pointTwo3D);
        
        System.out.println();
        
    }
    /**
     * Displays the distance between two 2D points
     * @param pointOne
     * @param pointTwo 
     */
    public static void displayDistance(Point2D pointOne, Point2D pointTwo)
    {
        System.out.println("Distance between " + pointOne.toString() + " and " + pointTwo.toString() + " is: " + distance( pointOne, pointTwo ));
    }
        /**
     * Displays the distance between two 3D points
     * @param pointOne
     * @param pointTwo 
     */
    public static void displayDistance(Point3D pointOne, Point3D pointTwo)
    {
        System.out.println("Distance between " + pointOne.toString() + " and " + pointTwo.toString() + " is: " + distance( pointOne, pointTwo ));
    }
    /**
     * Displays the equality between two 2D points
     * @param pointOne
     * @param pointTwo 
     */
    public static void displayEquality(Point2D pointOne, Point2D pointTwo)
    {
        System.out.println(pointOne.toString() + " == "  + pointTwo.toString() + ": " + pointOne.equals(pointTwo));
    }
    /**
     * Displays the equality between two 3D points
     * @param pointOne
     * @param pointTwo 
     */   
    public static void displayEquality(Point3D pointOne, Point3D pointTwo)
    {
        System.out.println(pointOne.toString() + " == "  + pointTwo.toString() + ": " + pointOne.equals(pointTwo));
    }
    /**
     * Calculates the distance between two 2D points
     * @param pointOne
     * @param pointTwo
     * @return the distance between two 2D points
     */
    public static double distance(Point2D pointOne, Point2D pointTwo)
    {
        double distanceValue = 0;

        distanceValue = Math.sqrt(Math.pow(pointTwo.getX() - pointOne.getX(), 2) + Math.pow(pointTwo.getY() - pointOne.getY(), 2));

        return distanceValue;
    }
    /**
     * Calculates the distance between two 3D points
     * @param pointOne
     * @param pointTwo
     * @return the distance between two 3D points
     */
    public static double distance(Point3D pointOne, Point3D pointTwo)
    {
        double distanceValue = 0;

        distanceValue = Math.sqrt(Math.pow(pointTwo.getX() - pointOne.getX(), 2) + Math.pow(pointTwo.getY() - pointOne.getY(), 2) + Math.pow(pointTwo.getZ() - pointOne.getZ(), 2));

        return distanceValue;
    }
}
