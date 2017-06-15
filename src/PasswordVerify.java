import java.util.Scanner;

public class PasswordVerify {
	
	public static  boolean PasswordCheck1(char[] password, char[] password2){
		
		String P1 =  String.valueOf(password);
		String P2 =  String.valueOf(password2);
		
		if (P1.equals(P2)==false){
			
			System.out.println("Error ! Both password is not the same, please retry.");
			 JPanelEncyption.error_check.setText("Error: Password Empty/Error/Not Matching");
			System.out.println();
			return false;
		}
		
		else {
			
			return true;
		}
		
	}
	
	public static boolean PasswordCheck2(char[] password) {
		
		String pass =  String.valueOf(password);
		
		 if (pass.length()>=8 == false){
			 
			 System.out.println("Password must be at least the length of 8.");
			 System.out.println("Password must be 8 length long At least one Upper Case, one Lower Case, Number, and one Special Character");
			 JPanelEncyption.error_check.setText("Error: Password Empty/Error/Not Matching");
			 return false;
			 
		 }

	     if (!pass.matches(".*[A-Z].*")) {
	    	 
	    	 
	    	 System.out.println("Password must be at least one Uppercase letter.");
	    	 System.out.println("Password must be 8 length long, At least one Upper Case, one Lower Case, Number, and one Special Character");
	    	 JPanelEncyption.error_check.setText("Error: Password Empty/Error/Not Matching");
	    	 return false;
	     }

	     if (!pass.matches(".*[a-z].*")){
	    	 
	    	 System.out.println("Password must be at least one Lowercase letter."); 
	    	 System.out.println("Password must be 8 length long, At least one Upper Case, one Lower Case, Number, and one Special Character");
	    	 JPanelEncyption.error_check.setText("Error: Password Empty/Error/Not Matching");
	    	 return false;
	     }

	     if (!pass.matches(".*\\d.*")) {
	    	 
	    	 System.out.println("Password must be at least one Number."); 
	    	 System.out.println("Password must be 8 length long, At least one Upper Case, one Lower Case, Number, and one Special Character");
	    	 JPanelEncyption.error_check.setText("Error: Password Empty/Error/Not Matching");
	    	 return false;
	     }

	     if (!pass.matches(".*[~!!#$%&'()*+,-./:;<=>?@\\[\\]^_`{|}~.......].*")) {
	    	 
	    	 System.out.println("Password must be at least one Special Character."); 
	    	 System.out.println("Password must be 8 length long, At least one Upper Case, one Lower Case, Number, and one Special Character");
	    	 JPanelEncyption.error_check.setText("Error: Password Empty/Error/Not Matching");
	    	 return false;
	     }

	     return true;
	}



}
