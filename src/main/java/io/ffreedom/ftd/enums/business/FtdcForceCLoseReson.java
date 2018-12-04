package io.ffreedom.ftd.enums.business;

/**
 * 
 * @author ee 2017年10月17日 下午7:41:04
 *
 */
public enum FtdcForceCLoseReson {
	/**
	 * 非强平
	 */
	THOST_FTDCFCC_NotForceClose("0"),
	/**
	 * 资金不足
	 */
	THOST_FTDCFCC_LackDeposit("1"),
	/**
	 * 客户超仓
	 */
	THOST_FTDCFCC_ClientOverPositionLimit("2"),
	/**
	 * 会员超仓
	 */
	THOST_FTDCFCC_MemberOverPositionLimit("3"),
	/**
	 * 持仓非整数倍
	 */
	THOST_FTDCFCC_NotMultiple("4"),
	/**
	 * 违规
	 */
	THOST_FTDCFCC_Violation("5"),
	/**
	 * 其它
	 */
	THOST_FTDCFCC_Other("6"),
	/**
	 * 自然人临近交割
	 */
	THOST_FTDCFCC_PersonDeliv("7");

	private String type;

	private FtdcForceCLoseReson(String type) {
		this.type = type;
	}

	public String type() {
		return this.type;
	}

	public static FtdcForceCLoseReson parseFrom(String reson) {
		switch (reson) {
		case "0":
			return THOST_FTDCFCC_NotForceClose;
		case "1":
			return THOST_FTDCFCC_LackDeposit;
		case "2":
			return THOST_FTDCFCC_ClientOverPositionLimit;
		case "3":
			return THOST_FTDCFCC_MemberOverPositionLimit;
		case "4":
			return THOST_FTDCFCC_NotMultiple;
		case "5":
			return THOST_FTDCFCC_Violation;
		case "6":
			return THOST_FTDCFCC_Other;
		case "7":
			return THOST_FTDCFCC_PersonDeliv;
		default:
			return null;
		}
	}

}
