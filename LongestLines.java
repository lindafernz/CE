package FizzBuzz;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;


public class LongestLines {

	public LongestLines() {
		// TODO Auto-generated constructor stub
	}
	
	static SortedMap<Integer, List<String>> result = null;
	static int currNumLinesInMap = 0;
	static int lineCount = 0;
	
	public static void initializeMap()
	{
		result = new TreeMap<>(Collections.reverseOrder());
		currNumLinesInMap = 0;
	}
	
	public static void processLine(String line)
	{
		int lineLen = line.length();
		
		List<String> ls = null;
		
		if(result.containsKey(lineLen))
		{
			ls = result.get(lineLen);
			ls.add(line);
			currNumLinesInMap++;
			result.put(lineLen, ls);
		}
		else	// key doesn't exist in map
		{
			int smallestLineLenInMap = 0;
			
			if(!result.isEmpty())
			{
				smallestLineLenInMap = result.lastKey();
			}
			
			//add this line if it is greater than the smallest line
			if(lineLen > smallestLineLenInMap)
			{
				ls = new ArrayList<>(1);
				ls.add(line);
				currNumLinesInMap++;
				result.put(lineLen, ls);
				
				//remove the smallest?
			}
		}
		
	}
	
	public static void printNLongestLines()
	{
		//System.out.println("Map size = " + result.size());
		
		for(List<String> listofLines : result.values())
		{
			for(String line: listofLines)
			{
				if(lineCount > 0)
				{
					System.out.println(line);
					lineCount--;	
				}
				else
					break;
				
			}
		}
	}
	
	public static void main(String[] args)
	{
        File file = new File(args[0]);
		FileReader fr = null;
		BufferedReader br= null;
		
		try {
			fr = new FileReader(file);
			br= new BufferedReader(fr);
		}
		catch(FileNotFoundException fex)
		{
			System.out.println("IO Exception:" + fex.getMessage());
		}
		
		if( fr != null && br != null )
		{
			String wholeLine, outStr;
			//FileWriter pw = null;
			
			try
			{
				//pw = new FileWriter(new File("output.txt"));
				initializeMap();
				
				//read the first line
				if((wholeLine = br.readLine()) != null)
					lineCount = Integer.parseInt(wholeLine.trim());
				
				//read the rest of the file
				while( (wholeLine = br.readLine()) != null)
				{
					 processLine(wholeLine.trim());
				}
				
				printNLongestLines();
			}
			catch(IOException ioEx)
			{
				System.out.println("IO Exception:" + ioEx.getMessage());
			}
			catch(NumberFormatException nfe)
			{
				System.out.println("First line of file should contain a number. Exception:" + nfe.getMessage());
			}
			finally {
				try{
					fr.close();
					br.close();
				}
				catch(IOException ex1)
				{
					System.out.println("IO Exception:" + ex1.getMessage());
				}
			}
		}
	}

}
