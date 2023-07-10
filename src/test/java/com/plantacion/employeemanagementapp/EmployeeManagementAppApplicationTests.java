package com.plantacion.employeemanagementapp;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


class EmployeeManagementAppApplicationTests {

	@Test
	void testForString() {
		//given
        String test = "testing";
		//when

		//then
		assertThat("testing").isEqualTo(test);
	}

}
