package common;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTime
{
	/**
	 * Format of the date time used
	 */
	protected final static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd-HH:mm:ss");
	
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
