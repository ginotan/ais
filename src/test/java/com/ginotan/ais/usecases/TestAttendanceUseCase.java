package com.ginotan.ais.usecases;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;

@SpringJUnitWebConfig
public class TestAttendanceUseCase {

  @InjectMocks private AttendanceUseCase attendanceUseCase;

  @Test
  void getMonthlyAttendanceTest() {
    attendanceUseCase.getMonthlyAttendance("N99999", "2020-06");
  }
}
