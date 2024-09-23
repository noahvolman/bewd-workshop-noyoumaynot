package han.aim.se.noyoumaynot.movie.controller;

import han.aim.se.noyoumaynot.movie.domain.Movie;
import han.aim.se.noyoumaynot.movie.service.AuthenticationService;
import han.aim.se.noyoumaynot.movie.service.MovieService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/movies")
public class MovieController {
    private final MovieService movieService;
    private final AuthenticationService authenticationService;

    @Autowired
    public MovieController(MovieService movieService, AuthenticationService authenticationService) {
        this.movieService = movieService;
        this.authenticationService = authenticationService;
    }

    @GetMapping
    public ArrayList<Movie> getAllMovies() {
        return movieService.getMovieList();
    }

    @GetMapping("/show")
    public Movie getMovieById(@RequestParam("id") String id) {

            Movie movie = movieService.getMovieById(id);
            return movie;
    }

    @PostMapping("/add")
    public Movie addMovie(@RequestBody Movie movie) {
        movieService.insertMovie(movie);
        return movie;
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteMovie(@PathVariable("id") String id) {
        movieService.deleteMovie(id);
        return ResponseEntity.ok().build();
    }

    private String authenticate(HttpServletRequest request) throws Exception {
        if (authenticationService.isValidToken("")){
            return authenticationService.getUsername("");
        } else {
            throw new Exception("Invalid token");
        }
    }


}
