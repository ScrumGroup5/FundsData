package fund.test;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import fund.model.FundsCode;

public class FundsCodeTest {
	private FundsCode fc;
	
	@Before
	public void setUp() {
		fc = new FundsCode();
		fc.loadData();
	}
	@Test
	public void test() {
		try {
			BufferedReader br = new BufferedReader(new FileReader(new File("E:\\data\\funds_code.txt")));
			String str0 = "1 000001 华夏成长";
			String str1 = "2308 770001 德邦优化配置";
			String line0 = br.readLine();
			String line1 = "", tmp;
			while((tmp = br.readLine())!=null) line1 = tmp;
			assertTrue(line0.equals(str0));
			assertTrue(line1.equals(str1));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
