import static org.junit.Assert.*;
import org.junit.Test;

public class SearchTester {

	@Test
	public void testGeoSearch() {
		Search geoSearch = new Search("nasa", 0.0, 0.0, 100.0, 1);
		assertNotNull(geoSearch);
	}
	
	@Test
	public void testSearch() {
		Search search = new Search("nasa", 1);
		assertNotNull(search);
	}
	
	@Test
	public void testSearchQuery() {
		Search search = new Search("nasa", 1);
		assertNotNull(search.query());
	}
	
	@Test
	public void testSearchGetSearchLimit() {
		Search search = new Search("nasa", 1);
		assertTrue(search.getSearchLimit(true) >= 0 && search.getSearchLimit(false) <= 480);
	}

}
