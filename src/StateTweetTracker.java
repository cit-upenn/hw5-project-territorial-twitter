import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StateTweetTracker {
	private Map<String, USAState> mapOfStates;
	
	public StateTweetTracker() {
		mapOfStates = new HashMap<String, USAState>();
		createStatesList();
	}
	
	public USAState getState(String state) {
		return mapOfStates.get(state);
	}
	
	public int getQuery1Count(String state) {
		return getState(state).getQueryCount(1);
	}
	
	public int getQuery2Count(String state) {
		return getState(state).getQueryCount(2);
	}
	
	public List<TaggedStatus> getTweets(String state) {
		return getState(state).getTweets();
	}
	
	private void createStatesList() {
		String[] states = { "Alabama", "Alaska", "Arizona", "Arkansas", "California", "Colorado", 
				"Connecticut", "Delaware", "Florida", "Georgia", "Hawaii", "Idaho", "Illinois", 
				"Indiana", "Iowa", "Kansas", "Kentucky", "Louisiana", "Maine", "Maryland", "Massachusetts", 
				"Michigan", "Minnesota", "Mississippi", "Missouri", "Montana", "Nebraska", "Nevada", 
				"New Hampshire", "New Jersey", "New Mexico", "New York", "North Carolina", "North Dakota", 
				"Ohio", "Oklahoma", "Oregon", "Pennsylvania", "Rhode Island", "South Carolina",
				"South Dakota", "Tennessee", "Texas", "Utah", "Vermont", "Virginia", "Washington", 
				"West Virginia", "Wisconsin", "Wyoming" };

		for (String state : states) {
			if (!mapOfStates.containsKey(state)) {
				mapOfStates.put(state, new USAState(state));
			}
		}
	}
}
