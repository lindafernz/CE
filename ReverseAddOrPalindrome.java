package FizzBuzz;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ReverseAddOrPalindrome {
	
	public static void algo(int n)
	{

		boolean isNeg = false;
		long num = n;
		
		if(num < 0)
		{
			isNeg = true;
			num = -num;
		}
		
		
		for(int i=0; i < 100; i++)
		{
			if(isPalindrome(num))
			{
				if(isNeg)
				{
					num = -num;
				}
				
				System.out.println(i + " " + num);
				break;
			}
			else
			{
				num += reverseOfNum(num);
				
				/*if(num < 0)
				{
					System.out.println("");
					break;
				}*/
			}
		}
		
		
	}
	
	public static long reverseOfNum(long n)
	{	
		long reverse = 0;
		long digit = 0;
		
		while(n > 0)
		{
			digit = n % 10;
			reverse = reverse * 10 + digit;
			n = n/10;
		}
		
		return reverse;
	}
	
	public static boolean isPalindrome(long n)
	{
		boolean retVal = false;
		
		if(reverseOfNum(n) == n)
			retVal = true;
		
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
				String strNum = null;
				int n = 0, i=0;
				boolean isJolly = false;
				
				//read the file
				while( (wholeLine = br.readLine()) != null)
				{
					strNum = wholeLine.trim();
					
					if(strNum != null && !strNum.equals(""))
					{
						n = Integer.parseInt(strNum);
						
						if( n < 10000 )
						{
							algo(n);
						}
						else
						{
							System.out.println("");
						}
					}
					else
					{
						System.out.println("");
					}
 
				}
				
			}
			catch(IOException ioEx)
			{
				System.out.println("IO Exception:" + ioEx.getMessage());
			}
			catch(NumberFormatException nfe)
			{
				System.out.println("Lines should only contain space-delimited integers. Exception:" + nfe.getMessage());
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
