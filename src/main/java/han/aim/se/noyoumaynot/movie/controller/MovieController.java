package han.aim.se.noyoumaynot.movie.controller;

import han.aim.se.noyoumaynot.movie.domain.Movie;
import han.aim.se.noyoumaynot.movie.repository.UserToken;
import han.aim.se.noyoumaynot.movie.service.AuthenticationService;
import han.aim.se.noyoumaynot.movie.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.naming.AuthenticationException;
import java.util.ArrayList;

@RestController
@RequestMapping("/movies")
public class MovieController {
    private final MovieService movieService;
    private final AuthenticationService authenticationService;

    // Use constant username and password as per the requirement
    private final String USERNAME = "noah";
    private final String PASSWORD = "12345";

    // Public variable to store the generated token
    public String authToken;

    @Autowired
    public MovieController(MovieService movieService, AuthenticationService authenticationService) {
        this.movieService = movieService;
        this.authenticationService = authenticationService;
    }

    @GetMapping
    public ArrayList<Movie> getAllMovies(@RequestHeader("Authorization") String token) throws Exception {
        authenticate(token);
        return movieService.getMovieList();
    }

    @GetMapping("/show")
    public Movie getMovieById(@RequestParam("id") String id, @RequestHeader("Authorization") String token) throws Exception {
        authenticate(token);
        return movieService.getMovieById(id);
    }

    @PostMapping("/add")
    public Movie addMovie(@RequestBody Movie movie, @RequestHeader("Authorization") String token) throws Exception {
        authenticate(token);
        movieService.insertMovie(movie);
        return movie;
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteMovie(@PathVariable("id") String id, @RequestHeader("Authorization") String token) throws Exception {
        authenticate(token);
        movieService.deleteMovie(id);
        return ResponseEntity.ok().build();
    }

    // Login method bound to /login (GET request without parameters)
    @GetMapping("/login")
    public ResponseEntity<String> login() {
        UserToken userToken = authenticationService.login(USERNAME, PASSWORD);

        if (userToken != null) {
            // Store the generated token in the public variable
            authToken = userToken.getToken();
            return ResponseEntity.ok("Login successful! Your token: " + authToken);
        } else {
            return ResponseEntity.status(401).body("Invalid username or password");
        }
    }

    // Authentication method that uses the public authToken
    private String authenticate(String token) throws Exception {
        // Check if the provided token matches the stored one
        if (authToken != null && authToken.equals(token)) {
            return authenticationService.getUsername(token);
        } else {
            throw new AuthenticationException("Invalid token");
        }
    }
}
