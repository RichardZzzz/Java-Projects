package Assignment3;

public class Disk 
{
	   private int sectorCount;   // sectors on the disk
	   private int sectorSize;    // characters in a sector
	   private char[][] store;    // all disk data is stored here
	  
	   public Disk()    // for default sectorCount and sectorSize
	   {
		   
	   }
	   public Disk(int sectorCount, int sectorSize)
	   {
		   this.sectorCount=sectorCount;
		   this.sectorSize =sectorSize;
		   this.store = new char[sectorCount][sectorSize];
	   }
	   public void readSector(int sectorNumber, char[] buffer)   // sector to buffer
	   {
		   for(int i=0; i<sectorSize;i++)
			   buffer[i]=this.store[sectorNumber][i];
	   }                                                        
	   public void writeSector(int sectorNumber, char[] buffer)  // buffer to sector 
	   {
		   for(int i=0; i<sectorSize;i++)
			   this.store[sectorNumber][i]=buffer[i];
	   }                                                        
	   public int getSectorCount()
	   {
	      return sectorCount;
	   }
	   public int getSectorSize()
	   {
	      return sectorSize;
	   }
	
}
