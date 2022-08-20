/*
 * CS310 Assignment 12 - Binary Search Trees
 */
package cs310datastructures;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

/**
 * Creates a FlutteroidFarm simulation game
 *
 * @author Jeffrey LaMarche
 * @version 1.0 2020-10-23 Template Version
 * 
 * @author Demis Mota
 * @version 1.1 2022-07-31 Initial Implementation
 */
public class FlutteroidFarm
{

    /*
    The left and bottom boundary for the farm (inclusive)
     */
    private static final int LOW_FARM_BOUNDARY = -100;

    /*
    The right and top boundary for the farm (inclusive)
     */
    private static final int HIGH_FARM_BOUNDARY = 100;

    /*
    The lowest time for a flutteroid to be captured
     */
    private static final int MIN_CAPTURE_TIME = 1;

    /*
    The highest time for a flutteroid to be captured
     */
    private static final int MAX_CAPTURE_TIME = 10;

    /*
    The root of the binary search tree of the farm
     */
    private FlutteroidBSTNode treeRoot;

    /*
    The number of Flutteroids currently on the farm
     */
    private int numberOfFlutteroids;

    /*
    A queue holding any escaped flutteroids
     */
    private Queue<FlutteroidEscapeEntry> escapedFlutteroids;

    /*
    Whether the most recent add operation was successful
     */
    private boolean addSuccessful;

    /*
    Whether the most recently removed Flutteroid
     */
    private Flutteroid removedFlutteroid;

    /*
    Random number generator reference
     */
    private Random rand;

    /*
    Whether or not Sam is currently capturing flutteroids
     */
    private boolean samIsCapturing;
    
    /*
    The time that a farm has been updated
    */
    private int farmTotalTime;
    
   /**
		* Default Constructor - Creates an empty Flutteroid Farm
		*/ 
    public FlutteroidFarm()
    {
				treeRoot = null;
				escapedFlutteroids = new LinkedList<>(); 
				numberOfFlutteroids = 0;
				samIsCapturing = false;
				farmTotalTime = 0;
				rand = new Random();
    }

		/**
		 * Gets the number of flutteroids in the farm
		 * @return an integer representing the number of flutteroids  
		 */
    public int getNumberOfFlutteroids()
    {
        return numberOfFlutteroids;
    }

		/**
		 * Checks whether the Farm is empty
		 * @return a boolean value of true if farm is empty 
		 */
    public boolean isFarmEmpty()
    {
        return (treeRoot == null); 
    }

		/**
		 * Resets farm to initial state
		 */
    public void clearFarm()
    {
				treeRoot = null;
				numberOfFlutteroids = 0;
				samIsCapturing = false;
				farmTotalTime = 0;
    }
    
		/**
		 * Public method which adds a Flutteroid to the farm
		 * @param flutteroid a Flutteroid instance
		 * @return true if a flutteroid was added 
		 */
    public boolean addFlutteroid(Flutteroid flutteroid)
    {
			if (flutteroid == null)
				addSuccessful = false;
			if ( findFlutteroid(flutteroid.getNameID()) != null)
				addSuccessful = false;
			else {
				treeRoot = addFlutteroid(treeRoot,flutteroid);
				addSuccessful = true;
			}
			return addSuccessful;
		}
    
		/**
		 * Private method called by the public method to add a flutteroid into BST
		 * @param localRoot - a BST representation
		 * @param flutteroid - flutteroid instance to be added
		 * @return a BST used for recursion 
		 */
    private FlutteroidBSTNode addFlutteroid(FlutteroidBSTNode localRoot,
            Flutteroid flutteroid)
    {
		if (localRoot == null)
		{
			localRoot = new FlutteroidBSTNode(flutteroid);
			numberOfFlutteroids++;
			return localRoot;
		}
		int stringCompareVal = flutteroid.getNameID().compareTo(
									localRoot.getFlutteroidData().getNameID());
		if ( stringCompareVal == 0 )
		{
			return null;
		}
		else if ( stringCompareVal < 0 )
		{
			localRoot.setLeftChild(addFlutteroid(localRoot.getLeftChild(), flutteroid));
			return localRoot;
		}
		else if ( stringCompareVal > 0 )
		{
			localRoot.setRightChild(addFlutteroid(localRoot.getRightChild(), flutteroid));
			return localRoot;
		}
		return null;
    }
    
