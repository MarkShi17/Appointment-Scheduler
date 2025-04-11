import java.sql.*;
import java.util.*;

public class DBConnectTest {
   public static void main(String[] srg) {
       String driverName
          = "com.microsoft.sqlserver.jdbc.SQLServerDriver"; // Start JDBC
       /*String dbURL = "
	   jdbcTongue Tiedqlserver://localhost:1433;  DatabaseName=BudgetAuthorization";  //  Connect the server and the database*/
	   //jdbcTongue Tiedqlserver://localhost:1433;databaseName=BudgetAuthorization;user=aaron792;password=12345; 
	   String dbURL = "jdbc:sqlserver://localhost:1433;databaseName=MARK";
       String userName="MyTest";
       String userPwd="MyTest";
       Connection dbConn=null;
       try
       {
          Class.forName(driverName);
          dbConn = DriverManager.getConnection(dbURL,userName,userPwd);
          //System.out.println("Connection Successful!"); 
          //Connection con = DriverManager.getConnection(url, userName, password);
          Statement s1 = dbConn.createStatement();
          // example 1 - 1 row
          ResultSet rs = s1.executeQuery("SELECT Top 1 * from dbo.vaccines;");
          String[] result = new String[6];
          //String result=new String();
          if(rs!=null){
              while (rs.next()) {
                  /*for(int i = 1; i <result.length ;i++) {
                      for(int j = 1; j <result.length;j++) {
                          result[j]=rs.getString(i);
                          //System.out.println(result[j]);}
                           * }
                           */
            	  for(int i = 1; i <result.length ;i++) {
            		  result[i]=rs.getString(i);
                      //System.out.println(result[i]);
                  }
              }
          }
          //System.out.println(result);
          System.out.println("rs: "+rs);
          // if successful print Connection Successful!
        }
       catch (Exception e)
       {
           e.printStackTrace();
        }
       //-------------------------------- example 2 - 2d array
       try
       {
          Class.forName(driverName);
          dbConn = DriverManager.getConnection(dbURL,userName,userPwd);
          //System.out.println("Connection Successful!"); 
          //Connection con = DriverManager.getConnection(url, userName, password);
          Statement myDbConn2 = dbConn.createStatement();
          // example 1 - 1 row
          ResultSet rs2 = myDbConn2.executeQuery("SELECT * from dbo.vaccines;");
          ResultSetMetaData rsmd = rs2.getMetaData();
          int colCount = rsmd.getColumnCount();
          int rowCount = 0;
          //System.out.println("colCount="+colCount);
          //int[][] arr = {{1,2,3},{4,5,6}};
          String[][] arQrs;
          List<String[]> cols = new ArrayList<>();
          //ArrayList<String> cols = new ArrayList<String>(Arrays.asList(question1));
          //aList.addAll(Arrays.asList(question2));
          int iRow;
          
          /*if(rs2!=null){
              while (rs2.next()) {
            	  rowCount++;
              }
          }*/
          iRow = 0;
          if(rs2!=null){
              while (rs2.next()) {
            	  String[] tempRow;
            	  tempRow = new String[colCount];
                  for(int i = 1; i <colCount;i++) {
                      //result[j]=rs.getString(i);
                      //System.out.println("row "+iRow+": "+rs2.getString(i));
                      //arQrs[iRow][i] = rs2.getString(i);
                      tempRow[i]=rs2.getString(i);
                  }
                  cols.add(tempRow);
                  iRow++;
                  //System.out.println(cols.get(i));
              } 
            	  /*for(int i = 1; i <rowCount ;i++) {
            		  for(int j = 1; j <colCount;j++) {
            			  arQrs[i][j] = rs2.
            		  }
            			  
            		  }*/
          }
          //System.out.println(result);
          for(int i = 0; i <iRow;i++) {
        	  for(int j = 1; j <colCount;j++) {
        		  System.out.println("row "+ i + " Column " + j +" " +cols.get(i)[j]);
        	  }
          }

          // if successful print Connection Successful!
        }
       catch (Exception e)
       {
           e.printStackTrace();
        }
       
}
}

 