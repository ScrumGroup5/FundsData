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
import fund.model.History;

public class HistoryTest {

	private History his;
	
	@Before
	public void setUp() {
		his = new History("000001");
	}
	@Test
	public void test() {
		try {
			BufferedReader br = new BufferedReader(new FileReader(new File("E:\\data\\jz\\"+"000001"+"_jz.txt")));
			br.readLine();
			String str1 = "2002-01-25 1.0050 1.0050 0.50% ·â±ÕÆÚ ·â±ÕÆÚ null  "; 
			String line0 = br.readLine();
			String line1 = "", tmp;
			while((tmp = br.readLine())!=null) line1 = tmp;
			System.out.println(line1);
			System.out.println(str1);
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
