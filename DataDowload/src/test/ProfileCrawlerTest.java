package fund.model;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

public class ProfileCrawlerTest {
	ProfileCrawler pc;
	@Before
	public void setUp() {
		pc = new ProfileCrawler("000001");
	}
	
	@Test
	public void crawlTest() {
		try {
			pc.crawl();
			BufferedReader br = new BufferedReader(new FileReader(new File(
					DataUtil.getProfileFilePath("000001"))));
			String line = br.readLine();
			String str = "基金全称 华夏成长证券投资基金 ";
			assertTrue(line.equals(str));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
