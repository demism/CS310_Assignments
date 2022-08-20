/*
 * CS310 Assignment 5 - Singly Linked Lists
 */
package cs310datastructures;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.Calendar;

/**
 * A class which keeps track of books read. To include, date, time, and year.
 * As well as keeping the books in read order.
 * 
 * @author Jeffrey LaMarhce 
 * @version 1.0 2020-08-29 - Initial Template Version
 * 
 * @author Demis Mota
 * @version 1.1 2022-06-01 - Initial Implementation
 */
public class BookReadingLog implements ReadingLogBasicOps, DataFileOps 
{
    /*
    The head node reference for the singly linked list
     */
    private BookReadNode listHead;

    /*
    The tail node reference for the singly linked list
     */
    private BookReadNode listTail;

    /*
    The number of items stored in the singly linked list
     */
    private int numberOfBooksRead;
	/**
	 * Default constructor initializes data members to 0 or null.  
	 */
	public BookReadingLog() {
		listHead = null;
		listTail = null;
		numberOfBooksRead = 0;
	}
	/**
	 * Getter method for number of Books Read
	 * @return an integer representing the number of books read 
	 */
	public int getNumberOfBooksRead() {
		return numberOfBooksRead;
	}
	/**
	 * Method checks whether the log is currently empty
	 * @return a boolean representing whether the log is empty or not
	 */
	public boolean isReadingLogEmpty() {
		if ( listHead == null)
			return true;
		return false;
	}
	/**
	 * Method that tries to add a book at the end of the list. Or creates a
	 * new entry. 
	 * @param bookEntryObj a parameter Representing a BookReadEntry
	 * @return a boolean variable representing whether a book was added.
	 */
	@Override
	public boolean addBook(BookReadEntry bookEntryObj) {
		BookReadNode bookNode = new BookReadNode(bookEntryObj);
		boolean addedBook = false;
		
		if (isReadingLogEmpty()) {
			listHead = bookNode;
			listTail = bookNode;
			addedBook = true;
		}
		else {
			listTail.setNextNode(bookNode);
			listTail = bookNode;
			addedBook = true;
		}
		if (addedBook)
			numberOfBooksRead++;
		return addedBook;
	}

	/**
	 * Method removes a book from list of books read
	 * @param bookTitle a String representing the title of the book
	 * @return the BookReadEntry object representing a book read 
	 */
	@Override
	public BookReadEntry removeBook(String bookTitle) {
		BookReadEntry bookToRemove = findBook(bookTitle);
		BookReadEntry curBook = null;
		BookReadNode current = listHead;
		BookReadNode previous = null;

		if (bookToRemove == null)
			return bookToRemove;

		while( current != null )
		{
			curBook = current.getBookReadEntryObj();

			if(curBook != null){
				Book theBook = curBook.getBookObj();
				if (theBook != null) {
					if( bookTitle.equals(theBook.getBookTitle()) )
					{
						if( listHead == current )
							listHead = listHead.getNextNode();
						else{
							previous.setNextNode(current.getNextNode());
							if ( current == listTail )
								listTail = previous;
						}
						numberOfBooksRead--;
						return bookToRemove;
					}
				}
			}
			previous = current;
			current = current.getNextNode();
		}
		bookToRemove = null;
		return bookToRemove;
	}
	/**
	* Method which finds whether a book is on the list of books read
	* @param bookTitle the title of the book you're searching for
	* @return a BookReadEntry data type representing the object
	*/
	@Override
	public BookReadEntry findBook(String bookTitle) {
		BookReadEntry readEntry = null;
		BookReadNode current = listHead;
		Book curBook = null;

		while(current != null)
		{
			readEntry = current.getBookReadEntryObj();
			if (readEntry != null){
				curBook = readEntry.getBookObj();
				if(curBook != null){
					if(curBook.getBookTitle().equals(bookTitle)){
						return readEntry;	
					}	
				}
			}
			readEntry = null;
			current = current.getNextNode();
		}
		return readEntry; 
	}
	/**
	 * Method shows all the books read from oldest to newest.
	 */
	@Override
	public void displayReadingLog() {
		BookReadNode current = listHead;
		System.out.print("** Displaying Books Read Log - ");
		System.out.println("Oldest to Newest **");
		if (listHead == null)
		{
			System.out.println("  The Reading Log is Empty!");
		}
		else
		{
			int count = 1;
			while (current != null){
				System.out.println("  " + count + ". " + current.getBookReadEntryObj().toString());
				current = current.getNextNode();
				count++;
			}
		}
	}
	/**
	 *  Method to load saved data from a file
	 * @param filename the file name of the file to load from
	 * @return returns true or false depending on whether data was loaded 
	 */
	@Override
	public boolean loadData(String filename) {
		File dataFile = new File(filename);
		Scanner inFile;
		Book theBook;
		final String delimiter = "###";
		BookReadEntry bookFound;
		Calendar newCalObj = Calendar.getInstance();  

		try {
			inFile = new Scanner(dataFile);
		}
		catch (FileNotFoundException fnfe) {
			System.out.println("ERROR! " + filename + " could not be opened!");
			return false;
		}
		if (!inFile.hasNext())
			System.out.println("File Is Empty!!!");

		while (inFile.hasNext()){
			String line = inFile.nextLine();
			String[] lineValues = line.split(delimiter);

			if (lineValues.length == 12)
			{
				if (lineValues[0].equals("BOOK"))
				{
					System.out.println("LOADING BOOK: " + lineValues[1]);
					bookFound = findBook(lineValues[1]); 
					if (bookFound == null)
					{
						theBook = new Book(lineValues[1],lineValues[2],
							Integer.parseInt(lineValues[3]),Integer.parseInt(lineValues[4]));
						newCalObj = Calendar.getInstance();
						newCalObj.set(Calendar.YEAR,Integer.parseInt(lineValues[5]));
						newCalObj.set(Calendar.MONTH,Integer.parseInt(lineValues[6]));
						newCalObj.set(Calendar.DAY_OF_MONTH,Integer.parseInt(lineValues[7]));
						newCalObj.set(Calendar.HOUR,Integer.parseInt(lineValues[8]));
						newCalObj.set(Calendar.MINUTE,Integer.parseInt(lineValues[9]));
						newCalObj.set(Calendar.SECOND,Integer.parseInt(lineValues[10]));
						BookReadEntry bookEntry = new BookReadEntry(theBook,newCalObj,
								Boolean.parseBoolean(lineValues[11]));
					  addBook(bookEntry);
					}
				}
			}
			else{
				System.out.println("ERROR: Illegal Item Type!");
				System.out.println("  Line Ignored: " + line );
			}
		}
		return true;
	}
	/**
	 * Method that saves all book read data into designated file.
	 * @param filename the file the data will be saved to
	 * @return a boolean value representing whether data was saved. 
	 */
	@Override
	public boolean saveData(String filename) {
		File saveFile = new File(filename);
		PrintWriter outFile; 
		BookReadNode current = listHead;

		try {
			outFile = new PrintWriter(saveFile);
		}
		catch (Exception e)
		{
			System.out.println("ERROR: " + filename + " Could not be created!");
			return false;
		}
		if (isReadingLogEmpty())
		{
			System.out.println("Reading Log is Empty!");
		}
		while (current != null){
			System.out.println("SAVING BOOK: " + 
							current.getBookReadEntryObj().getBookObj().getBookTitle());
			outFile.println(current.getBookReadEntryObj().toFileFormat());
			current = current.getNextNode();
		}
		outFile.close();
		return true;
	}
}
