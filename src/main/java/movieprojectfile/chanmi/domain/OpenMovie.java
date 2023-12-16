package movieprojectfile.chanmi.domain;

import jakarta.persistence.*;
import lombok.Getter;
import org.apache.commons.lang3.RandomStringUtils;

import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "OPEN_MOVIE")
public class OpenMovie {

    @Id
    @Column(name = "OPENMOVIE_PK", length = 100)
    private String OpenMovie_PK;

    @Column(name = "OPENMOVIESTARTTIME")
    private LocalDateTime openMovieStartTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MOVIE_PK")
    private Movie movie;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "THEATER_PK")
    private Theater theater;

    protected OpenMovie() {}

    public OpenMovie(LocalDateTime openMovieStartTime) {
        OpenMovie_PK = RandomStringUtils.randomAlphanumeric(1);;
        this.openMovieStartTime = openMovieStartTime;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public void setTheater(Theater theater) {
        this.theater = theater;
    }
}
