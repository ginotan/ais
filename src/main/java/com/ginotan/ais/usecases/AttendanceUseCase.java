package com.ginotan.ais.usecases;

import com.ginotan.ais.entities.Attendance;
import com.ginotan.ais.entities.AttendanceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AttendanceUseCase {
  private final AttendanceRepository attendanceRepository;

  public List<Attendance> getMonthlyAttendance(String userId, LocalDate monthDate) {
    return attendanceRepository.findMonth(userId, monthDate);
  }

  public Attendance addAttendance(Attendance attendance) {
    return attendanceRepository.save(attendance);
  }
}
