package Assignment3;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ReadFromFile {

	private char[] record ;
	private char[][] data;
	 
	   ReadFromFile ()
	   {
		   record = new char[60];
		   data= new char[4800][60];
		   try {
			ReadFile();
		} catch (IOException e) {
			
		}
	   }
	// read the file and reformat to record
	   void ReadFile () throws IOException 
	   {
		   String str = "";
		   FileReader file = new FileReader("data.txt");
		   BufferedReader reader = new BufferedReader(file);
		   str = reader.readLine();

		   int n=0;
		   
		   while (str!=null)
		   {
			   int j=0;
			   int i=0;
			   while(str.charAt(i)!='#')
				   {
					   if (i<27)
					   {
						   record[i] = str.charAt(i);	// put key (name) in the record array
						   i++;
					   }
					   else		// push i to the # positon
						   i++;
				   }
				   j = i+1;		// push j as the start of the country name
				   while(i<27)
				   {
					   record[i] = '\000'; // if name is shorter than 27, fill up with null char
					   i++;
				   }
				   int k = 27;
				   while(str.charAt(j)!='#')
				   {
					   
					   record[k] = str.charAt(j); // put country in the record array
					   k++;
					   i++;
					   j++;
				   }
				   j = j+1;
				   while(k<54)
				   {
					   record[k] = '\000';
					   k++;
				   }
				   while(k<60)
				   {
					   if(j<(str.length()))
					   {
						   record[k] = str.charAt(j);
						   k++;
						   j++;
					   }
					   else
					   {
						   record[k] = '\000';
						   k++;
					   }
				   }
				   data[n]= record.clone(); 
				   n++;
				   str = reader.readLine();
			   }
	   }
	   
	   public char[][] getData(){
		   return data;
	   }
	   
}
