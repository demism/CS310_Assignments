/*
 * CS310 Assignment 5 - Singly Linked Lists
 */
package cs310datastructures;

import java.util.Scanner;

/**
 * A class representing a basic book object.
 *
 * @author Jeffrey LaMarche
 * @version 1.0  2020-July-09 Initial version
 * @version 1.1  2020-Aug-15 Added user input validation
 * @version 1.11 2020-Aug-23 Updated to implement CollectionItem
 */
public class Book extends CollectionItem
{

    private String bookAuthor;  // the bookAuthor(s) of the book
    private int timesRead;      // how many times the book has been read
    private int numberPages;    // the number of pages in the book

    /**
     * The default constructor
     */
    public Book()
    {
        super();
        timesRead = 0;
        numberPages = 0;
        bookAuthor = "";
    }

    /**
     * A constructor that allows creating a book with a title, bookAuthor, the
     * number of pages in the book, and the number of times the book has been
     * read
     *
     * @param bookTitle the title of the book
     * @param bookAuthor the bookAuthor of the book
     * @param numberPages the number of pages in the book
     * @param timesRead the number of times the book has been read
     */
    public Book(String bookTitle, String bookAuthor, int numberPages, int timesRead)
    {
        super(bookTitle);
        this.bookAuthor = bookAuthor;
        this.numberPages = numberPages;
        this.timesRead = timesRead;
    }

    /**
     * Gets the title of the book.
     *
     * @return a string reference to the book title
     */
    public String getBookTitle()
    {
        return super.getItemName();
    }

    /**
     * Gets the bookAuthor(s) of a book.
     *
     * @return a string reference to the book author
     */
    public String getBookAuthor()
    {
        return bookAuthor;
    }

    /**
     * Get the number of pages in the book
     *
     * @return an integer value for the number of pages in the book
     */
    public int getNumberPages()
    {
        return numberPages;
    }

    /**
     * Gets the number of times a book has been read.
     *
     * @return an integer value for the number of times a book has been read
     */
    public int getTimesRead()
    {
        return timesRead;
    }

    /**
     * Sets the book title to a new value.
     *
     * @param bookTitle the title of the book
     */
    public void setTitle(String bookTitle)
    {
        super.setItemName(bookTitle);
    }

    /**
     * Sets the book bookAuthor(s) to a new value.
     *
     * @param bookAuthor the name of the bookAuthor(s)
     */
    public void setBookAuthor(String bookAuthor)
    {
        this.bookAuthor = bookAuthor;
    }

    /**
     * Allows setting the number of times a book has been read
     *
     * @param timesRead an integer for the number of times the book has been
     * read
     */
    public void setTimesRead(int timesRead)
    {
        this.timesRead = timesRead;
    }

    /**
     * Allows setting the number of pages in the book
     *
     * @param numberPages an integer value for the number of pages in the book
     */
    public void setNumberPages(int numberPages)
    {
        this.numberPages = numberPages;
    }

    /**
     * Increases the number of times a book has been read by one.
     */
    public void increaseTimesRead()
    {
        timesRead++;
    }

    /**
     * Decreases the number of times a book has been read. This value will never
     * be less than zero.
     */
    public void decreaseTimesRead()
    {
        if (timesRead > 0)
        {
            timesRead--;
        }
    }

    /**
     * Allow a user to enter the information for a book.
     *
     * @param input the scanner object used for input
     */
    @Override
    public void enterItem(Scanner input)
    {
        enterBookTitle(input);
        enterBookAuthor(input);
        enterNumberPages(input);
        enterTimesRead(input);
        System.out.println();
    }

