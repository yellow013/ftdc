package io.ffreedom.ftd.processor;

import io.ffreedom.ftd.dto.RspError;
import io.ffreedom.ftd.dto.RspQrySettlementInfoConfirm;
import io.ffreedom.ftd.dto.RspSettlementInfoConfirm;
import io.ffreedom.ftd.enums.Sequence;
import io.ffreedom.ftd.ftdc.FtdcProtocol.Ftdc;
import io.ffreedom.ftd.handler.FtdcTraderSpi;

/**
 * 
 * @author ee
 * 
 *         2017年11月12日 下午11:23:27
 *
 */
public class SettlementInfoConfirmTidProcessor implements FtdcTidProcessor {

	@Override
	public void process(Ftdc ftdc, FtdcTraderSpi spi, RspError error) {
		if (Sequence.QrySettlementInfoConfirm.rspSequence() == ftdc.getSequence()) {
			// QrySettlementInfoConfirmRsp
			RspQrySettlementInfoConfirm qrySettlementInfoConfirm = new RspQrySettlementInfoConfirm()
					.parseFrom(ftdc.ftdcBody(), error);
			spi.onRspQrySettlementInfoConfirm(qrySettlementInfoConfirm, (int) ftdc.getReqId(), true);
		} else if (Sequence.SettlementInfoConfirm.rspSequence() == ftdc.getSequence()) {
			RspSettlementInfoConfirm info = new RspSettlementInfoConfirm().parseFrom(ftdc.ftdcBody(), error);
			spi.onRspSettlementInfoConfirm(info, (int) ftdc.getReqId(), true);
		}
	}
}
