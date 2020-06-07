package com.ginotan.ais.interfaces.controllers;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/v1/attendance")
public class AttendanceController {

  @RequestMapping(value = "/{id}/{date}", method = RequestMethod.GET)
  public void getAttendance(
      @PathVariable String id,
      @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {}
}
