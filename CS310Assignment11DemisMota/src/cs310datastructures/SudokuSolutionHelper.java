/*
 * CS310 Assignment 11 - Java TreeSet
 */
package cs310datastructures;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.TreeSet;

/**
 * A Sudoku class that helps a user solve a Sudoku 
 *
 * @author Jeffrey LaMarche
 * 
 * @version 1.0 2020-Oct-11 
 * Template Version
 * 
 * @author Demis Mota
 * @version 1.01 2022-Jul-24 Initial implementation
 */
public class SudokuSolutionHelper
{
    /*
    The Sudoku board to check and help solve
    */
    private SudokuBoard board;

    
	/**
	 * Constructor sets SudokuBoard
	 * @param board the SudokuBoard representation 
	 */
    public SudokuSolutionHelper(SudokuBoard board)
    {
		if ( board == null)
			throw new IllegalArgumentException("SudokuSolutionHelper: Board"
					+ " parameter is null!");
		this.board = board;
    }

    
	/**
	 * Gets a set of values for a board of a given size.
	 * I.E. - a 4x4 would have a set with values {1,2,3,4}
	 * @return a TreeSet with possible values for a Sudoku board 
	 */
    public TreeSet<Integer> getMasterSet()
    {
		TreeSet<Integer> ts = new TreeSet<>();
		for (int i=1; i <= board.getBoardSize(); i++)
			ts.add(i);
        return ts;
    }
    
	/**
	 * Gets all values that show up in a row
	 * @param rowNumber an integer of the row
	 * @return returns a TreeSet with all values found on a row 
	 */
    public TreeSet<Integer> getFilledRow(int rowNumber)
    {
		if (rowNumber >= board.getBoardSize() || rowNumber < 0)
		{
			throw new IndexOutOfBoundsException(
				"SudokuSolutionHelp.getFilledRow: Invalid row number of " +
						rowNumber + "!");
		}

		TreeSet<Integer> ts = new TreeSet<>();
		for (int i=0; i < board.getBoardSize(); i++)
		{
			int cell = board.getCell(rowNumber, i);

			if (cell != 0)
			ts.add(cell);
		}
        return ts;
    }
    
	/**
	 * Gets all values that show up in a column
	 * @param colNumber an integer of the column
	 * @return returns a TreeSet with all values found in a column 
	 */
    public TreeSet<Integer> getFilledColumn(int colNumber)
    {
		if (colNumber >= board.getBoardSize() || colNumber < 0)
		{
			throw new IndexOutOfBoundsException(
				"SudokuSolutionHelp.getFilledColumn: Invalid column "
						+ "number of " + colNumber + "!");
		}

		TreeSet<Integer> ts = new TreeSet<>();
		for (int i=0; i < board.getBoardSize(); i++)
		{
			int cell = board.getCell(i, colNumber);
			if (cell != 0)
				ts.add(cell);
		}
        return ts;
    }

	/**
	 * Gets all values for a given miniGrid
	 * @param rowNumber a row from the entire SudokuBoard
	 * @param colNumber a col from the entire SudokuBoard 
	 * @return a TreeSet with all values found in a mini grid. 
	 */
    public TreeSet<Integer> getFilledMiniGrid(int rowNumber, int colNumber)
    {
		if (rowNumber >= board.getBoardSize() || rowNumber < 0)
		{
			throw new IndexOutOfBoundsException(
				"SudokuSolutionHelp.getFilledMiniGrid: Invalid row number of " +
						rowNumber + "!");
		}

		if (colNumber >= board.getBoardSize() || colNumber < 0)
		{
			throw new IndexOutOfBoundsException(
				"SudokuSolutionHelp.getFilledMiniGrid: Invalid column "
						+ "number of " + colNumber + "!");
		}

		TreeSet<Integer> ts = new TreeSet<>();
		int gridX, gridY;
		int gridSize; 
		
		gridSize = (int) Math.floor(Math.sqrt(board.getBoardSize()));

		gridX = (int) Math.floor(rowNumber/gridSize); 
		gridY = (int) Math.floor(colNumber/gridSize);
		/*
		  -----------
		 |     |     |
		 |(0,0)|(0,1)|
		 |-----------|
		 |     |     |
		 |(1,0)|(1,1)|
		  -----------
		*/
		for (int i=0; i<gridSize; i++){
			for (int j=0; j<gridSize; j++){
				int cell = board.getCell(gridSize*gridX+i, gridSize*gridY+j);
				if (cell != 0)
					ts.add(cell);
			}
		}
		return ts;
    }
    
