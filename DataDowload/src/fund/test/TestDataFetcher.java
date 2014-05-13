package fund.test;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.junit.Before;
import org.junit.Test;

import fund.model.data.DataFetcher;

public class TestDataFetcher {
	private DataFetcher df;
	
	@Before
	public void setUp() {
		df = new DataFetcher();
	}
	
	@Test
	public void testLoadData() throws Exception {
		Method testLoadDataMethod = df.getClass().getDeclaredMethod("loadData", null);
		testLoadDataMethod.setAccessible(true);
		boolean bln = (boolean) testLoadDataMethod.invoke(df, null);
		assertTrue(bln);
	}
	
	@Test
	public void testOpenOutputFile() throws Exception {
		Method testOpenOutputFileMethod = df.getClass().getDeclaredMethod("openOutputFile", null);
		testOpenOutputFileMethod.setAccessible(true);
		boolean bln = (boolean) testOpenOutputFileMethod.invoke(df, null);
		assertTrue(bln);
	}
	
	@Test
	public void testMatchData() throws Exception {
		df.matchData();
		try {
			BufferedReader br = new BufferedReader(new FileReader(new File("E:\\data\\funds.txt")));
			String testStr0 = "1 184692 博时裕隆封闭 2014-05-09 0.8017 4.1177 -- -2.1004 -2.1004 -6.64 -11.38 -16.14 -12.03 380.30";
			String testStr1 = "2141 150165 东吴中证可转换债券指数分级 2014-05-09 1.0000 1.0000 -- 0.0000 0.0000 0.00 0.00 0.00 0.00 0.00";
			String line0 = br.readLine();
			String line1 = "", tmp;
			while((tmp = br.readLine())!=null) line1 = tmp;
			assertTrue(line0.equals(testStr0));
			assertTrue(line1.equals(testStr1));
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
