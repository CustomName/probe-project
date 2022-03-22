package ru.axl.probeproject;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.axl.probeproject.controllers.AdminController;
import ru.axl.probeproject.controllers.ClientController;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ApplicationTests {

	@Autowired
	private AdminController adminController;
	@Autowired
	private ClientController clientController;

	@Test
	void contextLoads() {
		assertThat(adminController).isNotNull();
		assertThat(clientController).isNotNull();
	}

}
