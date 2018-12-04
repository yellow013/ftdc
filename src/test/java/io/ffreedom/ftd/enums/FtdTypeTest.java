package io.ffreedom.ftd.enums;

import static org.junit.Assert.*;

import org.junit.Test;

import io.ffreedom.ftd.enums.FtdType;

public class FtdTypeTest {

	@Test
	public void testParseFrom() {
		assertEquals(FtdType.FTDTypeNone, FtdType.parseFrom(0));
		assertEquals(FtdType.FTDTypeFTDC, FtdType.parseFrom(1));
		assertEquals(FtdType.FTDTypeCompressed, FtdType.parseFrom(2));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testParseFromException() {
		assertEquals(FtdType.FTDTypeNone, FtdType.parseFrom(10));
	}

}
