import java.util.Scanner;

public class ObtainTextPassword {
	
	public static String data(){
		
		Scanner input = new Scanner(System.in);
		
		String PlainText=null;
		String PlainText2=null;
		
		boolean Check=false;
		
		while (Check==false){
		
			System.out.println("Enter Plaintext Password: ");
			PlainText = input.next();
			Check=PasswordCheck(PlainText);
			System.out.println();
			
			if(Check==true){
			
				System.out.println("Confirm Plaintext Password: ");
				PlainText2 = input.next();
				
				if (PlainText.equals(PlainText2)==false){
					
					System.out.println("Error ! Both password is not the same, please retry.");
					System.out.println();
					Check=false;
				}
				
				
			}
			
			else{
				
				//
				
			}
		
		}
		
		return PlainText;
		
	}
	
	public static boolean PasswordCheck(String pass) {
		
		 //System.out.println(pass.length());
		
		 if (pass.length()>=8 == false){
			 
			 System.out.println("Password must be at least the length of 8.");
			 System.out.println("Password must be 8 length long, At least one Upper Case, one Lower Case, Number, and one Special Character");
			 return false;
			 
		 }

	     if (!pass.matches(".*[A-Z].*")) {
	    	 
	    	 
	    	 System.out.println("Password must be at least one Uppercase letter.");
	    	 System.out.println("Password must be 8 length long, At least one Upper Case, one Lower Case, Number, and one Special Character");
	    	 return false;
	     }

	     if (!pass.matches(".*[a-z].*")){
	    	 
	    	 System.out.println("Password must be at least one Lowercase letter."); 
	    	 System.out.println("Password must be 8 length long, At least one Upper Case, one Lower Case, Number, and one Special Character");
	    	 return false;
	     }

	     if (!pass.matches(".*\\d.*")) {
	    	 
	    	 System.out.println("Password must be at least one Number."); 
	    	 System.out.println("Password must be 8 length long, At least one Upper Case, one Lower Case, Number, and one Special Character");
	    	 return false;
	     }

	     if (!pass.matches(".*[~!!#$%&'()*+,-./:;<=>?@\\[\\]^_`{|}~.......].*")) {
	    	 
	    	 System.out.println("Password must be at least one Special Character."); 
	    	 System.out.println("Password must be 8 length long, At least one Upper Case, one Lower Case, Number, and one Special Character");
	    	 return false;
	     }

	     return true;
	}

}
