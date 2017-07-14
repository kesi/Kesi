package controlflow;

public class NestedIf {

	public static void main(String args[]) {
	      int x = 30;
	      int y = 10;
	      String a="Test";

	      if(a.equals("Test"))
	      {
	    	  
	         if( y == 100 ) 
	         {
	            System.out.print("X = 30 and Y = 10");
	         }
	         else
	         {
	        	 System.out.print("case failed.");
	         }
	      }
	      else{
	    	  System.out.print("main if case");
	      }
	   }
}

