package controller;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import controller.SandDisplay;
import controller.SandLab;

import java.util.ArrayList;
import java.util.Date;

public class FileController
{
	public static void saveLevelToFile(int[][] grid)
	{
		Date date = new Date();
		
		StringBuilder builder = new StringBuilder();
		
		File saveFile = new File("SandLevel" + date + ".txt");
		try
		{
			PrintWriter writeToDisk= new PrintWriter(saveFile);
			
			for(int row = 0; row < grid.length; row++)
			{
				for(int col = 0; col < grid[0].length; col++)
				{
					String currentRow = grid[row][col] + "";
					
					writeToDisk.println(row);
				}
			}
			writeToDisk.close();
			
		}
		catch (FileNotFoundException error)
		{
			System.out.println("There was an error:" + error.getMessage());
		}
		
	}
	
	/*
	public static String readPokemonFromFile()
	{
		String contents = "";
		String path = "Saved Pokedex.txt";
		try
		{
			Scanner fileScanner = new Scanner(new File(path));
			while (fileScanner.hasNextLine())
			{
				String row = fileScanner.nextLine();
				contents += row + "\n";
			}
			fileScanner.close();
		}
		catch (FileNotFoundException error)
		{
			System.out.println("There was an error:" + error.getMessage());
		}
		
		return contents;
	}
	*/
}

