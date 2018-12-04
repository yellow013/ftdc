package io.ffreedom.ftd.processor;

import io.ffreedom.ftd.dto.RspError;
import io.ffreedom.ftd.dto.RtnBankOrFuture;
import io.ffreedom.ftd.ftdc.FtdcProtocol.Ftdc;
import io.ffreedom.ftd.handler.FtdcTraderSpi;

/**
 * 
 * @author ee
 * 
 *         2017年11月12日 下午11:23:08
 *
 */
public class RtnBankOrFutureTidProcessor implements FtdcTidProcessor {

	@Override
	public void process(Ftdc ftdc, FtdcTraderSpi spi, RspError error) {
		RtnBankOrFuture rtnBankOrFuture = new RtnBankOrFuture().parseFrom(ftdc.ftdcBody(), error);
		spi.onRtnBankOrFuture(rtnBankOrFuture, (int) ftdc.getReqId(), true);
	}
}