package com.ginotan.ais.interfaces.gateways;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface AttendanceJPARepository extends JpaRepository<AttendanceEntity, Integer> {
  List<AttendanceEntity>
      findByAttendanceEntityKeyUserIdAndAttendanceEntityKeyAttendanceDateBetweenOrderByAttendanceEntityKeyAttendanceDate(
          String userId, LocalDate since, LocalDate until);
}
