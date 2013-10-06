package crawler;

import java.io.File;

import os.OSInfo;
import common.DateTime;
import common.FileType;
import log.StandardLogger;

/**
 * File and directory explorer on disks
 * @author yaoyao
 *
 */
public class DiskCrawler extends AbstractCrawler
{
	private long startTime;
	
	private long stopTime;
	
	private File filePath;
	
	private long dirCount = 0;
	
	private long fileCount = 0;
	
	CrawlerStatus status = CrawlerStatus.NONE;
	
	StandardLogger stdLogger = StandardLogger.getInstance();
	
	FileReaderManager fileReaderManager;
	
	
	public DiskCrawler()
	{
		fileReaderManager = FileReaderManager.createManager();
	}
	
	/**
	 * Initialize the crawler
	 * @param fileDirName name of the file or directory
	 * @return true if the path exists; false otherwise
	 */
	public boolean initialize(String path)
	{
		filePath = new File(path);
		
		if (filePath.exists()){
			status = CrawlerStatus.INITIALIZED;
			return true;
		}
		else
			return false;
	}
	
	/**
	 * TODO start the thread
	 * @return
	 */
	@Override
	public boolean start()
	{
		if(status != CrawlerStatus.INITIALIZED) return false;	// quit if not initialized
		status = CrawlerStatus.RUN;
		
		startTime = System.currentTimeMillis();
		
		stdLogger.printCrawlerLog("Operating System: " + OSInfo.getOsFullInfo());
		stdLogger.printCrawlerLog("Start crawling path: " + filePath.getAbsolutePath());
		stdLogger.printCrawlerLog("The inner-most dir: " + (filePath.getAbsolutePath().substring(filePath.getAbsolutePath().lastIndexOf(OSInfo.getFileSeparator()))));
		
		stdLogger.printCrawlerLog("Start at: " + DateTime.getDateTime(startTime));
		
		execute(filePath);
		
		return true;
	}
	
	private boolean execute(File fileDir)
	{
		int i;
		
		if(fileDir.isFile()){
			stdLogger.printCrawlerLog("file: " + fileDir.getName());
			stdLogger.printCrawlerLog("-- file type: " + FileType.getFileType(fileDir));
			fileCount ++;
		}
		else if(fileDir.isDirectory()){
			dirCount ++;
			stdLogger.printCrawlerLog("directory: " + fileDir.getPath());
			File[] files = fileDir.listFiles();
			for(i = 0; i < files.length; i ++)
				execute(files[i]);
		}
		
		return true;
	}
	
	
	/**
	 * TODO pause the thread
	 * @return
	 */
	@Override
	public boolean pause()
	{
		if(status != CrawlerStatus.RUN) return false;	// quit if not started
		status = CrawlerStatus.PAUSED;
		
		return true;
	}
	
	@Override
	public boolean resume()
	{
		if(status != CrawlerStatus.PAUSED) return false;
		status = CrawlerStatus.RUN;
		
		return true;
	}
	
	/**
	 * TODO stop the thread
	 * @return
	 */
	@Override
	public boolean stop()
	{
		if((status != CrawlerStatus.RUN) && (status != CrawlerStatus.PAUSED)) return false;	// quit if not started or paused
		status = CrawlerStatus.STOPPED;
		
		stopTime = System.currentTimeMillis();
		
		stdLogger.printCrawlerLog("Number of dirs (include self): " + dirCount);
		stdLogger.printCrawlerLog("Number of files: " + fileCount);
		stdLogger.printCrawlerLog("Stop at: " + DateTime.getDateTime(stopTime));
		return true;
	}
	
	/**
	 * Override the finalize()
	 * TODO
	 */
	@Override
	public void finalize()
	{
		stop();
	}
	
	/**
	 * restart the crawler
	 * TODO
	 */
	@Override
	public boolean restart()
	{
		if(status == CrawlerStatus.NONE) return false;	// must be initialized first
		
		return true;
	}
	
	/**
	 * When the crawler meets a file, it will (use a new thread FileReader) issue the task of read file and continue crawling
	 */
	public boolean readFile(String fileName)
	{
		// TODO Auto-generated method stub
		return false;
	}
	
	
	//================== test ============================
	public static void main(String[] args)
	{
		String filePath = "/home/yaoyao/test";
		
		DiskCrawler crawler = new DiskCrawler();
		crawler.initialize(filePath);
		crawler.start();
		crawler.stop();
	}

	

}
