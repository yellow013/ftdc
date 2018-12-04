package io.ffreedom.ftd.processor;

import io.ffreedom.ftd.dto.RspError;
import io.ffreedom.ftd.dto.RspInputOrder;
import io.ffreedom.ftd.ftdc.FtdcProtocol.Ftdc;
import io.ffreedom.ftd.handler.FtdcTraderSpi;

/**
 * 
 * @author ee
 * 
 *         2017年11月12日 下午11:22:36
 *
 */
public class OrderInsertTidProcessor implements FtdcTidProcessor {

	@Override
	public void process(Ftdc ftdc, FtdcTraderSpi spi, RspError error) {
		RspInputOrder inputOrder = new RspInputOrder().parseFrom(ftdc.ftdcBody(), error);
		spi.onRspOrderInput(inputOrder, (int) ftdc.getReqId(), true);
	}
}
