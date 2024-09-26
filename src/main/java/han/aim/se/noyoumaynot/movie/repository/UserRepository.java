package han.aim.se.noyoumaynot.movie.repository;

import han.aim.se.noyoumaynot.movie.controller.AuthenticationException;
import han.aim.se.noyoumaynot.movie.domain.Role;
import han.aim.se.noyoumaynot.movie.domain.User;
import han.aim.se.noyoumaynot.movie.security.SecurePass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {
    private final JdbcTemplate jdbcTemplate;
    private final SecurePass securePass;

    public UserRepository(@Autowired JdbcTemplate jdbcTemplate, @Autowired SecurePass securePass) {
        this.jdbcTemplate = jdbcTemplate;
        this.securePass = securePass;
    }

    public Role getRoleForUser(String username) {
        return jdbcTemplate.query("SELECT roles.name, roles.isAdmin from roles, user_roles, users WHERE roles.roleid = user_roles.roleid AND users.userid = user_roles.userid AND users.username = ?",
                BeanPropertyRowMapper.newInstance(Role.class),
                username
        ).get(0);
    }

    public boolean isValidUser(String username, String password) {
        User user = jdbcTemplate.query(
                "SELECT salt, password FROM users WHERE username = ?",
                BeanPropertyRowMapper.newInstance(User.class),
                username
        ).get(0);
        try {
            return securePass.validatePassword(password, user.getPassword(), user.getSalt());
        } catch (Exception e) {
            throw new AuthenticationException("Invalid password");
        }
    }
}
