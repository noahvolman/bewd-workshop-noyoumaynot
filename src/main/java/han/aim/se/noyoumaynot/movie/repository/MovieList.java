package han.aim.se.noyoumaynot.movie.repository;

import han.aim.se.noyoumaynot.movie.domain.Movie;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public class MovieList {
    private ArrayList<Movie> movieList = new ArrayList<>();

    public MovieList() {
        Movie movie1 = new Movie();
        Movie movie2 = new Movie();
        Movie movie3 = new Movie();
        movie1.setMovie_id("1");
        movie2.setMovie_id("2");
        movie3.setMovie_id("3");
        movie1.setName("Dune 2");
        movie2.setName("Twisters");
        movie3.setName("Love Actually");
        movieList.add(movie1);
        movieList.add(movie2);
        movieList.add(movie3);
    }

    public ArrayList<Movie> getMovieList() {
        return movieList;
    }

    public Movie findMovieById(String id) {
        for (Movie movie : movieList) {
            if (movie.getMovie_id().equals(id)) {
                return movie;
            }
        }
       throw new MovieNotFoundException();
    }

    public void add(Movie movie) {
        movieList.add(movie);
    }

    public void delete(Movie movie) {
        movieList.remove(movie);
    }

}
