package han.aim.se.noyoumaynot.movie.domain;

// This class is called a DTO (Data Transferable Object)

public class Movie {
    private String movie_id;
    private String name;

    public String getMovie_id() {
        return movie_id;
    }
    public void setMovie_id(String movie_id) {
        this.movie_id = movie_id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
