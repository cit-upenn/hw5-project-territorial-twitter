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
	
//	StateTweetTracker stt = new StateTweetTracker();
	
	public JSWriter(StateTweetTracker stt){
		this.stt =stt;
	}
	public String writeJS(String line){
		for(int i = 0; i < states.length; i++ ){
			if(line.contains(states[i])){
//				System.out.println(states[i]);
				double q1 = stt.getQuery1Count(states[i]);
				double q2 = stt.getQuery2Count(states[i]);
//				Need to decide on a better way to resolve divide by 0
				if(q2 == 0){
					q2 =1;
				}
				double qcomp = q1/q2;
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
			e.printStackTrace();
		}
	}
}
