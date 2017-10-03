package _Class_Selector;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
/*
 * Gets all the data from the file and the line in the file
 * */
public class TextDecoder
{
	//The FileItself
	private File fileF;
	
	private int fileSize;
	//Everything
	private String line;
	private String nextLine;
	//num for data
	private int numData = 1;
	//CRN	
	private String crn;
	//Subj	
	private String subject;
	//Crse	
	private String course;
	//Sec	
	private String selection;
	//Cmp	
	private String campus;
	//Cred	
	private String credit;
	//Title	
	private String title;
	//Days	
	private String days;
	//Time in military time, hours, minutes	
	private int startTime, stopTime;
	//Cap	
	private String capacity;
	//Act	
	private String actual;
	//Rem	
	private String remainder;
	//Instructor	
	private String instructor;
	//Date (MM/DD)	
	private String date;
	//Location	
	private String location;
	//Attribute
	private String attribute;
	
	//file, numberline
	public TextDecoder(File f)
	{
		fileF = f;
	}
	public String getText(int num)
	{
		numData = 1;
		getFileSize(fileF);
		try(Scanner fin = new Scanner(fileF))
		{
			line = fin.nextLine();
			for(int i = 0; i < num-1; i++)
			{
				if(fin.hasNextLine())
				{
					line = fin.nextLine();
				}
			}
			loadData();
			fin.close();
			return line;
		} 
		catch(FileNotFoundException e)
		{
			return "File not found!";
		}
	}
	
	public String getNextLine(int num)
	{
		try(Scanner fin = new Scanner(fileF))
		{
			nextLine = fin.nextLine();
			for(int i = 0; i < num-1; i++)
			{
				nextLine = fin.nextLine();
			}
			fin.close();
			return nextLine;
		} 
		catch(FileNotFoundException e)
		{
			return "File not found!";
		}
	}
	
	public int getFileSize(File f)
	{
		int x = 0;
		try(Scanner fin = new Scanner(f))
		{
			while(fin.hasNextLine())
			{
				fin.nextLine();
				x++;
			}
		}
		catch(FileNotFoundException e)
		{
			return 0;
		}
		fileSize = x;
		return x;
	}
	
	private void loadData()
	{
		char[] str = line.toCharArray();
		boolean flag = true;
		while(flag)
		{
			for(int i = 0; i < str.length; i++)
			{
				if(str[i] == '	')
				{
					fillData(getMyString(str, i));
					numData++;
				}
			}
			flag = false;
		}
	}
	
	private String getMyString(char[] str, int x)
	{
		String a = "";
		x++;
		while(str[x] != '	' && x != str.length-1)
		{
			a += str[x];
			x++;
		}
		//a+= str[str.length-1];
		return a;
	}
	
	private void fillData(String str)
	{
		switch(numData)
		{
			case 1: crn = str;
				break;
			case 2: subject = str;
				break;
			case 3: course = str;
				break;
			case 4: selection = str;
				break;
			case 5: campus = str;
				break;
			case 6: credit = str;
				break;
			case 7: title = str;
				break;
			case 8: days = str;
				break;
			case 9: startTime = deTextTime(str)[0];
					stopTime = deTextTime(str)[1];
				break;
			case 10: capacity = str;
				break;
			case 11: actual = str;
				break;
			case 12: remainder = str;
				break;
			case 13: instructor = str;
				break;
			case 14: date = str;
				break;
			case 15: location = str;
				break;
			case 16: attribute = str;
				break;
		}
	}
	
	private int[] deTextTime(String str)
	{
		int hourMin = 0000;
		int hour = 0;
		int start,stop;
		int startAt = 0;
		for(int i = 0; i < str.length(); i++)
		{
			if(str.charAt(i) == '-')
			{
				startAt = i;
				i = str.length();
			}
			else if(str.charAt(i) == ':')
			{
				if( Integer.parseInt(str.charAt(i-2)+"")*10+Integer.parseInt(str.charAt(i-1)+"") < 8)
				{
					hour = Integer.parseInt(str.charAt(i-2)+"")*10+Integer.parseInt(str.charAt(i-1)+"") +12;
					hour*=100;
				}
				else
				{
					hour =Integer.parseInt(str.charAt(i-2)+"")*10+Integer.parseInt(str.charAt(i-1)+"");
					hour*=100;
				}
				hourMin = Integer.parseInt(str.charAt(i+1)+"")*10
						+ Integer.parseInt(str.charAt(i+2)+"");
			}
		}
		start = hourMin+hour;
		
		for(int i = startAt; i < str.length(); i++)
		{
			if(str.charAt(i) == ':')
			{
				if( Integer.parseInt(str.charAt(i-2)+"")*10+Integer.parseInt(str.charAt(i-1)+"") < 8)
				{
					hour = Integer.parseInt(str.charAt(i-2)+"")*10+Integer.parseInt(str.charAt(i-1)+"") +12;
					hour*=100;
				}
				else
				{
					hour =Integer.parseInt(str.charAt(i-2)+"")*10+Integer.parseInt(str.charAt(i-1)+"");
					hour*=100;
				}
				hourMin = Integer.parseInt(str.charAt(i+1)+"")*10
						+ Integer.parseInt(str.charAt(i+2)+"");
			}
		}
		stop = hourMin+hour;
		int[] returnMe = {start,stop};
		return returnMe;
	}

	public String getLine()
	{
		return line;
	}
	
	public String getClassIdNum()
	{
		return crn;
	}
	
	public String getSubject()
	{
		return subject;
	}
	
	public String getCourse()
	{
		return course;
	}
	
	public String getSelection()
	{
		return selection;
	}
	
	public String getCampus()
	{
		return campus;
	}
	
	public String getCredit()
	{
		return credit;
	}
	
	public String getTitle()
	{
		return title;
	}
	
	public int getStartTime()
	{
		
		return startTime;
	}
	
	public int getStopTime()
	{
		return stopTime;
	}
	
	public String getDays()
	{
		return days;
	}
	
	public String getCapacity()
	{
		return capacity;
	}
	
	public String getActual()
	{
		return actual;
	}
	
	public String getRemainder()
	{
		return remainder;
	}
	
	public String getInstructor()
	{
		return instructor;
	}
	
	public String getDate()
	{
		return date;
	}
	
	public String getLocation()
	{
		return location;
	}
	
	public String getAttribute()
	{
		return attribute;
	}
	
	public int fileSize()
	{
		return fileSize;
	}
}

