
public class USAState {
	
	private String name;
	private int query1;
	private int query2;
	
	public USAState(String name) {
		this.name = name;
		query1 = 0;
		query2 = 0;
	}
	
	public void incrementQuery(int queryIndex) {
		if(queryIndex == 1) {
			query1 += 1;
		} else {
			query2 += 1;
		} 
		
	}
	
	public String getName() {
		return name;
	}
	
	public int getQuery(int queryIndex) {
		if(queryIndex == 1) {
			return query1;
		} else {
			return query2;
		} 
	}
	
	
}
