/**
 * 
 */
package fund.model;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * @author Saint Scott
 * 
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({ DataUtilTest.class, DataDownloaderTest.class,
		FundsCodeCrawlerTest.class, FundTest.class, HistoryCrawlerTest.class,
		PageFetcherTest.class, ProfileCrawlerTest.class, RecordTest.class })
public class AllTests {

}
