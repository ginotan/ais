package com.ginotan.ais.interfaces.controllers;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/attendance")
public class AttendanceController {

  /**
   * ユーザに紐づく月の出勤情報を取得する
   *
   * @param id ユーザID（半角英数6桁）
   * @param month 対象月（YYYY-MM形式）
   */
  @RequestMapping(value = "/{id}/{month}", method = RequestMethod.GET)
  public void getMonthlyAttendance(@PathVariable String id, @PathVariable String month) {}
}
