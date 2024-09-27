package han.aim.se.noyoumaynot.movie.controller;

import han.aim.se.noyoumaynot.movie.domain.Movie;
import han.aim.se.noyoumaynot.movie.domain.Role;
import han.aim.se.noyoumaynot.movie.domain.User;
import han.aim.se.noyoumaynot.movie.service.AuthenticationService;
import han.aim.se.noyoumaynot.movie.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class MovieController {
    private final MovieService movieService;
    private final AuthenticationService authenticationService;

    @Autowired
    public MovieController(MovieService movieService, AuthenticationService authenticationService) {
        this.movieService = movieService;
        this.authenticationService = authenticationService;
    }

    @PostMapping("/auth/login")
    public ResponseEntity<JwtAuthResponse> login(@RequestBody LoginDTO loginDto){
        String token = authenticationService.login(loginDto);

        JwtAuthResponse jwtAuthResponse = new JwtAuthResponse();
        jwtAuthResponse.setAccessToken(token);

        return new ResponseEntity<>(jwtAuthResponse, HttpStatus.OK);
    }

    @GetMapping("/movies")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public List<Movie> getAllMovies(@RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) String token) {
        return movieService.getMovieList();
    }

    @GetMapping("/movies/show")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public Movie getMovieById(@RequestParam("id") String id, @RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) String token) {
        Movie movie = movieService.getMovieById(id);
        return movie;
    }

    @PostMapping("/movies/add")
    @PreAuthorize("hasRole('ADMIN')")
    public Movie addMovie(@RequestBody Movie movie, @RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) String token) {
        movieService.insertMovie(movie);
        return movie;
    }

    @DeleteMapping("/movies/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteMovie(@PathVariable("id") String id, @RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) String token) {
        movieService.deleteMovie(id);
        return ResponseEntity.ok().build();
    }
}
