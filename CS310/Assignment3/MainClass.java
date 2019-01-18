package Assignment3;

import java.io.IOException;
import java.util.Scanner;

public class MainClass {

	public static void main(String[] args) throws IOException {
		
		Scanner SC = new Scanner (System.in);
		
		Disk disk = new Disk(10000,512);
		BuildDisk builddisk = new BuildDisk();
		builddisk.Build(disk);
		BuildIndex index = new BuildIndex(disk);
		IndexedFile IndFile = new IndexedFile (disk,60,27,1000,1588,44,1631,3);
		
		System.out.println("Select the method number you want: \n1. Insert new record \n2. Find record \n3. Quit");
		
		while (SC.hasNext())
		{
			String OperationString=SC.nextLine();
			
			if (OperationString.equalsIgnoreCase("one")||OperationString.equalsIgnoreCase("1"))
            {
                System.out.println("Please type in the mountain information you want to insert: ");
                System.out.println("(Follow the format: mountain name#country#altitude)");
                Scanner sc1 = new Scanner (System.in);
                String mountainInfo = sc1.nextLine();
                System.out.println(IndFile.insertRecord(mountainInfo.toCharArray()));
                System.out.println("Select the method number you want: \n1. Insert new record \n2. Find record \n3. Quit");
            }
			else if (OperationString.equalsIgnoreCase("two")||OperationString.equalsIgnoreCase("2"))
			{
				System.out.println("Please type in the mountain name you want to find: ");
				Scanner sc2 = new Scanner (System.in);
				String mountainName = sc2.nextLine();
				System.out.println(IndFile.findRecord(mountainName.toCharArray()));
				System.out.println("Select the method number you want: \n1. Insert new record \n2. Find record \n3. Quit");
			}
			else if (OperationString.equalsIgnoreCase("three")||OperationString.equalsIgnoreCase("3"))
            {
                System.out.println("Thank you!");
                System.exit(0);
            }
			else 
            {
                System.out.println("Invalid input! Type again!");
                System.out.println("Select the method number you want: \n1. Insert new record \n2. Find record \n3. Quit");
            }
		}
		
		}
}
