package _Class_Selector;

import java.util.NoSuchElementException;

//It Still needs to grab labs
//and if the current selection doesint work it needs to find another
public class ScheduleMaker
{
	String Classes = "";
	boolean[] m = new boolean[2400];
	boolean[] t = new boolean[2400];
	boolean[] w = new boolean[2400];
	boolean[] r = new boolean[2400];
	boolean[] f = new boolean[2400];
	//the size is how many diffrent classes that need to be chosen from
	public ScheduleMaker(TextDecoder[] files)
	{
		fillBooleans();
		//Load the first file, and get the text of the third line in the file
		//files[1].getText(3);
		//System.out.println(files[1].getTitle());
		
		files[0].getText(1);
		files[1].getText(1);
		files[2].getText(1);
		files[3].getText(1);
		Classes = pickClasses(files);
	}
	
	private void fillBooleans()
	{
		for(int i = 0; i < 2400; i++)
		{
			m[i] = true;
			t[i] = true;
			w[i] = true;
			r[i] = true;
			f[i] = true;
		}
		
	}
	
	private String pickClasses(TextDecoder[] files)
	{
		String ans ="";
		for(int i = 1; i < files[0].fileSize(); i++)
		{	
			boolean iFlag = false;
			files[0].getText(i);
			if(fits(files[0]))
			{
				ans += files[0].getClassIdNum()+". ";
				add(files[0]);
				iFlag = true;

				if(needsLab(files[0], i+1))
				{
					if(fits(files[0]))
					{
						files[1].getText(i+1);
						ans+= files[0].getClassIdNum()+ ". ";
						add(files[0]);
					}
					else
					{
						files[0].getText(i+1);
						ans=remove(files[0], ans);
						iFlag=false;
					}
				}
			}
			if(iFlag)
			{
				for(int x = 1; x < files[1].fileSize(); x++)
				{			
					boolean xFlag = false;
					files[1].getText(x);
					if(fits(files[1]))
					{
						ans += files[1].getClassIdNum()+". ";
						add(files[1]);
						xFlag = true;
						if(needsLab(files[1], x+1))
						{
							if(fits(files[1]))
							{
								files[1].getText(x+1);
								ans+= files[1].getClassIdNum()+ ". ";
								add(files[1]);
							}
							else
							{
								files[1].getText(x+1);
								ans=remove(files[1], ans);
								xFlag=false;
							}
						}
					}
					if(xFlag)
					{
						for(int y = 1; y < files[2].fileSize(); y++)
						{	
							boolean yFlag = false;
							files[2].getText(y);
							if(fits(files[2]))
							{
								ans += files[2].getClassIdNum() + ". ";
								add(files[2]);
								yFlag = true;
								if(needsLab(files[2], y+1))
								{
									if(fits(files[2]))
									{
										files[2].getText(y+1);
										ans += files[2].getClassIdNum()+". ";
										add(files[2]);
									}
									else
									{
										files[2].getText(y+1);
										ans = remove(files[2], ans);
										yFlag = false;
									}
								}
							}
							if(yFlag)
							{
								for(int z = 1; z < files[3].fileSize(); z++)
								{
									files[3].getText(z);
									if(fits(files[3]))
									{
										ans += files[3].getClassIdNum() + ". ";
										add(files[3]);
										if(needsLab(files[3], z+1))
										{
											if(fits(files[3]))
											{
												files[3].getText(z+1);
												ans += files[3].getClassIdNum()+". ";
												add(files[3]);
												return ans;
											}
											else
											{
												files[3].getText(z+1);
												ans= remove(files[3],ans);
												files[3].getText(z);
												ans = remove(files[3],ans);
											}
										}
									}
								}
							}
							files[2].getText(y);
							ans = remove(files[2], ans);
							if(needsLab(files[2], y+1))
							{
								files[2].getText(y+1);
								ans = remove(files[2], ans);
							}					
						}
					}
					files[1].getText(x);
					ans = remove(files[1], ans);
					if(needsLab(files[1], x+1))
					{
						files[1].getText(x+1);
						ans = remove(files[1], ans);
					}
				}
			}
			files[0].getText(i);
			ans = remove(files[0], ans);
			if(needsLab(files[0], i+1))
			{
				files[0].getText(i+1);
				ans = remove(files[0], ans);
			}
		}
		return "No Possible Combinations!";
	}
	
