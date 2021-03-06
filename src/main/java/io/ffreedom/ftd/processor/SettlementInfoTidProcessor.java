package io.ffreedom.ftd.processor;

import io.ffreedom.ftd.dto.RspError;
import io.ffreedom.ftd.dto.RspSettlementInfo;
import io.ffreedom.ftd.enums.ChainType;
import io.ffreedom.ftd.ftdc.FtdcProtocol.Ftdc;
import io.ffreedom.ftd.handler.FtdcTraderSpi;
import io.netty.buffer.ByteBuf;

/**
 * 
 * @author ee
 * 
 *         2017年11月12日 下午11:23:35
 *
 */
public class SettlementInfoTidProcessor implements FtdcTidProcessor {

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
			RspSettlementInfo rsinfo = new RspSettlementInfo()
					.parseFrom(ftdcBody.readRetainedSlice(ftdc.getStructOfTidLen()), error);
			rsinfo.setSequenceNo(ftdc.getCurrentSequence());
			spi.onRspSettlementInfo(rsinfo, (int) ftdc.getReqId(), isLast);
		}
	}
}
