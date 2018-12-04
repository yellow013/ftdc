package io.ffreedom.ftd.enums;

import static org.junit.Assert.*;

import org.junit.Test;

import io.ffreedom.ftd.enums.FtdTagType;

public class FtdTagTypeTest {

	@Test
	public void testParseFrom() {
		assertEquals(FtdTagType.FTDTagNone, FtdTagType.parseFrom(0));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testParseFromException() {
		assertEquals(FtdTagType.FTDTagNone, FtdTagType.parseFrom(10));
	}

}
