package io.ffreedom.ftd.dto;

import org.apache.commons.lang3.StringUtils;

import io.netty.buffer.ByteBuf;
/**
 * 
 * @author ee
 * 2017年10月17日 下午8:17:04
 *
 */
public class ReqQrySettlementInfoConfirm implements FtdcReq{
	private byte[] brokerID = new byte[11];
	private byte[] investorID = new byte[13];
	
	public void setBrokerID(String brokerID) {
		if(StringUtils.isNotEmpty(brokerID)) {
			System.arraycopy(brokerID.getBytes(), 0, this.brokerID, 0, brokerID.getBytes().length);
		}
	}
	
	public void setInvestorID(String investorID) {
		if(StringUtils.isNotEmpty(investorID)) {
			System.arraycopy(investorID.getBytes(), 0, this.investorID, 0, investorID.getBytes().length);
		}
	}
	
	@Override
	public ByteBuf write(ByteBuf buffer) {
		buffer.writeBytes(brokerID);
		buffer.writeBytes(investorID);
		return buffer;
	}
}

