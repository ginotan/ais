package com.ginotan.ais.interfaces.gateways;

import com.ginotan.ais.entities.Attendance;
import com.ginotan.ais.entities.AttendanceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class AttendanceRepositoryImp implements AttendanceRepository {

  @Autowired private AttendanceJPARepository attendanceJPARepository;

  @Override
  public List<Attendance> findMonth(String userId, LocalDate monthDate) {
    List<AttendanceEntity> attendances =
        attendanceJPARepository
            .findByAttendanceEntityKeyUserIdAndAttendanceEntityKeyAttendanceDateBetweenOrderByAttendanceEntityKeyAttendanceDate(
                userId,
                monthDate.with(TemporalAdjusters.firstDayOfMonth()),
                monthDate.with(TemporalAdjusters.lastDayOfMonth()));
    return attendances.stream().map(AttendanceEntity::toDomainModel).collect(Collectors.toList());
  }
}
