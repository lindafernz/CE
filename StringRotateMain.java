package FizzBuzz;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class StringRotateMain {

	public static boolean isStringRotated(String orig, String testMe)
	{
		boolean retVal = false;
		
		if("".equals(orig) || "".equals(testMe) 
				|| orig.length() != testMe.length())
		{
			return false;
		}
		
		if(orig.equals(testMe))
		{
			retVal = true;
		}
		else
		{
			String lastFirstSeq = "" + orig.charAt(orig.length()-1)+ orig.charAt(0) ;
			
			int idxOfLastFirst = testMe.indexOf(lastFirstSeq);
			
			if(idxOfLastFirst != -1)
			{
				// concat last half and first half of string
				String recon = "" + testMe.substring(idxOfLastFirst + 1, testMe.length()) + 
						testMe.substring(0, idxOfLastFirst + 1);
				
				if(orig.equals(recon))
				{
					retVal = true;
				}
			}
		}
		
		return retVal;
		
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
			String wholeLine = null;
			
			try
			{
				boolean out = false;
				
				//read the file
				while( (wholeLine = br.readLine()) != null)
				{
					String[] commaSeparated = wholeLine.trim().split(",");
					
					if(commaSeparated.length == 2)
					{
						 out = isStringRotated(commaSeparated[0], commaSeparated[1]);
						 
						 if(out) System.out.println("True");
						 else System.out.println("False");
					 
					}
					else
					{
						System.out.println("False");	
					}
				}
				
			}
			catch(IOException ioEx)
			{
				System.out.println("IO Exception:" + ioEx.getMessage());
			}
			catch(NumberFormatException nfe)
			{
				System.out.println("Line should contain a number. Exception:" + nfe.getMessage());
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
