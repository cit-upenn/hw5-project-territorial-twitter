import static org.junit.Assert.*;
import org.junit.Test;

public class SearchTester {

	@Test
	public void testGeoSearch() {
		Search geoSearch = new Search("nasa", 0.0, 0.0, 100.0, 1);
		assertNotNull(geoSearch);
	}
	
	@Test
	public void testSearchQuery() {
		Search search = new Search("nasa", 1);
		assertTrue(search.query().size() >= 0);
	}
	
	@Test
	public void testSearchGetSearchLimit() {
		Search search = new Search("nasa", 1);
		assertTrue(search.getSearchLimit(true) >= 0 && search.getSearchLimit(false) <= 480);
	}

}
