import org.json.JSONObject;

public class GeoTaggedTweet extends JSONObject {
	private int queryIndex;
	
	public GeoTaggedTweet(int queryIndex) {
		this.queryIndex = queryIndex;
	}
	
	public int getQueryIndex() {
		return queryIndex;
	}
	
}
