/*
 * CS310 Assignment 2 - Abstract Classes
 */
package cs310datastructures;

import java.util.Scanner;

/**
 * The main class for the entire program. This makes everything work.
 * 
 * @author Jeffrey LaMarche
 * @version 1.0 2020-July-09 Template Version
 * 
 * @author Demis Mota
 * @version 1.1 2022-May-10 Implementation Version 
 */
public class CS310Assignment2
{

    /**
     * The driver of the entire program
     * 
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);
        
        /* Book testing */
        System.out.println("-- Book Test 1 --");
        // tests the default constructor, display, and toString methods
        Book bookOne = new Book();
        bookOne.displayItem();
        System.out.println(bookOne);
        
        System.out.println();
        
        // tests the enter, display, and toString methods
        System.out.println("-- Book Test 2 --");
        bookOne.enterItem(input);
        bookOne.displayItem();
        System.out.println(bookOne);
        
        System.out.println();
        
        // testing non-default constructor
        System.out.println("-- Book Test 3 --");
        Book bookTwo = new Book("Old Man's War", "John Scalzi", 318, 1);
        bookTwo.displayItem();
        System.out.println(bookTwo);
        
        System.out.println();
        
        System.out.println("-- Book Test 4 --");
        // tests the setters of the book class
        Book bookThree = new Book();
        bookThree.setTitle("Neverwhere");
        bookThree.setBookAuthor("Neil Gaiman");
        bookThree.setNumberPages(480);
        bookThree.setTimesRead(1);
        bookThree.displayItem();
        System.out.println(bookThree);
        
        System.out.println();
        
        // decreasing times read to zero
        bookThree.decreaseTimesRead();
        bookThree.displayItem();
        
        // attempts to further decrease times read below zero, should have 
        //   no change
        bookThree.decreaseTimesRead();
        bookThree.displayItem();
        
        // increases times read twice, so should be two now
        bookThree.increaseTimesRead();
        bookThree.increaseTimesRead();
        bookThree.displayItem();
        
        // test the getters of the book class
        System.out.println("Book Title: " + bookThree.getBookTitle());
        System.out.println("Book Author: " + bookThree.getBookAuthor());
        System.out.println("Number Pages: " + bookThree.getNumberPages());
        System.out.println("Times Read: " + bookThree.getTimesRead());
        System.out.println();
        
        /* VideoGame testing */
        System.out.println("-- Game Test 1 --");
        // tests the default constructor, display, and toString methods
        VideoGame gameOne = new VideoGame();
        gameOne.displayItem();
        System.out.println(gameOne);
        
        System.out.println();
        
        System.out.println("-- Game Test 2 --");
        // tests the enter, display, and toString methods
        gameOne.enterItem(input);
        gameOne.displayItem();
        System.out.println(gameOne);
        
        System.out.println();
        
        // test the non-default constructor
        System.out.println("-- Game Test 3 --");
        VideoGame gameTwo = new VideoGame("Sonic 2", "Sega Genesis", 20, true);
        gameTwo.displayItem();
        System.out.println(gameTwo);
        
        System.out.println();
        
        // tests most functionality of the video game class
        System.out.println("-- Game Test 4 --");
        VideoGame gameThree = new VideoGame();
        gameThree.setGameTitle("Altered Beast");
        gameThree.setGameSystem("Sega Genesis");
        gameThree.setTimesFinished(1);
        gameThree.setGamePlayed(true);
        gameThree.displayItem();
        System.out.println(gameThree);
        gameThree.decreaseTimesFinished();
        gameThree.displayItem();
        gameThree.decreaseTimesFinished();
        gameThree.displayItem();
        gameThree.increaseTimesFinished();
        gameThree.increaseTimesFinished();
        gameThree.displayItem();
        System.out.println("Game Title: " + gameThree.getGameTitle());
        System.out.println("Game System: " + gameThree.getGameSystem());
        System.out.println("Times Finished: " + gameThree.getTimesFinished());
        System.out.println("Game played: " + gameThree.isGamePlayed());
    }
    
}
