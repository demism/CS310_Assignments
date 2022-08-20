/*
 * CS310 Assignment 12 - Binary Search Trees
 */
package cs310datastructures;

import java.util.Random;

/**
 * Generates a random valid flutteroid object
 * 
 * @author Jeffrey LaMarche
 * @version 1.0 2020-10-20 Initial Version
 */
public class FlutteroidGenerator
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
    The minimum and maximum integer portion for the name (inclusive)
    */
    private static final int NUM_ID_MIN = 1000;
    private static final int NUM_ID_MAX = 9999;
    
    /*
    The minimum and maximum range for flutteroid movement (inclusive)
    */
    private static final int MOVE_RANGE_MIN = 0;
    private static final int MOVE_RANGE_MAX = 20;
    
    /*
    The possible name starts for a flutteroid
    */
    private static final String[] NAMES =
                {"ALPA", "ASPU", "ARES",
                 "BETA", "BRIA", "BOVU",
                 "CHIU", "CLUA", "CHEA",
                 "DELA", "DUST", "DARK",
                 "EPSI", "EPHI", "ESSI",
                 "FLIT", "FLUT", "FREA",
                 "GAMA", "GAIA", "GEOM",
                 "HAST", "HALA", "HAPA",
                 "IOTA", "ILAM", "ISTO",
                 "JYKE", "JANI", "JOJU",
                 "KAPA", "KRIL", "KOPH",
                 "LAMA", "LURN", "LEEM",
                 "MUIA", "MULA", "MEKI", 
                 "NUZI", "NAGA", "NEEP", 
                 "OGEA", "OPIA", "OLUM", 
                 "PRIT", "PIRA", "PLUW",
                 "QEAL", "QUIX", "QUAE", 
                 "RHOU", "RENX", "RUMU", 
                 "SIMA", "SMOA", "SHOU", 
                 "THAU", "TRAL", "TINX",
                 "URET", "USIA", "ULEA", 
                 "VESI", "VUSA", "VIPE", 
                 "WHEI", "WOIL", "WOUZ", 
                 "XAIN", "XIIM", "XSUA",
                 "YEBO", "YRAG", "YEEB", 
                 "ZETA", "ZLAP", "ZEUS"};
    
    /*
    Random number generator reference
    */
    private Random rand;
    
    /**
     * The default constructor for the FlutteroidGenerator class
     */
    public FlutteroidGenerator()
    {
        rand = new Random();
    }
    
    /**
     * Obtains a new properly setup Flutteroid object with a unique name.
     * 
     * @return a Flutteroid object reference
     */
    public Flutteroid getFlutteroidInstance()
    {
        Flutteroid generatedFlutteroid = null;

        // create a random name for the flutteroid
        String name = NAMES[randRange(0, NAMES.length - 1)];
        int numID = randRange(NUM_ID_MIN, NUM_ID_MAX);
        name = name + "-" + numID;
        
        // create a random starting location on the farm
        int startX = randRange(LOW_FARM_BOUNDARY, HIGH_FARM_BOUNDARY);
        int startY = randRange(LOW_FARM_BOUNDARY, HIGH_FARM_BOUNDARY);
        
        // create a random movement range
        int moveRange = randRange(MOVE_RANGE_MIN, MOVE_RANGE_MAX);

        // instantiate a new Flutteroid object with the values created
        generatedFlutteroid = new Flutteroid(name, startX, startY, moveRange);
        
        return generatedFlutteroid;
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
