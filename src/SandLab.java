import java.awt.*;
import java.util.*;

public class SandLab
{
  public static final int EMPTY = 0;
  public static final int METAL = 1;
  public static final int SAND = 2;
  public static final int WATER = 3;
  public static final int SMOKE = 4;
  public static final int DIRT = 5;
  public static final int WOOD = 6;
  public static final int PINK_VIRUS = 7;
  public static final int PURPLE_VIRUS = 8;
  public static final int ACID = 9;
  
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
    names = new String[10];
    
    names[EMPTY] = "Eraser";
    names[METAL] = "Metal";
    names[SAND] = "Sand";
    names[WATER] = "Water";
    names[SMOKE] = "Smoke";
    names[DIRT] = "Dirt";
    names[WOOD] = "Wood";
    names[PINK_VIRUS] = "Pink Virus";
    names[PURPLE_VIRUS] = "Purple Virus";
    names[ACID] = "Acid";
    
    grid = new int[numRows][numCols];
    
    display = new SandDisplay("Sand Game", numRows, numCols, names);
  }
  
  //called when the user clicks on a location using the given tool
  private void locationClicked(int row, int col, int tool)
  {
	  //Assign the values associated with the parameters to the grid
	  grid[row][col] = tool;
  }

  public void updateDisplay()
  {
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
			  else if (grid[row][col] == PINK_VIRUS) 
			  {
				  display.setColor(row, col, new Color(255, 0, 255));
			  }
			  else if (grid[row][col] == PURPLE_VIRUS) 
			  {
				  display.setColor(row, col, new Color(138, 43, 226));
			  }
			  else if (grid[row][col] == ACID) 
			  {
				  display.setColor(row, col, new Color(137, 255, 0));
			  }
		  }
	  }
  }

  //called repeatedly.
  public void step()
  {
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
		else if (grid[randomRow][randomCol] == PURPLE_VIRUS)
		{
			if (grid[randomRow + 1][randomCol] == EMPTY)
			{
				grid[randomRow][randomCol] = EMPTY;
				grid[randomRow + 1][randomCol] = PURPLE_VIRUS;
			}
			if (grid[randomRow + 1][randomCol] == WATER)
			{
				grid[randomRow][randomCol] = WATER;
				grid[randomRow + 1][randomCol] = PURPLE_VIRUS;
			}
			if (grid[randomRow + 1][randomCol] == SMOKE)
			{
				grid[randomRow][randomCol] = SMOKE;
				grid[randomRow + 1][randomCol] = PURPLE_VIRUS;
			}
		}
		
	}
	
	if (grid[randomRow][randomCol] == WATER)
	{
		int randomDirection = (int) (Math.random() * 3);
		
		if (randomDirection == 0 && randomCol + 1 != grid[0].length)	//Right
		{
			if (grid[randomRow][randomCol + 1] == EMPTY)
			{
				grid[randomRow][randomCol + 1] = WATER;
				grid[randomRow][randomCol] = EMPTY;
			}
			else if (grid[randomRow][randomCol + 1] == SMOKE)
			{
				grid[randomRow][randomCol + 1] = WATER;
				grid[randomRow][randomCol] = SMOKE;
			}
		}
		else if (randomDirection == 1 && randomCol - 1 != -1)	//Left
		{
			if (grid[randomRow][randomCol - 1] == EMPTY)
			{
				grid[randomRow][randomCol - 1 ] = WATER;
				grid[randomRow][randomCol] = EMPTY;
			}
			else if (grid[randomRow][randomCol - 1] == SMOKE)
			{
				grid[randomRow][randomCol -1 ] = WATER;
				grid[randomRow][randomCol] = SMOKE;
			}
		}
		else if (randomDirection == 2 && randomRow != grid.length - 1)	//Down
		{
			if (grid[randomRow + 1][randomCol] == EMPTY)
			{
				grid[randomRow + 1][randomCol] = WATER;
				grid[randomRow][randomCol] = EMPTY;
			}
			else if (grid[randomRow + 1][randomCol] == SMOKE)
			{
				grid[randomRow + 1][randomCol] = WATER;
				grid[randomRow][randomCol] = SMOKE;
			}
		}
	}
	
	
	if (grid[randomRow][randomCol] == SMOKE)
	{
		int randomDirection = (int) (Math.random() * 3);
		
		if (randomDirection == 0 && randomCol + 1 != grid[0].length)	//Right
		{
			if (grid[randomRow][randomCol + 1] == EMPTY)
			{
				grid[randomRow][randomCol + 1] = SMOKE;
				grid[randomRow][randomCol] = EMPTY;
			}
		}
		else if (randomDirection == 1 && randomCol - 1 != -1)	//Left
		{
			if (grid[randomRow][randomCol - 1] == EMPTY)
			{
				grid[randomRow][randomCol -1 ] = SMOKE;
				grid[randomRow][randomCol] = EMPTY;
			}
		}
		else if (randomDirection == 2 && randomRow != 0)	//Up
		{
			if (grid[randomRow - 1][randomCol] == EMPTY)
			{
				grid[randomRow - 1][randomCol] = SMOKE;
				grid[randomRow][randomCol] = EMPTY;
			}
		}
	}	
	
	if (grid[randomRow][randomCol] == PINK_VIRUS)
	{
		int randomDirection = (int) (Math.random() * 4);
		
		if (randomDirection == 0 && randomCol + 1 != grid[0].length)	//Right
		{
			if (grid[randomRow][randomCol + 1] != EMPTY && grid[randomRow][randomCol + 1] != PINK_VIRUS)
			{
				grid[randomRow][randomCol + 1] = PINK_VIRUS;
			}
		}
		else if (randomDirection == 1 && randomCol - 1 != -1)	//Left
		{
			if (grid[randomRow][randomCol - 1] != EMPTY && grid[randomRow][randomCol - 1] != PINK_VIRUS)
			{
				grid[randomRow][randomCol - 1 ] = PINK_VIRUS;
			}
		}
		else if (randomDirection == 2 && randomRow != 0)	//Up
		{
			if (grid[randomRow - 1][randomCol] != EMPTY && grid[randomRow - 1][randomCol] != PINK_VIRUS)
			{
				grid[randomRow - 1][randomCol] = PINK_VIRUS;
			}
		}
		else if (randomDirection == 3 && randomRow != grid.length - 1)	//Down
		{
			if (grid[randomRow + 1][randomCol] != EMPTY && grid[randomRow + 1][randomCol] != PINK_VIRUS)
			{
				grid[randomRow + 1][randomCol] = PINK_VIRUS;
			}
			
		}
	}
	
	if (grid[randomRow][randomCol] == PURPLE_VIRUS)
	{
		int randomDirection = (int) (Math.random() * 4);
		
		if (randomDirection == 0 && randomCol + 1 != grid[0].length)	//Right
		{
			if (grid[randomRow][randomCol + 1] != EMPTY && grid[randomRow][randomCol + 1] != PURPLE_VIRUS)
			{
				grid[randomRow][randomCol + 1] = PURPLE_VIRUS;
			}
		}
		else if (randomDirection == 1 && randomCol - 1 != -1)	//Left
		{
			if (grid[randomRow][randomCol - 1] != EMPTY && grid[randomRow][randomCol - 1] != PURPLE_VIRUS)
			{
				grid[randomRow][randomCol - 1 ] = PURPLE_VIRUS;
			}
		}
		else if (randomDirection == 2 && randomRow != 0)	//Up
		{
			if (grid[randomRow - 1][randomCol] != EMPTY && grid[randomRow - 1][randomCol] != PURPLE_VIRUS)
			{
				grid[randomRow - 1][randomCol] = PURPLE_VIRUS;
			}
		}
		else if (randomDirection == 3 && randomRow != grid.length - 1)	//Down
		{
			if (grid[randomRow + 1][randomCol] != EMPTY && grid[randomRow + 1][randomCol] != PURPLE_VIRUS)
			{
				grid[randomRow + 1][randomCol] = PURPLE_VIRUS;
			}
			
		}
	}
	
	
	
	if (grid[randomRow][randomCol] == ACID)
	{
		int randomDirection = (int) (Math.random() * 3);
		
		if (randomRow != 0)
		{
			if (grid[randomRow - 1][randomCol] == SAND || grid[randomRow - 1][randomCol] == WATER || grid[randomRow - 1][randomCol] == DIRT)
			{
				grid[randomRow - 1][randomCol] = EMPTY;
				grid[randomRow][randomCol] = EMPTY;
			}
		}
		
		if (randomDirection == 0 && randomCol + 1 != grid[0].length)	//Right
		{
			if (grid[randomRow][randomCol + 1] == EMPTY)	//Normal liquid flow
			{
				grid[randomRow][randomCol + 1] = ACID;
				grid[randomRow][randomCol] = EMPTY;
			}
			else if (grid[randomRow][randomCol + 1] != ACID)	//Destroys block to the right
			{
				grid[randomRow][randomCol + 1] = EMPTY;
				grid[randomRow][randomCol] = EMPTY;
			}
		}
		else if (randomDirection == 1 && randomCol - 1 != -1)	//Left
		{
			if (grid[randomRow][randomCol - 1] == EMPTY)
			{
				grid[randomRow][randomCol - 1 ] = ACID;
				grid[randomRow][randomCol] = EMPTY;
			}
			else if (grid[randomRow][randomCol - 1] != ACID)
			{
				grid[randomRow][randomCol - 1] = EMPTY;
				grid[randomRow][randomCol] = EMPTY;
			}
		}
		else if (randomDirection == 2 && randomRow != grid.length - 1)	//Down
		{
			if (grid[randomRow + 1][randomCol] == EMPTY)
			{
				grid[randomRow + 1][randomCol] = ACID;
				grid[randomRow][randomCol] = EMPTY;
			}
			else if (grid[randomRow + 1][randomCol] != ACID)
			{
				grid[randomRow + 1][randomCol] = EMPTY;
				grid[randomRow][randomCol] = EMPTY;
			}
			
		}
	}
  }
  
  public void run()
  {
    while (true) //infinite loop
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
