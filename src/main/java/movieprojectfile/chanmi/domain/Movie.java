package movieprojectfile.chanmi.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import org.apache.commons.lang3.RandomStringUtils;

@Entity
@Getter
public class Movie {

    @Id
    @Column(name = "movieName", length = 1500)
    private String movieName;

    @Column
    private int movieRunTime;

    @Column(name = "movieImg", length = 300)
    private String movieImg;

    protected Movie() {}

    public Movie(String movieName, int movieRunTime, String movieImg) {
        this.movieName = movieName;
        this.movieRunTime = movieRunTime;
        this.movieImg = movieImg;
    }
}
