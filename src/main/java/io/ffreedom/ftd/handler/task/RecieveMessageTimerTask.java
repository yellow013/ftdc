package io.ffreedom.ftd.handler.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.ffreedom.ftd.ApplicationRuntime;
import io.ffreedom.ftd.RequestIdentity;
import io.ffreedom.ftd.dto.RspError;
import io.ffreedom.ftd.handler.FtdcTraderSpi;
import io.netty.util.Timeout;
import io.netty.util.TimerTask;

/**
 * 
 * @author ee 2017年10月17日 下午8:43:41
 *
 */
public class RecieveMessageTimerTask implements TimerTask {

	private static final Logger logger = LoggerFactory.getLogger(RecieveMessageTimerTask.class);
	private RequestIdentity requestIdentity;
	private FtdcTraderSpi spi;

	public RecieveMessageTimerTask(RequestIdentity requestIdentity, FtdcTraderSpi spi) {
		this.requestIdentity = requestIdentity;
		this.spi = spi;
	}

	@Override
	public void run(Timeout timeout) throws Exception {
		RequestIdentity ri = ApplicationRuntime.getRequestIdentity(requestIdentity.getBrokerId(),
				requestIdentity.getUserId(), requestIdentity.getReqId());
		if (ri != null) {
			logger.warn("reqId {}, brokerid {}, uid {} timeouts", requestIdentity.getReqId(),
					requestIdentity.getBrokerId(), requestIdentity.getUserId());
			RspError recieveMessageError = RspError.buildRecieveMessageError();
			spi.onRspError(recieveMessageError, this.requestIdentity);
		}
	}

}