	private boolean needsLab(TextDecoder file, int i)
	{
		if(file.getText(i).contains("LAB"))
		{
			file.getText(i);
			return true;
		}
		file.getText(i);
		return false;
	}


	
	private boolean fits(TextDecoder file)
	{
		String days = file.getDays();
		int startTime = file.getStartTime();
		int endTime = file.getStopTime();
		String remainder = file.getRemainder();
		
		//converts the string jumble to a useable array
		String[] day = new String[days.length()];
		for(int i = 0; i < days.length(); i++)
		{
			day[i] = days.charAt(i)+"";
		}
		for(int i = 0; i < days.length(); i++)
		{
			if(day[i].equals("M"))
			{
				for(int x = startTime; x < endTime; x++)
				{
					if(m[x]==false)
					{
						return false;
					}
				}
			}
			else if(day[i].equals("T"))
			{
				for(int x = startTime; x < endTime; x++)
				{
					if(t[x]==false)
					{
						return false;
					}
				}
			}
			else if(day[i].equals("W"))
			{
				for(int x = startTime; x < endTime; x++)
				{
					if(w[x]==false)
					{
						return false;
					}
				}
			}
			else if(day[i].equals("R"))
			{
				for(int x = startTime; x < endTime; x++)
				{
					if(r[x]==false)
					{
						return false;
					}
				}
			}
			else if(day[i].equals("F"))
			{
				for(int x = startTime; x < endTime; x++)
				{
					if(f[x]==false)
					{
						return false;
					}
				}
			}
		}
		if(Integer.parseInt(remainder)<=0)
		{
			return false;
		}
		return true;
	}
	
	private void add(TextDecoder file)
	{
		String days = file.getDays();
		String[] day = new String[days.length()];
		for(int i = 0; i < days.length(); i++)
		{
			day[i] = days.charAt(i)+"";
		}
		int startTime = file.getStartTime();
		int endTime = file.getStopTime();
		for(int i = 0; i < days.length(); i++)
		{			
			if(day[i].equals("M"))
			{
				for(int x = startTime; x < endTime; x++)
				{
					m[x]= false;
				}
			}
			else if(day[i].equals("T"))
			{
				for(int x = startTime; x < endTime; x++)
				{
					t[x] = false;
				}
			}
			else if(day[i].equals("W"))
			{
				for(int x = startTime; x < endTime; x++)
				{
					w[x] = false;
				}
			}
			else if(day[i].equals("R"))
			{
				for(int x = startTime; x < endTime; x++)
				{
					r[x] = false;
				}
			}
			else if(day[i].equals("F"))
			{
				for(int x = startTime; x < endTime; x++)
				{
					f[x]= false;
				}
			}
		}
	}
	
	private String remove(TextDecoder file, String ans)
	{
		String days = file.getDays();
		String[] day = new String[days.length()];
		for(int i = 0; i < days.length(); i++)
		{
			day[i] = days.charAt(i)+"";
		}
		int startTime = file.getStartTime();
		int endTime = file.getStopTime();
		for(int i = 0; i < days.length(); i++)
		{			
			if(day[i].equals("M"))
			{
				for(int x = startTime; x < endTime; x++)
				{
					m[x]= true;
				}
			}
			else if(day[i].equals("T"))
			{
				for(int x = startTime; x < endTime; x++)
				{
					t[x] = true;
				}
			}
			else if(day[i].equals("W"))
			{
				for(int x = startTime; x < endTime; x++)
				{
					w[x] = true;
				}
			}
			else if(day[i].equals("R"))
			{
				for(int x = startTime; x < endTime; x++)
				{
					r[x] = true;
				}
			}
			else if(day[i].equals("F"))
			{
				for(int x = startTime; x < endTime; x++)
				{
					f[x]= true;
				}
			}
		}

		return ans.replace(file.getClassIdNum() + ". ", "");
	}
	
	private int getLength(TextDecoder[] files)
	{
		int counter=0;
		for(int i = 0; i < 10; i++)
		{
			try
			{
				if(files[i] != null)
				{
					counter++;
				}
			}
			catch(ArrayIndexOutOfBoundsException e)
			{
				
			}
		}
		return counter;
	}

	public String getSchedule()
	{
		return Classes;
	}

}
