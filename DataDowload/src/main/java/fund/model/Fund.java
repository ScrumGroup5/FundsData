package fund.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Fund {
	private int datatype; // record的项目个数【6或7】!!!!!!!!!!!!!!!!详见0000001_jz和000013_jz
	private String name; // 全称
	private String briefName; // 简称
	private String code; // 代码
	private String publishDate; // 成立日期
	private String type; // 类型
	private String establishDateAndSize; // 成立时间和成立规模
	private String propertySize; // 资产规模
	private String portionSize; // 份额规模
	private String organization; // 管理人
	private String manager; // 经理人
	private String trustee; // 托管人
	private String shareProfit;// 成立来分红
	private String manageFeeRate;// 管理费率
	private String trusteeFeeRate;// 托管费率
	private String workStandard;// 业绩比较基准
	private String trackSubject;// 跟踪标的
	private Map<String, String> profile_map;
	private List<Record> recordList;

	public Fund(String code) {
		this.code = code;
		profile_map = new HashMap<String, String>();
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					new FileInputStream(new File(
							DataUtil.getProfileFilePath(code)))));
			String line = null;
			while ((line = reader.readLine()) != null) {
				if (!line.trim().equals("")) {
					String[] s = line.split(" ");
					profile_map.put(s[0], s[1]);
				}
			}
			reader.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		name = profile_map.get("基金全称");
		briefName = profile_map.get("基金简称");
		publishDate = profile_map.get("发行日期");
		type = profile_map.get("基金类型");
		establishDateAndSize = profile_map.get("成立日期/规模");
		propertySize = profile_map.get("资产规模");
		portionSize = profile_map.get("份额规模");
		organization = profile_map.get("基金管理人");
		manager = profile_map.get("基金经理人");
		trustee = profile_map.get("基金托管人");
		shareProfit = profile_map.get("成立来分红");
		manageFeeRate = profile_map.get("管理费率");
		trusteeFeeRate = profile_map.get("托管费率");
		workStandard = profile_map.get("业绩比较基准");
		trackSubject = profile_map.get("跟踪标的");

	}

	private void loadRecords() {
		try {
			recordList = new ArrayList<>();

			File file = new File(DataUtil.getRecordFilePath(code));
			if (!file.exists()) {
				new HistoryCrawler(code).crawl();
			}

			BufferedReader recordReader = new BufferedReader(
					new InputStreamReader(new FileInputStream(file)));
			String line = recordReader.readLine();
			String[] recordAttrs = line.split(" ");
			datatype = recordAttrs.length;
			if (datatype == 6) {
				while ((line = recordReader.readLine()) != null) {
					recordAttrs = line.split(" ");
					recordList.add(new Record(recordAttrs[0], recordAttrs[1],
							recordAttrs[2], recordAttrs[3], recordAttrs[4],
							recordAttrs[5]));
				}
			} else if (datatype == 7) {
				while ((line = recordReader.readLine()) != null) {
					recordAttrs = line.split(" ");
					recordList.add(new Record(recordAttrs[0], recordAttrs[1],
							recordAttrs[2], recordAttrs[3], recordAttrs[4],
							recordAttrs[5], recordAttrs[6]));
				}
			}
			recordReader.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBriefName() {
		return briefName;
	}

	public void setBriefName(String briefName) {
		this.briefName = briefName;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(String publishDate) {
		this.publishDate = publishDate;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getEstablishDateAndSize() {
		return establishDateAndSize;
	}

	public void setEstablishDateAndSize(String establishDateAndSize) {
		this.establishDateAndSize = establishDateAndSize;
	}

	public String getPropertySize() {
		return propertySize;
	}

	public void setPropertySize(String propertySize) {
		this.propertySize = propertySize;
	}

	public String getPortionSize() {
		return portionSize;
	}

	public void setPortionSize(String portionSize) {
		this.portionSize = portionSize;
	}

	public String getOrganization() {
		return organization;
	}

	public void setOrganization(String organization) {
		this.organization = organization;
	}

	public String getManager() {
		return manager;
	}

	public void setManager(String manager) {
		this.manager = manager;
	}

	public String getTrustee() {
		return trustee;
	}

	public void setTrustee(String trustee) {
		this.trustee = trustee;
	}

	public String getShareProfit() {
		return shareProfit;
	}

	public void setShareProfit(String shareProfit) {
		this.shareProfit = shareProfit;
	}

	public String getManageFeeRate() {
		return manageFeeRate;
	}

	public void setManageFeeRate(String manageFeeRate) {
		this.manageFeeRate = manageFeeRate;
	}

	public String getTrusteeFeeRate() {
		return trusteeFeeRate;
	}

	public void setTrusteeFeeRate(String trusteeFeeRate) {
		this.trusteeFeeRate = trusteeFeeRate;
	}

	public String getWorkStandard() {
		return workStandard;
	}

	public void setWorkStandard(String workStandard) {
		this.workStandard = workStandard;
	}

	public String getTrackSubject() {
		return trackSubject;
	}

	public void setTrackSubject(String trackSubject) {
		this.trackSubject = trackSubject;
	}

	public Map<String, String> getProfile_map() {
		return profile_map;
	}

	public void setProfile_map(Map<String, String> profile_map) {
		this.profile_map = profile_map;
	}

	public List<Record> getRecordList() {
		if (recordList == null) {
			loadRecords();
		}
		return recordList;
	}

	public int getDatatype() {
		return datatype;
	}

	public void setDatatype(int datatype) {
		this.datatype = datatype;
	}
}
