/**
 * 
 */
package fund.model;

import java.io.File;
import java.util.Queue;
import java.util.concurrent.Callable;

/**
 * @author Saint Scott
 *
 */
public class ProfileCrawlTask implements Callable<Void> {
	private final Queue<String> codeQueue;

	public ProfileCrawlTask(Queue<String> queue) {
		// TODO Auto-generated constructor stub
		this.codeQueue = queue;
	}

	@Override
	public Void call() throws Exception {
		// TODO Auto-generated method stub
		while (!codeQueue.isEmpty()) {
			String code = null;
			synchronized (codeQueue) {
				code = codeQueue.poll();
			}
			if (!new File(DataUtil.getProfileFilePath(code)).exists()) {
				new ProfileCrawler(code).crawl();
			}
		}
		return null;
	}

}
