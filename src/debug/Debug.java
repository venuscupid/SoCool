package debug;

/**
 * Debug class
 * 
 * @author yaoyao
 *
 */
public class Debug
{
	/**
	 * Print the debug message to standard IO
	 * @param msg
	 */
	public static void print(String msg)
	{
		StringBuffer message = new StringBuffer();
		message.append(">>>>>>>>>>>>>>> debug >>>>>>>>>>>>>>>\n");
		message.append(msg).append("\n<<<<<<<<<<<<<<< debug end <<<<<<<<<<<<<<<\n");
		System.out.println(message.toString());
	}
	
	/**
	 * Print the debug message to standard IO, with time stamp
	 * @param msg
	 * @param useTimestamp if true, include the current timestamp in the debug message
	 */
	public static void print(String msg, boolean useTimestamp)
	{
		StringBuffer message = new StringBuffer();
		message.append(">>>>> debug >>>>> ");
		if(useTimestamp)
			message.append(System.currentTimeMillis());
		message.append(msg).append("\n<<<<< debug end <<<<<\n");
		System.out.print(message.toString());
	}

}
