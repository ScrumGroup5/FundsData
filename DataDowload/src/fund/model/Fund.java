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
	private int num; //record����Ŀ������6��7��
	private String name; //ȫ��
	private String briefName; //���
	private String code; //����
	private String pubishDate; //��������
	private String type; //����
	private String establishDateAndSize; //����ʱ��ͳ�����ģ
	private String propertySize; //�ʲ���ģ
	private String portionSize; //�ݶ��ģ
	private String organization; //������
	private String manager; //������
	private String trustee; //�й���
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
		name = profile_map.get("����ȫ��");
		briefName = profile_map.get("������");
		pubishDate = profile_map.get("��������");
		type = profile_map.get("��������");
		establishDateAndSize = profile_map.get("��������/��ģ");
		propertySize = profile_map.get("�ʲ���ģ");
		portionSize = profile_map.get("�ݶ��ģ");
		organization = profile_map.get("���������");
		manager = profile_map.get("��������");
		trustee = profile_map.get("�����й���");
		loadRecords();
	}
	
	private void loadRecords() {
		try {
			BufferedReader br_record = new BufferedReader(new InputStreamReader(new FileInputStream(new File(filename_record))));
			String line;
			line = br_record.readLine();
			String[] ss = line.split(" +");
			num = ss.length;
			while((line=br_record.readLine())!=null) {
				if(num==6) {
					recordArray.add(new Record(ss[0], ss[1], ss[2], ss[3], ss[4], ss[5]));
				}
				else if(num==7) {
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
	
	
}
