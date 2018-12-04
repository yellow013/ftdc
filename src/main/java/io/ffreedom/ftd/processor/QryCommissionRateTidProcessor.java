package io.ffreedom.ftd.processor;

import io.ffreedom.ftd.dto.RspError;
import io.ffreedom.ftd.dto.RspQryCommissionRate;
import io.ffreedom.ftd.ftdc.FtdcProtocol.Ftdc;
import io.ffreedom.ftd.handler.FtdcTraderSpi;

/**
 * 
 * @author ee
 * 
 *         2017年11月12日 下午11:22:42
 *
 */
public class QryCommissionRateTidProcessor implements FtdcTidProcessor {

	@Override
	public void process(Ftdc ftdc, FtdcTraderSpi spi, RspError error) {
		RspQryCommissionRate qryCommissionRate = new RspQryCommissionRate().parseFrom(ftdc.ftdcBody(), error);
		spi.onRspQryCommissionRate(qryCommissionRate, (int) ftdc.getReqId(), true);
	}
}
