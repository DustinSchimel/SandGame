import java.awt.*;
import java.util.*;

public class SandLab
{
  //Step 4,6
  //add constants for particle types here
  public static final int EMPTY = 0;
  public static final int METAL = 1;
  public static final int SAND = 2;
  public static final int WATER = 3;
  public static final int SMOKE = 4;
  public static final int DIRT = 5;
  public static final int WOOD = 6;
  public static final int VIRUS = 7;
  
  //do not add any more fields below
  private int[][] grid;
  private SandDisplay display;
  
  
  /**
   * Constructor for SandLab
   * @param numRows The number of rows to start with
   * @param numCols The number of columns to start with;
   */
  public SandLab(int numRows, int numCols)
  {
    String[] names;
    // Change this value to add more buttons
    //Step 4,6
    names = new String[8];
    // Each value needs a name for the button
    names[EMPTY] = "Empty";
    names[METAL] = "Metal";
    names[SAND] = "Sand";
    names[WATER] = "Water";
    names[SMOKE] = "Smoke";
    names[DIRT] = "Dirt";
    names[WOOD] = "Wood";
    names[VIRUS] = "Virus";
    
    //1. Add code to initialize the data member grid with same dimensions
    grid = new int[numRows][numCols];
    
    display = new SandDisplay("Sand Game", numRows, numCols, names);
  }
  
  //called when the user clicks on a location using the given tool
  private void locationClicked(int row, int col, int tool)
  {
	  //2. Assign the values associated with the parameters to the grid
	  grid[row][col] = tool;
   
  }

  //copies each element of grid into the display
  public void updateDisplay()
  {
      //Step 3
	  //Hint - use a nested for loop
	  for (int row = 0; row < grid.length; row++)
	  {
		  for (int col = 0; col < grid[0].length; col++)
		  {
			  if (grid[row][col] == EMPTY)
			  {
				  display.setColor(row, col, Color.BLACK);
			  }
			  else if (grid[row][col] == METAL) 
			  {
				  display.setColor(row, col, Color.LIGHT_GRAY);
			  }
			  else if (grid[row][col] == SAND) 
			  {
				  display.setColor(row, col, new Color(193, 154, 107));
			  }
			  else if (grid[row][col] == WATER) 
			  {
				  display.setColor(row, col, Color.BLUE);
			  }
			  else if (grid[row][col] == SMOKE) 
			  {
				  display.setColor(row, col, new Color(73, 73, 73));
			  }
			  else if (grid[row][col] == DIRT) 
			  {
				  display.setColor(row, col, new Color(87, 59, 12));
			  }
			  else if (grid[row][col] == WOOD) 
			  {
				  display.setColor(row, col, new Color(130, 82, 1));
			  }
			  else if (grid[row][col] == VIRUS) 
			  {
				  display.setColor(row, col, new Color(255, 0, 255));
			  }
		  }
	  }
  }

