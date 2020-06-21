package com.ginotan.ais.entities;

import java.time.LocalDate;
import java.util.List;

public interface AttendanceRepository {
  List<Attendance> findMonth(String userId, LocalDate monthDate);
}
