package com.ginotan.ais.usecases;

import com.ginotan.ais.entities.Attendance;
import com.ginotan.ais.entities.AttendanceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@SpringJUnitWebConfig
public class AttendanceUseCaseTest {

  private static final String USER_ID = "N99999";
  private static final LocalDate MONTH_DATE = LocalDate.of(2020, 6, 1);
  private static final LocalDate DAY1 = LocalDate.of(2020, 6, 1);
  private static final LocalDate DAY2 = LocalDate.of(2020, 6, 2);
  private static final LocalDate DAY3 = LocalDate.of(2020, 6, 3);
  private static final LocalTime START_TIME = LocalTime.of(10, 0);
  private static final LocalTime END_TIME = LocalTime.of(18, 0);

  private List<Attendance> attendances = new ArrayList<>();
  private Attendance attendance1;
  private Attendance attendance2;
  private Attendance attendance3;

  @Mock private AttendanceRepository attendanceRepository;
  @InjectMocks private AttendanceUseCase attendanceUseCase;

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
    attendance3 =
        Attendance.builder()
            .userId(USER_ID)
            .attendanceDate(DAY3)
            .startTime(START_TIME)
            .endTime(END_TIME)
            .build();
    attendances.add(attendance1);
    attendances.add(attendance2);
    attendances.add(attendance3);
  }

  @Test
  void getMonthlyAttendanceTest() {
    when(attendanceRepository.findMonth(USER_ID, MONTH_DATE)).thenReturn(attendances);

    List<Attendance> actual = attendanceUseCase.getMonthlyAttendance(USER_ID, MONTH_DATE);

    verify(attendanceRepository, times(1)).findMonth(USER_ID, MONTH_DATE);
    assertThat(actual.get(0)).isEqualTo(attendance1);
    assertThat(actual.get(1)).isEqualTo(attendance2);
    assertThat(actual.get(2)).isEqualTo(attendance3);
  }
}
