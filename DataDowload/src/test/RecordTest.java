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
	public void recordGetterTest() {
		assertTrue(record.getDate().equals("2014-05-23"));
		assertTrue(record.getUnitNet().equals("1.0540"));
		assertTrue(record.getCumulateNet().equals("3.1550"));
		assertTrue(record.getDailyGrowthRate().equals("0.67%"));
		assertTrue(record.getPurchaseState().equals("限制大额申购"));
		assertTrue(record.getRedemptionState().equals("开放赎回"));
		assertTrue(record.getBonus().equals("null"));
	}

}
