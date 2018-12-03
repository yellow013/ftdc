package com.ee.ctp.dto;

import org.apache.commons.lang3.StringUtils;

import io.netty.buffer.ByteBuf;

/**
 * 
 * @author ee 2017年10月17日 下午8:18:01
 *
 */
public class ReqTradingAccount implements FtdcReq {
	private byte[] brokerID = new byte[11];
	private byte[] investorID = new byte[13];
	private byte[] currencyID = new byte[4];

	public void setCurrencyID(String currencyID) {
		if (StringUtils.isNotEmpty(currencyID)) {
			System.arraycopy(currencyID.getBytes(), 0, this.currencyID, 0, currencyID.getBytes().length);
		}
	}

	public void setBrokerID(String brokerID) {
		if (StringUtils.isNotEmpty(brokerID)) {
			System.arraycopy(brokerID.getBytes(), 0, this.brokerID, 0, brokerID.getBytes().length);
		}
	}

	public void setInvestorID(String investorID) {
		if (StringUtils.isNotEmpty(investorID)) {
			System.arraycopy(investorID.getBytes(), 0, this.investorID, 0, investorID.getBytes().length);
		}
	}

	@Override
	public ByteBuf write(ByteBuf buffer) {
		buffer.writeBytes(brokerID);
		buffer.writeBytes(investorID);
		buffer.writeBytes(currencyID);
		return buffer;
	}
}
