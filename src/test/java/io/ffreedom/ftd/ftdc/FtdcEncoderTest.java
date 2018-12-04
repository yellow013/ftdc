package io.ffreedom.ftd.ftdc;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import io.ffreedom.ftd.enums.FtdTagType;
import io.ffreedom.ftd.enums.FtdType;
import io.ffreedom.ftd.ftdc.FtdcEncoder;
import io.ffreedom.ftd.ftdc.FtdcProtocol;
import io.netty.buffer.ByteBuf;
import io.netty.channel.embedded.EmbeddedChannel;

public class FtdcEncoderTest {

	@Test
	public void testEncoder() {
		FtdcProtocol protocol = new FtdcProtocol(FtdType.FTDTypeFTDC);
		FtdTagType tagType = FtdTagType.FTDTagKeepAlive;
		protocol.addExt(tagType.type(), tagType.length(), 0);
		EmbeddedChannel channel = new EmbeddedChannel(new FtdcEncoder());
		channel.writeOutbound(protocol);
		
		assertTrue(channel.finish());

		ByteBuf buf = channel.readOutbound();
		assertEquals(1, buf.readByte());
		assertEquals(2, buf.readByte());
		assertEquals(0, buf.readShort());
		assertEquals(0x05, buf.readByte());
		assertEquals(0, buf.readByte());
		assertTrue(!buf.isReadable());
		assertNull(channel.readInbound());
		channel.finish();
	}

}
