package com.crm_SDET37.GenericUtility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * 
 * @author SHUBH
 *
 */
public class FileUtility {
	/**
	 * to get the common data from Property File
	 * @param key
	 * @return
	 * @throws Throwable 
	 * @throws IOException
	 */
	public String getPropertyValue(String key) throws Throwable  {
		FileInputStream fileInptStream=new FileInputStream(IConstants.filePath);
		Properties properties = new Properties();
		properties.load(fileInptStream);
		String value = properties.getProperty(key);
		return value;
	}
}
