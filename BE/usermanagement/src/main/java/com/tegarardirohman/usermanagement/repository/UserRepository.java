package com.tegarardirohman.usermanagement.repository;

import com.tegarardirohman.usermanagement.model.entity.User;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class UserRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public UserRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // Create user
    public int save(User user) {
        String sql = "INSERT INTO users (id, email, password, name, surname, phone, role, status, created_at, updated_at) " +
                "VALUES (:id, :email, :password, :name, :surname, :phone, :role, :status, :created_at, :updated_at)";

        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("id", user.getId())
                .addValue("email", user.getEmail())
                .addValue("password", user.getPassword())
                .addValue("name", user.getName())
                .addValue("surname", user.getSurname())
                .addValue("phone", user.getPhone())
                .addValue("role", user.getRole())
                .addValue("status", user.getStatus())
                .addValue("created_at", user.getCreatedAt())
                .addValue("updated_at", user.getUpdatedAt());

        return jdbcTemplate.update(sql, params);
    }

    // Find user by email
    public User findByEmail(String email) {
        String sql = "SELECT * FROM users WHERE email = :email";

        MapSqlParameterSource params = new MapSqlParameterSource().addValue("email", email);
        return jdbcTemplate.queryForObject(sql, params, (rs, rowNum) -> {
            User user = new User();
            user.setId(UUID.fromString(rs.getString("id")));
            user.setEmail(rs.getString("email"));
            user.setPassword(rs.getString("password"));
            user.setName(rs.getString("name"));
            user.setSurname(rs.getString("surname"));
            user.setPhone(rs.getString("phone"));
            user.setRole(rs.getString("role"));
            user.setStatus(rs.getString("status"));
            user.setCreatedAt(rs.getLong("created_at"));
            user.setUpdatedAt(rs.getLong("updated_at"));
            return user;
        });
    }

    // Find user by id
    public User findById(String id) {
        String sql = "SELECT * FROM users WHERE id = :id";

        MapSqlParameterSource params = new MapSqlParameterSource().addValue("id", id);
        return jdbcTemplate.queryForObject(sql, params, (rs, rowNum) -> {
            User user = new User();
            user.setId(UUID.fromString(rs.getString("id")));
            user.setEmail(rs.getString("email"));
            user.setPassword(rs.getString("password"));
            user.setName(rs.getString("name"));
            user.setSurname(rs.getString("surname"));
            user.setPhone(rs.getString("phone"));
            user.setRole(rs.getString("role"));
            user.setStatus(rs.getString("status"));
            user.setCreatedAt(rs.getLong("created_at"));
            user.setUpdatedAt(rs.getLong("updated_at"));
            return user;
        });
    }

    // Soft delete user by id
    public int softDelete(UUID userId) {
        String sql = "UPDATE users SET status = 'INACTIVE', updated_at = :updated_at WHERE id = :id";

        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("id", userId)
                .addValue("updated_at", System.currentTimeMillis());

        return jdbcTemplate.update(sql, params);
    }

    // Update user
    public int update(User user) {
        String sql = "UPDATE users SET email = :email, password = :password, name = :name, surname = :surname, " +
                "phone = :phone, role = :role, status = :status, updated_at = :updated_at WHERE id = :id";

        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("id", user.getId())
                .addValue("email", user.getEmail())
                .addValue("password", user.getPassword())
                .addValue("name", user.getName())
                .addValue("surname", user.getSurname())
                .addValue("phone", user.getPhone())
                .addValue("role", user.getRole())
                .addValue("status", user.getStatus())
                .addValue("updated_at", user.getUpdatedAt());

        return jdbcTemplate.update(sql, params);
    }
}
