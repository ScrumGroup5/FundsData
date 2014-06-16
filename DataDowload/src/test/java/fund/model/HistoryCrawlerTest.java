package fund.model;

import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

public class HistoryCrawlerTest {

	@Before
	public void setUp() {
		new HistoryCrawler("000001").crawl();
	}

	@Test
	public void loadJzTest() {
		try {
			BufferedReader br = new BufferedReader(new FileReader(new File(
					DataUtil.getRecordFilePath("000001"))));
			br.readLine();
			String str1 = "2002-03-04 1.0130 1.0130 0.20% 开放申购 开放赎回 null  ";
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
	
	@Test
	public void loadThTest() {
		try {
			BufferedReader br = new BufferedReader(new FileReader(new File(
					DataUtil.getRecordFilePath("000001"))));
			String line = br.readLine();
			String str = "净值日期 单位净值（元） 累计净值（元） 日增长率 申购状态 赎回状态 分红送配 ";
			assertTrue(line.equals(str));
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
