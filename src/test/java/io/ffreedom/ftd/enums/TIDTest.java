package io.ffreedom.ftd.enums;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import io.ffreedom.ftd.enums.FtdcType;
import io.ffreedom.ftd.enums.TID;

public class TIDTest {

	@Test
	public void testParseFrom() {
		assertEquals(TID.AccountRegisterReq, TID.parseFrom(0x30110025, FtdcType.REQ));
		assertEquals(TID.AccountRegisterRsp, TID.parseFrom(0x30120197, FtdcType.RSP));
		assertEquals(TID.UNUSE, TID.parseFrom(0x90110025, FtdcType.REQ));
		assertEquals(TID.UNUSE, TID.parseFrom(0x90110025, FtdcType.RSP));
	}

}
