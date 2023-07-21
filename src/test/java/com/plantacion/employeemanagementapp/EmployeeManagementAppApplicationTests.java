package com.plantacion.employeemanagementapp;

import com.plantacion.employeemanagementapp.model.domain.AppUser;
import com.plantacion.employeemanagementapp.repository.AppUserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest()
@ExtendWith(MockitoExtension.class)
class EmployeeManagementAppApplicationTests {
	@Mock
	private AppUserRepository repository;
	@Test
	void testForString() {

		AppUser user = AppUser.builder()
						.email("Jayhmz10@gmail.com")
								.firstname("james")
										.lastname("damilare")
												.build();

		when(repository.findByEmail("Jayhmz10@gmail.com")).thenReturn(user);
		System.out.println(repository.findByEmail("Jayhmz10@gmail.com"));

		assertEquals(user, repository.findByEmail("Jayhmz10@gmail.com") );
	}

}
