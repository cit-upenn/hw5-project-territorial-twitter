import static org.junit.Assert.*;


import org.junit.Test;

public class USAStateTester {
	
	
	@Test
	public void testIsFlorida() {
		
		USAState states = new USAState("Florida");
		String name = states.getName();
		assertSame(name, "Florida");
	
	}
}
