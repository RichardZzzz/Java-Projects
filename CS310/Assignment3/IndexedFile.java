package Assignment3;

public class IndexedFile
{
   private Disk disk;             // disk on which the file will be written
   private char[] buffer;         // disk buffer
   private int recordSize;        // in characters	60
   private int keySize;           // in characters	27
   // fields describing data portion of file
   private int recordsPerSector;  // sectorSize/recordSize	512/60 = 8.53
   private int firstAllocated;    // sector number where data begins	1000
   private int sectorsAllocated;  // sectors originally allocated for data	1000
   private int overflowStart =1632;     // sector number where overflow begins	1632
   private int overflowSectors =0;   // count of overflow sectors in use	0
   // fields describing index portion of file
   private int indexStart;        // sector number where index begins	1588
   private int indexSectors;      // number of sectors allocated for index	1631-1587 = 44
   private int indexRoot;         // sector number of root of index	1631
   private int indexLevels;       // number of levels of index	3
   
   int secNum = 0;	// sector number 

   char [] EmptySec = {'\000','\000','\000','\000','\000','\000','\000','\000','\000','\000','\000','\000','\000','\000','\000'
		   ,'\000','\000','\000','\000','\000','\000','\000','\000','\000','\000','\000','\000'};	// an empty sector with 27 '\000'
   String EmptyStr = String.valueOf(EmptySec);	// convert it into string
   
   public IndexedFile ()
   {
	   
   }
   
   public IndexedFile(Disk disk, int recordSize, int keySize,
                      int firstAllocated, int indexStart,
                      int indexSectors, int indexRoot, int indexLevels)
   {
	   this.disk=disk;
	   this.buffer = new char [disk.getSectorSize()];
	   this.recordSize=recordSize;
	   this.keySize=keySize;
	   this.firstAllocated=firstAllocated;
	   this.indexStart=indexStart;
	   this.indexSectors=indexSectors;
	   this.indexRoot=indexRoot;
	   this.indexLevels=indexLevels;
	   
   }
   
