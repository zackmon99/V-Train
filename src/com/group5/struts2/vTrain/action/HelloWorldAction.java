package com.group5.struts2.vTrain.action;

import com.opensymphony.xwork2.ActionSupport;

import java.sql.Connection;
import java.util.Date;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class HelloWorldAction extends ActionSupport {

    /**
	 * 
	 */
	private static final long serialVersionUID = -4440590941387949818L;

	private String message;

    private Date nowDate;

    @Override
    public void validate(){
        if (message==null || message.length()==0)
            addActionError(getText("error.enter.message"));
    }

    @Override
    public String execute() throws Exception {
        nowDate = new Date();
        Context ctx = new InitialContext();
        
        DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/testdb");
        
        Connection connection = ds.getConnection();
        
        String sql = message;
        java.sql.Statement statement = null;
        statement = connection.createStatement();
        int updateQuery = 0;
        try {
	        connection.setAutoCommit(false);
	        updateQuery = statement.executeUpdate(sql);
	        
	        if (updateQuery != 0) {
	        	System.out.println("SUCCESS!");
	        }
        } finally {

        }
        
        
        return SUCCESS;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getNowDate() {
        return nowDate;
    }

}