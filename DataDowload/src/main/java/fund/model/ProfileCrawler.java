package fund.model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;

import org.apache.commons.io.FileUtils;
import org.htmlparser.Node;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.OrFilter;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;

public class ProfileCrawler {
	private final String fundscode;

	public ProfileCrawler(String fundscode) {
		this.fundscode = fundscode;
	}

	public void crawl() {
		try {
			Parser parser = new Parser(
					new PageFetcher()
							.fetch("http://fund.eastmoney.com/f10/jbgk_"
									+ fundscode + ".html"));
			NodeFilter filter = new TagNameFilter("table");
			NodeList nodelist = parser.parse(filter);
			Node[] nodes = nodelist.toNodeArray();
			String s = nodes[0].toHtml();
			// System.out.println(s);
			parser = new Parser(s);
			NodeFilter filter0 = new TagNameFilter("td");
			NodeFilter filter1 = new TagNameFilter("th");
			NodeFilter filter2 = new TagNameFilter("a");
			NodeFilter[] filters = new NodeFilter[] { filter0, filter1 };
			OrFilter orfilter = new OrFilter(filters);
			nodelist = parser.parse(orfilter);
			Node[] ns = nodelist.toNodeArray();

			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
					FileUtils.openOutputStream(new File(DataUtil
							.getProfileFilePath(fundscode)))));

			for (int i = 0; i < ns.length; i++) {
				Node node = ns[i];
				String str = node.toHtml();

				Parser parsera = new Parser(str);
				NodeList als = parsera.parse(filter2);
				Node[] ans = als.toNodeArray();
				if (ans.length == 0) {
					// System.out.println(str);
					int index0 = str.indexOf(">");
					int index1 = str.indexOf("<");
					// System.out.println(index1);
					index1 = str.indexOf("<", index1 + 1);
					if (index0 + 1 == index1)
						writer.write("null ");
					else
						writer.write(str.substring(index0 + 1, index1) + " ");
				} else {
					for (int j = 0; j < ans.length; j++) {
						String ss = ans[j].toHtml();
						// System.out.println(ss);
						int index0 = ss.indexOf(">");
						int index1 = ss.indexOf("<");
						// System.out.println(index1);
						index1 = ss.indexOf("<", index1 + 1);
						if (j > 0)
							writer.write("|");
						if (index0 + 1 == index1)
							writer.write("null");
						else
							writer.write(ss.substring(index0 + 1, index1));
					}
				}
				if (i % 2 == 1)
					writer.newLine();

			}
			writer.close();
		} catch (ParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
