package fund.model;

public class Record {
	private String date;
	private String unitNet; // 单位净值|
	private String cumulativeNet; // 累积净值|
	private String dailyGrowthRate; // 日增长率|
	private String purchaseState; // 申购状态
	private String redemptionState; // 赎回状态
	private String bonus; // 分红
	private String tenThousandIncome; // 每万份收益
	private String weekAnnualReturnRate; // 7日年化收益率
	private String recentRuntimeReturnRate;// 最近运作期年化收益率

	public Record(String date, String unitNet, String cumulativeNet,
			String dailyGrowthRate, String purchaseState,
			String redemptionState, String bonus) {
		this.date = date;
		this.unitNet = unitNet;
		this.cumulativeNet = cumulativeNet;
		this.dailyGrowthRate = dailyGrowthRate;
		this.purchaseState = purchaseState;
		this.redemptionState = redemptionState;
		this.bonus = bonus;
	}

	public Record(String date, String tenThousandIncome,
			String weekAnnualReturnRate, String purchaseState,
			String redemptionState, String bonus) {
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

	public String getCumulateNet() {
		return cumulativeNet;
	}

	public void setCumulateNet(String cumulativeNet) {
		this.cumulativeNet = cumulativeNet;
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

	public String getRecentRuntimeReturnRate() {
		return recentRuntimeReturnRate;
	}

	public void setRecentRuntimeReturnRate(String recentRuntimeReturnRate) {
		this.recentRuntimeReturnRate = recentRuntimeReturnRate;
	}

}