   public boolean insertRecord(char[] record)
   {
	   String recordName; 
	   char [] tempRec = new char [60];	// 
	   String recordStr = new String (record);	// convert record to a string 
	   
	   recordName = getRecordName(recordStr);
	   if(recordStr!=null)		// reformat the record to the correct format 
	   {
		   int j=0;
		   int i=0;
		   while(recordStr.charAt(i)!='#')
		   {
			   if (i<27)
			   {
				   tempRec[i] = recordStr.charAt(i);	// put key (name) in the record array
				   i++;
			   }
			   else		// push i to the # positon
					   i++;
		   }
		   j = i+1;		// push j as the start of the country name
		   while(i<27)
		   {
			   tempRec[i] = '\000'; // if name is shorter than 27, fill up with null char
			   i++;
		   }
			   int k = 27;
			   while(recordStr.charAt(j)!='#')
			   {
				   
				   tempRec[k] = recordStr.charAt(j); // put country in the record array
				   k++;
				   i++;
				   j++;
			   }
			   j = j+1;
			   while(k<54)
			   {
				   tempRec[k] = '\000';
				   k++;
			   }
			   while(k<60)
			   {
				   if(j<(recordStr.length()))
				   {
					   tempRec[k] = recordStr.charAt(j);
					   k++;
					   j++;
				   }
				   else
				   {
					   tempRec[k] = '\000';
					   k++;
				   }
			   }
			   
		   }
	   recordStr = new String(tempRec);		// convert tempRec to string
	   
//	   search the record in the sector 
	   
	   if (findRecord(record))	// record exists already
		   return false;
	   else						// record needs to be inserted in
	   {
		   String bufferStr,temp;	 
		   
		   secNum = getSector (recordName);

		   if (secNum<indexStart)	// in the regular sectors  
		   {
			   disk.readSector(secNum, buffer);
			   
			   int firstIndex=300;	int secondIndex = firstIndex+recordSize;
			   bufferStr = String.valueOf(buffer);	// convert buffer to bufferString

			   while (secondIndex<=480)	// make sure operate in this sector because secondIndex should not bigger than 480
			   {

				   temp = bufferStr.substring(firstIndex, secondIndex);	// get one record

				   if((temp.substring(0, 27).equals(EmptyStr)==false))	// if the record is occupied
				   {					   
					   firstIndex=secondIndex;
					   secondIndex=secondIndex+recordSize;

					   try
					   {
						   temp = bufferStr.substring(firstIndex, secondIndex);	// jump to overflowsector
					   }
					   catch (Exception a)		// operate in overflow sector and do the same thing again
					   {
						   secNum = overflowStart;
						   disk.readSector(secNum, buffer);
						   
						   firstIndex=0;	secondIndex = firstIndex+recordSize;	
						   bufferStr = String.valueOf(buffer);	// convert buffer to bufferString
					   
						   temp = bufferStr.substring(firstIndex, secondIndex);

						   while (secondIndex<=480)
						   {
							   if((temp.substring(0, 27).equals(EmptyStr)==false))
							   {
								   firstIndex=secondIndex;
								   secondIndex=secondIndex+recordSize;

								   temp = bufferStr.substring(firstIndex, secondIndex);
							   }
							   else 
							   {
								   for (int i=0;i<60;i++)
								   {
									   buffer[firstIndex] = tempRec [i];
									   firstIndex++;
								   }
//								   System.out.println(String.valueOf(buffer).trim());
								   disk.writeSector(secNum, buffer);	// write to disk
								   overflowSectors++;	// increase overflow sector after it's full
								   return true;
							   }
						   }
					   }
				   }
				   else 	// there is space available
				   {
					   for (int i=0;i<60;i++)
					   {
						   buffer[firstIndex] = tempRec [i];
						   firstIndex++;
					   }
//					   System.out.println(String.valueOf(buffer).trim());
					   disk.writeSector(secNum, buffer);
					   return true;
				   }
			   }
		   }
		   else		// the record is in the overflow sector when we search it 
		   {
			   secNum = overflowStart;
			   disk.readSector(secNum, buffer);
			   int firstIndex=0;	int secondIndex = firstIndex+recordSize;
			   bufferStr = String.valueOf(buffer);	// convert buffer to bufferString
		   
			   temp = bufferStr.substring(firstIndex, secondIndex);

			   while (secondIndex<=480)
			   {
				   if((temp.substring(0, 27).equals(EmptyStr)==false))
				   {
					   firstIndex=secondIndex;
					   secondIndex=secondIndex+recordSize;

					   temp = bufferStr.substring(firstIndex, secondIndex);
				   }
				   else 
				   {
					   for (int i=0;i<60;i++)
					   {
						   buffer[firstIndex] = tempRec [i];
						   firstIndex++;
					   }
//					   System.out.println(String.valueOf(buffer).trim());
					   disk.writeSector(secNum, buffer);
					   return true;
				   }
			   }
			   secNum++;
			   overflowSectors++;
		   }
		   return false;
	   	}
   }   
   
   public boolean findRecord(char[] record)
   {
	   String bufferStr,temp,temp1;	 

	   if (record.length>27)
		   record = java.util.Arrays.copyOfRange(record,0,27);
	   
	   int firstIndex=0;	int secondIndex = firstIndex+recordSize;
	   
	   String key = String.valueOf(record).substring(firstIndex, firstIndex+record.length);	// String key is the name field of the record 0~27
	   int SectorNum = getSector(key);	// get the sector number of the key
	   
	   disk.readSector(SectorNum, buffer);	// read the sector from the disk and put in buffer
	   
	   bufferStr = String.valueOf(buffer);	// convert buffer to bufferString
	   
	   while (secondIndex<=420)	// search in the sector
	   {
		   
		   temp = bufferStr.substring(firstIndex, firstIndex+record.length);	// assign the first record name string to temp 0~27
		   
		   temp1 = bufferStr.substring(secondIndex, secondIndex+record.length);	// assign the second record name string to temp1 60~87

		   if (temp1.equalsIgnoreCase(EmptyStr))	
		   {
			   System.out.println("Record is found!");
			   return false;
		   }
		   
		   if (key.equalsIgnoreCase(temp))	// found it! print it out
		   {
			   record = new char [60];
			   record = bufferStr.substring(firstIndex, firstIndex+recordSize).toCharArray();
			   System.out.print(new String (record).substring(0, 27).trim()+",");
			   System.out.print("Country: "+new String (record).substring(27, 54).trim()+",");
			   System.out.println("altitude: "+new String (record).substring(54, 60).trim()+" ft.");
			   
			   return true;
		   }
		   else	// go to next record
		   {
			   firstIndex = secondIndex;
			   secondIndex = secondIndex + recordSize;
		   }
	   }
	   
	   // didn't find it , go to overflow sector
	   SectorNum = overflowStart;
	   disk.readSector(SectorNum, buffer);
	   firstIndex=0;	secondIndex = firstIndex+recordSize;
	   
	   while (secondIndex<=480)
	   {
		   if (new String (record).equals(new String (buffer).substring(firstIndex, firstIndex+record.length)))
		   {
			   String overflowStr = new String (buffer).substring(firstIndex, firstIndex+recordSize);
			   System.out.print(overflowStr.substring(0, 27).trim()+",");
			   System.out.print("Country: "+overflowStr.substring(27, 54).trim()+",");
			   System.out.println("altitude: "+overflowStr.substring(54, 60).trim()+" ft.");
			   return true;
		   }
		   else
		   {
			   firstIndex = secondIndex;
			   secondIndex = firstIndex+recordSize;
		   }
	   }
	   System.out.println("Sorry, no record is found!");
	   return false;
	   
   }   

