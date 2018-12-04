package io.ffreedom.ftd.enums.business;

/**
 * 
 * @author lyb 2017年10月17日 下午7:38:23
 *
 */
public enum FtdcContingentCondition {

	/**
	 * 立即
	 */
	FTDC_Immediately("1"),
	/**
	 * 止损
	 */
	FTDC_Touch("2"),
	/**
	 * 止赢
	 */
	FTDC_TouchProfit("3"),
	/**
	 * 预埋单
	 */
	FTDC_ParkedOrder("4"),
	/**
	 * 最新价大于条件价
	 */
	FTDC_LastPriceGreaterThanStopPrice("5"),
	/**
	 * 最新价大于等于条件价
	 */
	FTDC_LastPriceGreaterEqualStopPrice("6"),
	/**
	 * 最新价小于条件价
	 */
	FTDC_LastPriceLesserThanStopPrice("7"),
	/**
	 * 最新价小于等于条件价
	 */
	FTDC_LastPriceLesserEqualStopPrice("8"),
	/**
	 * 卖一价大于条件价
	 */
	FTDC_AskPriceGreaterThanStopPrice("9"),
	/**
	 * 卖一价大于等于条件价
	 */
	FTDC_AskPriceGreaterEqualStopPrice("A"),
	/**
	 * 卖一价小于条件价
	 */
	FTDC_AskPriceLesserThanStopPrice("B"),
	/**
	 * 卖一价小于等于条件价
	 */
	FTDC_AskPriceLesserEqualStopPrice("C"),
	/**
	 * 买一价大于条件价
	 */
	FTDC_BidPriceGreaterThanStopPrice("D"),
	/**
	 * 买一价大于等于条件价
	 */
	FTDC_BidPriceGreaterEqualStopPrice("E"),
	/**
	 * 买一价小于条件价
	 */
	FTDC_BidPriceLesserThanStopPrice("F"),
	/**
	 * 买一价小于等于条件价
	 */
	FTDC_BidPriceLesserEqualStopPrice("H");

	private String contingent;

	private FtdcContingentCondition(String contingent) {
		this.contingent = contingent;
	}

	public String getContingent() {
		return contingent;
	}

	public static FtdcContingentCondition parseFrom(String flag) {
		switch (flag) {
		case "1":
			return FTDC_Immediately;
		case "2":
			return FTDC_Touch;
		case "3":
			return FTDC_TouchProfit;
		case "4":
			return FTDC_ParkedOrder;
		case "5":
			return FTDC_LastPriceGreaterThanStopPrice;
		case "6":
			return FTDC_LastPriceGreaterEqualStopPrice;
		case "7":
			return FTDC_LastPriceLesserThanStopPrice;
		case "8":
			return FTDC_LastPriceLesserEqualStopPrice;
		case "9":
			return FTDC_AskPriceGreaterThanStopPrice;
		case "A":
			return FTDC_AskPriceGreaterEqualStopPrice;
		case "B":
			return FTDC_AskPriceLesserThanStopPrice;
		case "C":
			return FTDC_AskPriceLesserEqualStopPrice;
		case "D":
			return FTDC_BidPriceGreaterThanStopPrice;
		case "E":
			return FTDC_BidPriceGreaterEqualStopPrice;
		case "F":
			return FTDC_BidPriceLesserThanStopPrice;
		case "H":
			return FTDC_BidPriceLesserEqualStopPrice;
		default:
			return null;
		}
	}
}
