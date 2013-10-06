package crawler.fileReader;

/**
 * Interface for all crawler file readers
 *
 */
public interface FileRead
{
	/**
	 * read the whole file
	 * @return the content of the whole file
	 */
	public abstract String readAll();
}
