package common;

import java.io.File;
import java.util.HashMap;

import os.OSInfo;

public class FileType
{	
	private static HashMap<String, Long> typeMap = null;
	

	/**
	 * ERROR
	 */
	public static final long ERROR = -3;
	
	/**
	 * the file name is not valid
	 */
	public static final long INVALID_NAME = -2;
	
	/**
	 * not a file
	 */
	public static final long NON_FILE = -1;
	
	/**
	 * No type, e.g. a file without a extended name
	 */
	public static final long NO_TYPE = 0;
	
	public static final long OTHER_TYPE = 999999;
	
	// ============== text files =================
	public static final long TXT = 1;
	
	
	// ============== web files =================
	public static final long HTM = 1000;
	
	public static final long HTML = 1001;
	
	
	// ============== pdf files =================
	public static final long PDF = 2000;
	
	// ============== MS office files =================
	public static final long DOC = 3000;
	
	public static final long DOCX = 3001;
	
	
	// ============== source code files =================
	public static final long JAVA = 10000;
	
	
	
	/**
	 * Get the type of the file in the path given
	 * @param filePath
	 * @return the constants standing for the type
	 */
	public static synchronized long getFileType(File filePath)
	{
		if(typeMap == null) constructTypeMap();
		
		File file = filePath;
		String fileName;
		int posLastFileSeparator;
		
		// if not a file, return NON_FILE
		if(!file.isFile()) return NON_FILE;
		
		// get file name in lower case
		fileName = file.getAbsolutePath().toLowerCase();
		posLastFileSeparator = fileName.lastIndexOf(OSInfo.getFileSeparator());
		if (posLastFileSeparator >= fileName.length() - 1) return INVALID_NAME;	// incorrect file name
		
		fileName = fileName.substring(posLastFileSeparator + 1);
		
		return parseType(fileName);
	}
	
	private static long parseType(String fileName)
	{
		int posLastDot;
		String extendedName;
		posLastDot = fileName.lastIndexOf(CommonConstants.DOT);
		
		if(posLastDot < 0) return NO_TYPE;
		if(posLastDot >= fileName.length() - 1) return NO_TYPE;
		
		extendedName = fileName.substring(posLastDot + 1);
		
		return convertType(extendedName);
	}
	
	private static long convertType(String extendedName)
	{
		extendedName = extendedName.toLowerCase();
		
		if(typeMap == null) return ERROR;
		
		if(!typeMap.containsKey(extendedName)) return OTHER_TYPE;	// file type not in the map
		
		return typeMap.get(extendedName);
	}
	
	/**
	 * Construct the hashmap for file type ExtendedName String -> Long
	 */
	private static void constructTypeMap()
	{
		typeMap = new HashMap<String, Long>();
		typeMap.put("txt", TXT);
		typeMap.put("htm", HTM);
		typeMap.put("html", HTML);
		typeMap.put("pdf", PDF);
		typeMap.put("doc", DOC);
		typeMap.put("docx", DOCX);
		typeMap.put("java", JAVA);
	}
}
