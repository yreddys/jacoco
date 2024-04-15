package com.manager.repository;

import static org.assertj.core.api.Assertions.assertThat;

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

	@Test
	@DisplayName("Test getManagerById")
	void getManagerById() {
		// Given
		Manager manager = new Manager();
		manager.setManagerId(2);
		manager.setManagerName("Test Manager");
		managerRepository.save(manager);

		// When
		Manager foundManager = managerRepository.findById(2).orElse(null);

		// Then
		assertThat(foundManager).isNotNull();
		assertThat(foundManager.getManagerId()).isEqualTo(2);
		assertThat(foundManager.getManagerName()).isEqualTo("Test Manager");
	}
}
