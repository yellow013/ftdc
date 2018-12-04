package io.ffreedom.ftd.processor;

import io.ffreedom.ftd.dto.RspAccountRegister;
import io.ffreedom.ftd.dto.RspError;
import io.ffreedom.ftd.ftdc.FtdcProtocol.Ftdc;
import io.ffreedom.ftd.handler.FtdcTraderSpi;

/**
 * 
 * @author ee
 * 
 *         2017年11月12日 下午11:21:30
 *
 */
public class AccountRegisterTidProcessor implements FtdcTidProcessor {

	@Override
	public void process(Ftdc ftdc, FtdcTraderSpi spi, RspError error) {
		RspAccountRegister rspAccountRegister = new RspAccountRegister().parseFrom(ftdc.ftdcBody(), error);
		spi.onRspQryAccountRegister(rspAccountRegister, (int) ftdc.getReqId(), true);
	}
}
