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
	
	/**
	 * The constructor for the JSWriter class
	 * @param stt input StateTweetTracker object
	 */
	public JSWriter(StateTweetTracker stt){
		this.stt =stt;
		this.states=states;
	}
	/**
	 * This method overwrites the "counts" String in a text file with value of tweet counts
	 * from that particular state
	 * @param line the line of text scanned by outJS
	 * @return line the line of text with "counts" edited by value
	 */
	public String writeJS(String line){
		for(int i = 0; i < states.length; i++ ){
			if(line.contains(states[i])){
//				System.out.println(states[i]);
				double q1 = stt.getQuery1Count(states[i]);
				double q2 = stt.getQuery2Count(states[i]);
				double qcomp = q1/q2;
//				If q1 or q2 is 0, the qcomp is set to the maximum value for q1 in terms of map colors 
//				since no one in that state cares about the q2 search term
				if(q2 == 0){
					qcomp = 2;
				}
				qcomp = Math.round(qcomp*100.0)/100.0;
				String comp = String.valueOf(qcomp);
//				For testing: String comp = "12";
				if (line.contains("counts")){
//					System.out.println("success!");
					line = line.replaceAll("counts", comp);
	
			    return line;
			    } 
			}
		}
	    return line;
	}
	

	/**
	 * This method takes an input file called "us-states-tweets.js" and calls writeJS to overwrite the instances of "counts."
	 * The result is a text-output called "us-states-tweets-done.js"
	 */
	public void outJS(){
		try {
			File inputFile = new File("us-states-tweets.js");
			
			Scanner in = new Scanner(inputFile);
			
			PrintWriter out = new PrintWriter("us-states-tweets-done.js");
			
			
			while (in.hasNextLine()) {
				String line = in.nextLine();
					
				line = writeJS(line);
				
				out.println(line);
				
			}
			
			in.close();
			out.close();
			
		} catch (Exception e) {
			System.out.println("Error! Make sure us-states-tweets.js is in directory!");
			e.printStackTrace();
		}
	}
}
