package com.lexnod.genericLib;

import java.io.FileInputStream;
import java.util.Properties;

/**
 * 
 * @author ARUN
 *
 */

public class PropertyFileLibrary {
	/**
	 * To get the data from the property file
	 * @param propPath
	 * @param key
	 * @return
	 * @throws Throwable
	 */

	public String getPropertyData(String key) throws Throwable {
		FileInputStream fileInpuStream = new FileInputStream(IAutoConstants.PROP_PATH);
		Properties properties = new Properties();
		properties.load(fileInpuStream);
		String propertyValue = properties.getProperty(key, "Incorrect Key");
		return propertyValue;
	}

}
