package FizzBuzz;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

class MyStack {

	private StackNode tailNode = null;
	
	class StackNode
	{
		int x;
		StackNode prevNode = null;
		
		public StackNode(int x1) {
			// TODO Auto-generated constructor stub
			x = x1;
		}
	}
	
	public MyStack() {
		// TODO Auto-generated constructor stub
	}
	
	public boolean isEmpty()
	{
		return (tailNode == null);
	}
	
	public void push(int x)
	{
		if(isEmpty())
		{
			tailNode = new StackNode(x);
		}
		else
		{
			StackNode addMe = new StackNode(x);
			addMe.prevNode = tailNode;
			
			tailNode = addMe;
		}
	}
	
	public int pop()
	{
		int retVal = -65535;
		
		if(!isEmpty())
		{
			retVal = tailNode.x;
			
			tailNode = tailNode.prevNode;
		}
		
		return retVal;
	}
	
	public void printAlternateInts()
	{
		boolean isAlt = true;
		
		while(!isEmpty())
		{
			if(isAlt)
			{
				System.out.print(pop() + " ");
				isAlt = false;
			}
			else
			{
				//pop but don't print
				pop();
				isAlt = true;
			}
		}
		
	}
}

public class StackMain
{

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
				String[] strOfInts = null;
				MyStack st = new MyStack();
				
				//read the file
				while( (wholeLine = br.readLine()) != null)
				{
					 strOfInts = wholeLine.trim().split(" ");
					 
					 for(String iStr : strOfInts)
					 {
						 st.push(Integer.parseInt(iStr));
					 }
					 
					 st.printAlternateInts();
					 
					 System.out.print("\r\n");
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