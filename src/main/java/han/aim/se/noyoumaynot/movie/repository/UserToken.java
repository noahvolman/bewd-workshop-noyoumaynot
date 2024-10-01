package han.aim.se.noyoumaynot.movie.repository;

import java.time.Instant;
import java.util.UUID;

public class UserToken {
    private final int TOKENVALIDTIME = 60 * 10; // Token valid for 10 minutes
    private String token;
    private String username;
    private long expiresIn;
    private Instant creationTime;

    public UserToken(String username) {
        this.username = username;
        this.expiresIn = TOKENVALIDTIME;
        this.token = UUID.randomUUID().toString();
        this.creationTime = Instant.now();
    }

    public String getToken() {
        return token;
    }

    public String getUsername() {
        return username;
    }

    // Check if the token is expired
    public boolean isExpired() {
        Instant now = Instant.now();
        return now.isAfter(creationTime.plusSeconds(expiresIn));
    }
}
