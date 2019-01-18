package Game;

public class Mainclass {
	
	public static void main(String[] args) {
		
		test();
	}
	
	private static void test(){
		StringBuilder outString = new StringBuilder ();
        for(int i=0;i<5;i++){
	       	for(int j=0;j<5;j++){
	       		if((i==0)||(i==4))
	       			outString.append("--•--");
	        	else{
	        		
	        		if(j==0)
	        			outString.append("\n|");
	        		else if(j==4)
	        			outString.append("|\n|");
	        		else
	        			outString.append("	");
	        	}
	       	}
        }
		System.out.println(outString.toString());
	}
	
	
}
