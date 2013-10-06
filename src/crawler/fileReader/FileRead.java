package crawler.fileReader;

/**
 * Interface for all crawler file readers
 *
 */
public interface FileRead
{
	/**
	 * read the file
	 * @return true if read
	 */
	public abstract boolean readFile();
}
