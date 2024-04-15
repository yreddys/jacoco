package com.manager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manager.dto.ManagerDto;
import com.manager.entity.Manager;
import com.manager.repository.ManagerRepository;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class ManagerServiceImpl implements ManagerService {
	@Autowired
	private ManagerRepository managerRepository;

	@Override
	public ManagerDto createManager(ManagerDto managerDto) {
		Manager manager = new Manager();
		manager.setManagerName(managerDto.getManagerName());
		log.info("manager deatils saved {}", manager);
		manager = managerRepository.save(manager);
		ManagerDto Managerdto = new ManagerDto();
		Managerdto.setManagerId(manager.getManagerId());
		Managerdto.setManagerName(manager.getManagerName());
		log.info("saved manager details {}", Managerdto);
		return Managerdto;
	}

}
