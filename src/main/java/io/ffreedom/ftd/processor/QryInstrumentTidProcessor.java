package io.ffreedom.ftd.processor;

import io.ffreedom.ftd.dto.RspError;
import io.ffreedom.ftd.dto.RspQryInstrument;
import io.ffreedom.ftd.enums.ChainType;
import io.ffreedom.ftd.ftdc.FtdcProtocol.Ftdc;
import io.ffreedom.ftd.handler.FtdcTraderSpi;
import io.netty.buffer.ByteBuf;

/**
 * 
 * @author ee 2017年11月12日 下午11:22:48
 *
 */
public class QryInstrumentTidProcessor implements FtdcTidProcessor {

	@Override
	public void process(Ftdc ftdc, FtdcTraderSpi spi, RspError error) {
		ByteBuf ftdcBody = ftdc.ftdcBody();
		boolean lastPacket = false;
		if (ChainType.END.chain() == ftdc.getChain()) {
			lastPacket = true;
		}
		for (int i = 0; i < ftdc.getNumData(); i++) {
			if (i != 0) {
				// 每个数据域都是以TID开头
				if (ftdcBody.isReadable(4))
					ftdcBody.readInt();
				else
					break;
			}
			boolean isLast = lastPacket && (i == ftdc.getNumData() - 1);
			RspQryInstrument rspQryInstrument = new RspQryInstrument()
					.parseFrom(ftdcBody.readRetainedSlice(ftdc.getStructOfTidLen()), error);
			spi.onRspQryInstrument(rspQryInstrument, (int) ftdc.getReqId(), isLast);
		}
	}
}
