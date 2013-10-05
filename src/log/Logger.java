package log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import common.DateTime;

/**
 * Logger for the program
 * @author yaoyao
 *
 */
public abstract class Logger
{
	/**
	 * version infomration
	 */
	protected final String VERSION_INFO = "SoCool, Version: " + common.ProjectInfo.VERSION;
	
	/**
	 * The file of the log
	 */
	protected File logFile;
	
	/**
	 * This is the stream that the log should print to.
	 */
	protected PrintStream out;
	
	/**
	 * The start time in millisecond when the logger starts logging.
	 */
	protected long startTime;
	
	/**
	 * The time in millisecond when the logger stops logging
	 */
	protected long stopTime;
	
	/**
	 * Added at the beginning at each comment
	 */
	protected final String COMMENT_START = "# ";
	
	/**
	 * Added at the beginning at each error message
	 */
	protected final String ERROR_START = "!!! ERROR !!! -";
	
	/**
	 * The token to separate the segments in a log entry
	 */
	protected final String SEPARATOR = "|";
	
	/**
	 * Format of the date time in the log
	 * moved to common.DateTime
	 */
	// protected final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd-HH:mm:ss");
	
	/**
	 * Constructs a new Logger
	 * @param fileName the name of the output log file
	 * @throws IOException
	 */
	public Logger(String fileName) throws IOException
	{
		startTime = System.currentTimeMillis();
		logFile = new File(fileName);
		out = new PrintStream(new FileOutputStream(logFile));
	}
	
	/**
	 * Returns absolute path of the log file
	 * @return file name of the log
	 */
	public String getLogFileName()
	{
		return logFile.getAbsolutePath();
	}
	
	/**
	 * Close log file before the object is disposed.
	 */
	@Override
	protected void finalize()
	{
		stopLogging();
		closeFile();
	}
	
	protected void stopLogging()
	{
		stopTime = System.currentTimeMillis();
		
		String s = "#======================================#\n" +
		        "Stop time: " + DateTime.getDateTime(stopTime) + 
		        "\n#======================================#";
		out.println(s);
	}
	
	/**
	 * Closes the log file
	 */
	private void closeFile()
	{
		synchronized(out){
			out.flush();
			out.close();
		}
	}
	
	/**
	 * Appends the entry to the log file
	 * @param entry
	 *            the content to be written into the log file
	 */
	public synchronized void printEntry(String entry)	// this method should be applicable in multi-thread logging.
	{
		long curTime = System.currentTimeMillis();
		// String datetime = dateFormat.format(new Date(System.currentTimeMillis()));	// current time
		String dateTime = "";
		dateTime = DateTime.getDateTime(curTime);
		
//		try{
//			dateTime = dateFormat.format(dateFormat.parse(curTime + ""));
//		}catch(ParseException pe){
//			// no err msg for now
//		}
		String logEntry = dateTime + SEPARATOR + entry;
		
		out.println(logEntry);
	}
	
	/**
	 * Appends the comment to the log file
	 * @param comment
	 *            the comment to be written into the log file
	 */
	public synchronized void printComment(String comment)
	{
		if(comment == null || comment.equals(""))
			return;
		
		long curTime = System.currentTimeMillis();
		String dateTime = DateTime.getDateTime(curTime);
		
		String head = COMMENT_START + dateTime + SEPARATOR;	// current time
		
		if(comment.contains("\n"))
		{
			String[] split = comment.split("\n");
			for(String subcomment : split)
				out.println(head + subcomment);
		}
		else
		{
			out.println(head + comment);
		}
	}
	
	/**
	 * Appends the error message to the log file
	 * @param errMessage
	 *            the error message to be written into the log file
	 */
	public synchronized void printError(String errMessage)
	{
		long curTime = System.currentTimeMillis();
		String dateTime = "";
		dateTime = DateTime.getDateTime(curTime);
//		try{
//			dateTime = dateFormat.format(dateFormat.parse(curTime + ""));
//		}catch(ParseException pe){
//			// no err msg for now
//		}

		String logErrEntry = ERROR_START + dateTime + SEPARATOR + errMessage;
		
		out.println(logErrEntry);
	}
	
	/**
	 * Add the version information of the system to the log file
	 */
	public synchronized void addVersionInfo()
	{
		String s = "#======================================#\n#" +
        VERSION_INFO + "\n" + 
        "Start time: " + DateTime.getDateTime(startTime) + 
        "\n#======================================#";
		out.println(s);
	}
}
