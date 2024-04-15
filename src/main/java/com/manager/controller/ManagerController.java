package com.manager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.manager.dto.ManagerDto;
import com.manager.service.ManagerService;

import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/manager")
@Log4j2
public class ManagerController {
	@Autowired
	private ManagerService managerService;

	@PostMapping("/save")
	ResponseEntity<ManagerDto> createManager(@RequestBody ManagerDto managerDto) {
		log.info("creating manager {}:", managerDto);
		ManagerDto managerRes = managerService.createManager(managerDto);
		return new ResponseEntity<>(managerRes, HttpStatus.CREATED);

	}
	@GetMapping("/{managerId}")
	ResponseEntity<ManagerDto> getManagerById(@PathVariable int managerId) {
		ManagerDto ApiResponseDto = managerService.getManagerById(managerId);
		return new ResponseEntity<>(ApiResponseDto, HttpStatus.OK);
	}
}
