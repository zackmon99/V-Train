package com.group5.struts2.vTrain.action;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.group5.struts2.vTrain.classes.User;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class LoginAction extends ActionSupport {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2401503176678759464L;
	private String username;
	private String password;
	private User user = null;
	
	Connection con = null;
	DBLogger logger = new DBLogger();	
	
	public String execute() {
		
		  Map<String, Object> session = ActionContext.getContext().getSession();
		
		try {
			
			//Linking Datasource
			Context ctx;
			ctx = new InitialContext();
	        DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/testdb");
	        con = ds.getConnection();
	        con.setAutoCommit(false);
	        
	        //Setting up initial statement
	        PreparedStatement selectStmt = null;
	        String selectString = "SELECT user_id, username, password, activated, first_name, last_name, role "
	        		+ "FROM PUBLIC.user "
	        		+ "WHERE username = ? AND "
	        		+ "password = ?";
	        
	        //Setting up prepared statement
	        selectStmt = con.prepareStatement(selectString);
	        selectStmt.setString(1, username);
	        selectStmt.setString(2, password);
	        
	        //Setting up string array for DBLogger
	        String[] r = new String[2];
	        r[0] = username;
	        r[1] = "WITHHELD";
	        
	        ResultSet rs = selectStmt.executeQuery();

	        logger.log(selectString, r, "LOGIN");
	        
	        //Checking for account
	        if (!rs.next()) {
	        	return "input";
	        //Seeing if account is activated
	        } else if (!rs.getBoolean(4)) {
	        	return "notactivated";
	        //Logging in and setting user
	        } else {
	        	//Setting user object from result set
	        	user = new User(rs.getInt("USER_ID"), rs.getString("USERNAME"), 
	        			rs.getString("FIRST_NAME"), rs.getString("LAST_NAME"), 
	        			rs.getString("ROLE"));
	        	//storing session information
	        	session.put("logged-in", "true");
	        	session.put("user", user);
				return "success";
			}
	        
	        
		} catch (NamingException e) {
			System.out.println("CONTEXT ERROR...");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("DB CONNECTION ERROR...");
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				System.out.println("CONNECTION CLOSE ERROR...");
				e.printStackTrace();
			}
		}
		
		return "input";
	}
	
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}
}
