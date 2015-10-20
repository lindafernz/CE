package FizzBuzz;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


public class MthFromEndMain {

	public static char mthFromEndOfLine( String[] strsInLine)
	{
		char result = ' ';
		
		if(strsInLine != null && strsInLine.length > 1)
		{
			try
			{
				int m = strsInLine.length - 1 -
						Integer.parseInt(strsInLine[strsInLine.length-1]);
				
				if( 0 <= m && m <= strsInLine.length-2 )
				{
					if(strsInLine[m].length() == 1)
						result = strsInLine[m].charAt(0);
				}
			
			}
			catch(NumberFormatException nfe)
			{
				System.out.println("Incorrect value for index m in the line. "+ nfe.getMessage());
			}
		}
		
		return result;
		
	}
	
	public static void main(String[] args)
	{
	    File file = new File(args[0]);
		FileReader fr = null;
		BufferedReader br= null;
		
//		try(BufferedReader br= new BufferedReader(new FileReader(file))
//		{
//			String wholeLine = null;
//			char outChar = ' ';
//			
//			//read the file
//			while( (wholeLine = br.readLine()) != null)
//			{
//				 outChar = mthFromEndOfLine(wholeLine.trim().split(" "));
//				 
//				 System.out.println(outChar);
//			}
//		}
		
		
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
			String wholeLine = null;
			
			try
			{	
				//String[] strOfInts = null;
				char outChar = ' ';
				
				//read the file
				while( (wholeLine = br.readLine()) != null)
				{
					 outChar = mthFromEndOfLine(wholeLine.trim().split(" "));
					 
					 System.out.println(outChar);
				}
				
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