		/**
		 * Find's a flutteroid in the Farm
		 * @param flutteroidName name of flutteroid to search for
		 * @return A flutteroid instance if found; null otherwise 
		 */
    public Flutteroid findFlutteroid(String flutteroidName)
    {
		if (flutteroidName == null)
			return null;
		else
			return findFlutteroid(treeRoot, flutteroidName);
    }

		/**
		 * A private method used for recursing through the BST Farm to find the flutteroid
		 * @param localRoot A BST or BST Subtree 
		 * @param flutteroidName name of the flutteroid we're looking for
		 * @return flutteroid instance or null if not found 
		 */
    private Flutteroid findFlutteroid(FlutteroidBSTNode localRoot, String flutteroidName)
    {
			int stringCompareVal;
				
		if (localRoot == null)
			return null;
		else {
			stringCompareVal = flutteroidName.compareTo(
									localRoot.getFlutteroidData().getNameID());
		}
		if (stringCompareVal == 0)
		{
			return localRoot.getFlutteroidData();
		}
		else if (stringCompareVal < 0)
			return findFlutteroid(localRoot.getLeftChild(), flutteroidName);
		else if (stringCompareVal > 0)
			return findFlutteroid(localRoot.getRightChild(), flutteroidName);
		return null;
    }
 
		/**
		 * A text representation of the contents of BST in alphabetical order
		 */
    public void displayFlutteroidFarmAscending()
    {
			System.out.println("-=: Zebestation Farm Flutteroids - Ascending: ("
							 + numberOfFlutteroids + ") :=-");
			System.out.println("-------------------------------"
							+ "------------------------");
			if (treeRoot == null)
				System.out.println("    The Farm Has No Flutteroids");
			else
				displayFlutteroidFarmAscending(treeRoot);
    }

		/**
		 * An inorder traversal of the BST display contents alphabetically
		 * @param localRoot the BST to traverse
		 */
    private void displayFlutteroidFarmAscending(FlutteroidBSTNode localRoot)
    {
			if (localRoot == null)
				return;
			displayFlutteroidFarmAscending(localRoot.getLeftChild());
			System.out.println(localRoot.getFlutteroidData().toString());
			displayFlutteroidFarmAscending(localRoot.getRightChild());
    }

		/**
		 * A text representation of the contents in reverse alphabetical order
		 */
    public void displayFlutteroidFarmDescending()
    {
			System.out.println("-=: Zebestation Farm Flutteroids - Descending: ("
							 + numberOfFlutteroids + ") :=-");
			System.out.println("-------------------------------"
							+ "------------------------");
			if (treeRoot == null)
				System.out.println("    The Farm Has No Flutteroids");
			else
				displayFlutteroidFarmDescending(treeRoot);
    }

		/**
		 * A traversal of the tree to display items in reverse order 
		 * @param localRoot the BST to traverse
		 */
    private void displayFlutteroidFarmDescending(FlutteroidBSTNode localRoot)
    {
			if (localRoot == null)
				return;
			displayFlutteroidFarmDescending(localRoot.getRightChild());
			System.out.println(localRoot.getFlutteroidData().toString());
			displayFlutteroidFarmDescending(localRoot.getLeftChild());
    }

		/**
		 * A visual representation of the BST
		 */
    public void displayFlutteroidFarmStructure()
    {
			System.out.println("-=: Zebestation Farm Flutteroids - Structure: ("
							 + numberOfFlutteroids + ") :=-");
			System.out.println("-------------------------------"
							+ "------------------------");
			if (treeRoot == null)
				System.out.println("    The Farm Has No Flutteroids");
			else
				displayFlutteroidFarmStructure(treeRoot,0,"ROOT");
    }

		/**
		 * Private helper method to traverse the BST 
		 * @param localRoot BST to traverse
		 * @param depth the depth of the BST
		 * @param nodeLabel Whether it is ROOT, LEFT, RIGHT NODE 
		 */
    private void displayFlutteroidFarmStructure(FlutteroidBSTNode localRoot,
            int depth, String nodeLabel)
    {
			for (int i=0; i<depth; i++)
				System.out.print("    ");
			if (localRoot != null)
			{
				System.out.println(nodeLabel + " " + localRoot.getFlutteroidData().toString());
				displayFlutteroidFarmStructure(localRoot.getLeftChild(),depth+1,"LEFT");
				displayFlutteroidFarmStructure(localRoot.getRightChild(),depth+1,"RIGHT");
			}	
			else
					System.out.println(nodeLabel + " null");
    }
    
