package com.ginotan.ais.interfaces.gateways;

import com.ginotan.ais.entities.Attendance;
import com.ginotan.ais.entities.AttendanceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.List;
import java.util.stream.Collectors;

/** Attendanceを操作するためのRepository（実装クラス） */
@Repository
@RequiredArgsConstructor
public class AttendanceRepositoryImp implements AttendanceRepository {

  @Autowired private AttendanceJPARepository attendanceJPARepository;

  /**
   * DBへAttendanceの登録
   *
   * @param attendance 登録対象のAttendance
   * @return 登録されたAttendance
   */
  @Override
  public Attendance save(Attendance attendance) {
    return attendanceJPARepository
        .save(AttendanceEntity.fromDomainModel(attendance))
        .toDomainModel();
  }

  /**
   * DBから特定ユーザのAttendanceを月を指定してバルク取得
   *
   * @param userId 取得対象ユーザID
   * @param monthDate 取得対象月
   * @return 取得結果
   */
  @Override
  public List<Attendance> findMonth(String userId, LocalDate monthDate) {
    List<AttendanceEntity> attendances =
        attendanceJPARepository
            .findByAttendanceEntityKeyUserIdAndAttendanceEntityKeyAttendanceDateBetweenOrderByAttendanceEntityKeyAttendanceDate(
                userId,
                monthDate.with(TemporalAdjusters.firstDayOfMonth()),
                monthDate.with(TemporalAdjusters.lastDayOfMonth()));
    return attendances.stream().map(AttendanceEntity::toDomainModel).collect(Collectors.toList());
  }
}
