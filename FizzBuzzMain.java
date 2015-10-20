package FizzBuzz;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FizzBuzzMain {

	public static String fizzBuzz(String[] threeNumsStr)
	{
		if(threeNumsStr.length != 3)
			return null;
		
		int[] threeNums = new int[3];
		int i =0;
		try
		{
			for(String s: threeNumsStr)
			{
			  threeNums[i] = Integer.parseInt(s);
			  i++;
			}
			
			// check constraints on the allowed numbers
			if(!( threeNums[0] > 0 && threeNums[0] <= 20 &&
					 threeNums[1] > 0 && threeNums[1] <= 20 &&
							 threeNums[2] > 0 && threeNums[2] <= 100 )) 
				return null;
			
			int[] outNums = new int[threeNums[2]];
			for( i=0; i < threeNums[2] ; i++ )
			{
				outNums[i] = i + 1;
			}
			
			StringBuffer sb = new StringBuffer(threeNums[2] * 3);
			boolean divBy1, divBy2;
			
			for(int y : outNums)
			{
				divBy1 = ((y % threeNums[0] == 0) ? true : false);
				divBy2 = ((y % threeNums[1] == 0) ? true : false);
				
				if(divBy1 && divBy2)
				{
					sb.append("FB");
				}
				else if(divBy1) sb.append('F');
				else if(divBy2) sb.append('B');
				else 
				{
					sb.append(y);
				}

				sb.append(' ');
			}

			return sb.toString().trim();
		}
		catch(NumberFormatException ex)
		{
			System.out.println("Invalid character in the input file."
					+ "Only numbers are allowed");
		}
		finally
		{}
		
		return null;
	}
	
	public static void main(String[] args)
	{
		FileReader fr = null;
		BufferedReader br= null;
		
		try {
			fr = new FileReader("input.txt");
			br= new BufferedReader(fr);
		}
		catch(FileNotFoundException fex)
		{
			System.out.println("IO Exception:" + fex.getMessage());
		}
		
		if( fr != null && br != null )
		{
			String wholeLine, outStr;
			FileWriter pw = null;
			int lineCount = 0;
			
			try
			{
				pw = new FileWriter(new File("output.txt"));
				
				while( (wholeLine = br.readLine()) != null && lineCount <= 20)
				{
					String[] threeNumsStr = wholeLine.trim().split(" ");
					outStr = fizzBuzz(threeNumsStr);
					
					pw.write(outStr + "\r\n") ;
					//System.out.println("output=" + outStr);
					
					lineCount++;
				}
			}
			catch(IOException ioEx)
			{
				System.out.println("IO Exception:" + ioEx.getMessage());
			}
			finally {
				try{
					fr.close();
					br.close();
					pw.close();
				}
				catch(IOException ex1)
				{
					System.out.println("IO Exception:" + ex1.getMessage());
				}
			}
		}
	}
}


