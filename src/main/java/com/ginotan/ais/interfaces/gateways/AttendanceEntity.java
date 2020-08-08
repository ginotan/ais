package com.ginotan.ais.interfaces.gateways;

import com.ginotan.ais.entities.Attendance;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalTime;

/** AttendanceのEntityクラス */
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Table(name = "attendances")
public class AttendanceEntity {

  @EmbeddedId private AttendanceEntityKey attendanceEntityKey;

  @Column(name = "start_time")
  private LocalTime startTime;

  @Column(name = "end_time")
  private LocalTime endTime;

  /**
   * DomainクラスからEntityクラスへの変換
   *
   * @param attendance Attendanceのドメインクラス
   * @return 変換後のEntityクラス
   */
  public static AttendanceEntity fromDomainModel(Attendance attendance) {
    return AttendanceEntity.builder()
        .attendanceEntityKey(
            AttendanceEntityKey.builder()
                .userId(attendance.getUserId())
                .attendanceDate(attendance.getAttendanceDate())
                .build())
        .startTime(attendance.getStartTime())
        .endTime(attendance.getEndTime())
        .build();
  }

  /**
   * EntityクラスからDomainクラスへの変換
   *
   * @return 変換後のDomainクラス
   */
  public Attendance toDomainModel() {
    return Attendance.builder()
        .userId(attendanceEntityKey.getUserId())
        .attendanceDate(attendanceEntityKey.getAttendanceDate())
        .startTime(startTime)
        .endTime(endTime)
        .build();
  }
}
