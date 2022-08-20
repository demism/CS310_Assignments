/*
 * CS310 Assignment 12 - Binary Search Trees
 */
package cs310datastructures;

/**
 * A Node for a binary search tree that contains Flutteroid data
 *
 * @author Jeffrey LaMarche
 * @version 1.0 2020-10-20 - Initial Version
 */
public class FlutteroidBSTNode
{
    /*
     The Flutteroid data held in the node
     */
    private Flutteroid flutteroidData;
    
    /*
    The left child link for the node
    */
    private FlutteroidBSTNode leftChild;
    
    /*
    The right child link for the node
    */
    private FlutteroidBSTNode rightChild;

    /**
     * The default constructor for a Flutteroid Node object. All
     * references are set to null. 
     */
    public FlutteroidBSTNode()
    {
        flutteroidData = null;
        leftChild = null;
        rightChild = null;
    }

    /**
     * A constructor that allows setting the Flutteroid data to a specific 
     * starting value. This could be null. The left and right links are set
     * to null.
     * 
     * @param flutteroidData a reference to the Flutteroid data to set in the node
     */
    public FlutteroidBSTNode(Flutteroid flutteroidData)
    {
        this.flutteroidData = flutteroidData;
        leftChild = null;
        rightChild = null;
    }

    /**
     * Allows obtaining access to the Flutteroid data contained in the node
     * 
     * @return a reference to the Flutteroid data in the node, which could be null
     */
    public Flutteroid getFlutteroidData()
    {
        return flutteroidData;
    }

    /**
     * Allows obtaining access to the left child of the node
     * 
     * @return a FlutteroidBSTNode reference to the left child of the node, 
     * which could be null
     */
    public FlutteroidBSTNode getLeftChild()
    {
        return leftChild;
    }

    /**
     * Allows obtaining access to the right child of the node
     * 
     * @return a FlutteroidBSTNode reference to the right child of the node, 
     * which could be null
     */
    public FlutteroidBSTNode getRightChild()
    {
        return rightChild;
    }

    /**
     * Allows setting the Flutteroid data in the node to a new value
     * 
     * @param flutteroidData a FlutteroidBSTNode reference to set the node data
     * to, which could be null
     */
    public void setFlutteroidData(Flutteroid flutteroidData)
    {
        this.flutteroidData = flutteroidData;
    }

    /**
     * Allows setting the left child FlutteroidBSTNode reference to a new value
     * 
     * @param leftChild a FlutteroidBSTNode reference to set the left child to,
     * which could be null
     */
    public void setLeftChild(FlutteroidBSTNode leftChild)
    {
        this.leftChild = leftChild;
    }

    /**
     * Allows setting the right child FlutteroidBSTNode reference to a new value
     * 
     * @param rightChild a FlutteroidBSTNode reference to set the right child to,
     * which could be null
     */
    public void setRightChild(FlutteroidBSTNode rightChild)
    {
        this.rightChild = rightChild;
    }
    
}
