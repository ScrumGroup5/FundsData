package fund.model;

import static org.junit.Assert.*;

import org.junit.Test;

public class DataUtilTest {

	@Test
	public void getProfileFilePathTest() {
		String str = DataUtil.getProfileFilePath("000001");
		String cmpStr = "data/profile/000001_profile.txt";
	}
	
	@Test
	public void getRecordFilePathTest() {
		String str = DataUtil.getRecordFilePath("000001");
		String cmpStr = "data/jz/000001_jz.txt";
	}
	
	@Test
	public void getFundCodeFilePathTest() {
		String str = DataUtil.getFundCodeFilePath();
		String cmpStr = "data/funds_code.txt";
	}
}
