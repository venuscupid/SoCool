package crawler;

/**
 * An abstract crawler is generic.
 * It can be a hard disk crawler, or a web crawler.
 * @author yaoyao
 *
 */
public abstract class AbstractCrawler
{
	public abstract boolean start();
	
	public abstract boolean pause();
	
	public abstract boolean resume();
	
	public abstract boolean stop();
	
	public abstract boolean restart();
}
