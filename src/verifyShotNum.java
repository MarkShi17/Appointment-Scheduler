import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import javax.swing.JFrame;

public class verifyShotNum {
	public static List<String[]> cols;
	
	public static List<String[]> getDBdata(String mySql) {
		String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver"; // Start JDBC
	    String dbURL = "jdbc:sqlserver://localhost:1433;databaseName=MARK";
	     String userName="sa";
	     String userPwd="SA";
	     Connection dbConn=null;
	     try {
	        Class.forName(driverName);
	        dbConn = DriverManager.getConnection(dbURL,userName,userPwd);
	        Statement myDbConn2 = dbConn.createStatement();
	        ResultSet rs2 = myDbConn2.executeQuery(mySql);
	        ResultSetMetaData rsmd = rs2.getMetaData();
	        int colCount = rsmd.getColumnCount();
	        int rowCount = 0;
	        String[][] arQrs;
	        cols = new ArrayList<>();
	        int iRow;
	        iRow = 0;
	        if(rs2!=null){
	            while (rs2.next()) {
	          	  String[] tempRow;
	          	  tempRow = new String[colCount];
	                for(int i = 1; i <colCount;i++) {
	                    tempRow[i]=rs2.getString(i);
	                }
	                cols.add(tempRow);
	                iRow++;
	            }
	        }
	     }
	     catch (Exception e) {
	         e.printStackTrace();
	     }
		return cols;
	}
	
	//checks if current vaccine is the same as teh previous vaccine for same customer
	public static String verifyVaccName(int custId, int vaccId) {
		String getPrevVacc = "select v.id, v.name, 1 from dbo.vaccine_appointments va, dbo.vaccines v where va.vaccine_id = v.id and va.customer_id = "+custId+";";
		List<String[]> previousVacc = new ArrayList<>();
		previousVacc = getDBdata(getPrevVacc);
		if(previousVacc.isEmpty()) {
			return "yes";
		}
		else {
			String sPrevVaccId = previousVacc.get(0)[1];
			int iPrevVaccId = Integer.parseInt(sPrevVaccId);
			if(iPrevVaccId == vaccId) {
				return "yes";
			}
			else {
				String sPrevVaccName = previousVacc.get(0)[2];
				return sPrevVaccName;
			}
		}
	}
	
	//checks if current shot number is directly after last shot number
	public static String verifyShotNum(int custId, int vaccId) {
		/*String getPrevShots = "select count(*), 1 from dbo.vaccine_appointments where customer_id = "+custId+";";
		List<String[]> previousShots = new ArrayList<>();
		previousShots = getDBdata(getPrevShots);
		String sNumOfShots = previousShots.get(0)[1];
		int iNumOfShots = Integer.parseInt(sNumOfShots);
		if(shotNum == iNumOfShots + 1) {
			return "yes";
		}
		else {
			return sNumOfShots;
		}*/
		String mySqlMaxShots = "select num_of_shots, name from dbo.vaccines where id = "+vaccId+";";
		List<String[]> maxNumShots = new ArrayList<>();
		maxNumShots = getDBdata(mySqlMaxShots);
		String sMaxShots = maxNumShots.get(0)[1];
		int iMaxShots = Integer.parseInt(sMaxShots);
		String getPrevShots = "select count(*), 1 from dbo.vaccine_appointments where customer_id = "+custId+";";
		List<String[]> previousShots = new ArrayList<>();
		previousShots = getDBdata(getPrevShots);
		String sNumOfShots = previousShots.get(0)[1];
		int iNumOfShots = Integer.parseInt(sNumOfShots);
		if(iNumOfShots >= iMaxShots) {
			return "0:You've already gotten the maximum amount of the vaccine";
		}
		else {
			iNumOfShots = iNumOfShots + 1;
			return "1:"+iNumOfShots;
		}
	}
	
