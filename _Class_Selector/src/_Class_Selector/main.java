package _Class_Selector;

import java.io.File;

public class main
{

	public static void main(String[] args)
	{
		File f = new File("text.txt");
		TextDecoder test = new TextDecoder(f);
		test.getText(4);

		f = new File("class1.txt");
		TextDecoder class1 = new TextDecoder(f);
		
		f = new File("class2.txt");
		TextDecoder class2 = new TextDecoder(f);

		f = new File("class3.txt");
		TextDecoder class3 = new TextDecoder(f);

		f = new File("class4.txt");
		TextDecoder class4 = new TextDecoder(f);
		
		
		TextDecoder[] textD = {class1, class2, class3, class4};
		ScheduleMaker SchM = new ScheduleMaker(textD);
		System.out.println(SchM.getSchedule());
	}

}
