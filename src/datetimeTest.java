import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class datetimeTest {

	public static void main(String[] args)throws Exception {
		//for date time
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");  
		LocalDateTime now = LocalDateTime.now();  
	    System.out.println(dtf.format(now));  
		
	    String accent = "Á ç";
	    System.out.println("before "+accent);
	    accent = accent.toLowerCase();
	    System.out.println("after "+accent);
	    
	    
	    
	    
	    String mySqlCustCount = "select count(*), 123 from dbo.vacc_customers where SURNAME = '"+Surname+
				"' and FIRST_NAME = '"+FirstName+"' and DOB = '"+DOB+"' and SSN = "+SSN+";";
		//System.out.println("Count "+mySqlCustCount);
		List<String[]> custCount = new ArrayList<>();
		custCount = getDBdata(mySqlCustCount);
		String sCount = custCount.get(0)[1];
		System.out.println("sCount "+sCount);
		if (sCount != "0") { //if reoccuring person
			String mySqlCustId = "select ID, 1 from dbo.vacc_customers where SURNAME = '"+Surname+
					"' and FIRST_NAME = '"+FirstName+"' and DOB = '"+DOB+"' and SSN = "+SSN+";";
			//System.out.println("get id "+mySqlCustId);
			List<String[]> custId = new ArrayList<>();
			custId = getDBdata(mySqlCustId);
			String sId = custId.get(0)[1];
			iNextCustId = Integer.parseInt(sId);   
		}
		else {
    		String mySqlId2 = "select max(id), 1 from dbo.vacc_customers;";
	    	List<String[]> customerId = new ArrayList<>();
			customerId = getDBdata(mySqlId2);
			String sCustId = customerId.get(0)[1];
			int iNextCustId = 0;
			if(sCustId == null) { //if blank table
				iNextCustId = 1;
			}
			else { //if new person
				iNextCustId = Integer.parseInt(sCustId) + 1;  
				System.out.println("101 Customer id " + iNextCustId);
			}
		}
		}
	}
	

}
