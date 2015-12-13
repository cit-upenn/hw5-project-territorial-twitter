import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Holds a Hashmap of State names and USAState objects.
 * 
 * @author brendonlavernia
 *
 */
public class StateTweetTracker {
	private Map<String, USAState> mapOfStates;

	/**
	 * The constructor for this class.
	 */
	public StateTweetTracker() {
		mapOfStates = new HashMap<String, USAState>();
		createStatesList();
	}

	/**
	 * Returns a USAState object for the specified state name
	 * @param state the state name to return
	 * @return USAState object
	 */
	public USAState getState(String state) {
		return mapOfStates.get(state);
	}

	/**
	 * Gets the number of the first search term results associated with the
	 * specified state name
	 * @param state the state name to lookup
	 * @return the number of first search term hits
	 */
	public int getQuery1Count(String state) {
		return getState(state).getQueryCount(1);
	}

	/**
	 * Gets the number of the second search term results associated with the
	 * specified state name
	 * @param state the state name to lookup
	 * @return the number of second search term hits
	 */
	public int getQuery2Count(String state) {
		return getState(state).getQueryCount(2);
	}

	/**
	 * Gets the list of TaggedStatus objects associated with the specified state
	 * @param state the state name to lookup
	 * @return all TaggedStatus objects in that state
	 */
	public List<TaggedStatus> getTweets(String state) {
		return getState(state).getTweets();
	}

	/**
	 * Gets the list of state names
	 * @return the list of states
	 */
	public Set<String> getStates() {
		return mapOfStates.keySet();
	}

	/**
	 * Adds another StateTweetTracker to this object. Joins multiple searches
	 * into one.
	 * 
	 * @param parsedTweets the StateTweetTracker to add
	 * @param queryIndex the search index (1 or 2) data that the other 
	 * StateTweetTracker holds
	 */
	public void addParsedTweets(StateTweetTracker parsedTweets, int queryIndex) {
		for (String otherState : parsedTweets.getStates()) {
			int count = 0;
			if (queryIndex == 2) {
				count = parsedTweets.getQuery1Count(otherState);
			} else {
				count = parsedTweets.getQuery2Count(otherState);
			}

			for (int i = 0; i < count; i++) {
				this.getState(otherState).incrementQuery(queryIndex);
			}
			this.getState(otherState).addTaggedStatusList(parsedTweets.getTweets(otherState));

		}
	}

	/**
	 * Initializes the hashmap with state names.
	 */
	private void createStatesList() {
		String[] states = { "Alabama", "Alaska", "Arizona", "Arkansas", "California", "Colorado", "Connecticut",
				"Delaware", "Florida", "Georgia", "Hawaii", "Idaho", "Illinois", "Indiana", "Iowa", "Kansas",
				"Kentucky", "Louisiana", "Maine", "Maryland", "Massachusetts", "Michigan", "Minnesota", "Mississippi",
				"Missouri", "Montana", "Nebraska", "Nevada", "New Hampshire", "New Jersey", "New Mexico", "New York",
				"North Carolina", "North Dakota", "Ohio", "Oklahoma", "Oregon", "Pennsylvania", "Rhode Island",
				"South Carolina", "South Dakota", "Tennessee", "Texas", "Utah", "Vermont", "Virginia", "Washington",
				"West Virginia", "Wisconsin", "Wyoming" };

		for (String state : states) {
			if (!mapOfStates.containsKey(state)) {
				mapOfStates.put(state, new USAState(state));
			}
		}
	}
}
