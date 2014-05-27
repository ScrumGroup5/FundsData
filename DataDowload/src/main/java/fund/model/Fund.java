package fund.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Fund {
	private int datatype; //record的项目个数【6或7】!!!!!!!!!!!!!!!!详见0000001_jz和000013_jz
	private String name; //全称
	private String briefName; //简称
	private String code; //代码
	private String pubishDate; //成立日期
	private String type; //种类
	private String establishDateAndSize; //成立时间和成立规模
	private String propertySize; //资产规模
	private String portionSize; //份额规模
	private String organization; //管理人
	private String manager; //经理人
	private String trustee; //托管人
	private Map<String, String> profile_map;
	private ArrayList<Record> recordArray;
	
	private String filename_profile;
	private String filename_record;
	
	public Fund(String code) {
		this.code = code;
		filename_profile = "E:\\data\\profile\\" + code +"_profile.txt";
		filename_record = "E:\\data\\jz\\" + code +"_jz.txt";
		profile_map = new HashMap<String, String>();
		recordArray = new ArrayList<Record>();
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(new File(filename_profile))));
			String line;
			while((line=br.readLine())!=null) {
				String[] s = line.split(" +");
				profile_map.put(s[0], s[1]);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		name = profile_map.get("基金全称");
		briefName = profile_map.get("基金简称");
		pubishDate = profile_map.get("发行日期");
		type = profile_map.get("基金类型");
		establishDateAndSize = profile_map.get("成立日期/规模");
		propertySize = profile_map.get("资产规模");
		portionSize = profile_map.get("份额规模");
		organization = profile_map.get("基金管理人");
		manager = profile_map.get("基金经理人");
		trustee = profile_map.get("基金托管人");
		loadRecords();
	}
	
	private void loadRecords() {
		try {
			BufferedReader br_record = new BufferedReader(new InputStreamReader(new FileInputStream(new File(filename_record))));
			String line;
			line = br_record.readLine();
			String[] ss = line.split(" +");
			datatype = ss.length;
			while((line=br_record.readLine())!=null) {
				if(datatype==6) {
					recordArray.add(new Record(ss[0], ss[1], ss[2], ss[3], ss[4], ss[5]));
				}
				else if(datatype==7) {
					Record a = new Record(ss[0], ss[1], ss[2], ss[3], ss[4], ss[5], ss[6]);
					recordArray.add(a);
				}
			}
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
	public String getPubishDate() {
		return pubishDate;
	}
	public void setPubishDate(String pubishDate) {
		this.pubishDate = pubishDate;
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

	public Map<String, String> getProfile_map() {
		return profile_map;
	}

	public void setProfile_map(Map<String, String> profile_map) {
		this.profile_map = profile_map;
	}

	public ArrayList<Record> getRecordArray() {
		return recordArray;
	}

	public void setRecordArray(ArrayList<Record> recordArray) {
		this.recordArray = recordArray;
	}

	public int getDatatype() {
		return datatype;
	}

	public void setDatatype(int datatype) {
		this.datatype = datatype;
	}
}
