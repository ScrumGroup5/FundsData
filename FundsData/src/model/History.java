package model;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class History {
	protected BufferedWriter bw;
	protected BufferedReader br;
	private URL url;
	private String urlStr = "http://fund.eastmoney.com/f10/F10DataApi.aspx?type=lsjz&code=000001&page=1&per=2997";
	String fileName = "E:\\data\\aa.txt";
	private String date_regex = "\\d+-\\d+-\\d+";
	private String jz_regex = "\\d+\\.\\d+<";
	private String rate_regex = "-*\\d+.\\d+%|---";
	protected Pattern date_pat;
	protected Pattern jz_pat;
	protected Pattern rate_pat;
	protected Matcher date_mat;
	protected Matcher jz_mat;
	protected Matcher rate_mat;
	public History() {
		try {
			url = new URL(urlStr);
			br = new BufferedReader(new InputStreamReader(url.openStream()));
			bw=new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(fileName))));
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		date_pat = Pattern.compile(date_regex);
		jz_pat = Pattern.compile(jz_regex);
		rate_pat = Pattern.compile(rate_regex);
		
		String line;
		try {
			while((line = br.readLine())!=null) {
				date_mat = date_pat.matcher(line);
				jz_mat = jz_pat.matcher(line);
				rate_mat = rate_pat.matcher(line);
				while(date_mat.find()) {
					jz_mat.find();
					rate_mat.find();
					String out = "";
					String tmp = date_mat.group();
					out = out + tmp;
					String jz01 = jz_mat.group(), jz02 = jz_mat.group();
					tmp = " " + jz01.substring(0, jz01.length()-1) + " " + jz02.substring(0, jz02.length()-1);
					out = out + tmp;
					tmp = " " + rate_mat.group();
					out = out + tmp;
					bw.write(out);
					
					bw.newLine();
					bw.flush();
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void main(String args[]) {
		new History();
		System.out.println("done");
	}
}
