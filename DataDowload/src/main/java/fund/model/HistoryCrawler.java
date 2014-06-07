package fund.model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;

import org.apache.commons.io.FileUtils;
import org.htmlparser.Node;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;

public class HistoryCrawler {
	private BufferedWriter bw;
	private String urlStr;
	private int num = 0;

	public HistoryCrawler(String fundscode) {
		try {

			urlStr = "http://fund.eastmoney.com/f10/F10DataApi.aspx?type=lsjz&code="
					+ fundscode + "&page=1&per=2997";
			bw = new BufferedWriter(new OutputStreamWriter(
					FileUtils.openOutputStream(new File(DataUtil
							.getRecordFilePath(fundscode)))));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void crawl() {
		try {
			String result = new PageFetcher().fetch(urlStr);
			loadTh(result);
			loadJz(result);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void loadJz(String data) {
		try {
			Parser parser = new Parser();
			parser.setInputHTML(data);
			NodeFilter filter = new TagNameFilter("td");
			NodeList nodelist = parser.parse(filter);
			Node[] nodes = nodelist.toNodeArray();
			// System.out.println(nodes.length);
			for (int i = 0; i < nodes.length; i++) {
				Node node = nodes[i];
				String s = node.toHtml();
				int index0 = s.indexOf(">");
				int index1 = s.indexOf("<");
				// System.out.println(index1);
				index1 = s.indexOf("<", index1 + 1);
				if (index0 + 1 == index1)
					bw.write("null ");
				bw.write(s.substring(index0 + 1, index1) + " ");
				if (i % num == num - 1)
					bw.newLine();
				bw.flush();
				// System.out.println(s.substring(index0+1, index1));
			}
			// System.out.println(fundscode + " done!");

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

	private void loadTh(String data) {
		try {
			Parser parser = new Parser();
			parser.setInputHTML(data);
			NodeFilter filter = new TagNameFilter("th");
			NodeList nodelist = parser.parse(filter);
			Node[] nodes = nodelist.toNodeArray();
			num = nodes.length;
			for (int i = 0; i < nodes.length; i++) {
				Node node = nodes[i];
				String s = node.toHtml();
				int index0 = s.indexOf(">");
				int index1 = s.indexOf("</th>");
				bw.write(s.substring(index0 + 1, index1) + " ");
				// System.out.println(s.substring(index0+1, index1));
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

	// public static void main(String[] args) {
	// new HistoryCrawler("000001").crawl();
	// }

}
