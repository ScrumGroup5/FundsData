package fund.model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;

public class FundsCodeCrawler {
	private final static String urlStr = "http://fund.eastmoney.com/allfund.html";
	private final static String regex = "（\\d+）\\S*<";

	public static void crawl() {
		try {
			String page = new PageFetcher().fetch(urlStr);
			saveData(page);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void saveData(String page) throws IOException {
		Pattern pat = Pattern.compile(regex);
		Matcher mat = pat.matcher(page);
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
				FileUtils.openOutputStream(new File(DataUtil
						.getFundCodeFilePath()))));
		while (mat.find()) {
			String tmp = mat.group();
			StringBuilder builder = new StringBuilder();
			builder.append(tmp.substring(1, 7));
			builder.append(' ');
			builder.append(tmp.substring(8, tmp.length() - 1));
			writer.write(builder.toString());
			writer.newLine();
		}
		writer.close();
	}
}
