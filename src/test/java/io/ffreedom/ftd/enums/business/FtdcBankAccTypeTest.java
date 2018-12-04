package io.ffreedom.ftd.enums.business;

import static org.junit.Assert.*;

import org.junit.Test;

import io.ffreedom.ftd.enums.business.FtdcBankAccType;

public class FtdcBankAccTypeTest {

	@Test
	public void testParseFrom() {
		assertEquals(FtdcBankAccType.THOST_FTDCBAT_BankBook, FtdcBankAccType.parseFrom("1"));
		assertEquals(null, FtdcBankAccType.parseFrom("0"));
	}

}
