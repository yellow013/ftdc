package io.ffreedom.ftd.processor;

import io.ffreedom.ftd.dto.RspError;
import io.ffreedom.ftd.dto.RspQryMarginRate;
import io.ffreedom.ftd.ftdc.FtdcProtocol.Ftdc;
import io.ffreedom.ftd.handler.FtdcTraderSpi;

/**
 * 
 * @author ee
 * 
 *         2017年11月12日 下午11:22:55
 *
 */
public class QryMarginRateTidProcessor implements FtdcTidProcessor {

	@Override
	public void process(Ftdc ftdc, FtdcTraderSpi spi, RspError error) {
		RspQryMarginRate qryMarginRate = new RspQryMarginRate().parseFrom(ftdc.ftdcBody(), error);
		spi.onRspQryMarginRate(qryMarginRate, (int) ftdc.getReqId(), true);
	}
}