	public static String verifyAppDates(int custId, String selectedDate, int vaccId) {
		System.out.println("selectedDate "+selectedDate);
		String getPrevDate = "select date, 1 from dbo.vaccine_appointments where customer_id = "+custId+ " order by date DESC;";
		List<String[]> previousDate = new ArrayList<>();
		previousDate = getDBdata(getPrevDate);
		String sPrevDate = previousDate.get(0)[1];
		
		String getDaysbBtwnShots = "select days_between_shots, 1 from dbo.vaccines where id = "+vaccId+";";
		List<String[]> daysBetweenShots = new ArrayList<>();
		daysBetweenShots = getDBdata(getDaysbBtwnShots);
		String sDaysBetween = daysBetweenShots.get(0)[1];
		
		
		if(sDaysBetween == null) {
			//System.out.println("is j&j");
			return "This vaccine only requires one dose and you've already scheduled one."; 
		}
		else {
			int iDaysBetween = Integer.parseInt(sDaysBetween);
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM-dd-yyyy");
			SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
			SimpleDateFormat formatter2 = new SimpleDateFormat("MM-dd-yy", Locale.ENGLISH);
			try {
				Date dCurrentDate1 = formatter2.parse(selectedDate);
				System.out.println("previous date "+sPrevDate);
				System.out.println("current date "+dCurrentDate1);
				Date dPreviousDate1 = formatter1.parse(sPrevDate);
				//System.out.println(dPreviousDate1);
				System.out.println("current date time "+dCurrentDate1.getTime());
				System.out.println("previous date time "+dPreviousDate1.getTime());
				long lDiff = dCurrentDate1.getTime() - dPreviousDate1.getTime();
				int iDiff = (int)TimeUnit.DAYS.convert(lDiff, TimeUnit.MILLISECONDS);
				System.out.println("Difference "+iDiff+" daysbetween"+iDaysBetween);
				if(iDiff >= iDaysBetween) {
					//System.out.println("days between "+iDiff);
					return "yes";
				}
				else {
					int daysBtwnNeeded = iDaysBetween - iDiff;
					Calendar c = Calendar.getInstance();
					c.setTime(dPreviousDate1); // Using today's date
					c.add(Calendar.DATE, iDaysBetween); // Adding 5 days
					//String output = formatter.format(c.getTime());
					System.out.println("date length bad");
					return "This vaccine requires "+sDaysBetween+" days between 2 shots. Please choose a new day after "+formatter1.format(c.getTime());
				}
			} catch (ParseException e) {
				//e.printStackTrace();
				//System.out.println("1"+e.toString());
				//System.out.println("is error");
				return "unexpected error: "+e.toString();
			}
			
		}
		
	}
	
	
	public static Boolean ifSpotAvailable(int vaccSiteId, String selectedDate, String selectedTime, int in_max_hourly, int vaccSite) {
		if(in_max_hourly == 0) {
			String getMaxAppts = "select max_appts_hourly, 1 from dbo.vaccine_sites where id = "+vaccSiteId+";";
			List<String[]> maxApptsHour = new ArrayList<>();
			maxApptsHour = getDBdata(getMaxAppts);
			String sMaxAppts = maxApptsHour.get(0)[1];
			in_max_hourly = Integer.parseInt(sMaxAppts);
		}
		
		selectedTime = selectedTime + ":00:00";
		String getPrevAppts = "select count(*), 1 from dbo.vaccine_appointments where date = '"+selectedDate+"' and time = '"+selectedTime+"' and vaccine_site_id = '"+vaccSite+"';";
		List<String[]> previousAppts = new ArrayList<>();
		previousAppts = getDBdata(getPrevAppts);
		String sPrevAppts = previousAppts.get(0)[1];
		int iPrevAppts = Integer.parseInt(sPrevAppts);
		
		//System.out.println("maxAppts: "+in_max_hourly +"  prevAppts: "+sPrevAppts);
		if(iPrevAppts >= in_max_hourly) {
			return false;
		}
		else {
			return true;
		}
	}
	
	public static void main(String[] args) {
		String getMaxAppts = "select max_appts_hourly, 1 from dbo.vaccine_sites where id = 1;";
		List<String[]> maxApptsHour = new ArrayList<>();
		maxApptsHour = getDBdata(getMaxAppts);
		String sMaxAppts = maxApptsHour.get(0)[1];
		int iMaxAppts = Integer.parseInt(sMaxAppts);
		System.out.println(sMaxAppts);
		
		boolean returnStatement = ifSpotAvailable(2,"8-10-21", "10", iMaxAppts);
		System.out.println(returnStatement);
		
		for (int i = 0; i < 12; i++ ) {
			int j = i+7;
			String timeSlot = j +"";
			boolean ifAvailable = ifSpotAvailable(2, "8-10-21", timeSlot,iMaxAppts);
			System.out.println(ifAvailable+" number "+j);
		}
		
		/*SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
		Date dCurrentDate1 = null;
		Date dPreviousDate1 = null;
		try {
			dCurrentDate1 = formatter.parse("2020-10-30");
			dPreviousDate1 = formatter.parse("2020-10-10");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		long diff = dCurrentDate1.getTime() - dPreviousDate1.getTime();
		System.out.println ("Days: " + TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS));
		int iDiff = (int)TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
		System.out.println("int "+iDiff);
		if(iDiff == 20) {
			System.out.println("20");
		}
		else {
			System.out.println("not 20");
		}*/
		
	}	
	
}
