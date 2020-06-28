package com.ginotan.ais.interfaces.controllers;

import com.ginotan.ais.entities.Attendance;
import com.ginotan.ais.usecases.AttendanceUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringJUnitWebConfig
public class AttendanceControllerTest {

  private static final String USER_ID = "N99999";
  private static final LocalDate MONTH_DATE = LocalDate.of(2020, 6, 1);
  private static final LocalDate DAY1 = LocalDate.of(2020, 6, 1);
  private static final LocalDate DAY2 = LocalDate.of(2020, 6, 2);
  private static final LocalTime START_TIME = LocalTime.of(10, 0);
  private static final LocalTime END_TIME = LocalTime.of(18, 0);

  private List<Attendance> attendances = new ArrayList<>();
  private Attendance attendance1;
  private Attendance attendance2;

  @Mock private AttendanceUseCase attendanceUseCase;
  @InjectMocks private AttendanceController attendanceController;
  private MockMvc mockMvc;

  @BeforeEach
  void setup() {
    attendance1 =
        Attendance.builder()
            .userId(USER_ID)
            .attendanceDate(DAY1)
            .startTime(START_TIME)
            .endTime(END_TIME)
            .build();
    attendance2 =
        Attendance.builder()
            .userId(USER_ID)
            .attendanceDate(DAY2)
            .startTime(START_TIME)
            .endTime(END_TIME)
            .build();
    attendances.add(attendance1);
    attendances.add(attendance2);

    mockMvc = MockMvcBuilders.standaloneSetup(attendanceController).alwaysDo(log()).build();
  }

  @Test
  void getMonthlyAttendanceTest() throws Exception {
    when(attendanceUseCase.getMonthlyAttendance(USER_ID, MONTH_DATE)).thenReturn(attendances);
    mockMvc
        .perform(get("/v1/attendance/N99999/2020-06-01"))
        .andExpect(status().is(200))
        .andExpect(jsonPath("[0].userId").value(USER_ID))
        .andExpect(jsonPath("[0].attendanceDate[0]").value(2020))
        .andExpect(jsonPath("[0].attendanceDate[1]").value(6))
        .andExpect(jsonPath("[0].attendanceDate[2]").value(1))
        .andExpect(jsonPath("[0].startTime[0]").value(10))
        .andExpect(jsonPath("[0].startTime[1]").value(0))
        .andExpect(jsonPath("[0].endTime[0]").value(18))
        .andExpect(jsonPath("[0].endTime[1]").value(0))
        .andExpect(jsonPath("[1].userId").value(USER_ID))
        .andExpect(jsonPath("[1].attendanceDate[0]").value(2020))
        .andExpect(jsonPath("[1].attendanceDate[1]").value(6))
        .andExpect(jsonPath("[1].attendanceDate[2]").value(2))
        .andExpect(jsonPath("[1].startTime[0]").value(10))
        .andExpect(jsonPath("[1].startTime[1]").value(0))
        .andExpect(jsonPath("[1].endTime[0]").value(18))
        .andExpect(jsonPath("[1].endTime[1]").value(0));

    verify(attendanceUseCase, times(1)).getMonthlyAttendance(USER_ID, MONTH_DATE);
  }
}
