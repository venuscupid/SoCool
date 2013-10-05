package os;

/**
 * Get the information of the Operating System
 * 
 * @author yaoyao
 *
 */
public class OSInfo
{
	private static String osName = System.getProperty("os.name").toLowerCase();
	
	public static String getOsName()
	{
		return osName;
	}
	
	public static String getOsArch()
	{
		return System.getProperty("os.arch");
	}
	
	public static String getOsVersion()
	{
		return System.getProperty("os.version");
	}
	
	public static String getOsFullInfo()
	{
		StringBuffer info = new StringBuffer();
		info.append(osName).append(" ").append(System.getProperty("os.arch")).append(" ").append(System.getProperty("os.version"));
		
		return info.toString();
	}
	
	public static boolean isLinux(){
        return osName.indexOf("linux") >= 0;
    }
	
	public static boolean isMacOS(){
        return (osName.indexOf("mac") >= 0) && (osName.indexOf("os") > 0) && (osName.indexOf("x") < 0);  
    }  
      
    public static boolean isMacOSX(){
        return (osName.indexOf("mac") >= 0) && (osName.indexOf("os") > 0) && (osName.indexOf("x") > 0);  
    }
      
    public static boolean isWindows(){  
        return (osName.indexOf("windows") >= 0);
    }
}
