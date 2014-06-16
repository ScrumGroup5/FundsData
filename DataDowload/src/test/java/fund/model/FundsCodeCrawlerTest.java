package fund.model;

import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

public class FundsCodeCrawlerTest {

	@Before
	public void setUp() {
		FundsCodeCrawler.crawl();
	}

	@Test
	public void saveDataTest() {
		try {
			BufferedReader br = new BufferedReader(new FileReader(new File(
					DataUtil.getFundCodeFilePath())));
			String str0 = "000001 华夏成长";
			String str1 = "770001 德邦优化配置";
			String line0 = br.readLine();
			String line1 = "", tmp;
			while ((tmp = br.readLine()) != null)
				line1 = tmp;
			assertTrue(line0.equals(str0));
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
