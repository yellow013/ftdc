package io.ffreedom.ftd.dto;

import io.ffreedom.ftd.enums.FtdTagType;
import io.ffreedom.ftd.enums.FtdType;
import io.ffreedom.ftd.ftdc.FtdcProtocol;
/**
 * 
 * @author ee
 * 2017年10月17日 下午8:15:02
 *
 */
public class HeartBeat {

	public static FtdcProtocol getHeartBeat() {
		FtdcProtocol fp = new FtdcProtocol(FtdType.FTDTypeNone);
		FtdTagType tagType = FtdTagType.FTDTagKeepAlive;
		fp.addExt(tagType.type(), tagType.length(), 0);
		return fp;
	}
	
}
