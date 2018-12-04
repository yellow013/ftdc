package io.ffreedom.ftd.processor;

import io.ffreedom.ftd.dto.RspError;
import io.ffreedom.ftd.dto.RspOrderAction;
import io.ffreedom.ftd.ftdc.FtdcProtocol.Ftdc;
import io.ffreedom.ftd.handler.FtdcTraderSpi;

/**
 * 
 * @author ee
 * 
 *         2017年11月12日 下午11:22:30
 *
 */
public class OrderActionTidProcessor implements FtdcTidProcessor {

	@Override
	public void process(Ftdc ftdc, FtdcTraderSpi spi, RspError error) {
		RspOrderAction rspOrderAction = new RspOrderAction().parseFrom(ftdc.ftdcBody(), error);
		spi.onRspOrderAction(rspOrderAction, (int) ftdc.getReqId(), true);
	}
}
