package crawler;

import java.io.File;

import os.OSInfo;
import common.DateTime;
import common.FileType;
import common.config.Config;
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
	
	private String startPath;
	
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
	 * Set the path for the crawler to start crawling
	 * @param path name of the file or directory
	 */
	public void setStartPath(String path)
	{
		startPath = path;
	}
	
	/**
	 * Determine if the crawler can be initialized.
	 */
	@Override
	public boolean canInitialize()
	{
		if(status == CrawlerStatus.NONE) return true;
		return false;
	}
	
	/**
	 * Initialize the crawler
	 * @return true if the path exists; false otherwise
	 */
	@Override
	public boolean initialize()
	{
		if(!canInitialize()) return false;
		
		filePath = new File(startPath);
		
		if (filePath.exists()){
			status = CrawlerStatus.INITIALIZED;
			return true;
		}
		else
			return false;
	}
	
	/**
	 * Determine if the crawler can start
	 */
	@Override
	public boolean canStart()
	{
		if(status == CrawlerStatus.INITIALIZED) return true;
		return false;
	}
	
	/**
	 * TODO start the thread
	 * @return
	 */
	@Override
	public boolean start()
	{
		if(!canStart()) return false;	// quit if not initialized
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
	
	
	@Override
	public boolean canPause()
	{
		if(status == CrawlerStatus.RUN) return true;
		return false;
	}
	
	/**
	 * TODO pause the thread
	 * @return
	 */
	@Override
	public boolean pause()
	{
		if(!canPause()) return false;	// quit if not RUN
		status = CrawlerStatus.PAUSED;
		
		return true;
	}
	
	@Override
	public boolean canResume()
	{
		if(status == CrawlerStatus.PAUSED) return true;
		return false;
	}
	
	@Override
	public boolean resume()
	{
		if(!canResume()) return false;
		status = CrawlerStatus.RUN;
		
		return true;
	}
	
	@Override
	public boolean canStop()
	{
		if(status == CrawlerStatus.RUN) return true;
		if(status == CrawlerStatus.PAUSED) return true;
		
		return false;
	}
	
	/**
	 * TODO stop the thread
	 * @return
	 */
	@Override
	public boolean stop()
	{
		if(!canStop()) return false;	// quit if not started or paused
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
	
	
	@Override
	public boolean canRestart()
	{
		if(status == CrawlerStatus.STOPPED) return true;
		return false;
	}
	
	/**
	 * restart the crawler, only after stopped
	 * TODO
	 */
	@Override
	public boolean restart()
	{
		if(!canRestart()) return false;	// must be stopped before restart
		
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
		Config.readConfigFile("config.ini");
		
		String filePath = "/home/yaoyao/test";
		
		DiskCrawler crawler = new DiskCrawler();
		crawler.setStartPath(filePath);
		crawler.initialize();
		crawler.start();
		crawler.stop();
	}

	

}
