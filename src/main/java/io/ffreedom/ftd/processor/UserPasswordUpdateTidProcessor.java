package io.ffreedom.ftd.processor;

import io.ffreedom.ftd.dto.RspError;
import io.ffreedom.ftd.dto.RspUserPasswordUpdate;
import io.ffreedom.ftd.ftdc.FtdcProtocol.Ftdc;
import io.ffreedom.ftd.handler.FtdcTraderSpi;

/**
 * 
 * @author ee
 * 
 *         2017年11月12日 下午11:24:25
 *
 */
public class UserPasswordUpdateTidProcessor implements FtdcTidProcessor {

	@Override
	public void process(Ftdc ftdc, FtdcTraderSpi spi, RspError error) {
		RspUserPasswordUpdate userPasswordUpdate = new RspUserPasswordUpdate().parseFrom(ftdc.ftdcBody(), error);
		spi.onRspUserPasswordUpdate(userPasswordUpdate, (int) ftdc.getReqId(), true);
	}
}
