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
    @Column(length = 100)
    private String Movie_PK;

    @Column(name = "MOVIENAME", length = 1500)
    private String movieName;

    @Column(name = "MOVIERUNTIME")
    private int movieRuntime;

    protected Movie() {}

    public Movie(String movieName, int movieRuntime) {
        Movie_PK = RandomStringUtils.randomAlphanumeric(1);
        this.movieName = movieName;
        this.movieRuntime = movieRuntime;
    }
}
