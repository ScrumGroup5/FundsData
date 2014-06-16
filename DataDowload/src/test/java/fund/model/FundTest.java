package fund.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class FundTest {

	private Fund fund;
	private List<Record> array;

	@Before
	public void setUp() {
		fund = new Fund("000001");
	}

	@Test
	public void fundGetterTest() {
		assertTrue(fund.getName().equals("华夏成长证券投资基金"));
		assertTrue(fund.getType().equals("混合型"));
		array = fund.getRecordList();
		assertEquals(array.size(), 2997);
	}

}
