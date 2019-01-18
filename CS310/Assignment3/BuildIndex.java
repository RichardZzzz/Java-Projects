package Assignment3;

public class BuildIndex 
{
	char[] IndexRecord = new char[34];
	char[] IndexSector = new char[512];
	char[] buffer = new char[512];
	int secNum=1000;
	int IndSecNum = 1588;
	int IndSecNumL1 = IndSecNum;
	BuildDisk x;
	
	public BuildIndex(Disk disk)
	{

		BuildDisk x = new BuildDisk();
		x.Build(disk);
		BuildIndTree(disk,IndSecNum);
		for(int i=0;i<2;i++)
		{
			BuildIndTree(disk,IndSecNumL1);
		}

	}
	
	public void BuildIndTree (Disk disk, int IndSecNum)
	{
		while(secNum<IndSecNum)
		{
			int n=0;
			while(n<510&&secNum<IndSecNum) 	// build one index sector
			{
				disk.readSector(secNum, buffer); // read one data sector 
				
				for (int i=0;i<27;i++)
				{
					IndexRecord[i]=buffer[i];	// put key field
				}
				String secNumStr = String.valueOf(secNum);
				int sctNumLen = secNumStr.length();
				for (int i=0; i<sctNumLen; i++)	
				{
					IndexRecord[27+i]=secNumStr.charAt(i);	// put number field
				}
				// completed one index sector
				
				// put index record into index sector
				for (int i=0;i<34;i++)			
				{
					IndexSector[n]=IndexRecord[i];
					n++; 
				}
				secNum++;	// next data sector 
			}
			disk.writeSector(IndSecNumL1, IndexSector);
			IndexSector = new char[512];
			IndSecNumL1++;
		}
	}
}
