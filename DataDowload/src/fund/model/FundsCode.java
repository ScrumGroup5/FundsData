package fund.model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FundsCode {
	private BufferedWriter unmatch_bw;
	protected BufferedWriter bw;
	protected BufferedReader br;
	private URL url;
	private final String urlStr = "http://fund.eastmoney.com/allfund.html";
	String fileName = "E:\\data\\funds_code.txt";
	String inputname = "E:\\data\\code.txt";
	private final String regex = "��\\d+��\\S*<";
	protected Pattern pat;
	protected Matcher mat;

	public FundsCode() {
		try {
			br = new BufferedReader(new InputStreamReader(new FileInputStream(
					new File(inputname))));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			bw = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(new File(fileName))));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void loadData() {
		String line;
		int i = 1;
		try {
			while ((line = br.readLine()) != null) {
				pat = Pattern.compile(regex);
				mat = pat.matcher(line);
				while (mat.find()) {
					String tmp = mat.group();
					bw.write(i + " " + tmp.substring(1, 7) + " "
							+ tmp.substring(8, tmp.length() - 1));
					i++;
					bw.newLine();
					bw.flush();
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catc h block
			e.printStackTrace();
		}
	}

	public static void main(String args[]) {
		FundsCode f = new FundsCode();
		f.loadData();
		System.out.println("done");
	}
}
