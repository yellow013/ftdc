package io.ffreedom.ftd.enums;

import static org.junit.Assert.*;

import org.junit.Test;

import io.ffreedom.ftd.enums.Sequence;

public class SequenceTest {

	@Test
	public void testRspSequence() {
		Sequence[] values = Sequence.values();
		for (Sequence sequence : values) {
			assertEquals(sequence.sequence() + 1, sequence.rspSequence());
		}
	}
}
