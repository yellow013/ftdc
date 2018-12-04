package io.ffreedom.ftd.processor;

import io.ffreedom.ftd.dto.RspError;
import io.ffreedom.ftd.dto.RspUserLogout;
import io.ffreedom.ftd.ftdc.FtdcProtocol.Ftdc;
import io.ffreedom.ftd.handler.FtdcTraderSpi;

/**
 * 
 * @author ee
 * 
 *         2017年11月12日 下午11:24:20
 *
 */
public class UserLogoutTidProcessor implements FtdcTidProcessor {

	@Override
	public void process(Ftdc ftdc, FtdcTraderSpi spi, RspError error) {
		RspUserLogout lout = new RspUserLogout().parseFrom(ftdc.ftdcBody(), error);
		spi.onRspUserLogout(lout, (int) ftdc.getReqId(), true);
	}
}
