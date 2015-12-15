import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * This class writes the query values into a new js file
 * @author weiyin
 *
 */
public class JSWriter {
	
	String[] states = { "Alabama", "Alaska", "Arizona", "Arkansas", "California", "Colorado", 
			"Connecticut", "Delaware", "Florida", "Georgia", "Hawaii", "Idaho", "Illinois", 
			"Indiana", "Iowa", "Kansas", "Kentucky", "Louisiana", "Maine", "Maryland", "Massachusetts", 
			"Michigan", "Minnesota", "Mississippi", "Missouri", "Montana", "Nebraska", "Nevada", 
			"New Hampshire", "New Jersey", "New Mexico", "New York", "North Carolina", "North Dakota", 
			"Ohio", "Oklahoma", "Oregon", "Pennsylvania", "Rhode Island", "South Carolina",
			"South Dakota", "Tennessee", "Texas", "Utah", "Vermont", "Virginia", "Washington", 
			"West Virginia", "Wisconsin", "Wyoming" };
	private StateTweetTracker stt;
	String firstTerm;
	String secondTerm;
	
	/**
	 * The constructor for the JSWriter class
	 * @param stt input StateTweetTracker object
	 * @param firstTerm the first search term
	 * @param secondTerm the second search term
	 */
	public JSWriter(StateTweetTracker stt, String firstTerm, String secondTerm){
//		The class takes in inputs from both the parser class and user input in order to edit the .js file
		this.stt =stt;
		this.firstTerm = firstTerm;
		this.secondTerm = secondTerm;
	}
	/**
	 * This method finds the state name in a text file and edits its tweet data based on values
	 * from that particular state
	 * @param line the line of text scanned by outJS
	 * @return line the line of text with "ratio", "q1", "q2", "qc1", and "qc2" edited by corresponding value
	 */
	public String writeJS(String line){
//		Most lines in the .js file contains data for a state. Therefore, the state name is the first string to check for
		for(int i = 0; i < states.length; i++ ){
//			When a state is identified, query data from StateTweetTracker is pulled
			if(line.contains(states[i])){
//				System.out.println(states[i]);
				double qc1 = stt.getQuery1Count(states[i]);
				double qc2 = stt.getQuery2Count(states[i]);
//				qcomp is the ratio between the two tweets
				double qcomp = qc1/qc2;
//				If qc1 or qc2 is 0, the qcomp is set to 0 for q1 in terms of map colors (i.e. black)
//				we assume the data is inconclusive if either of the queries has 0 counts
				if(qc2 == 0){
					qcomp = 0;
				}
				qcomp = Math.round(qcomp*100.0)/100.0;
				String comp = String.valueOf(qcomp);
//				the double values are converted to int for display on the map
				int qci1 = (int) qc1;
				int qci2 = (int) qc2;
//				The string "ratio" is replaced with the query value and "q1" and "q2" are replaced with the search terms
//				"qc1" and "qc2" are replaced by the actual counts from the parser
				if (line.contains("ratio")){
//					System.out.println("success!");
					line = line.replaceAll("ratio", comp);
					line = line.replaceAll("q1", "\"" + firstTerm + "\"");
					line = line.replaceAll("qc1", "\"" + qci1 + "\"");
					line = line.replaceAll("q2", "\"" + secondTerm + "\"");
					line = line.replaceAll("qc2", "\"" + qci2 + "\"");
			    return line;
			    } 
			}
		}
	    return line;
	}
	
	/**
	 * This method takes an input file called "us-states-tweets.js" and calls writeJS to overwrite the instances of 
	 * "ratio", "q1", "q2", "qc1", and "qc2". The result is a text-output file called "us-states-tweets-done.js"
	 */
	public void outJS(){
		try {
//			The .js file is defined since the class will only be handling this particular file
			File inputFile = new File("us-states-tweets.js");
			Scanner in = new Scanner(inputFile);
//			The output is saved in a different file to avoid overwriting
			PrintWriter out = new PrintWriter("us-states-tweets-done.js");
//			Each line in the .js file is iterated through to find the keywords and edited accordingly
			while (in.hasNextLine()) {
				String line = in.nextLine();	
				line = writeJS(line);
				out.println(line);	
			}		
			in.close();
			out.close();
			
		} catch (Exception e) {
//			If an exception is encountered, a message is displayed to the user and the program shuts down
			System.out.println("Error! Make sure us-states-tweets.js is in directory!");
			System.exit(0);
		}
	}
}