  //Step 5,7
  //called repeatedly.
  //causes one random particle in grid to maybe do something.
  public void step()
  {
    //Remember, you need to access both row and column to specify a spot in the array
    //The scalar refers to how big the value could be
    //int someRandom = (int) (Math.random() * scalar)
    //remember that you need to watch for the edges of the array
    
	int randomRow = (int) (Math.random() * grid.length);
	int randomCol = (int) (Math.random() * grid[0].length);
	
	if (randomRow != grid.length - 1)	//If the row is not the bottom row, objects with gravity
	{	
		if (grid[randomRow][randomCol] == SAND)
		{
			if (grid[randomRow + 1][randomCol] == EMPTY)
			{
				grid[randomRow][randomCol] = EMPTY;
				grid[randomRow + 1][randomCol] = SAND;
			}
			if (grid[randomRow + 1][randomCol] == WATER)
			{
				grid[randomRow][randomCol] = WATER;
				grid[randomRow + 1][randomCol] = SAND;
			}
			if (grid[randomRow + 1][randomCol] == SMOKE)
			{
				grid[randomRow][randomCol] = SMOKE;
				grid[randomRow + 1][randomCol] = SAND;
			}
		}
		else if (grid[randomRow][randomCol] == DIRT)
		{
			if (grid[randomRow + 1][randomCol] == EMPTY)
			{
				grid[randomRow][randomCol] = EMPTY;
				grid[randomRow + 1][randomCol] = DIRT;
			}
			if (grid[randomRow + 1][randomCol] == WATER)
			{
				grid[randomRow][randomCol] = WATER;
				grid[randomRow + 1][randomCol] = DIRT;
			}
			if (grid[randomRow + 1][randomCol] == SMOKE)
			{
				grid[randomRow][randomCol] = SMOKE;
				grid[randomRow + 1][randomCol] = DIRT;
			}
		}
	}
	
	if (grid[randomRow][randomCol] == WATER)
	{
		if (randomRow != grid.length - 1)
		{	
			int randomDirection = (int) (Math.random() * 3);
			if (randomDirection == 0)	//Down
			{
				if (grid[randomRow + 1][randomCol] == EMPTY)
				{
					grid[randomRow][randomCol] = EMPTY;
					grid[randomRow + 1][randomCol] = WATER;
				}
				else if (grid[randomRow + 1][randomCol] == SMOKE)
				{
					grid[randomRow][randomCol] = SMOKE;
					grid[randomRow + 1][randomCol] = WATER;
				}
			}
			else if (randomDirection == 1 && randomCol + 1 != grid[0].length)	//Right
			{
				if (grid[randomRow][randomCol + 1] == EMPTY)
				{
					grid[randomRow][randomCol] = EMPTY;
					grid[randomRow][randomCol + 1] = WATER;
				}
				else if (grid[randomRow][randomCol + 1] == SMOKE)
				{
					grid[randomRow][randomCol] = SMOKE;
					grid[randomRow][randomCol + 1] = WATER;
				}
			}
			else if (randomDirection == 2 && randomCol - 1 != -1)	//Left
			{
				if (grid[randomRow][randomCol - 1] == EMPTY)
				{
					grid[randomRow][randomCol] = EMPTY;
					grid[randomRow][randomCol -1 ] = WATER;
				}
				else if (grid[randomRow][randomCol - 1] == SMOKE)
				{
					grid[randomRow][randomCol] = SMOKE;
					grid[randomRow][randomCol -1 ] = WATER;
				}
			}
		}
		else
		{
			int randomDirection = (int) (Math.random() * 2);
			if (randomDirection == 0 && randomCol + 1 != grid[0].length)	//Right
			{
				if (grid[randomRow][randomCol + 1] == EMPTY)
				{
					grid[randomRow][randomCol] = EMPTY;
					grid[randomRow][randomCol + 1] = WATER;
				}
				else if (grid[randomRow][randomCol + 1] == SMOKE)
				{
					grid[randomRow][randomCol] = SMOKE;
					grid[randomRow][randomCol + 1] = WATER;
				}
			}
			else if (randomDirection == 1 && randomCol - 1 != -1)	//Left
			{
				if (grid[randomRow][randomCol - 1] == EMPTY)
				{
					grid[randomRow][randomCol] = EMPTY;
					grid[randomRow][randomCol -1 ] = WATER;
				}
				else if (grid[randomRow][randomCol - 1] == SMOKE)
				{
					grid[randomRow][randomCol] = EMPTY;
					grid[randomRow][randomCol -1 ] = WATER;
				}
			}
		}
	}
	
	if (grid[randomRow][randomCol] == SMOKE)
	{
		if (randomRow != 0)
		{	
			int randomDirection = (int) (Math.random() * 3);
			if (randomDirection == 0)	//Up
			{
				if (grid[randomRow - 1][randomCol] == EMPTY)
				{
					grid[randomRow][randomCol] = EMPTY;
					grid[randomRow - 1][randomCol] = SMOKE;
				}
			}
			else if (randomDirection == 1 && randomCol + 1 != grid[0].length)	//Right
			{
				if (grid[randomRow][randomCol + 1] == EMPTY)
				{
					grid[randomRow][randomCol] = EMPTY;
					grid[randomRow][randomCol + 1] = SMOKE;
				}
			}
			else if (randomDirection == 2 && randomCol - 1 != -1)	//Left
			{
				if (grid[randomRow][randomCol - 1] == EMPTY)
				{
					grid[randomRow][randomCol] = EMPTY;
					grid[randomRow][randomCol -1 ] = SMOKE;
				}
			}
		}
		else
		{
			int randomDirection = (int) (Math.random() * 2);
			if (randomDirection == 0 && randomCol + 1 != grid[0].length)	//Right
			{
				if (grid[randomRow][randomCol + 1] == EMPTY)
				{
					grid[randomRow][randomCol] = EMPTY;
					grid[randomRow][randomCol + 1] = SMOKE;
				}
			}
			else if (randomDirection == 1 && randomCol - 1 != -1)	//Left
			{
				if (grid[randomRow][randomCol - 1] == EMPTY)
				{
					grid[randomRow][randomCol] = EMPTY;
					grid[randomRow][randomCol -1 ] = SMOKE;
				}
			}
		}
	}	
	
	if (grid[randomRow][randomCol] == VIRUS)
	{
		int randomDirection = (int) (Math.random() * 4);
		if (randomDirection == 0)	//Up
		{
			if (randomRow != 0 && grid[randomRow - 1][randomCol] != EMPTY && grid[randomRow - 1][randomCol] != VIRUS)
			{
				grid[randomRow - 1][randomCol] = VIRUS;
			}
		}
		else if (randomDirection == 1 && randomCol + 1 != grid[0].length)	//Right
		{
			if (grid[randomRow][randomCol + 1] != EMPTY && grid[randomRow][randomCol + 1] != VIRUS)
			{
				grid[randomRow][randomCol + 1] = VIRUS;
			}
		}
		else if (randomDirection == 2 && randomCol - 1 != -1)	//Left
		{
			if (grid[randomRow][randomCol - 1] != EMPTY && grid[randomRow][randomCol - 1] != VIRUS)
			{
				grid[randomRow][randomCol - 1 ] = VIRUS;
			}
		}
		else if (randomDirection == 3 /*&& randomCol - 1 != -1*/)	//Down
		{
			if (randomRow != grid.length - 1 && grid[randomRow + 1][randomCol] != EMPTY && grid[randomRow + 1][randomCol] != VIRUS)
			{
				grid[randomRow + 1][randomCol] = VIRUS;
			}
			
		}
	}
  }
  
  //do not modify this method!
  public void run()
  {
    while (true) // infinite loop
    {
      for (int i = 0; i < display.getSpeed(); i++)
      {
        step();
      }
      updateDisplay();
      display.repaint();
      display.pause(1);  //wait for redrawing and for mouse
      int[] mouseLoc = display.getMouseLocation();
      if (mouseLoc != null)  //test if mouse clicked
      {
        locationClicked(mouseLoc[0], mouseLoc[1], display.getTool());
      }
    }
  }
}
