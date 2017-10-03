package _Class_Selector;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class RemoveUnwanted
{
	//Time in military time
	public RemoveUnwanted(File f, String timeNoEarler)
	{
		String line = "";
		try(Scanner fin = new Scanner(f))
		{
			while(fin.hasNextLine())
			{
				String lines = fin.nextLine();
				if(!lines.contains(timeNoEarler))
				{
					line+= lines+"\r\n";
				}
			}
		} 
		catch(FileNotFoundException e)
		{
		}
		
		try(PrintWriter pfin = new PrintWriter(f))
		{
			pfin.write(line);
		}
		catch(FileNotFoundException e)
		{
			
		}
	}
}
