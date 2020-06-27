package com.ginotan.ais.entities;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Builder
public class Attendance {
  private String userId;
  private LocalDate attendanceDate;
  private LocalTime startTime;
  private LocalTime endTime;
}