		/**
		 * Updates the Farm by a single time unit
		 */
		public void updateFarm() {
			farmTotalTime++;
			System.out.println("Updating Farm Status By One Time Unit (Total Time: "
							+ farmTotalTime + "):");

			if (!isFarmEmpty()) {
				moveFlutteroids();
				updateEscapees();
				attemptCapture();
			} else {
				System.out.println("    Farm has no flutteroids. No work to be done.");
			}
		}
    
		/**
		 * Moves flutteroids randomly on x,y in [-100,100] grid 
		 */
    public void moveFlutteroids()
    {
			Queue<FlutteroidBSTNode> flutteroidQ = new LinkedList<>();
			if ( !isFarmEmpty() )
			{
				flutteroidQ.add(treeRoot);
				while ( !flutteroidQ.isEmpty() )
				{
					FlutteroidBSTNode curFlutteroid = flutteroidQ.remove();
					curFlutteroid.getFlutteroidData().move();
					if (curFlutteroid.getLeftChild() != null)
						flutteroidQ.add(curFlutteroid.getLeftChild());
					if (curFlutteroid.getRightChild() != null)
						flutteroidQ.add(curFlutteroid.getRightChild());
				}

			}

    }

		/**
		 * Updates escaped flutteroids
		 */
    public void updateEscapees()
    {
			if ( treeRoot != null)
				updateEscapees(treeRoot);
    }

		/**
		 * Updates the flutteroids and provides user with information on escapee
		 * @param localRoot Tree of Flutteroids to choose from 
		 */
    private void updateEscapees(FlutteroidBSTNode localRoot)
    {
			if (localRoot == null)
				return;
			else
			{
				updateEscapees(localRoot.getLeftChild());
				updateEscapees(localRoot.getRightChild());
				if ( localRoot.getFlutteroidData().getLocation().getX() > HIGH_FARM_BOUNDARY || 
						 localRoot.getFlutteroidData().getLocation().getX() < LOW_FARM_BOUNDARY ||
						 localRoot.getFlutteroidData().getLocation().getY() > HIGH_FARM_BOUNDARY ||
						 localRoot.getFlutteroidData().getLocation().getY() < LOW_FARM_BOUNDARY )
				{
					int randCap = randRange(1,10);
					System.out.println("WARNING: " + localRoot.getFlutteroidData().toString() 
					+ " Has Escaped!! Will take " + randCap + " time units to capture.");
					localRoot.getFlutteroidData().increaseEscapes();
					FlutteroidEscapeEntry newEntry = new FlutteroidEscapeEntry(
									localRoot.getFlutteroidData(), randCap);
					escapedFlutteroids.add(newEntry);
					removeFlutteroid(treeRoot, localRoot.getFlutteroidData().getNameID());
				}
				
			}
    }
    
		/**
		 * Method that attempts a capture of a flutteroid 
		 */
    public void attemptCapture()
    {
			if ( escapedFlutteroids.isEmpty() )
				System.out.println("     All Flutteroids Safely on the Farm!");
			else {
				if ( !samIsCapturing )
				{
					System.out.println("SAM UPDATE: The spice must flow!! Sam is now"
									+ " hunting the escaped flutteroids."); 
					samIsCapturing = true;
				}
				FlutteroidEscapeEntry eeoEscapee = escapedFlutteroids.peek();
				eeoEscapee.decressRemainingCaptureTime();
				if (eeoEscapee.getRemainingCaptureTime() == 0)
				{
					eeoEscapee = escapedFlutteroids.remove();
					Flutteroid flutter = eeoEscapee.getEscapedFlutteroid();
					System.out.println("    CAPTURE: Flutteroid " + flutter.toString() + 
									" has been captured and returned to the farm!");
					flutter.setXLocation(0);
					flutter.setYLocation(0);
					addFlutteroid(flutter);
					if ( escapedFlutteroids == null )
					{
						System.out.println("SAM UPDATE: Her hunting is complete. Sam "
										+ "stays on the farm.");
						samIsCapturing = false;
					}
				}
				else
					System.out.println("SAM UPDATE: Getting closer to capturing "
										+ eeoEscapee.getEscapedFlutteroid().toString() + 
									" with " + eeoEscapee.getRemainingCaptureTime() + " time"
													+ " units remaining.");
			}
    }
    
