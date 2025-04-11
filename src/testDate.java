import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class testDate {
	/*private static Pattern patternDOB;
	private static Matcher matcherDOB;
	private static final String DOB_REGEX = "^\\d{2}/\\d{2}/\\d{4}$";*/
	
	private static Pattern patternPhone1;
	private static Pattern patternPhone2;
	private static Matcher matcherPhone1;
	private static Matcher matcherPhone2;
	private static final String PHONE_REGEX1 = "^((\\(\\d{3}\\)))\\d{3}-\\d{4}$"; //requires exact (000)000-0000 format
	private static final String PHONE_REGEX2 = "^(?!000)\\d{3}(?!000)\\d{3}(?!0000)\\d{4}$";
	
	public static void main(String[] args) {
		String phone_num1 = "(123)456-7890";
		String phone_num2 = phone_num1.replace('(',' ');
		phone_num2 = phone_num2.replace(')', ' ');
		phone_num2 = phone_num2.replace('-', ' ');
		phone_num2 = phone_num2.replaceAll("\\s", "");;
		//System.out.println("only numbers: "+phone_num2);
		//patternDOB = Pattern.compile(DOB_REGEX);
		//matcherDOB = patternDOB.matcher("01/01/word");
		patternPhone1 = Pattern.compile(PHONE_REGEX1);
		matcherPhone1 = patternPhone1.matcher(phone_num1);
		patternPhone2 = Pattern.compile(PHONE_REGEX2);
		matcherPhone2 = patternPhone2.matcher(phone_num2);
		if (matcherPhone1.matches() == true) {
			if (matcherPhone2.matches() == true) {
				System.out.println("valid phone num");
			}
			else {
				System.out.println("invalid phone num");
			}
		}
		else {
			System.out.println("invalid phone num format");
		}
		//System.out.println("boolean dob check format: " + matcherDOB.matches());
		//System.out.println("boolean phone check format: " + matcherPhone.matches());
		/*String sDate="01/31/2021";
		try {
			DateFormat format = new SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH);
			format.setLenient(false);
			Date date1 = format.parse(sDate);
			Calendar cal = Calendar.getInstance();
			cal.setLenient(false);
			cal.setTime(date1);
		    //cal.getTime();
			System.out.println(cal.getTime());
		}
		catch (java.text.ParseException e) {
		  System.out.println("Invalid date");
		}*/
	}

}
