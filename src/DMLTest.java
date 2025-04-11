import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DMLTest {
	
	public static Boolean DML(String mySql) {
		String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver"; // Start JDBC
	    String dbURL = "jdbc:sqlserver://localhost:1433;databaseName=MARK";
	    String userName="MyTest";
	    String userPwd="MyTest";
	    Connection dbConn=null;
	    boolean rs2;
	    try {
	       Class.forName(driverName);
	       dbConn = DriverManager.getConnection(dbURL,userName,userPwd);
	       //System.out.println("1");
	       Statement myDbConn2 = dbConn.createStatement();
	       //System.out.println("2");
	       rs2 = myDbConn2.execute(mySql);
	       //System.out.println("insert returned " + rs2);
	    }
	    catch (Exception e){
	    	e.printStackTrace();
	    	rs2 = true;
	    	//System.out.println("error");
	    }
	    
	    return !rs2;
	}
	public static void main(String[] args) {
		List<String[]> cols = new ArrayList<>();
		String sql = "Insert into dbo.test_tabel(id) values(999);";
		boolean result = DML(sql);
		System.out.println(result);
	}

}
