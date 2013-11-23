package crawler;

/**
 * An abstract crawler is generic.
 * It can be a hard disk crawler, or a web crawler.
 * @author yaoyao
 *
 */
public abstract class AbstractCrawler
{
	protected CrawlerStatus status;
	
	public abstract boolean canInitialize();
	public abstract boolean initialize();
	
	public abstract boolean canStart();
	public abstract boolean start();
	
	public abstract boolean canPause();
	public abstract boolean pause();
	
	public abstract boolean resume();
	public abstract boolean canResume();
	
	public abstract boolean stop();
	public abstract boolean canStop();
	
	public abstract boolean restart();
	public abstract boolean canRestart();
}
