package com.ginotan.ais.usecases;

import com.ginotan.ais.entities.Attendance;
import com.ginotan.ais.entities.AttendanceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

/** Attendanceを操作するためのService */
@Service
@RequiredArgsConstructor
public class AttendanceUseCase {
  private final AttendanceRepository attendanceRepository;

  /**
   * Attendanceの登録
   *
   * @param attendance 登録対象のAttendance
   * @return 登録されたAttendance
   */
  public Attendance addAttendance(Attendance attendance) {
    return attendanceRepository.save(attendance);
  }

  /**
   * 特定ユーザのAttendanceを月を指定してバルク取得
   *
   * @param userId 取得対象ユーザID
   * @param monthDate 取得対象月
   * @return 取得結果
   */
  public List<Attendance> getMonthlyAttendance(String userId, LocalDate monthDate) {
    return attendanceRepository.findMonth(userId, monthDate);
  }
}
