package han.aim.se.noyoumaynot.movie.service;

import han.aim.se.noyoumaynot.movie.controller.AuthenticationException;
import han.aim.se.noyoumaynot.movie.domain.Role;
import han.aim.se.noyoumaynot.movie.repository.UserRepository;
import han.aim.se.noyoumaynot.movie.repository.UserToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;

@Service
public class AuthenticationService {
    private final UserRepository userRepository;
    private ArrayList<UserToken> userTokens = new ArrayList<>();

    public AuthenticationService(@Autowired UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserToken login(String username, String password) {
        if (userRepository.isValidUser(username, password)) {
            UserToken userToken = new UserToken(username);
            userTokens.add(userToken);
            return userToken;
        }
        throw new AuthenticationException("Invalid username or password");
    }

    public boolean isValidToken(String token) {
        return userTokens.stream().anyMatch(userToken -> userToken.getToken().equals(token));
    }

    public String getUsername(String token) {
        return userTokens.stream().filter(userToken -> userToken.getToken().equals(token)).findFirst().get().getUsername();
    }

    public Role getRole(String token) {
        return userRepository.getRoleForUser(getUsername(token));
    }
}
