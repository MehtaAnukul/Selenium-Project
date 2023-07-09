package utilities;

import com.google.gson.Gson;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

public class JavaHelpers 
{
	
	//Time-stamps 
	/**
	 * Get current time-stamp in given format
	 * @param format e.g. "yyyy MMM dd", 'yyyyMMdd_HHmmss' etc.
	 * @return String timestamp
	 * 
	 */
	public String getTimeStamp(String format) 
	{
		/*
		 * Example format are :
		 * 
		 * "yyyy MMM dd" for "2013 Nov 28"
		 * 
		 * "yyyyMMdd_HHmmss" for "20130131000000"
		 * 
		 * "yyyy MMM dd HH:mm:ss" for "2013 Jan 31 00:00:00"
		 * 
		 * "dd MMM yyyy" for "28 Nov 2017"
		 */
		DateFormat dateFormat = new SimpleDateFormat(format);
		Date date = new Date();
		return dateFormat.format(date);
	}

	/**
	 * Get current time-stamp in "_yyyyMMdd_HHmmss" format
	 * @return timestamp
	 */
	public String timeStamp() 
	{
		return getTimeStamp("_yyyyMMdd_HHmmss");
	}


	//Java Methods 
	/**
	 * Get method name where this method is called
	 * @return String method name
	 */
	public String getMethodName() 
	{
		return Thread.currentThread().getStackTrace()[3].getMethodName();
	}

	
	//Data Reader 
	/** 
	 * Get Property value
	 * @param propertyFile property file name
	 * @param propertyName property name
	 * @return property value
	 */
	public static String getPropertyValue(String propertyFile, String propertyName)
	{
		Properties prop = accessPropertiesFile(propertyFile);
		return prop.getProperty(propertyName);
	}

	/**
	 * Access property file
	 * @param propertyFile property file name
	 * @return Properties object
	 */
	public static Properties accessPropertiesFile(String propertyFile)
	{
		Properties prop = new Properties();
		// try retrieve data from file 
		try 
		{
			prop.load(new FileInputStream(propertyFile));
		}
		// catch exception in case properties file does not exist 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		return prop;
	}

	//JSON
	/**
	 * Json To String
	 * @param filePath json file path
	 * @return string
	 * @throws IOException exception
	 * @throws ParseException exception
	 */
	public static String jsonToString(String filePath) throws IOException, ParseException {

		JSONParser jsonParser = new JSONParser();
		Object obj = jsonParser.parse(new FileReader(filePath));
		return obj.toString();
	}

	/**
	 * Json deserialization to Java Object
	 * @param json string json
	 * @param dto Class Object
	 * @return Class Object equivalent to json file
	 */
	public static <T> T jsonDeserialization(String json, Class<T> dto)
	{
		return new Gson().fromJson(json, dto);
	}

	
	//Folder Operations
	/**
	 * Delete all files from given folder
	 * @param folderPath folder path
	 * 
	 */
	public void deleteAllFilesFromFolder(String folderPath)
	{
		File dir = new File(folderPath);
		if (!dir.isDirectory()) 
		{
			return;
		}
		File[] listFiles = dir.listFiles();
		if(listFiles != null)
		{
			for (File file : listFiles)
			{
				file.delete();
			}
		}
	}

	//Reading system properties

	/**
	 * Set system variable - set it from system variable first, if not found -set it from properties file
	 * @param name variable name
	 * @param propertyFileLocation properties file location
	 * @return variable value
	 */
	public static String setSystemVariable(String propertyFileLocation ,String name)
	{
		//Reading from system properties.
		String variable = System.getProperty(name);

		//if not specified via command line, take it from constants.properties file
		if (variable == null || variable.isEmpty())
		{
			variable = JavaHelpers.getPropertyValue(propertyFileLocation,name);
		}
		return variable;
	}
}
