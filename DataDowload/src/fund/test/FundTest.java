package fund.test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import fund.model.Fund;
import fund.model.History;
import fund.model.Record;

public class FundTest {

	private Fund fund;
	private ArrayList<Record> array;
	
	@Before
	public void setUp() {
		fund = new Fund("000001");
	}
	
	@Test
	public void test() {
		assertTrue(fund.getName().equals("���ĳɳ�֤ȯͶ�ʻ���"));
		assertTrue(fund.getType().equals("�����"));
		array = fund.getRecordArray();
		assertEquals(array.size(), 2997);
	}

}
