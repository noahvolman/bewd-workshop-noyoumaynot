package han.aim.se.noyoumaynot.movie.service;

import han.aim.se.noyoumaynot.movie.domain.Movie;
import han.aim.se.noyoumaynot.movie.repository.MovieRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MovieService {
    private final MovieRepository movieRepository;

    public List<Movie> getMovieList() {
        return movieRepository.getMovieList();
    }

    public Movie getMovieById(String movieId) {
        return movieRepository.findMovieById(movieId);
    }

    public void insertMovie(Movie movie) {
        movieRepository.add(movie);
    }

    public void deleteMovie(String movieId) {
        movieRepository.delete(movieRepository.findMovieById(movieId));
    }
}
