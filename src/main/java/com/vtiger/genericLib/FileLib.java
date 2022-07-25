package com.vtiger.genericLib;

import java.io.FileInputStream;
import java.util.Properties;

public class FileLib {
	
	public String readPropertyData(String propPath, String key) throws Throwable
	{
		FileInputStream fileInputStream = new FileInputStream(propPath);
		Properties properties = new Properties();
		properties.load(fileInputStream);
		String propertyValue = properties.getProperty(key, "Incorrect Key");
		return propertyValue;
		
	}

}
