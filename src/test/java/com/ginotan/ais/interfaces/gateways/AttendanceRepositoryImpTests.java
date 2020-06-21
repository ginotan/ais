package com.ginotan.ais.interfaces.gateways;

import com.ginotan.ais.entities.Attendance;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@SpringJUnitWebConfig
class AttendanceRepositoryImpTests {

  private static final String USER_ID = "N99999";
  private static final LocalDate MONTH_DATE = LocalDate.of(2020, 6, 1);
  private static final LocalDate DAY1 = LocalDate.of(2020, 6, 1);
  private static final LocalDate DAY2 = LocalDate.of(2020, 6, 30);
  private static final LocalTime START_TIME = LocalTime.of(10, 0);
  private static final LocalTime END_TIME = LocalTime.of(18, 0);

  private List<AttendanceEntity> attendanceEntities = new ArrayList<>();
  private AttendanceEntity attendanceEntity1;
  private AttendanceEntity attendanceEntity2;

  @Mock AttendanceJPARepository attendanceJPARepository;
  @InjectMocks AttendanceRepositoryImp attendanceRepositoryImp;

  @BeforeEach
  void setup() {
    attendanceEntity1 =
        AttendanceEntity.builder()
            .attendanceEntityKey(
                AttendanceEntityKey.builder().userId(USER_ID).attendanceDate(DAY1).build())
            .startTime(START_TIME)
            .endTime(END_TIME)
            .build();
    attendanceEntity2 =
        AttendanceEntity.builder()
            .attendanceEntityKey(
                AttendanceEntityKey.builder().userId(USER_ID).attendanceDate(DAY2).build())
            .startTime(START_TIME)
            .endTime(END_TIME)
            .build();
    attendanceEntities.add(attendanceEntity1);
    attendanceEntities.add(attendanceEntity2);
  }

  @Test
  void findMonthTest() {
    when(attendanceJPARepository
            .findByAttendanceEntityKeyUserIdAndAttendanceEntityKeyAttendanceDateBetweenOrderByAttendanceEntityKeyAttendanceDate(
                USER_ID,
                MONTH_DATE.with(TemporalAdjusters.firstDayOfMonth()),
                MONTH_DATE.with(TemporalAdjusters.lastDayOfMonth())))
        .thenReturn(attendanceEntities);

    List<Attendance> actual = attendanceRepositoryImp.findMonth(USER_ID, MONTH_DATE);

    // 呼び出すメソッドの確認
    verify(attendanceJPARepository, times(1))
        .findByAttendanceEntityKeyUserIdAndAttendanceEntityKeyAttendanceDateBetweenOrderByAttendanceEntityKeyAttendanceDate(
            USER_ID,
            MONTH_DATE.with(TemporalAdjusters.firstDayOfMonth()),
            MONTH_DATE.with(TemporalAdjusters.lastDayOfMonth()));

    // 戻り値の確認
    assertThat(actual).extracting("userId").containsExactly(USER_ID, USER_ID);
    assertThat(actual).extracting("attendanceDate").containsExactly(DAY1, DAY2);
    assertThat(actual).extracting("startTime").containsExactly(START_TIME, START_TIME);
    assertThat(actual).extracting("endTime").containsExactly(END_TIME, END_TIME);
  }
}
