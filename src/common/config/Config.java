/**
 * Config.java
 */

package common.config;

import java.io.IOException;

import debug.Debug;
import log.StandardLogger;


/**
 * This is used to read the config file and store the config Keys and Values
 *
 */
public class Config
{	
	/**
	 * Default name of the configuration file
	 */
	public static final String DEFAULT_CONFIG_FILE = "config.ini";
	
	/**
	 * Name of the configuration file
	 */
	public static String config_file = DEFAULT_CONFIG_FILE;
	
	/**
	 * Logger to standard IO
	 */
	private static StandardLogger stdLogger = StandardLogger.getInstance();
	
	/*
	 * =============== Region : COMMON ==============================================
	 */
	
	/**
	 * Flag for whether debug mode is on.
	 * If true, there will be debug statements.
	 */
	public static boolean DEBUG_ON = true;
	
	
	
	/*
	 * =============== Region : CRAWLER ==============================================
	 */
	
	
	
	/*
	 * =============== Region : DATA HOUSE ==============================================
	 */
	
	

	
	/*
	 * =============== Region: SEARCH ENGINE ==============================================
	 */
	

	
	/*
	 * =============== Region starts: CONTENT PRESENTER ==============================================
	 */
	
	
	
	/*
	 * =============== Region Ends ==============================================
	 */
	
	
	
	
	
	/**
	 * Reads the configuration file
	 * @param configFile
	 *            Configuration file. If null, default name "config.ini" will be used.
	 */
	public static void readConfigFile(String configFile)
	{
		if(configFile != null)
			config_file = configFile;
		
		ConfigReader configReader = null;
		
		try{
			configReader = new ConfigReader(config_file);
			stdLogger.printLog("Reading config file: " + configReader.getConfigFileName());
		} catch(IOException ioe) {
			stdLogger.printError("ERROR reading config file: " + config_file);
			System.exit(1);
		}
		
		// print the config
		configReader.printConfig();
		
		fillValues(configReader);
	}
	
	/**
	 * Read and fill the values for keys
	 * @param configReader
	 */
	private static void fillValues(ConfigReader configReader)
	{
		String value_String = "";
		int value_Int = -1;
		double value_Double = -1;
		
		/*
		 * =============== Region. Common parameters ==============================================
		 */
		// DEBUG_ON
		if(configReader.hasValue("DEBUG_ON"))
		{
			value_Int = configReader.getInt("DEBUG_ON");
			if(value_Int == 1) {
				DEBUG_ON = true;
				stdLogger.printLog("Debug Mode enabled");
			}
			else if(value_Int == 0) {
				DEBUG_ON = false;
				Debug.print("Debug Mode disabled, DEBUG_ON：" + value_Int);
			}
		}
		
		/*
		 * =============== Region. Crawler ==============================================
		 */
		
	}
	
/*
 * ========= Region Starts: test ================================================
 */
	
	public static void main(String[] args)
	{
		Config.readConfigFile("config.ini");

	}
/*
* ========= Region Ends ==================================================
*/
}
