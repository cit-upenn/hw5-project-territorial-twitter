import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * This test will write the query values into a new js file
 * 
 * @author weiyin
 *
 */
public class JSWriterTester {
	
	
	
	public static void main(String[] args) {

		try {
			File inputFile = new File("us-states-tweets.js");
			
			Scanner in = new Scanner(inputFile);
			
			PrintWriter out = new PrintWriter("us-states-tweets-done.js");
			
			StateTweetTracker parsedTweets = new StateTweetTracker();
			
			JSWriter jsw = new JSWriter(parsedTweets, "str1", "str2");
			
			while (in.hasNextLine()) {
				String line = in.nextLine();
					
				line = jsw.writeJS(line);
				
				out.println(line);
				
			}
			
			in.close();
			out.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
