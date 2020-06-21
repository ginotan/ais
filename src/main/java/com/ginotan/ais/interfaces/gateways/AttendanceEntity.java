package com.ginotan.ais.interfaces.gateways;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalTime;

@Entity
@Data
@Builder
@Table(name = "attendances")
public class AttendanceEntity {

  @EmbeddedId private AttendanceEntityKey attendanceEntityKey;

  @Column(name = "start_time")
  private LocalTime startTime;

  @Column(name = "end_time")
  private LocalTime endTime;
}
