package han.aim.se.noyoumaynot.movie.service;

import han.aim.se.noyoumaynot.movie.domain.Movie;
import han.aim.se.noyoumaynot.movie.repository.MovieList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class MovieService {
    private MovieList movieList;

    @Autowired
    public MovieService(MovieList movieList) {
        this.movieList = movieList;
    }

    public ArrayList<Movie> getMovieList() {
        return movieList.getMovieList();
    }

    public Movie getMovieById(String movieId) {
        return movieList.findMovieById(movieId);
    }

    public void insertMovie(Movie movie) {
        movieList.add(movie);
    }

    public void deleteMovie(String movieId) {
        movieList.delete(movieList.findMovieById(movieId));

    }
}
