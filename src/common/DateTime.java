package common;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTime
{
	/**
	 * Format of the date time used
	 * SSS is millisecond
	 */
	private final static String strDateFormat = "yyyy.MM.dd-HH:mm:ss::SSS";
	protected final static SimpleDateFormat dateFormat = new SimpleDateFormat(strDateFormat);
	
	public static String getDateTime(long timeMilliSec)
	{
		return dateFormat.format(new Date(timeMilliSec));
		
//		try{
//			dateTime = dateFormat.format(dateFormat.parse(timeMilliSec + ""));
//		}catch(ParseException pe){
//			// no err msg for now
//		}
	}

}
