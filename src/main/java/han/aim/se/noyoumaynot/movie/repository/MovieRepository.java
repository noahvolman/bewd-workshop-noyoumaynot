package han.aim.se.noyoumaynot.movie.repository;

import han.aim.se.noyoumaynot.movie.domain.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MovieRepository {


    private final JdbcTemplate jdbcTemplate;

    public MovieRepository(@Autowired JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Movie> getMovieList() {
        return jdbcTemplate.query("SELECT * FROM movies", BeanPropertyRowMapper.newInstance(Movie.class));
    }

    public Movie findMovieById(String id) {
        List<Movie> movies = jdbcTemplate.query("SELECT * FROM movies WHERE movieid = ?", BeanPropertyRowMapper.newInstance(Movie.class), id);
        if (!movies.isEmpty()) {
            return movies.get(0);
        }
        throw new MovieNotFoundException();
    }

    public void add(Movie movie) {
        jdbcTemplate.update("INSERT INTO movies (name) VALUES (?)", movie.getName());
    }

    public void delete(Movie movie) {
        jdbcTemplate.update("DELETE FROM movies WHERE movieid = ?", movie.getMovieid());
    }
}
