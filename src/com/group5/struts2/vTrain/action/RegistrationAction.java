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

public class RegistrationAction {
	
	
	Connection con = null;
	List<String> deptNames = new ArrayList<String>();
	List<String> roleNames = new ArrayList<String>();

	public String execute() {
		try {
			
			//Linking Datasource
			Context ctx;
			ctx = new InitialContext();
	        DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/testdb");
	        con = ds.getConnection();
	        con.setAutoCommit(false);
	        
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
	        
	        
		} catch (NamingException e) {
			System.out.println("CONTEXT ERROR...");
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
		return "registration";
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
