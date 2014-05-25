package fund.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import fund.model.History;
import fund.model.Record;

public class RecordTest {
	private Record record;
	
	@Before
	public void setUp() {
		record = new Record("2014-05-23", "1.0540", "3.1550", "0.67%", "���ƴ���깺", "�������", "null");
	}
	@Test
	public void test() {
		assertTrue(record.getBonus().equals("null"));
	}

}