		/**
		 * Text representation of the escaped flutteroids
		 */
    public void displayEscapedFlutteroids()
    {
			System.out.println("-=: Escaped Flutteroids ("+ escapedFlutteroids.size() 
							+") :=-");
			System.out.println("-------------------------------------------"
							+ "------------");
			if (escapedFlutteroids == null)
				System.out.println("    Currently No Escaped Flutteroids");
			else
			{
				for (FlutteroidEscapeEntry flutter: escapedFlutteroids) {
					System.out.println("     {" + flutter.getEscapedFlutteroid().toString()  
									+ "CTime: " + flutter.getRemainingCaptureTime() + "}");
				}
			}
    }

    /********  Completed Methods Section ********/
    
    /**
     * Attempts to remove a Flutteroid from the BST farm based on the Flutteroid
     * name value. If the name is null, no action is taken.
     *
     * @param flutteroidName a string reference containing the Flutteroid name
     * to remove
     *
     * @return true if the Flutteroid was removed, false otherwise
     */
    public Flutteroid removeFlutteroid(String flutteroidName)
    {
        removedFlutteroid = null;

        if (flutteroidName != null)
        {
            treeRoot = removeFlutteroid(treeRoot, flutteroidName);
        }

        return removedFlutteroid;
    }

    /**
     * Private recursive helper method that attempts to remove a Flutteroid from
     * the BST farm.
     *
     * @param localRoot the local root of the BST
     * @param flutteroidName a string reference containing the Flutteroid name
     * to search for
     *
     * @return the local tree root with the node removed (if found)
     */
    private FlutteroidBSTNode removeFlutteroid(FlutteroidBSTNode localRoot,
            String flutteroidName)
    {
        // the item is not in the tree
        if (localRoot == null)
        {
            removedFlutteroid = null;
            return localRoot;
        }

        int compResult = flutteroidName.compareTo(localRoot.getFlutteroidData().getNameID());

        // item is smaller than local root data
        if (compResult < 0)
        {
            localRoot.setLeftChild(removeFlutteroid(localRoot.getLeftChild(), flutteroidName));
            return localRoot;
        }
        // item is larger than the local root data
        else if (compResult > 0)
        {
            localRoot.setRightChild(removeFlutteroid(localRoot.getRightChild(), flutteroidName));
            return localRoot;
        }
        // item is the local root
        else
        {
            removedFlutteroid = localRoot.getFlutteroidData();
            numberOfFlutteroids--;

            // if no left child, return right child
            if (localRoot.getLeftChild() == null)
            {
                return localRoot.getRightChild();
            }
            // if no right child, return left child
            if (localRoot.getRightChild() == null)
            {
                return localRoot.getLeftChild();
            }
            // there is a left and right child
            else
            {
                // left child has no right child, replace root data with left child data
                if (localRoot.getLeftChild().getRightChild() == null)
                {
                    localRoot.setFlutteroidData(localRoot.getLeftChild().getFlutteroidData());

                    // replace left child with its left child
                    localRoot.setLeftChild(localRoot.getLeftChild().getLeftChild());

                    return localRoot;
                }
                else
                {
                    localRoot.setFlutteroidData(findLargestChild(localRoot.getLeftChild()));
                    
                    return localRoot;
                }
            }
        }

    }

    /**
     * Find the largest child value in the subtree and replace it with its left
     * child (if any)
     *
     * @param localRoot the local root (parent) of a possible in-order
     * predecessor
     *
     * @return the data in the in-order predecessor
     */
    private Flutteroid findLargestChild(FlutteroidBSTNode localRoot)
    {
        // if the right child has no right child, it is the inorder predecessor
        if (localRoot.getRightChild().getRightChild() == null)
        {
            Flutteroid returnedFlutteroid = localRoot.getRightChild().getFlutteroidData();
            localRoot.setRightChild(localRoot.getRightChild().getLeftChild());
            
            return returnedFlutteroid;
        }
        else
        {
            return findLargestChild(localRoot.getRightChild());
        }
    }
    
    /**
     * Returns an integer in the range of min to max inclusive. If min is
     * greater than max, the method throws an illegal argument exception.
     *
     * @param min minimum random integer value in the range
     * @param max maximum random integer value in the range
     *
     * @return a integer between min and max inclusive
     */
    private int randRange(int min, int max)
    {
        if (min > max)
        {
            throw new IllegalArgumentException("min is greater than max");
        }

        return rand.nextInt((max - min) + 1) + min;
    }
}
