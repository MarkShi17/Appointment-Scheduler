
public class validateSSN4digit {
	
	public String SSNvalidator4digit(String SSNvalue) {
		String returnMsg = " ";
		try {
	        int SSNint = Integer.parseInt(SSNvalue);
	        //System.out.println(SSNint+ " ssn variabl type");
	        int SSNlength = String.valueOf(SSNint).length();
	        if (SSNlength != 4) {
	        	//System.out.println("error SSN length"); //make popup error message
	        	returnMsg = "error: SSN length is not 4";
	        	//System.out.println(errorMsg);
	        }
	        else {
	        	//System.out.println("valid SSN number"); 
	        	returnMsg = "yes";
	        }
	    } catch (NumberFormatException nfe) {
	    	//System.out.println("error SSN string"); //make popup error message
	    	returnMsg = "error: SSN contains non-digit values";
	    	//System.out.println(errorMsg);
	    }
		return returnMsg;
	}
}
