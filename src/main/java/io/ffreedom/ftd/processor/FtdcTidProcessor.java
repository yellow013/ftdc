package io.ffreedom.ftd.processor;

import io.ffreedom.ftd.dto.RspError;
import io.ffreedom.ftd.ftdc.FtdcProtocol.Ftdc;
import io.ffreedom.ftd.handler.FtdcTraderSpi;

/**
 * tid 处理器
 * 
 * @author ee
 * 
 *         2017年11月12日 下午11:20:48
 *
 */
public interface FtdcTidProcessor {
	/**
	 * tid 处理
	 * 
	 * @param ftdc
	 * @param spi
	 * @param error
	 */
	void process(Ftdc ftdc, FtdcTraderSpi spi, RspError error);
}
