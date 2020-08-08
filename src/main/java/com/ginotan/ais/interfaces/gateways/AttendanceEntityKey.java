package com.ginotan.ais.interfaces.gateways;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.time.LocalDate;

/** Attendanceの複合主キーを表現するためのクラス */
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class AttendanceEntityKey implements Serializable {
  @Column(name = "user_id")
  private String userId;

  @Column(name = "attendance_date")
  private LocalDate attendanceDate;
}
