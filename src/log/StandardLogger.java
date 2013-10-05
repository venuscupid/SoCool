/**
 * StandardLogger.java
 */

package log;

/**
 * This is in charge of output log message to standard IO.
 * 
 * <p>Use <b>Initialization-on-demand holder idiom</b> to implement <b>thread-safe lazy init Singleton</b>
 */
public class StandardLogger
{
	/**
	 * Constructor, but is not visible to public
	 */
	private StandardLogger(){}
	
	private static class LazyHolder
	{
		private static final StandardLogger instance = new StandardLogger();
	}
	
	/**
	 * Returns instance of StandardLogger.
	 * <p>Every process has only 1 StandardLogger
	 * @return instance of StandardLogger
	 */
	public static StandardLogger getInstance()
	{
		return LazyHolder.instance;	// only getInstance() is called, the singleton instance is created
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

