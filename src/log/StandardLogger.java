/**
 * StandardLogger.java
 */

package log;

/**
 * This is in charge of output log message to standard IO.
 */
public class StandardLogger
{
	/**
	 * the instance of the standard logger
	 */
	private static StandardLogger logger = null;
	
	/**
	 * Returns instance of StandardLogger.
	 * <p>Every process has only 1 StandardLogger
	 * @return instance of StandardLogger
	 */
	public static StandardLogger getInstance()
	{
		if(logger == null)
			logger = new StandardLogger();
		return logger;
	}
	
	/**
	 * Constructs logger to standard IO.
	 */
	private StandardLogger()
	{
		System.out.println("");
	}
	
	/**
	 * Print general log
	 * @param msg
	 */
	public void printLog(String msg)
	{
		System.out.println("GENERAL: " + msg);
	}
	
	/**
	 * Print general error
	 * @param err
	 */
	public void printError(String err)
	{
		System.err.println("ERROR: " + err);
	}
	
	/**
	 * Outputs ordinary log of Crawler to standard IO
	 * @param msg log entry
	 */
	public void printCrawlerLog(String msg)
	{
		System.out.println("CRAWLER: " + msg);
	}
	
	/**
	 * Outputs error of Crawler to standard IO
	 * @param err error message
	 */
	public void printCrawlerError(String err)
	{
		System.err.println("CRAWLER: " + err);
	}
	
	
}

