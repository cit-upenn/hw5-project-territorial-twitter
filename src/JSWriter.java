
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
	
	StateTweetTracker stt = new StateTweetTracker();
	
	public JSWriter(){
		
	}
	public String writeJS(String line){
		for(int i = 0; i < states.length; i++ ){
			if(line.contains(states[i])){
				System.out.println(states[i]);
				int q1 = stt.getQuery1Count(states[i]);
				int q2 = stt.getQuery2Count(states[i]);
				int qcomp = q1-q2;
				String comp = String.valueOf(qcomp);
//				For testing: String comp = "12";
				if (line.contains("counts")){
					System.out.println("success!");
					line = line.replaceAll("counts", comp);
	
			    return line;
			    } 
			}
		}
	    return line;

		
	}
}
