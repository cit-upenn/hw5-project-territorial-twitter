import static org.junit.Assert.*;
import org.junit.Test;

public class ConnectTester {

	@Test
	public void testGetTwitter() {
		assertNotNull(Connect.getTwitter());
	}

}
