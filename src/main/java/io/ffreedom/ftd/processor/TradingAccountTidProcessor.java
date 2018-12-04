package io.ffreedom.ftd.processor;

import io.ffreedom.ftd.dto.RspError;
import io.ffreedom.ftd.dto.RspTradingAccount;
import io.ffreedom.ftd.ftdc.FtdcProtocol.Ftdc;
import io.ffreedom.ftd.handler.FtdcTraderSpi;

/**
 * 
 * @author ee
 * 
 *         2017年11月12日 下午11:24:03
 *
 */
public class TradingAccountTidProcessor implements FtdcTidProcessor {

	@Override
	public void process(Ftdc ftdc, FtdcTraderSpi spi, RspError error) {
		RspTradingAccount tradingAccount = new RspTradingAccount().parseFrom(ftdc.ftdcBody(), error);
		spi.onRspTradingAccount(tradingAccount, (int) ftdc.getReqId(), true);
	}
}
