package com.group5.struts2.vTrain.action;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.regex.Pattern;

public class DBLogger {

	public void log(String selectString, String[] r, String message) {
		Date today = new Date();
		String toLog = "";
	
		for (int i = 0; i < r.length; i++) {
			selectString = selectString.replaceFirst(Pattern.quote("?"), r[i]);
		}
		
		toLog += today + " : " + message + ": " + selectString + "\n\n";
		try {
			File file = new File("/home/xcom/v-trainLog.txt");
 
			// if file doesn't exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}
 
			FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(toLog);
			bw.close();
 
			System.out.println("Done");
 
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
