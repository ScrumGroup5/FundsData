package fund.model;

public class Record {
	private String date;
	private String unitNet; //��λ��ֵ
	private String accumulateNet; //�ۻ�ֵ
	private String dailyGrowthRate; //��������
	private String purchaseState; //�깺״̬
	private String redemptionState; //���״̬
	private String bonus; //�ֺ�
	private String tenThousandIncome; //ÿ�������
	private String weekAnnualReturnRate; //7���껯������
	
	public Record(String date, String unitNet, String accumulateNet, String dailyGrowthRate, String purchaseState, String redemptionState, String bonus) {
		this.date = date;
		this.unitNet = unitNet;
		this.accumulateNet = accumulateNet;
		this.dailyGrowthRate = dailyGrowthRate;
		this.purchaseState = purchaseState;
		this.redemptionState = redemptionState;
		this.bonus = bonus;
	}
	
	public Record(String date, String tenThousandIncome, String weekAnnualReturnRate, String purchaseState, String redemptionState, String bonus) {
		this.date = date;
		this.tenThousandIncome = tenThousandIncome;
		this.weekAnnualReturnRate = weekAnnualReturnRate;
		this.purchaseState = purchaseState;
		this.redemptionState = redemptionState;
		this.bonus = bonus;
	}
	
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getUnitNet() {
		return unitNet;
	}
	public void setUnitNet(String unitNet) {
		this.unitNet = unitNet;
	}
	public String getAccumulateNet() {
		return accumulateNet;
	}
	public void setAccumulateNet(String accumulateNet) {
		this.accumulateNet = accumulateNet;
	}
	public String getDailyGrowthRate() {
		return dailyGrowthRate;
	}
	public void setDailyGrowthRate(String dailyGrowthRate) {
		this.dailyGrowthRate = dailyGrowthRate;
	}
	public String getPurchaseState() {
		return purchaseState;
	}
	public void setPurchaseState(String purchaseState) {
		this.purchaseState = purchaseState;
	}
	public String getRedemptionState() {
		return redemptionState;
	}
	public void setRedemptionState(String redemptionState) {
		this.redemptionState = redemptionState;
	}
	public String getBonus() {
		return bonus;
	}
	public void setBonus(String bonus) {
		this.bonus = bonus;
	}
	public String getTenThousandIncome() {
		return tenThousandIncome;
	}
	public void setTenThousandIncome(String tenThousandIncome) {
		this.tenThousandIncome = tenThousandIncome;
	}
	public String getWeekAnnualReturnRate() {
		return weekAnnualReturnRate;
	}
	public void setWeekAnnualReturnRate(String weekAnnualReturnRate) {
		this.weekAnnualReturnRate = weekAnnualReturnRate;
	}
	
}
