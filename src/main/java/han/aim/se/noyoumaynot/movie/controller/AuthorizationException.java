package han.aim.se.noyoumaynot.movie.controller;

public class AuthorizationException extends RuntimeException {
    public AuthorizationException(String message) {
        super(message);
    }
}
