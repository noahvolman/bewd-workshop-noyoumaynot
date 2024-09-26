package han.aim.se.noyoumaynot.movie.service;

import han.aim.se.noyoumaynot.movie.domain.Movie;
import han.aim.se.noyoumaynot.movie.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {
    private MovieRepository movieRepository;

    @Autowired
    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

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
