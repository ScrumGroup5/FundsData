package fund.model;

import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

public class HistoryTest {

	@Before
	public void setUp() {
		new HistoryCrawler("000001").crawl();
	}

	@Test
	public void test() {
		try {
			BufferedReader br = new BufferedReader(new FileReader(new File(
					DataUtil.getRecordFilePath("000001"))));
			br.readLine();
			String str1 = "2002-01-25 1.0050 1.0050 0.50% 封闭期 封闭期 null  ";
			String line1 = "", tmp;
			br.readLine();
			while ((tmp = br.readLine()) != null)
				line1 = tmp;
			assertTrue(line1.equals(str1));
			br.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
