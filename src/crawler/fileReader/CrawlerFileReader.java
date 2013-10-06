package crawler.fileReader;

public abstract class CrawlerFileReader
{
	public String type;	// plain text, pdf, doc, html, etc.
	
	public abstract String readAll();
}
