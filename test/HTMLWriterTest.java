import static org.junit.Assert.assertNotNull;


import org.junit.Test;



/**
 * This class is the JUnit test for HTMLWriter
 * @author weiyin
 *
 */
public class HTMLWriterTest {
	
	@Test
	public void HTMLWriterNotNull() {
		HTMLWriter html = new HTMLWriter("test1", "test2");
		assertNotNull("HTMLWriter cannot be null", html);
		
	}
	
	@Test
	public void writeJSNotNull() {
		HTMLWriter html = new HTMLWriter("test1", "test2");
		String line = "This is a test String";
		html.writeHTML(line);
		assertNotNull("writeHTML cannot be null", html.writeHTML(line));
		
	}
	
	@Test
	public void outHTMLWorks() {
		HTMLWriter html = new HTMLWriter("test1", "test2");
		html.outHTML();	
	}
}
