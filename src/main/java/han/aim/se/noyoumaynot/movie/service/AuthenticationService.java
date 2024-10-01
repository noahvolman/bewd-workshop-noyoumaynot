package han.aim.se.noyoumaynot.movie.service;

import han.aim.se.noyoumaynot.movie.repository.UserToken;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class AuthenticationService {

  // Constant username and password, similar to MovieController
  private final String USERNAME = "noah";
  private final String PASSWORD = "12345";

  // List to store active tokens
  private ArrayList<UserToken> userTokens = new ArrayList<>();

  // Login method to authenticate the user and generate a token
  public UserToken login(String username, String password) {
    // Check if the provided username and password match the constants
    if (this.USERNAME.equals(username) && this.PASSWORD.equals(password)) {
      // Create a new token if credentials match
      UserToken newToken = new UserToken(username);
      userTokens.add(newToken); // Store the token
      return newToken; // Return the generated token
    }
    // If credentials don't match, return null
    return null;
  }

  // Check if a token is valid by searching the list of active tokens
  public boolean isValidToken(String token) {
    return userTokens.stream().anyMatch(userToken -> userToken.getToken().equals(token));
  }

  // Retrieve the username associated with a token
  public String getUsername(String token) {
    return userTokens.stream()
            .filter(userToken -> userToken.getToken().equals(token))
            .map(UserToken::getUsername)
            .findFirst()
            .orElse(null);
  }
}