   public int getSector(String key)   // returns sector number indicated by key
   {
	   String bufferStr,temp,temp1;	 
	   int counter = 0;
	   if (key.length()>27)
		   key = key.substring(0,27);
	   
	   disk.readSector(indexRoot, buffer);	// read the root into the buffer
	   
	   for (int i=0; i<indexLevels;i++)
	   {
		   int firstIndex=0;	int secondIndex =firstIndex+ 34;
		   bufferStr = String.valueOf(buffer);	// convert buffer to bufferString
		   String tempEptStr = EmptyStr.substring(0, key.length());
		   
		   temp = bufferStr.substring(firstIndex, firstIndex+keySize);	// assign the current string to temp
		   temp = temp.substring(0, key.length());	// give temp the same length as the key
		   
		   temp1 = bufferStr.substring(secondIndex, secondIndex+keySize);	// assign the next string to temp1
		   temp1 = temp1.substring(0, key.length());	// give temp the same length as the key
		   
		   while(key.compareToIgnoreCase(temp)>=0)
		   {
			   if (temp1.equalsIgnoreCase(tempEptStr)&&(!key.equalsIgnoreCase(temp)))		// if temp1 string is empty and key doesn't match	
			   {
				   try
				   {   
					   counter = Integer.valueOf(bufferStr.substring(firstIndex+keySize, secondIndex).trim());
					   disk.readSector(counter, buffer);
					   break;
				   }
				   catch (Exception e)
				   {
					   System.out.println(key+" is not in the file");
					   break;
				   }
			   }
			   
			   if (key.compareToIgnoreCase(temp1)<0)	// key should be before temp1
			   {
				   counter = Integer.valueOf(bufferStr.substring(firstIndex+keySize, secondIndex).trim());
				   disk.readSector(counter, buffer);
			   }
			   
			   if (key.equalsIgnoreCase(temp))		// if temp is the key 
			   {
				   counter = Integer.valueOf(bufferStr.substring(firstIndex+keySize, secondIndex).trim());
				   disk.readSector(counter, buffer);
				   break;
			   }
			   
			   else
			   {
				  try
				  {
					  firstIndex = firstIndex + 34;
					  secondIndex = secondIndex + 34;
					
					  temp = bufferStr.substring(firstIndex, firstIndex+keySize);
					  temp = temp.substring(0, key.length());

					  temp1 = bufferStr.substring(secondIndex, secondIndex+keySize);
					  temp1 = temp1.substring(0, key.length());
				  }
				  catch (Exception e)	// cross sector, go to next sector
				   {
					  counter++;
					  disk.readSector(counter, buffer);	// next sector
					  firstIndex=0;	secondIndex =firstIndex+ 34;	// from the top
					  bufferStr = String.valueOf(buffer);	// convert buffer to bufferString
					   tempEptStr = EmptyStr.substring(0, key.length());
					   
					   temp = bufferStr.substring(firstIndex, firstIndex+keySize);	// assign the current string to temp
					   temp = temp.substring(0, key.length());	// give temp the same length as the key
					   
					   temp1 = bufferStr.substring(secondIndex, secondIndex+keySize);	// assign the next string to temp1
					   temp1 = temp1.substring(0, key.length());	// give temp the same length as the key
					   
					   counter = Integer.valueOf(bufferStr.substring(firstIndex+keySize, secondIndex).trim());
					   counter--;	
					   
				   }
			   }
		   }
		   
	   }
	   
	   return counter;
   }
   
   public String getRecordName (String temp)
   {
	   String name; int a=0;
	   while (temp.charAt(a)!='#')
		   a++;
	   name = temp.substring(0, a);
	   return name;
   }
   
}
