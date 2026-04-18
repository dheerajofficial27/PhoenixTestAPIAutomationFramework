package com.api.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigManager {
	
	private static Properties prop=new Properties();
	
	private ConfigManager() {
		//Created private constructor so that no one create the object of ConfigManager class.
	}
	
	private static String path = "config/config.properties";
	private static String env;
	
	static {
		//Created static block that will execute one during program run that will read & load file in class memory.
		//Introducing File Separator to make framework platform independent.
//		File configfile=new File(System.getProperty("user.dir")+File.separator+"src"+File.separator+"test"+File.separator+"resources"+File.separator+"config"+File.separator+"config.properties");
//		FileReader configreader = null;
		//configreader = new FileReader(configfile);
		
		env = System.getProperty("env", "qa"); //if user didn't pass any env then by-defualt it will run the QA properties.
		env=env.toLowerCase().trim(); //if user want to enter parameter in lower/upper/mix then it will pick the configuration.
		switch(env) {
		case "qa" -> path = "config/config.qa.properties";
			
		case "uat" -> path = "config/config.uat.properties";

		default -> path = "config/config.properties";
		
		}
		
		InputStream input=Thread.currentThread().getContextClassLoader().getResourceAsStream(path);
		
		try {
			prop.load(input);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static String getProperty(String key) {
		
		return prop.getProperty(key);
	}

}
