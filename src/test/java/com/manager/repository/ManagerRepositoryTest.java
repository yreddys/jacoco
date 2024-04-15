package com.manager.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.manager.entity.Manager;

@DataJpaTest
public class ManagerRepositoryTest {
	@Autowired
	private ManagerRepository managerRepository;

	@Test
	@DisplayName("Test creating a manager")
	void createManager() {

		Manager manager = new Manager();
		manager.setManagerId(1);
		manager.setManagerName("Hi");
		Manager savedManager = managerRepository.save(manager);
		Assertions.assertThat(savedManager).isNotNull();
		Assertions.assertThat(savedManager.getManagerId()).isGreaterThan(0);
	}

}
