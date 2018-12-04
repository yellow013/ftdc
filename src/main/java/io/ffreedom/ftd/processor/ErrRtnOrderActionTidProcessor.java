package io.ffreedom.ftd.processor;

import io.ffreedom.ftd.dto.ErrRtnOrderAction;
import io.ffreedom.ftd.dto.RspError;
import io.ffreedom.ftd.ftdc.FtdcProtocol.Ftdc;
import io.ffreedom.ftd.handler.FtdcTraderSpi;

/**
 * 
 * @author ee
 * 
 *         2017年11月12日 下午11:22:01
 *
 */
public class ErrRtnOrderActionTidProcessor implements FtdcTidProcessor {

	@Override
	public void process(Ftdc ftdc, FtdcTraderSpi spi, RspError error) {
		ErrRtnOrderAction errRtnOrderAction = new ErrRtnOrderAction().parseFrom(ftdc.ftdcBody(), error);
		spi.onErrRtnOrderAction(errRtnOrderAction, (int) ftdc.getReqId(), true);
	}
}
