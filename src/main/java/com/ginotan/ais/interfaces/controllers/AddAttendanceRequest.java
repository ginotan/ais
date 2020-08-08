package com.ginotan.ais.interfaces.controllers;

import com.ginotan.ais.entities.Attendance;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalTime;

/** Attendanceへの登録対象データを受けるクラス */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class AddAttendanceRequest {
  @NotBlank
  @Size(max = 6)
  private String userId;

  @NotNull private LocalDate attendanceDate;

  private LocalTime startTime;
  private LocalTime endTime;

  /**
   * 登録対象データをDomainクラスへ変換する
   *
   * @return AttendanceのDomainクラス
   */
  public Attendance toDomainModel() {
    return Attendance.builder()
        .userId(userId)
        .attendanceDate(attendanceDate)
        .startTime(startTime)
        .endTime(endTime)
        .build();
  }
}
