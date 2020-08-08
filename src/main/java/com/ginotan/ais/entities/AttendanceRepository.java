package com.ginotan.ais.entities;

import java.time.LocalDate;
import java.util.List;

/** Attendanceを操作するためのRepository（インターフェイス） */
public interface AttendanceRepository {
  Attendance save(Attendance attendance);

  List<Attendance> findMonth(String userId, LocalDate monthDate);
}
