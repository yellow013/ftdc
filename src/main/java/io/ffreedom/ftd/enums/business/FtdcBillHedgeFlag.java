package io.ffreedom.ftd.enums.business;

/**
 * 
 * @author ee 2017年10月17日 下午7:35:38
 *
 */
public enum FtdcBillHedgeFlag {
	/**
	 * 投机
	 */
	FTDC_Speculation("1"),
	/**
	 * 套利
	 */
	FTDC_Arbitrage("2"),
	/**
	 * 套保
	 */
	FTDC_Hedge("3");

	private String flag;

	private FtdcBillHedgeFlag(String flag) {
		this.flag = flag;
	}

	public String getFlag() {
		return flag;
	}

	public static FtdcBillHedgeFlag parseFrom(String flag) {
		switch (flag) {
		case "1":
			return FTDC_Speculation;
		case "2":
			return FTDC_Arbitrage;
		case "3":
			return FTDC_Hedge;
		default:
			return null;
		}
	}
}
