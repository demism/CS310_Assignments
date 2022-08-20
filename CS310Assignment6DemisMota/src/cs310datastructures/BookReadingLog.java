/*
 * CS310 Assignment 6 - Java Collected LinkedList and Iterators
 */
package cs310datastructures;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Scanner;

/**
 * Class implementation for a book reading log.   
 * @author Demis Mota
 * @version 1.0  - 2022-Jun-12 - LinkedList implementation of BookReadingLog  
 */
public class BookReadingLog implements ReadingLogBasicOps, ReadingLogAdvOps, 
	DataFileOps
{
	private LinkedList<BookReadEntry> booksFinished;

	public BookReadingLog(){
		booksFinished = new LinkedList<>();
	}

	public int getNumberOfBooksRead() {
		return booksFinished.size();
	}

	public boolean isReadingLogEmpty() {
		return booksFinished.isEmpty();
	}
	/**
	 * Adds a book to the reading log
	 * @param bookEntryObj the book log data to be added
	 * @return returns true if data was added successfully 
	 */
	@Override
	public boolean addBook(BookReadEntry bookEntryObj) {
		if (bookEntryObj == null)
			return false;
		else {
			booksFinished.add(new BookReadEntry(bookEntryObj.getBookObj(),
					bookEntryObj.getDateFinished(),bookEntryObj.isOwned()));
			return true;
		}
	}
	/**
	 * Removes the oldest entry in the book Reading Log with the book title 
	 * 
	 * @param bookTitle the title of the book to be removed
	 * @return the removed book as a BookReadEntry object 
	 */
	@Override
	public BookReadEntry removeBook(String bookTitle) {
		Iterator readItem = booksFinished.iterator();
		while(readItem.hasNext()){
			BookReadEntry bookRemove = findBook(bookTitle);
			BookReadEntry tempBook = (BookReadEntry)readItem.next();
			if ( bookRemove != null && tempBook.equals(bookRemove))
			{
				bookRemove = tempBook;
				readItem.remove();
				return bookRemove;
			}
		}
		return null;
	}
	/**
	 * Finds the oldest entry in the book Reading Log with the book title 
	 * 
	 * @param bookTitle the title of the book to be found
	 * @return the found book as a BookReadEntry object 
	 */
	@Override
	public BookReadEntry findBook(String bookTitle) {
		Iterator readBook = booksFinished.iterator();
		while ( readBook.hasNext())
		{
			BookReadEntry bookRead = (BookReadEntry)readBook.next();
			if (bookRead.getBookObj().getBookTitle().equals(bookTitle))
				return bookRead;
		}
		return null;
	}
	/**
	 * Shows the reading log 
	 */
	@Override
	public void displayReadingLog() {
		Iterator readItem = booksFinished.iterator();
		System.out.println("** Displaying Books ReadLog - Newest to Oldest **");
		if (isReadingLogEmpty()){
			System.out.println("  The Reading Log is Empty!");
		}
		else {
			int logSize = booksFinished.size(); 
			while (readItem.hasNext()) {
				BookReadEntry bookRead = (BookReadEntry)readItem.next();
				System.out.println("  " + logSize-- + ": " +
						bookRead.toString());
						
			}
		}
	}
	/**
	 * Removes all books with the same title 
	 * @param bookTitle the title of the book to be removed
	 * @return an ArrayList with the all removed books 
	 */
	@Override
	public ArrayList<BookReadEntry> removeAllBooks(String bookTitle) {
		ArrayList<BookReadEntry> bookList = new ArrayList<>();
		Iterator readItem = booksFinished.iterator();
		
		while(readItem.hasNext()) {
			BookReadEntry bookRead = (BookReadEntry)readItem.next();
			if ( bookRead.getBookObj().getBookTitle().equals(bookTitle)) {
				bookList.add(bookRead);
				readItem.remove();
			}
		} return bookList;
	}
	/**
	 * Finds all books with the same title 
	 * @param bookTitle the title of the book to be found
	 * @return an ArrayList with the all found books 
	 */
	@Override
	public ArrayList<BookReadEntry> findAllBooks(String bookTitle) {
		ArrayList<BookReadEntry> bookList = new ArrayList<>();
		for (BookReadEntry bookRead : booksFinished) {
			if ( bookRead.getBookObj().getBookTitle().equals(bookTitle)) {
				bookList.add(bookRead);
			}
		}
		return bookList;
	}
	/**
	 * Displays the reading log in reverse order
	 */
	@Override
	public void displayReadingLogReverse() {
		ListIterator readItem = booksFinished.listIterator(booksFinished.size());
		System.out.println("** Displaying Books ReadLog - Oldest to Newest **");
		if (isReadingLogEmpty()){
			System.out.println("  The Reading Log is Empty!");
		}
		else {
			int logSize = 1; 
			while (readItem.hasPrevious()) {
				BookReadEntry bookRead = (BookReadEntry)readItem.previous();
				System.out.println("  " + logSize++ + ": " +
						bookRead.toString());
						
			}
		}
	}
	/**
	 * Loads book data from a file 
	 * @param filename the name of the file to load data from
	 * @return returns true if load was successful
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
					theBook = new Book(lineValues[1],lineValues[2],
						Integer.parseInt(lineValues[3]),
							Integer.parseInt(lineValues[4]));
					newCalObj = Calendar.getInstance();
					newCalObj.set(Calendar.YEAR,
							Integer.parseInt(lineValues[5]));
					newCalObj.set(Calendar.MONTH,
							Integer.parseInt(lineValues[6]));
					newCalObj.set(Calendar.DAY_OF_MONTH,
							Integer.parseInt(lineValues[7]));
					newCalObj.set(Calendar.HOUR,
							Integer.parseInt(lineValues[8]));
					newCalObj.set(Calendar.MINUTE,
							Integer.parseInt(lineValues[9]));
					newCalObj.set(Calendar.SECOND,
							Integer.parseInt(lineValues[10]));
					addBook(new BookReadEntry(theBook,newCalObj,
							Boolean.parseBoolean(lineValues[11])));
			  
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
	 * Saves reading log data to a file
	 * @param filename the name of the file to save to
	 * @return returns true if the data was successfully saved.
	 */
	@Override
	public boolean saveData(String filename) {
		File saveFile = new File(filename);
		PrintWriter outFile; 
		Iterator saveItem = booksFinished.iterator();

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
		while (saveItem.hasNext()){
			BookReadEntry current = (BookReadEntry)saveItem.next();
			System.out.println("SAVING BOOK: " + 
							current.getBookObj().getBookTitle());
			outFile.println(current.toFileFormat());
		}
		outFile.close();
		return true;
	}
}
