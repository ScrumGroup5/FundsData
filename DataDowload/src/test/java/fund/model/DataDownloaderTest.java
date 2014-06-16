package fund.model;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Queue;

import org.junit.Before;
import org.junit.Test;

public class DataDownloaderTest {
	private DataDownloader ddl;
	private Class ddlClass;
	
	@Before
	public void setUp() {
		ddl = new DataDownloader();
		ddlClass = ddl.getClass();
	}
	
	@Test
	public void generateQueueTest() {
		try {
			Method method = ddlClass.getDeclaredMethod("generateQueue", null);
			method.setAccessible(true);
			Queue<String> q = (Queue<String>) method.invoke(ddl, null);
			String str = q.peek();
			assertTrue(str.equals("000001"));
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void downloadCodesTest() {
		Method method;
		try {
			method = ddlClass.getDeclaredMethod("downloadCodes", null);
			method.setAccessible(true);
			method.invoke(ddl, null);
			BufferedReader br = new BufferedReader(new FileReader(new File(
					DataUtil.getFundCodeFilePath())));
			String line = br.readLine();
			String str = "000001 华夏成长";
			assertTrue(str.equals(line));
			
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
