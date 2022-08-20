/*
 * CS310 Assignment 4 - ArrayLists, Data Persistence, and Refactoring
 */
package cs310datastructures;

import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * A class that implements a Shelving System with features to
 * save data stored in a shelf.
 * 
 * @author Demis Mota
 * @version 1.0 - Initial ShelvingSytem implementation
 */

public class ShelvingSystem implements ShelvingOperations, DataFileOperations
{

    /*
    The size of a shelf. (Just used for displaying now)
     */
    private static final int SHELF_SIZE = 5;

    /*
    The ArrayList that holds all of the shelves items
     */
    private ArrayList<ShelfItem> shelves;

	/**
	 * Default Constructor instantiates a new ArrayList<ShelfItem>
	 * 
	 */
	public ShelvingSystem(){
		shelves = new ArrayList<>();
	} 
	/**
	 * Gives the number of shelf items 
	 * @return an integer representing the number of items on the shelf 
	 */
	public int getNumberOfShelfItems(){ 
		return shelves.size();
	}

	@Override
	public boolean addItem(ShelfItem item) {
		return shelves.add(item);
	}

	@Override
	public boolean addItem(ShelfItem item, int position) {
		if ( position < 0 || position >= shelves.size())
			return false;
		shelves.add(position, item);
		return true;
	}

	@Override
	public ShelfItem removeItem(String itemName) {
		for (ShelfItem item : shelves){
			if(item == findItem(itemName))
			{
				shelves.remove(item);
				return item;
			}
		}
		return null;
	}

	@Override
	public ShelfItem findItem(String itemName) {
		for(ShelfItem item : shelves){
			if( item.getItemName().equals(itemName))
				return item;
		}
		return null;
	}

	@Override
	public void displayShelves() {
	System.out.println("** Displaying All Shelf Contents **");
		int shelfNumber = 1;

		if( shelves.isEmpty())
			System.out.println("    Shelves Contain No Items");
		else {
			for (int i=0; i<shelves.size(); i++)
			{
				if ( i % SHELF_SIZE == 0)
					System.out.println("    Shelf " + shelfNumber++ + " Contents:");
					System.out.printf("%8d: %s\n",(i+1),shelves.get(i).toString());
			}
		}
	}

	@Override
	public boolean loadData(String filename) {
		File file = new File(filename); 
		Scanner infile = null;
		String temp;
		final String delimiter = "###";
		try {
			infile = new Scanner(file);
		}
		catch (FileNotFoundException fnfE) {
			System.out.println("ERROR: " + filename + "could not be opened!!");
			return false;
		}

		while( infile.hasNext()){
			temp = infile.nextLine();
			String[] lineValues = temp.split(delimiter);
			if (lineValues.length == 5)
			{
				if (lineValues[0].equals("BOOK"))
				{
					System.out.println("LOADING BOOK: " + lineValues[1]);
					Book tmpBook;
					tmpBook = new Book(lineValues[1],lineValues[2],
							Integer.parseInt(lineValues[3]), 
							Integer.parseInt(lineValues[4]));
					addItem(tmpBook);
				}
				else if (lineValues[0].equals("GAME"))
				{
					System.out.println("LOADING GAME: " + lineValues[1]);
					VideoGame tmpGame;
					tmpGame = new VideoGame(lineValues[1],lineValues[2],
							Integer.parseInt(lineValues[3]), 
							Boolean.getBoolean(lineValues[4]));
					addItem(tmpGame);
				}
				else
				{
					System.out.println("ERROR:Illegal Item Type!");
					System.out.println("  Item Type Ignored: " + lineValues[0]);
				}
			}
			else {
				System.out.println("ERROR:Illegal Line Format!");
				System.out.println("    Line Ignored: " + temp);
			}
		}
		infile.close();
		return true;
	}

	@Override
	public boolean saveData(String filename) {
		File file = new File(filename);
		PrintWriter outfile = null;
		try {
			outfile = new PrintWriter(file);
		}
		catch (Exception e){
			System.out.println("ERROR: " + filename + " Could Not Be Created!");
			return false;
		}
		if (shelves.isEmpty())
			System.out.println("The shelves contain no data.");
		for (ShelfItem item : shelves){
			System.out.println("SAVING " + item.toString());
			outfile.println(item.toFileFormat());
		}
		outfile.flush();
		outfile.close();

		return true;
	}
}
