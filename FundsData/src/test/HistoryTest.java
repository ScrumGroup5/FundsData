package test;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import model.FundsCode;
import model.History;

public class HistoryTest {

	private History his;
	
	@Before
	public void setUp() {
		his = new History();
	}
	@Test
	public void test() {
		try {
			BufferedReader br = new BufferedReader(new FileReader(new File("E:\\data\\aa.txt")));
			String str0 = "2014-05-16 1.0420 1.0420 -0.29%";
			String str1 = "2001-12-21 1.4230 1.4230 0.00%";
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
