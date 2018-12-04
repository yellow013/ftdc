package io.ffreedom.ftd.processor;

import io.ffreedom.ftd.dto.RspError;
import io.ffreedom.ftd.dto.RspFundPasswordUpdate;
import io.ffreedom.ftd.ftdc.FtdcProtocol.Ftdc;
import io.ffreedom.ftd.handler.FtdcTraderSpi;

/**
 * 
 * @author ee
 * 
 *         2017年11月12日 下午11:22:09
 *
 */
public class FundPasswordUpdateTidProcessor implements FtdcTidProcessor {

	@Override
	public void process(Ftdc ftdc, FtdcTraderSpi spi, RspError error) {
		RspFundPasswordUpdate rspFund = new RspFundPasswordUpdate().parseFrom(ftdc.ftdcBody(), error);
		spi.onRspFundPasswordUpdate(rspFund, (int) ftdc.getReqId(), true);
	}
}
