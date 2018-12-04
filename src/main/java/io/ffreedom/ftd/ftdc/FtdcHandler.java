package io.ffreedom.ftd.ftdc;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Verify;

import io.ffreedom.ftd.dto.HeartBeat;
import io.ffreedom.ftd.dto.RspError;
import io.ffreedom.ftd.enums.FtdTagType;
import io.ffreedom.ftd.enums.FtdcType;
import io.ffreedom.ftd.enums.Sequence;
import io.ffreedom.ftd.enums.TID;
import io.ffreedom.ftd.ftdc.FtdcProtocol.Ext;
import io.ffreedom.ftd.ftdc.FtdcProtocol.Ftdc;
import io.ffreedom.ftd.handler.FtdcTraderSpi;
import io.ffreedom.ftd.processor.FtdcTidProcessor;
import io.ffreedom.ftd.processor.TidProcessorFactory;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.Attribute;

/**
 * 
 * @author ee
 * 
 *         2017年10月17日 下午8:26:30
 *
 */
public class FtdcHandler extends SimpleChannelInboundHandler<FtdcProtocol> {

	private static final Logger logger = LoggerFactory.getLogger(FtdcHandler.class);

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, FtdcProtocol msg) throws Exception {
		if (msg.hasExt())
			handleFtdExt(ctx, msg);
		if (msg.hasBody()) {
			Ftdc ftdc = null;
			try {
				ftdc = msg.ftdc();
				if (Sequence.AfterUserLogin.sequence() == ftdc.getSequence())
					// 暂时忽略,认为是登录后的数据
					return;
				ByteBuf ftdcBody = ftdc.ftdcBody();
				if (ctx.channel().hasAttr(FtdcTraderSpi.TRADER_API)) {
					Attribute<FtdcTraderSpi> attr = ctx.channel().attr(FtdcTraderSpi.TRADER_API);
					FtdcTraderSpi ftdcTraderApi = attr.get();
					Verify.verifyNotNull(ftdcTraderApi, "FtdcTraderSpi not register, pls register it first");
					handleDataByTID(ftdc, ftdcBody, ftdcTraderApi);
				}
			} finally {
				if (ftdc != null)
					ftdc.release();
			}
		}

	}

	private void handleDataByTID(Ftdc ftdc, ByteBuf ftdcBody, FtdcTraderSpi spi) {
		if (!ftdc.hasBody()) {
			spi.onNodata((int) ftdc.getReqId());
			return;
		}
		TID tid = TID.parseFrom(ftdc.getTid(), FtdcType.RSP);
		RspError rspError = null;
		if (TID.CommonRsp == tid) {
			rspError = RspError.parseFrom(ftdcBody);
			if (ftdcBody.isReadable(Integer.SIZE / Byte.SIZE)) {
				tid = TID.parseFrom(ftdcBody.readUnsignedInt(), FtdcType.RSP);
			} else {
				int reqId = (int) ftdc.getReqId();
				spi.onRspError(rspError, reqId);
				logger.warn("{} with error {}", reqId, rspError);
				return;
			}
		}
		FtdcTidProcessor processor = TidProcessorFactory.Holder.INSTANCE.processor(tid);
		processor.process(ftdc, spi, rspError);
	}

	private void handleFtdExt(ChannelHandlerContext ctx, FtdcProtocol msg) {
		Ext ext = msg.ext();
		while (ext != null) {
			FtdTagType tagType = FtdTagType.parseFrom(ext.getTagType());
			if (FtdTagType.FTDTagKeepAlive.equals(tagType)) {
				logger.debug("recieve heart beat from ctp");
				ctx.writeAndFlush(HeartBeat.getHeartBeat());
			}
			ext = ext.next();
		}
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		logger.error(ExceptionUtils.getStackTrace(cause));
	}

}
