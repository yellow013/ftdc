package io.ffreedom.ftd.enums.business;

import static org.junit.Assert.*;

import org.junit.Test;

import io.ffreedom.ftd.enums.business.FtdcActionFlagType;

public class FtdcActionFlagTypeTest {

	@Test
	public void testParseFrom() {
		assertEquals(FtdcActionFlagType.THOST_FTDCAF_Delete, FtdcActionFlagType.parseFrom("0"));
		assertEquals(null, FtdcActionFlagType.parseFrom("2"));
	}

}
