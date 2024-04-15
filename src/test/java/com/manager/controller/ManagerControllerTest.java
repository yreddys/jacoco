package com.manager.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.manager.dto.ManagerDto;
import com.manager.service.ManagerService;

@WebMvcTest(ManagerController.class)
public class ManagerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ManagerService managerService;

    @Test
    void createManager() throws Exception {
        // Arrange
        ManagerDto managerDto = new ManagerDto();
        managerDto.setManagerId(1);
        managerDto.setManagerName("Hello");

        // Stubbing the createManager method of managerService mock to return managerDto
        when(managerService.createManager(any(ManagerDto.class))).thenReturn(managerDto);

        // Act & Assert
        mockMvc.perform(post("/manager/save")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(managerDto)))
                .andExpect(status().isCreated());
    }

    @Test
    void getManagerById() throws Exception {
        // Arrange
        ManagerDto managerDto = new ManagerDto();
        managerDto.setManagerId(1);
        managerDto.setManagerName("Test Manager");

        // Stubbing the getManagerById method of managerService mock to return managerDto
        when(managerService.getManagerById(anyInt())).thenReturn(managerDto);

        // Act & Assert
        mockMvc.perform(get("/manager/{managerId}", 1))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(new ObjectMapper().writeValueAsString(managerDto)));
    }
}
