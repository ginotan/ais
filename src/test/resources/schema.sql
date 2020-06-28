CREATE TABLE IF NOT EXISTS attendances (
    user_id CHAR(6) NOT NULL,
    attendance_date DATE NOT NULL,
    start_time TIME,
    end_time TIME,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (user_id,attendance_date)
  );