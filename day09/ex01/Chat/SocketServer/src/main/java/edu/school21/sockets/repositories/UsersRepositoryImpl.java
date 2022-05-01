package edu.school21.sockets.repositories;

import edu.school21.sockets.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Component("usersRepository")
public class UsersRepositoryImpl implements UsersRepository {
    private final DataSource dataSource;
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UsersRepositoryImpl(DataSource dataSource) {
        this.dataSource = dataSource;
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public User findById(Long id) {
        return jdbcTemplate.query("SELECT * FROM public.\"users\" WHERE id = ?", new Object[]{id}, new UserMapper())
                .stream().findAny().orElse(null);
    }

    @Override
    public List<User> findAll() {
        return jdbcTemplate.query("SELECT * from public.\"users\"",
                new UserMapper());
    }

    @Override
    public void save(User entity) {
            jdbcTemplate.update("INSERT INTO public.\"users\" VALUES (?, ?, ?)",
                    entity.getId(), entity.getUsername(), entity.getPassword());
    }

    @Override
    public void update(User entity) {
        jdbcTemplate.update("UPDATE public.\"users\" SET username = ?, password = ? WHERE id = ?;",
                entity.getUsername(), entity.getPassword(), entity.getId());
    }

    @Override
    public void delete(Long id) {
        jdbcTemplate.update("DELETE FROM public.\"users\" WHERE id = ?", id);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return jdbcTemplate.query("SELECT * FROM public.\"users\" WHERE username = ?", new Object[]{username}, new UserMapper())
                .stream().findAny();
    }

    @Override
    public void saveMessage(String message) {
        String[] replaced = message.replaceAll("'", "").split(" ");
        jdbcTemplate.update("INSERT INTO public.\"messages\" (time, username, message) VALUES (?, ?, ?)",
                replaced[0], replaced[1], replaced[2]);
    }

    @Override
    public Long getMaxId() {
        String sql = "SELECT MAX(id) FROM public.\"users\" WHERE id IS NOT NULL";
        return jdbcTemplate.queryForObject(sql, Long.class);
    }

    public static class UserMapper implements RowMapper<User> {

        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new User(rs.getLong("id"), rs.getString("username"), rs.getString("password"));
        }
    }
}