/*
 * CS310 Assignment 5 - Singly Linked Lists
 */
package cs310datastructures;

import java.util.Calendar;

/**
 * A class the represents an entry for when a book is finished being read. Each 
 * entry contains information about a book, the date it was finished being read, 
 * and whether the book is bookOwned.
 *
 * @author Jeffrey LaMarche
 * @version 1.0  2020-Aug-23 Initial Version
 * @version 1.01 2020-Sep-21 Updated toString month to account for months 
 *      starting at zero. Also updated some variable names.
 */
public class BookReadEntry
{
    private Book bookObj;               // the book that was read
    private Calendar dateFinishedObj;   // the date that the book was finished
    private boolean bookOwned;              // whether the book is bookOwned

    /**
     * The default constructor for a BookReadEntry object.
     *
     * The default state is a null book, a null date, and bookOwned is false.
     */
    public BookReadEntry()
    {
        bookObj = null;
        dateFinishedObj = null;
        bookOwned = false;
    }

    /**
     * Constructor that allows setting the book, date, and bookOwned status.
     *
     * @param bookObj a book object reference with information about the book
     * @param dateFinishedObj a calendar object reference for the date the book
     *                        was finished
     * @param owned a boolean for whether the book is bookOwned or not
     */
    public BookReadEntry(Book bookObj, Calendar dateFinishedObj, boolean owned)
    {
        this.bookObj = bookObj;
        this.dateFinishedObj = dateFinishedObj;
        this.bookOwned = owned;
    }

    /**
     * Allows access to the book object reference stored in the entry.
     *
     * @return a Book object reference or null
     */
    public Book getBookObj()
    {
        return bookObj;
    }

    /**
     * Allows access to the Calendar object reference for when the book was
     * finished being read.
     *
     * @return a Calendar object reference or null
     */
    public Calendar getDateFinished()
    {
        return dateFinishedObj;
    }

    /**
     * Allows access to whether the book is owned or not. A true value 
     * indicates that the book is owned, false otherwise.
     *
     * @return a boolean value
     */
    public boolean isOwned()
    {
        return bookOwned;
    }

    /**
     * Allows setting the Book object reference in the entry to a new value. If
     * the new Book object is null, the original value is not changed.
     *
     * @param bookObj a book object reference
     */
    public void setBookObj(Book bookObj)
    {
        if (bookObj != null)
        {
            this.bookObj = bookObj;
        }
    }

    /**
     * Allows setting the Calendar object reference in the entry to a new value.
     * If the new Date object is null, the original value is not changed.
     *
     * @param dateFinishedObj a Calendar object reference for when the book was
     *                      finished being read
     */
    public void setDateRead(Calendar dateFinishedObj)
    {
        if (dateFinishedObj != null)
        {
            this.dateFinishedObj = dateFinishedObj;
        }
    }

    /**
     * Allows setting the owned status of a book to a new value. A true value
    *  indicates that the book is owned, false if the book is not owned.
     *
     * @param owned a boolean value
     */
    public void setOwned(boolean owned)
    {
        this.bookOwned = owned;
    }

    /**
     * Returns a string in the format suitable for writing the BookReadEntry
     * data to a delimited file
     * 
     * The delimiter between data members is the "###" string
     *
     * @return a string in the required file format
     */
    public String toFileFormat()
    {
        final String delimiter = "###";

        int day = dateFinishedObj.get(Calendar.DAY_OF_MONTH);
        int month = dateFinishedObj.get(Calendar.MONTH);
        int year = dateFinishedObj.get(Calendar.YEAR);
        int hours = dateFinishedObj.get(Calendar.HOUR_OF_DAY);
        int minutes = dateFinishedObj.get(Calendar.MINUTE);
        int seconds = dateFinishedObj.get(Calendar.SECOND);

        return bookObj.toFileFormat() + 
                delimiter + year + 
                delimiter + month + 
                delimiter + day + 
                delimiter + hours +
                delimiter + minutes +
                delimiter + seconds +
                delimiter + bookOwned;
    }

    /**
     * Converts a book read entry object into a string representation. 
     *
     * @return a reference to a string representing a book read entry object
     */
    @Override
    public String toString()
    {
        String ownedStatus;
        String formattedString;

        int day = dateFinishedObj.get(Calendar.DAY_OF_MONTH);
        // The months start at zero, so add one when displaying
        int month = dateFinishedObj.get(Calendar.MONTH) + 1;
        int year = dateFinishedObj.get(Calendar.YEAR);
        int hours = dateFinishedObj.get(Calendar.HOUR_OF_DAY);
        int minutes = dateFinishedObj.get(Calendar.MINUTE);
        int seconds = dateFinishedObj.get(Calendar.SECOND);

        if (bookOwned)
        {
            ownedStatus = "and is Owned";
        }
        else
        {
            ownedStatus = "and is Not Owned";
        }

        // add in a padded zero if it is missing so the output looks nicer
        String strDay = String.format("%2d", day).replace(' ', '0');
        String strMonth = String.format("%2d", month).replace(' ', '0');
        String strHours = String.format("%2d", hours).replace(' ', '0');
        String strMinutes = String.format("%2d", minutes).replace(' ', '0');
        String strSeconds = String.format("%2d", seconds).replace(' ', '0');
        
        // create the formatted string to return
        formattedString = String.format(
                "%s" +  
                " finished on %d-%s-%s" +
                " at %s:%s:%s %s", 
                bookObj.toString().replace("BOOK: ", ""),
                year, strMonth, strDay,
                strHours, strMinutes, strSeconds,
                ownedStatus);
        
        return formattedString;
    }

}
