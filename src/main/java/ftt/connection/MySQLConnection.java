package ftt.connection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PreDestroy;
import ftt.config.AppConfig;

public class MySQLConnection {
	private static final String INSERT_HISTORY_STATEMENT = " insert into rawdata (id, token, IPadress_from)" + " values (null, ?, ?)";
	private static List<ArrayList<String>> historyCached = new ArrayList<ArrayList<String>>();
	private static final int CACH_SIZE = 2;
	static AppConfig appConfig = AppConfig.getInstance();
	static final String USER = appConfig.getProperty("db.user");
	static final String PASSWORD = appConfig.getProperty("db.password");
	static final String DB = appConfig.getProperty("db.db");
	
	
	public static void main(String args[]) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(DB, USER, PASSWORD);
			String query = " insert into rawdate (id, token, IPadress_from)" + " values (null, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			preparedStmt.setString(1, "1_3_5_6_3_12_5_2_3");
			preparedStmt.setString(2, "1:1221:231:321");
			preparedStmt.execute();
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	@PreDestroy
	 public static void writeCached() {
		 // TODO use Hibernate
		System.out.println("Saving ...");
		PreparedStatement preparedStmt;
	     try(Connection conn = DriverManager.getConnection(DB, USER, PASSWORD)){
	    	 for(List<String> hist : historyCached) {
	    		preparedStmt = conn.prepareStatement(INSERT_HISTORY_STATEMENT);
	 			preparedStmt.setString(1, hist.get(0));
				preparedStmt.setString(2, hist.get(1));
				preparedStmt.execute();
	    	 }
	    	 historyCached.clear();
	    	 System.out.print("Saved");
	     }catch (Exception e) {
	    	System.out.println(e.getMessage());
			System.out.println(e.getStackTrace());
		}
	    
	 }
	
	public static boolean addHistory(final String token) {
		if(token.length() < 19 || token.length() > 40) {
			return false;
		}
		historyCached.add(new ArrayList<String>() {{add(token); add(null);}});
		//TODO remove later on
		System.out.println("chach size is: " + historyCached.size());
		if(historyCached.size() > CACH_SIZE) {
			writeCached();
		}
		return true;
	}
}