    /**
     * Allows a user to enter the book title information. Ensures that the
     *  title entered is not the empty string.
     * 
     * @param input the scanner object used for input
     */
    public void enterBookTitle(Scanner input)
    {
        final String BAD_INPUT = "";
        String userInput;
        
        // attempt to get the book title
        System.out.print("Enter the book title: ");
        userInput = input.nextLine();
        
        // while the book title is an empty string, get the title again
        while(userInput.equals(BAD_INPUT))
        {
            System.out.println("ERROR: Book title cannot be empty!");
            System.out.print("Enter the book title: ");

            userInput = input.nextLine();
        }
        
        // once valid, set the title entered as the item name
        super.setItemName(userInput);
    }
    
    /**
     * Allows a user to enter the book author information. Ensures that the
     *  author entered is not the empty string.
     * 
     * @param input the scanner object used for input
     */
    public void enterBookAuthor(Scanner input)
    {
        final String BAD_INPUT = "";
        String userInput;
        
        // attempt to get the book author(s)
        System.out.print("Enter book author(s): ");
        userInput = input.nextLine();
        
        // while the book author(s) is an empty string, get the author again
        while(userInput.equals(BAD_INPUT))
        {
            System.out.println("ERROR: Book author(s) cannot be empty!");
            System.out.print("Enter book author(s): ");

            userInput = input.nextLine();
        }
        
        // once valid, set the user input as the author name
        setBookAuthor(userInput);
    }
    
    /**
     * Allows a user to enter the number of pages in the book. Ensures that the
     *  number of pages is a positive integer value.
     * 
     * @param input the scanner object used for input
     */
    public void enterNumberPages(Scanner input)
    {
        String userInput;
        
        // attempt to get the number of pages
        System.out.print("Enter number of pages: ");
        userInput = input.nextLine();
        
        // while the page count is not a positive integer value, get the page
        //  count again
        while(!super.isPositiveInteger(userInput))
        {
            System.out.println("ERROR: Number of pages must be a positive "
                    + "integer value!");
            System.out.print("Enter number of pages: ");

            userInput = input.nextLine();
        }
        
        // once valid, convert user input to an integer and set as the 
        //   number of pages value
        setNumberPages(Integer.parseInt(userInput));
    }
    
    /**
     * Allows a user to enter the number of times a book has been read. Ensures 
     *  that the number of pages is a positive integer value.
     * 
     * @param input the scanner object used for input
     */
    public void enterTimesRead(Scanner input)
    {
        String userInput;
        
        // attempt to get the number of times read
        System.out.print("Enter number of times read: ");
        userInput = input.nextLine();
        
        // while the times read is not a positive integer value, get the times
        //  read again
        while(!super.isPositiveInteger(userInput))
        {
            System.out.println("ERROR: Number of times read must be a "
                    + "positive integer value!");
            System.out.print("Enter number of times read: ");

            userInput = input.nextLine();
        }
        
        // once valid, convert user input to an integer and set as the 
        //   number of times read value
        setTimesRead(Integer.parseInt(userInput));
    }
    
    /**
     * Displays information for a book to standard output.
     */
    @Override
    public void displayItem()
    {
        System.out.println("** Book Information **");
        System.out.println("Title: " + getBookTitle());
        System.out.println("Author: " + getBookAuthor());
        System.out.println("Number Pages: " + getNumberPages());
        System.out.println("Times Read: " + getTimesRead());
        System.out.println();
    }

    /**
     * Converts a book object into a string representation. The format of the
     * string is BOOK: BookTitle by BookAuthor
     *
     * @return a reference to a string representing a book
     */
    @Override
    public String toString()
    {
        return "BOOK: " + super.getItemName() + " by " + getBookAuthor();
    }

    /**
     * Returns a string in the format suitable for writing the Book data 
     *  to a delimited file
     * 
     * The delimiter between data members is the "###" string
     * 
     * @return a string in the required file format
     */
    public String toFileFormat()
    {
        final String delimiter = "###";
        
        return "BOOK" + delimiter + 
                super.getItemName() + delimiter + 
                getBookAuthor() + delimiter + 
                getNumberPages() + delimiter +
                getTimesRead();
    }
}
