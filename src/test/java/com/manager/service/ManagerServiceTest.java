package com.manager.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.manager.dto.ManagerDto;
import com.manager.entity.Manager;
import com.manager.repository.ManagerRepository;

@ExtendWith(MockitoExtension.class)
public class ManagerServiceTest {
	@Mock
	private ManagerRepository managerRepository;
	@InjectMocks
	private ManagerServiceImpl managerService;

	@Test
	void createManager() {
		Manager manager = new Manager();
		ManagerDto movieDto = new ManagerDto();
		movieDto.setManagerId(1);
		movieDto.setManagerName("Hello");
		manager.setManagerId(movieDto.getManagerId());
		manager.setManagerName(movieDto.getManagerName());
		when(managerRepository.save(any(Manager.class))).thenReturn(manager);
		ManagerDto savedDto = managerService.createManager(movieDto);
		assertEquals(movieDto.getManagerId(), savedDto.getManagerId());
		assertEquals(movieDto.getManagerName(), savedDto.getManagerName());
	}

	@Test
	void getManagerById() {
		Manager manager = new Manager();
		manager.setManagerId(1);
		manager.setManagerName("Test Manager");
		when(managerRepository.findById(1)).thenReturn(java.util.Optional.of(manager));

		// When
		ManagerDto managerDto = managerService.getManagerById(1);

		// Then
		assertEquals(manager.getManagerId(), managerDto.getManagerId());
		assertEquals(manager.getManagerName(), managerDto.getManagerName());

	}
}
