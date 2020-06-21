package com.ginotan.ais.interfaces.gateways;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.TemporalAdjusters;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringJUnitWebConfig
@DataJpaTest
public class AttendanceJPARepositoryTests {

  private static final String USER_ID_1 = "N99999";
  private static final String USER_ID_2 = "N99998";
  private static final LocalDate MONTH_DATE = LocalDate.of(2020, 6, 1);
  private static final LocalDate DAY1 = LocalDate.of(2020, 5, 31);
  private static final LocalDate DAY2 = LocalDate.of(2020, 6, 1);
  private static final LocalDate DAY3 = LocalDate.of(2020, 6, 30);
  private static final LocalDate DAY4 = LocalDate.of(2020, 7, 1);
  private static final LocalTime START_TIME = LocalTime.of(10, 0);
  private static final LocalTime END_TIME = LocalTime.of(18, 0);

  @Autowired private AttendanceJPARepository attendanceJPARepository;
  @Autowired private TestEntityManager testEntityManager;

  @BeforeEach
  void setup() {
    testEntityManager.persist(
        AttendanceEntity.builder()
            .attendanceEntityKey(
                AttendanceEntityKey.builder().userId(USER_ID_1).attendanceDate(DAY4).build())
            .startTime(START_TIME)
            .endTime(END_TIME)
            .build());
    testEntityManager.persist(
        AttendanceEntity.builder()
            .attendanceEntityKey(
                AttendanceEntityKey.builder().userId(USER_ID_1).attendanceDate(DAY3).build())
            .startTime(START_TIME)
            .endTime(END_TIME)
            .build());
    testEntityManager.persist(
        AttendanceEntity.builder()
            .attendanceEntityKey(
                AttendanceEntityKey.builder().userId(USER_ID_1).attendanceDate(DAY2).build())
            .startTime(START_TIME)
            .endTime(END_TIME)
            .build());
    testEntityManager.persist(
        AttendanceEntity.builder()
            .attendanceEntityKey(
                AttendanceEntityKey.builder().userId(USER_ID_1).attendanceDate(DAY1).build())
            .startTime(START_TIME)
            .endTime(END_TIME)
            .build());
    testEntityManager.persist(
        AttendanceEntity.builder()
            .attendanceEntityKey(
                AttendanceEntityKey.builder().userId(USER_ID_2).attendanceDate(DAY2).build())
            .startTime(START_TIME)
            .endTime(END_TIME)
            .build());
  }

  @Test
  void findMonthTest() {
    List<AttendanceEntity> actual =
        attendanceJPARepository
            .findByAttendanceEntityKeyUserIdAndAttendanceEntityKeyAttendanceDateBetweenOrderByAttendanceEntityKeyAttendanceDate(
                USER_ID_1,
                MONTH_DATE.with(TemporalAdjusters.firstDayOfMonth()),
                MONTH_DATE.with(TemporalAdjusters.lastDayOfMonth()));

    System.out.println(actual);

    // 取得できるデータはUSER_ID_1のDAY2,Day3を想定。尚、取得順はMONTH_DATEの昇順
    assertThat(actual).size().isEqualTo(2);
    assertThat(actual.get(0).getAttendanceEntityKey().getUserId()).isEqualTo(USER_ID_1);
    assertThat(actual.get(0).getAttendanceEntityKey().getAttendanceDate()).isEqualTo(DAY2);
    assertThat(actual.get(1).getAttendanceEntityKey().getUserId()).isEqualTo(USER_ID_1);
    assertThat(actual.get(1).getAttendanceEntityKey().getAttendanceDate()).isEqualTo(DAY3);
  }
}
