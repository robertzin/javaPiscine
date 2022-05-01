package edu.school21.spring.service.repositories;

import edu.school21.spring.service.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import javax.sql.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Component("hikariRepository")
public class UsersRepositoryJdbcTemplateImpl implements UsersRepository {
    private final DataSource dataSource;
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UsersRepositoryJdbcTemplateImpl(@Qualifier("hikariDataSource") DataSource dataSource) {
        this.dataSource = dataSource;
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public User findById(Long id) {
        return jdbcTemplate.query("SELECT * FROM public.\"user\" WHERE id = ?", new Object[]{id}, new UserMapper())
                .stream().findAny().orElse(null);
    }

    @Override
    public List<User> findAll() {
        return jdbcTemplate.query("SELECT * from public.\"user\"", new UserMapper());
    }

    @Override
    public void save(User entity, String password) {
        jdbcTemplate.query("INSERT INTO public.\"user\" VALUES (?, ?, ?)", new Object[]{entity.getId(), entity.getEmail(), password}, new UserMapper());
    }

    @Override
    public void update(User entity) {
        jdbcTemplate.query("UPDATE public.\"user\" SET email = ? WHERE id = ?;", new Object[]{entity.getEmail(), entity.getId()}, new UserMapper());
    }

    @Override
    public void delete(Long id) {
        try {
            jdbcTemplate.query("DELETE FROM public.\"user\" WHERE id = ?", new Object[]{id}, new UserMapper());
        } catch (Exception ignored) {}
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return jdbcTemplate.query("SELECT * FROM public.\"user\" WHERE email = ?", new Object[]{email}, new UserMapper())
                .stream().findAny();
    }

    public static class UserMapper implements RowMapper<User> {

        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new User(rs.getLong("id"), rs.getString("email"));
        }
    }
}
