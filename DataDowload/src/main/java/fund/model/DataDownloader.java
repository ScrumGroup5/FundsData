package fund.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import fund.Context;

public class DataDownloader {

	public void download() {
		downloadCodes();
		downloadProfiles();
		Context.getInstance().loadFunds();
	}

	private void downloadCodes() {
		FundsCodeCrawler.crawl();
	}

	private void downloadProfiles() {

		int threadcount = 50;
		Queue<String> queue = generateQueue();

		ExecutorService exec = Executors.newFixedThreadPool(threadcount);
		List<Future<Void>> result = new ArrayList<>();

		Collection<ProfileCrawlTask> tasks = new ArrayList<>();
		while (threadcount-- != 0) {
			tasks.add(new ProfileCrawlTask(queue));
		}

		try {
			result.addAll(exec.invokeAll(tasks));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for (Iterator<Future<Void>> iterator = result.iterator(); iterator
				.hasNext();) {
			try {
				Future<Void> future = iterator.next();
				future.get();

			} catch (InterruptedException e) {
				// TODO: handle exception
				e.printStackTrace();
			} catch (ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				exec.shutdown();
			}
		}

	}

	private Queue<String> generateQueue() {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(new File(
					DataUtil.getFundCodeFilePath())));
			Queue<String> queue = new ConcurrentLinkedDeque<>();
			String tmp = null;
			while ((tmp = reader.readLine()) != null) {
				String[] attrs = tmp.split(" ");
				queue.add(attrs[0]);
			}
			reader.close();
			return queue;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}
}