	/**
	 * Gets the set union of the row, column, and mini grid values 
	 * @param rowNumber a row in the sudokuBoard
	 * @param colNumber a col in the sudoku board
	 * @return a TreeSet with all values a cell cannot be 
	 */
    public TreeSet<Integer> getFilledCellMoves(int rowNumber, int colNumber)
    {
		if (rowNumber >= board.getBoardSize() || rowNumber < 0)
		{
			throw new IndexOutOfBoundsException(
				"SudokuSolutionHelp.getFilledMiniGrid: Invalid row number of " +
						rowNumber + "!");
		}

		if (colNumber >= board.getBoardSize() || colNumber < 0)
		{
			throw new IndexOutOfBoundsException(
				"SudokuSolutionHelp.getFilledMiniGrid: Invalid column "
						+ "number of " + colNumber + "!");
		}
		TreeSet<Integer> ts = new TreeSet<>();
		ts.addAll(getFilledRow(rowNumber));
		ts.addAll(getFilledColumn(colNumber)); 
		ts.addAll(getFilledMiniGrid(rowNumber,colNumber)); 
		return ts;
    }
    
	/**
	 * Gets the set containing any moves remaining in a specific row
	 * @param rowNumber the row in Sudoku board
	 * @return a TreeSet with possible values for a given row 
	 */
    public TreeSet<Integer> getRemainingRow(int rowNumber)
    {
		if (rowNumber >= board.getBoardSize() || rowNumber < 0)
		{
			throw new IndexOutOfBoundsException(
				"SudokuSolutionHelp.getRemainingRow: Invalid row number of " +
						rowNumber + "!");
		}

		TreeSet<Integer> ts = new TreeSet<>();

		ts.addAll(getMasterSet());
		ts.removeAll(getFilledRow(rowNumber));

        return ts;
    }
    
	/**
	 * gets the set containing any moves remaining in a specific column 
	 * @param colNumber the column number of a sudoku board
	 * @return a TreeSet with possible values for a given Column 
	 */
    public TreeSet<Integer> getRemainingColumn(int colNumber)
    {
		if (colNumber >= board.getBoardSize() || colNumber < 0)
		{
			throw new IndexOutOfBoundsException(
				"SudokuSolutionHelp.getRemainingColumn: Invalid column "
						+ "number of " + colNumber + "!");
		}

		TreeSet<Integer> ts = new TreeSet<>();

		ts = getMasterSet();
		ts.removeAll(getFilledColumn(colNumber));

        return ts;
    }
    
	/**
	 * Gets the set containing any moves remaining in a specific mini grid
	 * @param rowNumber a row which will be a part of a mini grid
	 * @param colNumber a col which will be a part of a mini grid
	 * @return a TreeSet with possible solutions for a given grid 
	 */
    public TreeSet<Integer> getRemainingMiniGrid(int rowNumber, int colNumber)
    {
		if (rowNumber >= board.getBoardSize() || rowNumber < 0)
		{
			throw new IndexOutOfBoundsException(
				"SudokuSolutionHelp.getRemainingMiniGrid: Invalid row number "
						+ "of " + rowNumber + "!");
		}
		if (colNumber >= board.getBoardSize() || colNumber < 0)
		{
			throw new IndexOutOfBoundsException(
				"SudokuSolutionHelp.getRemainingColumn: Invalid column "
						+ "number of " + colNumber + "!");
		}

		TreeSet<Integer> ts = new TreeSet<>();

		ts = getMasterSet();
		ts.removeAll(getFilledMiniGrid(rowNumber,colNumber));

        return ts;
    }
   
