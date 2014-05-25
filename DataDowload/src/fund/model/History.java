package fund.model;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.htmlparser.Node;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;


public class History {
	protected BufferedWriter bw;
	protected BufferedReader br;
	private String urlStr;
	private String fileName = "";
	private String fundscode;
	int num = 0;
	public History(String fundscode) {
		try {
			this.fundscode = fundscode;
			fileName = "E:\\data\\jz\\" + fundscode + "_jz.txt";
			urlStr = "http://fund.eastmoney.com/f10/F10DataApi.aspx?type=lsjz&code="+fundscode+"&page=1&per=2997";
			bw=new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(fileName))));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		loadTh();
		loadJz();
	}
	
	public void loadJz() {
		try {
			URL url = new URL(urlStr);
			Parser parser = new Parser((HttpURLConnection)url.openConnection());
			NodeFilter filter = new TagNameFilter("td"); 
			NodeList nodelist = parser.parse(filter);
			Node[] nodes = nodelist.toNodeArray();
			//System.out.println(nodes.length);
			for (int i = 0; i < nodes.length; i++) {
				Node node = nodes[i];
				String s = node.toHtml();
				int index0 = s.indexOf(">");
				int index1 = s.indexOf("<");
				//System.out.println(index1);
				index1 = s.indexOf("<", index1+1);
				if(index0+1==index1) bw.write("null ");
				bw.write(s.substring(index0+1, index1) + " ");
				if(i%num==num-1) bw.newLine();
				bw.flush();
				//System.out.println(s.substring(index0+1, index1));
			}
			//System.out.println(fundscode + " done!");
			
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void loadTh() {
		try {
			URL url = new URL(urlStr);
			Parser parser = new Parser((HttpURLConnection)url.openConnection());
			NodeFilter filter = new TagNameFilter("th"); 
			NodeList nodelist = parser.parse(filter);
			Node[] nodes = nodelist.toNodeArray();
			num = nodes.length;
			for (int i = 0; i < nodes.length; i++) {
				Node node = nodes[i];
				String s = node.toHtml();
				int index0 = s.indexOf(">");
				int index1 = s.indexOf("</th>");
				bw.write(s.substring(index0+1, index1) + " ");
				//System.out.println(s.substring(index0+1, index1));
			}
			bw.newLine();
			bw.flush();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
