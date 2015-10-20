package FizzBuzz;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class UglyNumbersMain {


	private static ArrayList<String> al;
	
	public static int countUglyNumExpressions(String num)
	{
		int retVal = 0;
		
		if(num == null || num.length() > 13)
			return 0;
	
		int totalCombos = (int)Math.pow(3, num.length()-1);
		al = new ArrayList<String>(totalCombos);
		
//		System.out.println("isUglyNumber(39) "+ isUglyNumber(39)
//		+ "isUglyNumber(-39) "+ isUglyNumber(-39)
//		+ "isUglyNumber(121) "+ isUglyNumber(121));
		
		// check for string to have only '0' thru '9'
		//StringBuilder sb = new StringBuilder(num);
		
		al.add(num);
		
		if(num.length() > 1)
		{
			String sWithOp = combos(num, "+");
			mixOps(sWithOp, "+", "-");
			
			combos(num, "-");
		}
		
		System.out.println("UglyNumbersMain.countUglyNumExpressions() al.length="+ al.size());
		for(String s : al)
		{
			System.out.println("s="+s);
		}
		
		return retVal;
	}
	
	private static String combos(String s, String op)
	{	
		StringBuilder sb = new StringBuilder();
		
		int i;
		for(i=0; i< s.length()-1; i ++)
		{
			sb.append(s.charAt(i));
			sb.append(op);
		}
		sb.append(s.charAt(i));
		
		al.add(sb.toString());	
		
		String sWithOp = sb.toString();
		
		for(i=0;i<s.length()-1 ; i++)
		{
			sb = new StringBuilder();
			sb.append(s.substring(0, i+1));
			sb.append(op);
			sb.append(s.substring(i+1, s.length()));
			al.add(sb.toString());
		}
		
		return sWithOp;
	}
	
	private static void mixOps(String sWithOp, String op, String op2)
	{
		int i ;
		for(i=1; i< sWithOp.length()-1; i ++)
		{			
			if(op.charAt(0) == sWithOp.charAt(i))
			{
				StringBuilder sb = new StringBuilder();
				sb.append(sWithOp.substring(0, i));
				sb.append(op2);
				sb.append(sWithOp.substring(i+1, sWithOp.length()));
				al.add(sb.toString());
			}
		}
	}
	
	private static boolean isUglyNumber(int n)
	{
		if(n < 0)
			n = -n;
		
		return ( (n%2 == 0) || (n%3 == 0) || (n%5 == 0) || (n%7 == 0)	);
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
				int out = 0;
				
				//read the file
				while( (wholeLine = br.readLine()) != null)
				{
					 out = countUglyNumExpressions(wholeLine.trim());
					 
					 System.out.println(out);
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
