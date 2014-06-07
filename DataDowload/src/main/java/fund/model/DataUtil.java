/**
 * 
 */
package fund.model;

/**
 * @author Saint Scott
 *
 */
public class DataUtil {

	public static String getProfileFilePath(String code) {
		return "data/profile/" + code + "_profile.txt";
	}

	public static String getRecordFilePath(String code) {
		return "data/jz/" + code + "_jz.txt";
	}

	public static String getFundCodeFilePath() {
		return "data/funds_code.txt";
	}
}
