package fund.model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.htmlparser.Node;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.OrFilter;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;

public class Profile {
	private String urlStr;
	private String fileName;
	private String fundscode;
	private BufferedWriter bw;
	
	public Profile(String fundscode) {
		this.fundscode = fundscode;
		urlStr = "http://fund.eastmoney.com/f10/jbgk_" + fundscode + ".html";
		fileName = "E:\\data\\profile\\" + fundscode +"_profile.txt";
		try {
			bw=new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(fileName))));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		loadProfile();
	}
	
	public void loadProfile() {
		URL url;
		try {
			url = new URL(urlStr);
			Parser parser = new Parser((HttpURLConnection)url.openConnection());
			NodeFilter filter = new TagNameFilter("table"); 
			NodeList nodelist = parser.parse(filter);
			Node[] nodes = nodelist.toNodeArray();
			String s = nodes[0].toHtml();
			//System.out.println(s);
			parser = new Parser(s);
			NodeFilter filter0 = new TagNameFilter("td");
			NodeFilter filter1 = new TagNameFilter("th");
			NodeFilter filter2 = new TagNameFilter("a");
			NodeFilter[] filters = new NodeFilter[] {filter0, filter1};
			OrFilter orfilter = new OrFilter(filters);
			nodelist = parser.parse(orfilter);
			Node[] ns = nodelist.toNodeArray();
			for (int i = 0; i < ns.length; i++) {
				Node node = ns[i];
				String str = node.toHtml();
				
				Parser parsera = new Parser(str); //����<a>
				NodeList als = parsera.parse(filter2);
				Node[] ans = als.toNodeArray();
				if(ans.length==0) {
					//System.out.println(str);
					int index0 = str.indexOf(">");
					int index1 = str.indexOf("<");
					//System.out.println(index1);
					index1 = str.indexOf("<", index1+1);
					if(index0+1==index1) bw.write("null ");
					else bw.write(str.substring(index0+1, index1) + " ");
				}
				else {
					for(int j=0; j<ans.length; j++) {
						String ss = ans[j].toHtml();
						//System.out.println(ss);
						int index0 = ss.indexOf(">");
						int index1 = ss.indexOf("<");
						//System.out.println(index1);
						index1 = ss.indexOf("<", index1+1);
						if(j>0) bw.write("|");
						if(index0+1==index1) bw.write("null");
						else bw.write(ss.substring(index0+1, index1));
					}
				}
				if(i%2==1) bw.newLine();
				bw.flush();
				//System.out.println(s.substring(index0+1, index1));
			}
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
	public static void main(String args[]) {
		new Profile("000088");
	}
}
