import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;


/**
 * This class writes the search terms into the HTML file
 * @author weiyin
 *
 */
public class HTMLWriter {
	String firstTerm;
	String secondTerm;



	/**
	 * The constructor for the HTMLWriter class
	 * 
	 * @param firstTerm the first search term
	 * @param secondTerm the second search term
	 */
	public HTMLWriter(String firstTerm, String secondTerm){
	//	The class takes in inputs from both the user input in order to edit the .html file
		
		this.firstTerm = firstTerm;
		this.secondTerm = secondTerm;
	}
	/**
	 * This method finds the state name in a text file and edits its tweet data based on values
	 * from that particular state
	 * @param line the line of text scanned by outHTML
	 * @return line the line of text with "'searchTerm1'" and "'searchTerm2'" edited by corresponding value
	 */
	public String writeHTML(String line){
//		The search terms in the bottom right legend is replaced by the actual search terms
		if(line.contains("'searchTerm1'")){
			line = line.replaceAll("'searchTerm1'", "\"" + firstTerm + "\"");
		}
		if(line.contains("'searchTerm2'")){
			line = line.replaceAll("'searchTerm2'", "\"" + secondTerm + "\"");
		}
	
	    return line;
	}
	
	/**
	 * This method takes an input file called "Leaflet-embed.html" and calls writeJS to overwrite the instances of "counts."
	 * The result is a text-output called "Leaflet-embed-done.html"
	 */
	public void outHTML(){
		try {
	//		The .HTML file is defined since the class will only be handling this particular file
			File inputFile = new File("Leaflet-embed.html");
			Scanner in = new Scanner(inputFile);
	//		The output is saved in a different file to avoid overwriting
			PrintWriter out = new PrintWriter("Leaflet-embed-done.html");
	//		Each line in the .HTML file is iterated through to find the keywords and edited accordingly
			while (in.hasNextLine()) {
				String line = in.nextLine();	
				line = writeHTML(line);
				out.println(line);	
			}		
			in.close();
			out.close();
			
		} catch (Exception e) {
	//		If an exception is encountered, a message is displayed to the user and the program shuts down
			System.out.println("Error! Make sure Leaflet-embed.html is in directory!");
			System.exit(0);
		}
	}
}
