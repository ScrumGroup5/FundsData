package fund.model.data;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataFetcher {
	
	private BufferedWriter unmatch_bw;
	protected BufferedWriter bw;
	protected BufferedReader br;
	private URL url;
	private String urlStr = "http://www.chinastock.com.cn/fund/fundscreening/index.shtml";
	String fileName = "E:\\data\\funds.txt";
	
	private String id_regex = "<td id=\"idnum\" name=\"idnum\">\\d+";
	private String symbol_regex = ">\\d+";
	private String sname_regex  = ">[\u4e00-\u9fa5]+";
	private String time_regex = "\\d+-\\d+-\\d+";
	private String num_regex = "^\\d+.\\d+|^--";
	private String rate_regex = ">-*\\d+.\\d+";
	
	String regexArray[] = {id_regex, symbol_regex, sname_regex, time_regex, num_regex, num_regex, 
			num_regex, rate_regex, rate_regex, rate_regex, rate_regex, rate_regex, rate_regex, rate_regex};
	int index[] = {28, 1, 1, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1};
	int num = 14;
	
	protected Pattern pat;
	protected Matcher mat;
	
	public DataFetcher() {
		
	}
	
	public DataFetcher(String urlStr, String fileName, int num, String refexArray[], int index[]) {
		this.urlStr = urlStr;
		this.fileName = fileName;
		this.num = num;
		this.regexArray = refexArray;
		this.index = index;
	}
	
	//下载数据
	protected boolean loadData() {
		try {
			url = new URL(urlStr);
			br = new BufferedReader(new InputStreamReader(url.openStream()));
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	//打开输出文件
	protected boolean openOutputFile() {
		try {
			bw=new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(fileName))));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	//数据匹配
	public void matchData() {
		loadData();
		openOutputFile();
		String line;
		int i = 0;
		try {
			while((line = br.readLine())!=null) {
				pat = Pattern.compile(regexArray[i]);
				mat = pat.matcher(line);
				if(mat.find()) {
					if(i!=0) bw.write(" ");
					bw.write(mat.group().substring(index[i]));
					i = (i + 1) % num;
					if(i==0) {
						bw.newLine();;
					}
					bw.flush();
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
