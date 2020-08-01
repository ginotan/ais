package com.ginotan.ais.interfaces.controllers;

import com.ginotan.ais.entities.Attendance;
import com.ginotan.ais.usecases.AttendanceUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/v1/attendance")
public class AttendanceController {

  @Autowired AttendanceUseCase attendanceUseCase;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Attendance addAttendance(
      @RequestBody @Validated AddAttendanceRequest addAttendanceRequest) {
    return attendanceUseCase.addAttendance(addAttendanceRequest.toDomainModel());
  }

  /**
   * ユーザに紐づく月の出勤情報を取得する
   *
   * @param id ユーザID（半角英数6桁）
   * @param monthDate 対象月（YYYY-MM-DD形式）
   */
  @RequestMapping(value = "/{id}/{monthDate}", method = RequestMethod.GET)
  public List<Attendance> getMonthlyAttendance(
      @PathVariable String id,
      @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate monthDate) {

    return attendanceUseCase.getMonthlyAttendance(id, monthDate);
  }
}
