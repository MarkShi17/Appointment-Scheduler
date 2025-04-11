import java.util.regex.*;
import java.util.Scanner;

public class javaRegExTest {
	
	static myValidators validatorEmail = new myValidators();
	
	public static void main(String[] args) {
		
		// '.' represents any char value, but only 1. Anything other than '.' has to be in the same position and the same value
		Pattern pattern1 = Pattern.compile("..xx.");
		Matcher matcher1 = pattern1.matcher("AAxxA");
		System.out.println("1 String matches the given Regex - "+matcher1.matches());
		
		Pattern pattern2 = Pattern.compile("(...)...-...."); //doesn't work
		Matcher matcher2 = pattern2.matcher("(abc)def-ghij");
		System.out.println("2 String matches the given Regex - "+matcher2.matches());
		
		Pattern pattern3 = Pattern.compile("[abc]"); 
		Matcher matcher3 = pattern3.matcher("ab"); //only shows true if this is 1 character long
		System.out.println("3 String matches the given Regex - "+matcher3.matches());
				
		Pattern pattern4 = Pattern.compile("[a-z&&[def]]"); //(Intersection) 
	    Matcher matcher4 = pattern4.matcher("iuafgdefafa"); //shows true as long as d, e or f are present, length doesn't matter
	    while(matcher4.find()) {
	    	System.out.println("4 Match String start(): "+matcher4.start());
	    }
	    
	    Pattern pattern5 = Pattern.compile("[a-d[m-p]]");  //union
		Matcher matcher5 = pattern5.matcher("j"); //only says true if char is a-d or m-p
		System.out.println("5 String matches the given Regex - "+matcher5.matches());
		
		Pattern pattern6 = Pattern.compile("[a-z&&[^bc]]");  //subtraction
		Matcher matcher6 = pattern6.matcher("b"); //only says true if char is a-z but not b or c
		System.out.println("6 String matches the given Regex - "+matcher6.matches());
		
		Pattern pattern7 = Pattern.compile("[a-z&&[^m-p]]");  //subtraction
		Matcher matcher7 = pattern7.matcher("y"); //only says true if char is a-z but not m-p
		System.out.println("7 String matches the given Regex - "+matcher7.matches());
		
		System.out.println("8 String matches the given Regex - "+Pattern.matches("[xyz]", "xy"));
		
		//only shows true if "x" or null, shows false in every other case
		System.out.println("x? String matches the given Regex - "+Pattern.matches("x?", "xx"));
		
		//shows true if there is 1 or more x
		//says false is there is any character other than x, or if x is not present
		System.out.println("x+ String matches the given Regex - "+Pattern.matches("x+", "x"));
		
		//will show true if null, or contains any number of x's but only x
		//shows false if any character other than x is present
		System.out.println("x* String matches the given Regex - "+Pattern.matches("x*", ""));
		
		//Only allowed the {n} number of x, no other character is allowed
		//"x{2}", "xxa" produces false
		//Only "x{2}", "xx" produces true
		System.out.println("x{2} String matches the given Regex - "+Pattern.matches("x{2}", "xx")); 
		
		//Shows true if there is 2 or more x's, says false if there are characters other than x
		System.out.println("x{2,} String matches the given Regex - "+Pattern.matches("x{2,}", "xx")); 
		
		//Shows true if x is between 2 and 4, including 2 and 4. Says false if there are characters that aren't x
		System.out.println("x{2,4} String matches the given Regex - "+Pattern.matches("x{2,4}", "xxxx")); 
		//Shows true if x is between 2 and 3, including 2 and 3. Says false if there are characters that aren't x
		System.out.println("x{2,3} String matches the given Regex - "+Pattern.matches("x{2,3}", "xxx")); 
		//Shows true if x is 2. Says false if there are characters that aren't x
		System.out.println("x{2,2} String matches the given Regex - "+Pattern.matches("x{2,2}", "xx")); 
		//creates error
		//System.out.println("x{2,2} String matches the given Regex - "+Pattern.matches("x{2,1}", "xx"));
		
		//shows true if its null, "a", "b", or "c". If there is more than 1 character or a character not in [abc] it will be false
		System.out.println("[abc]? String matches the given Regex - "+Pattern.matches("[abc]?", "a"));
		
		//shows true if there is 1 or more of 'a','b', or 'c'. Shows false if null or there are characters other than a, b, and c
		//"a","ab","abc" are all true
		System.out.println("[abc]+ String matches the given Regex - "+Pattern.matches("[abc]+", "abc"));
		
		//true no matter what except if there are characters that are not in [abc]
		//"a", "ab", "abc", and null are all true
		System.out.println("[abc]* String matches the given Regex - "+Pattern.matches("[abc]*", "bc"));
		
		//true if any combination using 'a', 'b', or 'c' is 2 characters long. False is characters other than [abc] are used
		//"ab","ac", and "bc" are true, "a" and "abc" are false
		System.out.println("[abc]{2} String matches the given Regex - "+Pattern.matches("[abc]{2}", "ab"));
		
		//True if any combinations of characters inside [abc] is 2 characters or longer. False if characters other than [abc] are used
		// "a", and null are false. "ab", "abc", "abcc" are true.
		System.out.println("[abc]{2,} String matches the given Regex - "+Pattern.matches("[abc]{2,}", "abab"));
		
		//True if any combinations of characters inside [abc] is 2-4 characters. False if characters other than [abc] are used
		// "a", "abbbc" and null are false. "ab", "abc", and "abca" are true.
		System.out.println("[abc]{2,4} String matches the given Regex - "+Pattern.matches("[abc]{2,4}", "abbbc"));
		
		//-------------------- Metacharacters
		System.out.println("Metacharacters");
		System.out.println("d String matches the given Regex - "+Pattern.matches("(\\d)", "1"));
		System.out.println("D String matches the given Regex - "+Pattern.matches("(\\D\\d)", "a1"));
		System.out.println("1 -- "+Pattern.matches("(\\w\\d)\\1", "a2a2")); // '\\1' copies the \\w\\d and attaches it to the end.
		System.out.println("2 -- "+Pattern.matches("(\\w\\d)\\1", "a2b2")); // '\\1' would be false here since b2 would have to be a2
		System.out.println("3 -- "+Pattern.matches("(\\s\\w)", " 1")); 
		//System.out.println("3 -- "+Pattern.matches("\\.\\b\\w", "x w")); //doesnt work
		
		String regex = "\\bbecause\\b";
	    String input = "A sentence doesn't end with because because, because is a conjunction";
	    Pattern p = Pattern.compile(regex);
	    Matcher m = p.matcher(input);
	    int count = 0;
	    while(m.find()) {
	       count ++;
	    }
	    System.out.println("Number of matches: "+count);
	    
	    System.out.println("Markshi07@gmail.com is valid "+validatorEmail.validateEmail("Markshi07@gmail.com"));
	    System.out.println("_Markshi07@gmail.com is valid "+validatorEmail.validateEmail("_Markshi07@gmail.com"));
	    System.out.println("Markshi07@_gmail.com is valid "+validatorEmail.validateEmail("Markshi07@_gmail.com"));
	    System.out.println("Markshi07@gmail.c_om is valid "+validatorEmail.validateEmail("Markshi07@gmail.c_om"));
	}
}
