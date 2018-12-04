package io.ffreedom.ftd.processor;

import io.ffreedom.ftd.dto.RspError;
import io.ffreedom.ftd.dto.RspFutureOrBank;
import io.ffreedom.ftd.ftdc.FtdcProtocol.Ftdc;
import io.ffreedom.ftd.handler.FtdcTraderSpi;

/**
 * 
 * @author ee
 * 
 *         2017年11月12日 下午11:21:48
 *
 */
public class BankOrFurureProcessor implements FtdcTidProcessor {

	@Override
	public void process(Ftdc ftdc, FtdcTraderSpi spi, RspError error) {
		RspFutureOrBank rspFutureToBank = new RspFutureOrBank().parseFrom(ftdc.ftdcBody(), error);
		spi.onRspFutureOrBank(rspFutureToBank, (int) ftdc.getReqId(), true);
	}
}
