package com.ginotan.ais.entities;

import lombok.Builder;

import java.time.LocalDate;
import java.time.LocalTime;

@Builder
public class Attendance {
  private String userId;
  private LocalDate date;
  private LocalTime startTime;
  private LocalTime endTime;
}
