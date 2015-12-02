import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONException;

public class TweetParser {
	private Map<String, USAState> mapOfStates;
	private List<GeoTaggedTweet> tweets;
	
	public TweetParser(List<GeoTaggedTweet> tweets) {
		mapOfStates = new HashMap<String, USAState>();
		this.tweets = tweets;
	}
	
	public void createStatesList() {
		String[] states = { "Alabama", "Alaska",  "Arizona",  "Arkansas",  "California",  
				"Colorado",  "Connecticut",  "Delaware",  "Florida",  "Georgia",  "Hawaii",  
				"Idaho",  "Illinois Indiana",  "Iowa",  "Kansas",  "Kentucky",  "Louisiana",  
				"Maine",  "Maryland",  "Massachusetts",  "Michigan",  "Minnesota",  
				"Mississippi",  "Missouri",  "Montana Nebraska",  "Nevada",  "New Hampshire", 
				"New Jersey",  "New Mexico",  "New York",  "North Carolina",  "North Dakota",  
				"Ohio",  "Oklahoma",  "Oregon",  "Pennsylvania Rhode Island",  
				"South Carolina",  "South Dakota",  "Tennessee",  "Texas",  "Utah",  
				"Vermont",  "Virginia",  "Washington",  "West Virginia",  "Wisconsin",  
				"Wyoming" };
		
		for(String state : states) {
			if(!mapOfStates.containsKey(state)) {
				mapOfStates.put(state, new USAState(state));
			}
		}
	}
	
	public Map<String, USAState> getStatesList() {
		return mapOfStates;
	}
	
	public void parseTweets() {
		
		try{
			for(GeoTaggedTweet tweet : tweets) {
				String state = (String) tweet.get("region");
				int queryIndex = tweet.getQueryIndex(); 
				if(!state.equals(null)) {
					setByRegion(state, queryIndex);
				}
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		
	}
	
	private void setByRegion(String state, int queryIndex) {
		if(mapOfStates.containsKey(state)) {
			mapOfStates.get(state).incrementQuery(queryIndex);
		} else if (mapOfStateSynonyms.contains(state)) {
			mapOfStates.get(mapOfStateSynonyms.get(state)).incrementQuery(queryIndex);
		}
		
	}

	
}
