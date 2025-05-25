package com.example.scheduleapp.repository;

import com.example.scheduleapp.model.Schedule;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ScheduleRepositoryImpl implements ScheduleRepository {

    private final DataSource dataSource;
    private final JdbcTemplate jdbcTemplate;

    private final RowMapper<Schedule> rowMapper = (rs, rowNum) -> new Schedule(
            rs.getLong("id"),
            rs.getString("task"),
            rs.getString("writer"),
            rs.getString("password"),
            rs.getTimestamp("created_at").toLocalDateTime(),
            rs.getTimestamp("updated_at").toLocalDateTime()
    );

    @Override
    public Schedule save(Schedule schedule) {
        String sql = "INSERT INTO schedule (task, writer, password, created_at, updated_at) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql,
                schedule.getTask(),
                schedule.getWriter(),
                schedule.getPassword(),
                schedule.getCreatedAt(),
                schedule.getUpdatedAt()
        );
        Long id = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Long.class);
        schedule.setId(id);
        return schedule;
    }

    @Override
    public List<Schedule> findAll(String writer, String date) {
        StringBuilder sql = new StringBuilder("SELECT * FROM schedule WHERE 1=1");
        new Object() {
            void addCondition(String column, Object value) {
                if (value != null && !value.toString().isEmpty()) {
                    sql.append(" AND ").append(column).append(" = '").append(value).append("'");
                }
            }
        }.addCondition("writer", writer);
        if (date != null && !date.isEmpty()) {
            sql.append(" AND DATE(updated_at) = '").append(date).append("'");
        }
        sql.append(" ORDER BY updated_at DESC");
        return jdbcTemplate.query(sql.toString(), rowMapper);
    }

    @Override
    public Optional<Schedule> findById(Long id) {
        String sql = "SELECT * FROM schedule WHERE id = ?";
        List<Schedule> results = jdbcTemplate.query(sql, rowMapper, id);
        return results.stream().findFirst();
    }

    @Override
    public boolean update(Long id, Schedule schedule) {
        String sql = "UPDATE schedule SET task = ?, writer = ?, updated_at = ? WHERE id = ?";
        return jdbcTemplate.update(sql,
                schedule.getTask(),
                schedule.getWriter(),
                schedule.getUpdatedAt(),
                id
        ) > 0;
    }

    @Override
    public boolean delete(Long id) {
        String sql = "DELETE FROM schedule WHERE id = ?";
        return jdbcTemplate.update(sql, id) > 0;
    }

    @Override
    public boolean checkPassword(Long id, String password) {
        String sql = "SELECT COUNT(*) FROM schedule WHERE id = ? AND password = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, id, password);
        return count != null && count > 0;
    }
}