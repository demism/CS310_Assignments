/*
 * CS310 Assignment 11 - Java TreeSet
 */
package cs310datastructures;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Creates a Sudoku Board from a file 
 *
 * @author Jeffrey LaMarche
 * 
 * @version 1.0  2020-Oct-11 
 * Template Version
 * 
 * @version 1.01 2021-Mar-22 
 * Made class final, to get rid of NetBeans warnings about using other class
 * members inside the constructor.
 * 
 * @author Demis Mota
 * @version 1.02 2022-Jul-24 Initial class implementation
 */
public final class SudokuBoard
{

    /*
    Stores the values of the Sudoku board game
    */
    private Integer[][] board;      
    
    /*
    The size of one board side - the N value
    */
    private int boardSize;          


	/**
	 * Constructor - creates a SudokuBoard from a file
	 * @param filename the name of the file with the sudoku puzzle
	 * @throws FileNotFoundException if filename is not found 
	 */
    public SudokuBoard(String filename) throws FileNotFoundException
    {
		File inputFile = new File(filename);
		Scanner inFile = null;
		boardSize = 0;

		try {
			inFile = new Scanner(inputFile);
		}
		catch (FileNotFoundException e)
		{
			throw new FileNotFoundException("SudokuBoard: File " + filename + " was not found"
					+ " or could not be opened!");
		}

		String line;
		line = inFile.nextLine();

		try {
			boardSize = Integer.parseInt(line);
		}
		catch (NumberFormatException e){
			throw new NumberFormatException("SudokuBoard: Board size " + 
					line + " is not an integer!");
		}

		if ( Math.ceil(Math.sqrt(boardSize)) != 
									Math.floor(Math.sqrt(boardSize)))
		{
			throw new IllegalArgumentException("SudokuBoard: Board size " +
					boardSize + " is not a perfect square number!");
		}

		board = new Integer[boardSize][boardSize];
		int rowCount = 0;
		int lineCount = 1;

		while (inFile.hasNext())
		{
			lineCount++;
			line = inFile.nextLine();
			String[] lineArray = line.split(" ");

			if ( lineArray.length > boardSize ) {
				throw new IllegalArgumentException("SudokuBoard: Line " +
						lineCount + " length of " + lineArray.length + " is "
								+ "incorrect!");
			}

			for (int i=0; i < lineArray.length ; i++ )
			{
				Integer squareValue = null;
				try {
					squareValue = Integer.parseInt(lineArray[i]);
				}
				catch (NumberFormatException nfe)
				{
					throw new NumberFormatException("SudokuBoard: Board cell (" 
							+ rowCount +", " + i + ") value of " + lineArray[i] 
							+ " is not an integer!");
				}
				try {
					setCell(rowCount, i, squareValue); 
				}
				catch (IndexOutOfBoundsException e)
				{
					if ( lineCount > boardSize )
					{
						throw new IllegalArgumentException("SudokuBoard: " +
								lineCount + " lines in file is incorrect!");
					}
				}
			}
			rowCount++;

		}
		if ( rowCount < boardSize )
		{
			throw new IllegalArgumentException("SudokuBoard: " +
					lineCount + " lines in file is incorrect!");
		}
		inFile.close();
    }

	/**
	 * Get method returns the size of the Sudoku Board
	 * @return integer value representing the board size 
	 */
    public int getBoardSize()
    {
        return boardSize;
    }

	/**
	 * Gets the value of a cell in the Sudoku board 
	 * @param row an integer value representing the row
	 * @param col an integer value representing the column
	 * @return an Integer that represents the value of the cell 
	 */
    public Integer getCell(int row, int col)
    {
		if (row < 0 || row > boardSize)
			throw new IndexOutOfBoundsException("SudokuBoard.getCell: Row " +
					row + " is out of bounds!");
		if (col < 0 || col > boardSize)
			throw new IndexOutOfBoundsException("SudokuBoard.getCell: Column " +
					col + " is out of bounds!");
        return board[row][col];
    }

	/**
	 * Sets the cell to a specific value
	 * @param row integer value representing the row
	 * @param col integer value representing the column
	 * @param value the integer representing the value to set cell to. 
	 */
    public void setCell(int row, int col, Integer value)
    {
		if (row < 0 || row > boardSize)
			throw new IndexOutOfBoundsException("SudokuBoard.setCell: Row " +
					row + " is out of bounds!");
		if (col < 0 || col > boardSize)
			throw new IndexOutOfBoundsException("SudokuBoard.setCell: Column " +
					col + " is out of bounds!");
		if ( value == null )
			throw new IllegalArgumentException("SudokuBoard.setCell: Cell"
					+ " value is null!");
		board[row][col] = value;
    }

	/**
	 * String representation of a Sudoku board
	 * @return a string with Sudoku representation 
	 */
    @Override
    public String toString()
    {
		StringBuilder sudokuBoard = new StringBuilder();
		String strFormat;
		
		sudokuBoard.append("   ");
		for (int i=0; i < boardSize; i++ )
		{
			strFormat = String.format("%3d", i);
			sudokuBoard.append(strFormat);
		}
		sudokuBoard.append("\n   ");
		for (int i = 0; i < boardSize; i++) {
			sudokuBoard.append("---");
		}
		sudokuBoard.append("-\n");
		for (int i = 0; i < boardSize; i++) {
			strFormat = String.format("%2d|", i);
			sudokuBoard.append(strFormat);
			for (int j = 0; j < boardSize; j++) {
				strFormat = String.format("%3d", board[i][j]);
				sudokuBoard.append(strFormat);

			}
			sudokuBoard.append("\n");
		}

		return sudokuBoard.toString();
	}

}
