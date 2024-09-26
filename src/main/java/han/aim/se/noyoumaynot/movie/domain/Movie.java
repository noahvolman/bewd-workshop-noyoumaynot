package han.aim.se.noyoumaynot.movie.domain;

// This class is called a DTO (Data Transferable Object)

public class Movie {
    public String getMovieid() {
        return movieid;
    }

    public void setMovieid(String movieid) {
        this.movieid = movieid;
    }

    private String movieid;
    private String name;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
