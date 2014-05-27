package fund.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class FundTest {

	private Fund fund;
	private ArrayList<Record> array;

	@Before
	public void setUp() {
		fund = new Fund("000001");
	}

	@Test
	public void test() {
		assertTrue(fund.getName().equals("华夏成长证券投资基金"));
		assertTrue(fund.getType().equals("混合型"));
		array = fund.getRecordArray();
		assertEquals(array.size(), 2997);
	}

}