	/**
	 * Gets the remaining values for a given cell
	 * @param rowNumber a row in the Sudoku Board
	 * @param colNumber a col in the Sudoku Board
	 * @return a TreeSet with all values a cell can have 
	 */
    public TreeSet<Integer> getRemainingCellMoves(int rowNumber, int colNumber)
    {
		if (rowNumber >= board.getBoardSize() || rowNumber < 0)
		{
			throw new IndexOutOfBoundsException(
				"SudokuSolutionHelp.getRemainingCellMoves: Invalid row number of " +
						rowNumber + "!");
		}

		if (colNumber >= board.getBoardSize() || colNumber < 0)
		{
			throw new IndexOutOfBoundsException(
				"SudokuSolutionHelp.getRemainingCellMoves: Invalid column "
						+ "number of " + colNumber + "!");
		}

		TreeSet<Integer> ts = new TreeSet<>();

		ts = getMasterSet();
		ts.removeAll(getFilledCellMoves(rowNumber, colNumber));

        return ts;
    }
    
	/**
	 * Determines whether a sudoku board has been solved.
	 * @return true if Sudoku Board has been solved. 
	 */
    public boolean isBoardSolved()
    {
		TreeSet<Integer> ts = new TreeSet<>();

		for (int i=0; i < board.getBoardSize(); i++)
		{
			for (int j=0; j < board.getBoardSize(); j++)
			{
				if (!getRemainingCellMoves(i,j).isEmpty())
					return false;
			}
		}
		
        return true;
    }
    
	/**
	 * Generates an output file that gives help for solving a given SudokuBoard
	 * @param filename the name of the file to generate
	 * @return true if the file was successfully generated. 
	 */
    public boolean generateSolutionHelpFile(String filename) 
    {
		File outFile = new File(filename);
		PrintWriter output;
		try {
			output = new PrintWriter(outFile);
		}
		catch (FileNotFoundException fnfe) {
			System.out.println("SudokuSolutionHelper.generateSolutionHelpFile:"
					+ " ERROR: File could not be created! " + fnfe);
			return false;
		}
		TreeSet<Integer> ts = new TreeSet<>();

		output.println(board.getBoardSize() + " x " + board.getBoardSize() + 
				" Sudoku Puzzle Values");
		output.println("-----------------------------");
		output.println(board.toString());

		if (isBoardSolved())
			output.println("Congratulations! The Sudoku puzzle has been solved!");
		else
		{
			// Row Help
			output.println("-- Row Help --");
			for (int i=0; i < board.getBoardSize(); i++ )
			{
				//output.println("Row " + i + " is: " + getFilledRow(i).toString());
				ts.addAll(getRemainingRow(i));
				if (!ts.isEmpty())
					output.println("Row " + i + " remaining moves: " + 
							ts.toString());
				ts.clear();
			}
			output.println();

			// Column Help
			output.println("-- Column Help --");
			for (int i=0; i < board.getBoardSize(); i++ )
			{
				ts.addAll(getRemainingColumn(i));
				if (!ts.isEmpty())
					output.println("Column " + i + " remaining moves: " + 
							ts.toString());
			ts.clear();
			}
			output.println();

			// Mini-Grid Help
			int miniGridSize;
			miniGridSize = (int)Math.floor(Math.sqrt(board.getBoardSize()));
			output.println("-- Mini-Grid Help --");
			for (int i=0; i < miniGridSize; i++ )
			{
				for (int j=0; j < miniGridSize; j++)
				{
					ts.addAll(getRemainingMiniGrid(i*miniGridSize,j*miniGridSize));
					if (!ts.isEmpty())
						output.println("Mini-Grid (" + i*miniGridSize + ", " + j*miniGridSize + ") "
								+ "Remaining moves: " + ts.toString());
					ts.clear();
				}
			}
			output.println();
			
			output.println("-- Cell Help --");
			for (int i=0; i < board.getBoardSize(); i++ )
			{
				for (int j=0; j < board.getBoardSize(); j++)
				{
					ts.addAll(getRemainingCellMoves(i,j));
					if (!ts.isEmpty())
						output.println("Cell (" + i + ", " + j + ") "
								+ "Remaining moves: " + ts.toString());
					ts.clear();
				}
			}
			output.println();
		}
		output.flush();
		output.close();
        return true;
    }
}
