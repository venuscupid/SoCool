package crawler;

import java.util.Hashtable;
import java.util.Set;

import crawler.fileReader.CrawlerFileReader;
import common.FileType;

/**
 * Every crawler has a file reader manager.
 * Provide and maintain CrawlerFileReader for the crawler.
 * Do not name it as "***Service" because there is no standalone thread for it.
 * 
 * @author yaoyao
 *
 */
public class FileReaderManager
{
	private FileReaderManager(){}
	
	private Hashtable<FileType, Set> readerPool;
	
	/**
	 * Create and return an instance of FileReaderManager
	 * @return
	 */
	public static FileReaderManager createManager()
	{
		FileReaderManager manager = null;
		
		return manager;
	}
	
	/**
	 * recycle and destroy all the file readers managed by this object.
	 * TODO
	 */
	@Override
	public void finalize()
	{
		
	}
	
	public CrawlerFileReader getFileReader(String fileName)
	{
		CrawlerFileReader reader = null;
		
		return reader;
	}
}
