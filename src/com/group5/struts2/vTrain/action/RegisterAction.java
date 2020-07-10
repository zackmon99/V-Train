package com.group5.struts2.vTrain.action;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.group5.struts2.vTrain.classes.User;

public class RegisterAction {
	
	private String username;
	private String password;
	private String fname;
	private String lname;
	private String department;
	private String role;
	private String error;
	private int deptID;
	private int userID;
	private User user;
	
	List<String> deptNames = new ArrayList<String>();
	List<String> roleNames = new ArrayList<String>();
	
	Connection con = null;
	DBLogger logger = new DBLogger();

	public String execute() {
		
		  
		  
		  //Checking Fields
		  
		  
		  try {
				
				//Linking Datasource
				Context ctx;
				ctx = new InitialContext();
		        DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/testdb");
		        con = ds.getConnection();
		        con.setAutoCommit(false);
		        
		        if (username.equals("") ||
						  fname.equals("") ||
						  lname.equals("") ||
						  department.equals("")||
						  department.equals("-1") ||
						  department == null ||
						  role.equals("") || 
						  role.equals("-1") ||
						  role == null ||
						  password.equals("")) {
					  error = "Not all fields are filled out";
					//Set up Roles
			        roleNames.add("Employee");
			        roleNames.add("Manager");
				        
			        //Setting up stmt to get department names
			        PreparedStatement selectStmt = null;
			        String selectString = "SELECT dept_name "
			        		+ "FROM PUBLIC.department "
			        		+ "ORDER BY dept_name";
			        selectStmt = con.prepareStatement(selectString);
			        
			        ResultSet rs = selectStmt.executeQuery();
			        while (rs.next()) {
			        	deptNames.add(rs.getString("dept_name"));
			        }
			        
					  return "fail";
				  }
		        
		        //Checking if username is taken
		        PreparedStatement selectStmt = null;
		        String selectString = "SELECT username "
		        		+ "FROM PUBLIC.user "
		        		+ "WHERE username = ?";
		        
		        //Setting up prepared statement
		        selectStmt = con.prepareStatement(selectString);
		        selectStmt.setString(1, username);
		        
		        ResultSet rs = selectStmt.executeQuery();
		        if (rs.next()) {
		        	error = "Username is taken";
		        	return "fail";
		        }
		        
		        //Getting department ID
		        rs = null;
		        selectStmt = null;
		        selectString = "SELECT dept_id "
		        		+ "FROM PUBLIC.department "
		        		+ "WHERE dept_name = ?";
		        selectStmt = con.prepareStatement(selectString);
		        selectStmt.setString(1, department);
		        
		        rs = selectStmt.executeQuery();
		        rs.next();
		        deptID = rs.getInt("DEPT_ID");
		        
		        //User update
		        PreparedStatement updateStmt = null;
		        String updateString = "INSERT INTO PUBLIC.user "
		        		+ "VALUES(null, ?, ?, ?, ?, ?, false)";
		        updateStmt = con.prepareStatement(updateString);
		        updateStmt.setString(1, username);
		        updateStmt.setString(2, fname);
		        updateStmt.setString(3, lname);
		        updateStmt.setString(4, role);
		        updateStmt.setString(5, password);
		        
		        updateStmt.executeUpdate();
		        con.commit();
		        
		        String[] r = new String[5];
		        r[0] = username;
		        r[1] = fname;
		        r[2] = lname;
		        r[3] = role;
		        r[4] = "WITHHELD";
		        logger.log(updateString, r, "NEW USER CREATED");
		        
		        //Get user_id
		        rs = null;
		        selectStmt = null;
		        selectString = "SELECT user_id "
		        		+ "FROM PUBLIC.user "
		        		+ "WHERE username = ?";
		        selectStmt = con.prepareStatement(selectString);
		        selectStmt.setString(1, username);
		        rs = selectStmt.executeQuery();
		        rs.next();
		        userID = rs.getInt("USER_ID");
		        
		        //Set User-Department relation
		        updateStmt = null;
		        updateString = "INSERT INTO PUBLIC.deptuser "
		        		+ "VALUES(null, ?, ?)";
		        updateStmt = con.prepareStatement(updateString);
		        updateStmt.setInt(1, userID);
		        updateStmt.setInt(2, deptID);
		        updateStmt.executeUpdate();
		        con.commit();
		        
		        r = new String[2];
		        r[0] = "" + userID;
		        r[1] = "" + deptID;
		        logger.log(updateString, r, "NEW USER-DEPARTMENT RELATION CREATED");		        
		        
		        
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
		
		return "success";
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

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<String> getDeptNames() {
		return deptNames;
	}

	public void setDeptNames(List<String> deptNames) {
		this.deptNames = deptNames;
	}

	public List<String> getRoleNames() {
		return roleNames;
	}

	public void setRoleNames(List<String> roleNames) {
		this.roleNames = roleNames;
	}
}
