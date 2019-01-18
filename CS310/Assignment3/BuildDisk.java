package Assignment3;

public class BuildDisk {
	
	ReadFromFile file = new ReadFromFile() ;
	private char[][] data = file.getData();

	int k=0;
	
	public void Build(Disk x)
	{
		int secNum = 1000;	// sector number
		while (secNum<1588)
		{
			char [] buffer = new char [512];
			int n = 0;		// pointer in a buffer
			while (n<512)	// create a sector
			{
				if(n<300)
				{
					for (int i=0;i<5;i++)	// put five records into the buffer
					{
						for (int j=0; j<60; j++)	// put one data record into the buffer
						{
							buffer[n] = data[k][j];		
							n++;
						}
						k++;
					}
				}
				else
				{
					buffer[n] = '\000';
					n++;
				}

			}
			x.writeSector(secNum, buffer);
			secNum++;
			
		}
	}
	
}
