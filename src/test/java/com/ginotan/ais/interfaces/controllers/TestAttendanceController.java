package com.ginotan.ais.interfaces.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringJUnitWebConfig
public class TestAttendanceController {

  MockMvc mockMvc;

  @BeforeEach
  void setup() {
    mockMvc = MockMvcBuilders.standaloneSetup(new AttendanceController()).alwaysDo(log()).build();
  }

  @Test
  void getMonthlyAttendanceTest() throws Exception {
    mockMvc
        .perform(MockMvcRequestBuilders.get("/v1/attendance/N99999/2020-06-07"))
        .andExpect(status().is(200));
  }
}
