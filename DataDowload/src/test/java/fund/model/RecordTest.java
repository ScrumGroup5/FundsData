package fund.model;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class RecordTest {
	private Record record;

	@Before
	public void setUp() {
		record = new Record("2014-05-23", "1.0540", "3.1550", "0.67%",
				"限制大额申购", "开放赎回", "null");
	}

	@Test
	public void test() {
		assertTrue(record.getBonus().equals("null"));
	}

}
