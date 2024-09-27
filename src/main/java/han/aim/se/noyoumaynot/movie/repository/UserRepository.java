package han.aim.se.noyoumaynot.movie.repository;

import han.aim.se.noyoumaynot.movie.controller.AuthenticationException;
import han.aim.se.noyoumaynot.movie.domain.Role;
import han.aim.se.noyoumaynot.movie.domain.User;
import han.aim.se.noyoumaynot.movie.security.SecurePass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserRepository {
    private final JdbcTemplate jdbcTemplate;

    public UserRepository(@Autowired JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Optional<User> findByUsername(String username) {
        User user = jdbcTemplate.queryForObject(
                "SELECT username, password, roles.name AS rolename, roles.isadmin FROM roles, user_roles, users WHERE roles.roleid = user_roles.roleid AND users.userid = user_roles.userid AND users.username = ?",
                new UserRowMapper(), username);
        return Optional.of(user);
    }
}
