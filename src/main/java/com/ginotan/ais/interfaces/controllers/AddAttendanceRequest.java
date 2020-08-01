package com.ginotan.ais.interfaces.controllers;

import com.ginotan.ais.entities.Attendance;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class AddAttendanceRequest {
  private String userId;
  private LocalDate attendanceDate;
  private LocalTime startTime;
  private LocalTime endTime;

  public Attendance toDomainModel() {
    return Attendance.builder()
        .userId(userId)
        .attendanceDate(attendanceDate)
        .startTime(startTime)
        .endTime(endTime)
        .build();
  }
}
