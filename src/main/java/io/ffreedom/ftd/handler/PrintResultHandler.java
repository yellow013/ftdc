package io.ffreedom.ftd.handler;

import java.io.UnsupportedEncodingException;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.ffreedom.ftd.ApplicationRuntime;
import io.ffreedom.ftd.RequestIdentity;
import io.ffreedom.ftd.dto.ErrRtnOrderAction;
import io.ffreedom.ftd.dto.RspAccountRegister;
import io.ffreedom.ftd.dto.RspAuth;
import io.ffreedom.ftd.dto.RspContractBank;
import io.ffreedom.ftd.dto.RspError;
import io.ffreedom.ftd.dto.RspFundPasswordUpdate;
import io.ffreedom.ftd.dto.RspFutureOrBank;
import io.ffreedom.ftd.dto.RspInputOrder;
import io.ffreedom.ftd.dto.RspInvestorPosition;
import io.ffreedom.ftd.dto.RspOrderAction;
import io.ffreedom.ftd.dto.RspQryCommissionRate;
import io.ffreedom.ftd.dto.RspQryInstrument;
import io.ffreedom.ftd.dto.RspQryMarginRate;
import io.ffreedom.ftd.dto.RspQrySettlementInfoConfirm;
import io.ffreedom.ftd.dto.RspQryTransferSerial;
import io.ffreedom.ftd.dto.RspSettlementInfo;
import io.ffreedom.ftd.dto.RspSettlementInfoConfirm;
import io.ffreedom.ftd.dto.RspTradingAccount;
import io.ffreedom.ftd.dto.RspUserLogin;
import io.ffreedom.ftd.dto.RspUserLogout;
import io.ffreedom.ftd.dto.RspUserPasswordUpdate;
import io.ffreedom.ftd.dto.RtnBankOrFuture;
import io.ffreedom.ftd.dto.RtnOrder;
import io.ffreedom.ftd.dto.RtnTrade;

/**
 * 
 * @author ee 2017年10月17日 下午8:43:12
 *
 */
public class PrintResultHandler extends BaseFtdcTraderSpiAdapter {

	private static final Logger logger = LoggerFactory.getLogger(PrintResultHandler.class);
	private static final String LOGGR_TEMPLATE_DEBUG = "recieve msg: reqid {}, {}";

	private ConcurrentHashMap<Integer, byte[]> multipartMap = new ConcurrentHashMap<>();

	@Override
	protected void doRspUserLogin(RspUserLogin rspUserLogin, RequestIdentity ri, boolean authPassed) {
		logger.debug(LOGGR_TEMPLATE_DEBUG, ri.getReqId(), rspUserLogin);
	}

	@Override
	protected void doNodata(RequestIdentity ri) {
		logger.debug(LOGGR_TEMPLATE_DEBUG, ri.getReqId(), null);
	}

	@Override
	protected void doRspUserLogout(RequestIdentity requestIdentity, RspUserLogout lout) {
		logger.debug(LOGGR_TEMPLATE_DEBUG, requestIdentity.getReqId(), lout);
	}

	@Override
	protected void doRspSettlementInfo(RequestIdentity requestIdentity, RspSettlementInfo info, boolean isLast) {
		if (!isLast) {
			if (multipartMap.containsKey(requestIdentity.getReqId())) {
				byte[] part = multipartMap.get(requestIdentity.getReqId());
				multipartMap.put(requestIdentity.getReqId(), ArrayUtils.addAll(part, info.getContent()));
			} else {
				multipartMap.put(requestIdentity.getReqId(), info.getContent());
			}
		} else {
			byte[] part = multipartMap.get(requestIdentity.getReqId());
			try {
				logger.debug(requestIdentity.getReqId() + ":"
						+ new String(part, ApplicationRuntime.conf().defaultEncoding()));
			} catch (UnsupportedEncodingException e) {
				// nop
			}
		}
	}

	@Override
	protected void doRspSettlementInfoConfirm(RequestIdentity requestIdentity, RspSettlementInfoConfirm info,
			boolean isLast) {
		logger.debug(LOGGR_TEMPLATE_DEBUG, requestIdentity.getReqId(), info);
	}

	@Override
	protected void doRspQrySettlementInfoConfirm(RequestIdentity requestIdentity, RspQrySettlementInfoConfirm info,
			boolean isLast) {
		logger.debug(LOGGR_TEMPLATE_DEBUG, requestIdentity.getReqId(), info);
	}

	@Override
	protected void doRspTradingAccount(RequestIdentity requestIdentity, RspTradingAccount tradingAccount,
			boolean isLast) {
		logger.debug(LOGGR_TEMPLATE_DEBUG, requestIdentity.getReqId(), tradingAccount);
	}

	@Override
	protected void doRspOrderInput(RequestIdentity requestIdentity, RspInputOrder inputOrder, boolean isLast) {
		logger.debug(LOGGR_TEMPLATE_DEBUG, requestIdentity.getReqId(), inputOrder);
	}

