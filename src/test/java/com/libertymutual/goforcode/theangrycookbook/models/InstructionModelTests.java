package com.libertymutual.goforcode.theangrycookbook.models;

import static org.assertj.core.api.Assertions.*;

import org.junit.Test;
import org.meanbean.test.BeanTester;

public class InstructionModelTests {
	
	@Test
	public void test_full_argument_constructor() {
		// Act
		Instruction instruction = new Instruction("Will");

		// Assert
		assertThat(instruction.getInstruction()).isEqualTo("Will");
	}
	
	@Test
	public void test_all_getters_and_setters() {
		new BeanTester().testBean(Instruction.class);
	}

}
