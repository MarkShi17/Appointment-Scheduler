import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class myValidators {
	//private static final String EMAIL_REGEX = "^[\\w-\\+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,})$";

	// static Pattern object, since pattern is fixed
	private static Pattern patternEmail;
	private static Pattern patternPhoneFormat;
	private static Pattern patternPhoneNumbers;
	private static Pattern patternDOB;
	// non-static Matcher object because it's created from the input String
	private Matcher matcherEmail;
	private Matcher matcherPhoneFormat;
	private Matcher matcherPhoneNumbers;
	private Matcher matcherDOB;
	
	private static final String EMAIL_REGEX = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	//private static final String PHONE_REGEX = "^((\\(\\d{3}\\))|\\d{3})[- .]?\\d{3}[- .]?\\d{4}$"; //any (), -, ., or whitespace is ok
	private static final String PHONE_REGEX_FORMAT = "^((\\(\\d{3}\\)))\\d{3}-\\d{4}$"; //requires exact (000)000-0000 format
	private static final String PHONE_REGEX_NUMBERS = "^(?!000)\\d{3}(?!000)\\d{3}(?!0000)\\d{4}$"; //doesn't take 000, 000, or 0000 in the 3 spaces but no () or -
	//private static final String PHONE_REGEX = "^((\\(?!000)\\d{3}\\))\\(?!000)\\d{3}(?!0000)\\d{4}$"; //requires exact (000)000-0000 format
	private static final String DOB_REGEX = "^\\d{2}/\\d{2}/\\d{4}$";
	
	public myValidators() {
		// initialize the Pattern object
		patternEmail = Pattern.compile(EMAIL_REGEX);
		patternPhoneFormat = Pattern.compile(PHONE_REGEX_FORMAT);
		patternPhoneNumbers = Pattern.compile(PHONE_REGEX_NUMBERS);
		patternDOB = Pattern.compile(DOB_REGEX);
	}

	/**
	 * This method validates the input email address with EMAIL_REGEX pattern
	 * 
	 * @param email 
	 * @return boolean
	 */
	public boolean validateEmail(final String email) {
		matcherEmail = patternEmail.matcher(email);
		return matcherEmail.matches();
	}
	
	public String phoneNumValidator(String phoneNum) {
	    /*matcherPhone = patternPhone.matcher(phoneNum);
	    return matcherPhone.matches();*/
		String return_message = "";
		String phoneNum2 = phoneNum.replace('(',' ');
		phoneNum2 = phoneNum2.replace(')', ' ');
		phoneNum2 = phoneNum2.replace('-', ' ');
		phoneNum2 = phoneNum2.replaceAll("\\s", "");;
		patternPhoneFormat = Pattern.compile(PHONE_REGEX_FORMAT);
		matcherPhoneFormat = patternPhoneFormat.matcher(phoneNum);
		patternPhoneNumbers = Pattern.compile(PHONE_REGEX_NUMBERS);
		matcherPhoneNumbers = patternPhoneNumbers.matcher(phoneNum2);
		if (phoneNum.length() == 0) {
			return_message = "Missing Phone Number.";
		}
		else if (matcherPhoneFormat.matches() == true) {
			if (matcherPhoneNumbers.matches() == true) {
				return_message = "yes";
			}
			else {
				return_message = "Invalid Phone Number.";
			}
		}
		else {
			return_message = return_message + "Invalid Phone Number format.";
			if (matcherPhoneNumbers.matches() != true) {
				return_message = return_message + "\nInvalid phone Number.";
			}
		}
		return return_message;
	}
	
	public String dobValidator(String myDate) {
		matcherDOB = patternDOB.matcher(myDate);
		if (matcherDOB.matches() == true) {
			try {
				DateFormat format = new SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH);
				format.setLenient(false);
				Date date1 = format.parse(myDate);
				Calendar cal = Calendar.getInstance();
				cal.setLenient(false);
				cal.setTime(date1);
			    //cal.getTime();
				//System.out.println(cal.getTime());
				return "yes";
			}
			catch (java.text.ParseException e) {
			  //System.out.println("Invalid date");
			  return "Invalid DOB date.";
			}
		}
		else {
			return "DOB not in mm/dd/yyyy format.";
		}
	}
	
	public String SSNvalidator4digit(String SSNvalue) {
		String returnMsg = " ";
		try {
	        int SSNint = Integer.parseInt(SSNvalue);
	        //System.out.println(SSNint+ " ssn variabl type");
	        int SSNlength = String.valueOf(SSNint).length();
	        if (SSNlength != 4) {
	        	//System.out.println("error SSN length"); //make popup error message
	        	returnMsg = "SSN is not 4 digits.";
	        	//System.out.println(errorMsg);
	        }
	        else {
	        	//System.out.println("valid SSN number"); 
	        	returnMsg = "yes";
	        }
	    } catch (NumberFormatException nfe) {
	    	//System.out.println("error SSN string"); //make popup error message
	    	returnMsg = "SSN must be 4 digits.";
	    	//System.out.println(errorMsg);
	    }
		return returnMsg;
	}
	
	

}
