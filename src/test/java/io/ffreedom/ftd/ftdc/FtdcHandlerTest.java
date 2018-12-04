package io.ffreedom.ftd.ftdc;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

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
import io.ffreedom.ftd.enums.FtdTagType;
import io.ffreedom.ftd.enums.FtdType;
import io.ffreedom.ftd.ftdc.FtdcHandler;
import io.ffreedom.ftd.ftdc.FtdcProtocol;
import io.ffreedom.ftd.handler.FtdcTraderSpi;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.embedded.EmbeddedChannel;

public class FtdcHandlerTest {

	@Test
	public void testHandlerHeartbeat() {
		FtdcProtocol protocol = new FtdcProtocol(FtdType.FTDTypeFTDC);
		FtdTagType tagType = FtdTagType.FTDTagKeepAlive;
		protocol.addExt(tagType.type(), tagType.length(), 0);
		EmbeddedChannel channel = new EmbeddedChannel(new FtdcHandler());
		channel.writeInbound(protocol);
		
		assertTrue(channel.finish());

		FtdcProtocol p = channel.readOutbound();
		assertEquals(FtdType.FTDTypeNone, p.getType());
		assertEquals(2, p.getExtLength());
		assertEquals(5, p.ext().getTagType());
		assertNull(channel.readInbound());
		channel.finish();
	}

	@Test(timeout = 5000)
	public void testHandlerNoData() {
		ByteBuf buffer = Unpooled.buffer();
		buffer.writeByte(2);
		buffer.writeByte(0);
		buffer.writeShort(22);
		buffer.writeZero(22);
		FtdcProtocol protocol = new FtdcProtocol((short)2, (short)0, 22, buffer, true);
		EmbeddedChannel channel = new EmbeddedChannel(new FtdcHandler());
		channel.attr(FtdcTraderSpi.TRADER_API).set(new Testhandler());
		channel.writeInbound(protocol);
	}
	
	
	static class Testhandler implements FtdcTraderSpi {

		@Override
		public void reqister(Channel channel) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onRspUserLogin(RspUserLogin rspUserLogin, int reqId, boolean isLast) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onNodata(int reqId) {
			assertEquals(0, reqId);
		}

		@Override
		public void onRspError(RspError error, int reqId) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onRspError(RspError error, RequestIdentity ri) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onRspUserLogout(RspUserLogout lout, int reqId, boolean isLast) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onRspSettlementInfo(RspSettlementInfo info, int reqId, boolean isLast) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onRspSettlementInfoConfirm(RspSettlementInfoConfirm info, int reqId, boolean isLast) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onRspQrySettlementInfoConfirm(RspQrySettlementInfoConfirm info, int reqId, boolean isLast) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onRspTradingAccount(RspTradingAccount tradingAccount, int reqId, boolean isLast) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onRspOrderInput(RspInputOrder inputOrder, int reqId, boolean isLast) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onRtnOrder(RtnOrder rtnOrder, int reqId, boolean isLast) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onRtnTrade(RtnTrade rtnTrade, int reqId, boolean isLast) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onRecieveUnUsedTid(String tid, int reqId) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onRspInvestorPosition(RspInvestorPosition investorPisition, int reqId, boolean isLast) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onRspQryMarginRate(RspQryMarginRate qryMarginRate, int reqId, boolean isLast) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onRspQryCommissionRate(RspQryCommissionRate qryCommissionRate, int reqId, boolean isLast) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onErrRtnOrderAction(ErrRtnOrderAction errRtnOrderAction, int reqId, boolean isLast) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onRspQryAccountRegister(RspAccountRegister accountRegister, int reqId, boolean isLast) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onRspQryContractBank(RspContractBank contractBank, int reqId, boolean isLast) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onRspOrderAction(RspOrderAction rspOrderAction, int reqId, boolean isLast) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onRspQryTransferSerial(RspQryTransferSerial info, int reqId, boolean isLast) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onRspQryInstrument(RspQryInstrument info, int reqId, boolean isLast) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onRspFutureOrBank(RspFutureOrBank info, int reqId, boolean isLast) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onRtnBankOrFuture(RtnBankOrFuture rtnBankOrFuture, int reqId, boolean isLast) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onRspUserPasswordUpdate(RspUserPasswordUpdate info, int reqId, boolean isLast) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void unRecieveUnusedSequence(int reqId, int tid, int sequence) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onRspQryOrder(RtnOrder order, int reqId, boolean isLast) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onRspQryTrade(RtnTrade trade, int reqId, boolean isLast) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onRspFundPasswordUpdate(RspFundPasswordUpdate info, int reqId, boolean isLast) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onRspUserAuthFirst(RspAuth rspAuth, int reqId, boolean isLast) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onRspUserAuthSecond(RspAuth rspAuth, int reqId, boolean isLast) {
			// TODO Auto-generated method stub
			
		}

		
		
	}
}
