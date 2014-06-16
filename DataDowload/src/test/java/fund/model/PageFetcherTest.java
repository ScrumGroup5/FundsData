package fund.model;

import static org.junit.Assert.*;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.junit.Before;
import org.junit.Test;

public class PageFetcherTest {
	PageFetcher pf;
	
	@Before
	public void setUp() {
		pf = new PageFetcher();
	}
	
	@Test
	public void fetchTest() {
		try {
			String page = pf.fetch("http://jwc.jnu.edu.cn/");
			String[] s = page.split("\n");
			String str = "<style type=\"text/css\">\r";
			assertTrue(str.equals(s[0]));
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