	@Override
	protected void doRtnOrder(RequestIdentity requestIdentity, RtnOrder rtnOrder, boolean isLast) {
		logger.debug(LOGGR_TEMPLATE_DEBUG, requestIdentity.getReqId(), rtnOrder);
	}

	@Override
	protected void doRtnTrade(RequestIdentity requestIdentity, RtnTrade rtnTrade, boolean isLast) {
		logger.debug(LOGGR_TEMPLATE_DEBUG, requestIdentity.getReqId(), rtnTrade);
	}

	@Override
	protected void doRspInvestorPosition(RequestIdentity requestIdentity, RspInvestorPosition investorPisition,
			boolean isLast) {
		logger.debug(LOGGR_TEMPLATE_DEBUG, requestIdentity.getReqId(), investorPisition);
	}

	@Override
	protected void doRspError(RequestIdentity requestIdentity, RspError error) {
		logger.debug(LOGGR_TEMPLATE_DEBUG, requestIdentity.getReqId(), error);
	}

	@Override
	protected void doRspQryMarginRate(RequestIdentity requestIdentity, RspQryMarginRate qryMarginRate, boolean isLast) {
		logger.debug(LOGGR_TEMPLATE_DEBUG, requestIdentity.getReqId(), qryMarginRate);
	}

	@Override
	protected void doRspQryCommissionRate(RequestIdentity requestIdentity, RspQryCommissionRate qryCommissionRate,
			boolean isLast) {
		logger.debug(LOGGR_TEMPLATE_DEBUG, requestIdentity.getReqId(), qryCommissionRate);
	}

	@Override
	protected void doErrRtnOrderAction(RequestIdentity requestIdentity, ErrRtnOrderAction errRtnOrderAction,
			boolean isLast) {
		logger.debug(LOGGR_TEMPLATE_DEBUG, requestIdentity.getReqId(), errRtnOrderAction);
	}

	@Override
	protected void doRspQryAccountRegister(RequestIdentity requestIdentity, RspAccountRegister accountRegister,
			boolean isLast) {
		logger.debug(LOGGR_TEMPLATE_DEBUG, requestIdentity.getReqId(), accountRegister);
	}

	@Override
	protected void doRspQryContractBank(RequestIdentity requestIdentity, RspContractBank contractBank, boolean isLast) {
		logger.debug(LOGGR_TEMPLATE_DEBUG, requestIdentity.getReqId(), contractBank);
	}

	@Override
	protected void doRspOrderAction(RequestIdentity requestIdentity, RspOrderAction rspOrderAction, boolean isLast) {
		logger.debug(LOGGR_TEMPLATE_DEBUG, requestIdentity.getReqId(), rspOrderAction);
	}

	@Override
	protected void doRspQryTransferSerial(RequestIdentity requestIdentity, RspQryTransferSerial info, boolean isLast) {
		logger.debug(LOGGR_TEMPLATE_DEBUG, requestIdentity.getReqId(), info);
	}

	@Override
	protected void doRspQryInstrument(RequestIdentity requestIdentity, RspQryInstrument info, boolean isLast) {
		logger.debug(LOGGR_TEMPLATE_DEBUG, requestIdentity.getReqId(), info);
	}

	@Override
	protected void doRtnBankOrFuture(RequestIdentity requestIdentity, RtnBankOrFuture rtnBankOrFuture, boolean isLast) {
		logger.debug(LOGGR_TEMPLATE_DEBUG, requestIdentity.getReqId(), rtnBankOrFuture);
	}

	@Override
	protected void doRspUserPasswordUpdate(RequestIdentity requestIdentity, RspUserPasswordUpdate info,
			boolean isLast) {
		logger.debug(LOGGR_TEMPLATE_DEBUG, requestIdentity.getReqId(), info);
	}

	@Override
	protected void doRspQryOrder(RequestIdentity requestIdentity, RtnOrder order, boolean isLast) {
		logger.debug(LOGGR_TEMPLATE_DEBUG, requestIdentity.getReqId(), order);
	}

	@Override
	protected void doRspQryTrade(RequestIdentity requestIdentity, RtnTrade trade, boolean isLast) {
		logger.debug(LOGGR_TEMPLATE_DEBUG, requestIdentity.getReqId(), trade);
	}

	@Override
	protected void doRspFundPwdUpdtae(RequestIdentity requestIdentity, RspFundPasswordUpdate info, boolean isLast) {
		logger.debug(LOGGR_TEMPLATE_DEBUG, requestIdentity.getReqId(), info);
	}

	@Override
	protected void doRspUserAuth(RequestIdentity requestIdentity, RspAuth info, boolean isLast) {
		logger.debug(LOGGR_TEMPLATE_DEBUG, requestIdentity.getReqId(), info);
	}

	@Override
	protected void doRspFutureOrBank(RequestIdentity requestIdentity, RspFutureOrBank info, boolean isLast) {
		logger.debug(LOGGR_TEMPLATE_DEBUG, requestIdentity.getReqId(), info);
	}
}
