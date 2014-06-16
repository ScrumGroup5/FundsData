 /**
 * 
 */
package fund.model;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Saint Scott
 * 
 */
public class PageFetcher {

	private static Logger logger = LoggerFactory.getLogger(PageFetcher.class);

	private static int count = 0;
	private static int repeatCount = 0;

	public static void resetCounter() {
		count = 0;
		repeatCount = 0;
	}

	public static int getCounter() {
		return count;
	}

	public static int getRepeatCounter() {
		return repeatCount;
	}

	public String fetch(String url, String charset)
			throws ClientProtocolException, IOException {
		// TODO Auto-generated method stub
		logger.trace(url);
		DefaultHttpRequestRetryHandler retryHandler = new DefaultHttpRequestRetryHandler(
				10, true);
		CloseableHttpClient httpclient = HttpClients.custom()
				.setRetryHandler(retryHandler).build();

		HttpGet httpGet = new HttpGet(url);
		// RequestConfig requestConfig = RequestConfig.custom()
		// .setSocketTimeout(50000).setConnectTimeout(50000)
		// .setConnectionRequestTimeout(50000).build();// 设置请求和传输超时时间
		//
		// httpGet.setConfig(requestConfig);

		CloseableHttpResponse response = httpclient.execute(httpGet);

		while (true) {
			count++;
			int status = response.getStatusLine().getStatusCode();
			if (status >= 200 && status < 300) {
				break;
			}
			repeatCount++;
			response.close();
			response = httpclient.execute(httpGet);
		}

		String content = null;
		try {
			HttpEntity entity = response.getEntity();
			content = EntityUtils.toString(entity, charset).replace("&nbsp;",
					" ");
			EntityUtils.consume(entity);
		} finally {
			response.close();
		}
		httpclient.close();
		return content;
	}

	public String fetch(String url) throws ClientProtocolException, IOException {
		return fetch(url, "gb18030");
	}

}
