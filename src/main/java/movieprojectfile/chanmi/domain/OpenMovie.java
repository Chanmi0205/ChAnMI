package movieprojectfile.chanmi.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import org.apache.commons.lang3.RandomStringUtils;

import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "OPEN_MOVIE")
public class OpenMovie {

    @Id
    @Column(name = "OPEN_MOVIE_PK", length = 1500)
    private String Open_Movie_PK;

    @Column(name = "OPENMOVIESTARTTIME")
    private LocalDateTime openMovieStartTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "movieName")
    private Movie movie;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "THEATER_PK")
    private Theater theater;

    protected OpenMovie() {}

    public OpenMovie(LocalDateTime openMovieStartTime) {
        Open_Movie_PK = RandomStringUtils.randomAlphanumeric(1);;
        this.openMovieStartTime = openMovieStartTime;
    }

    public void setMovieName(Movie movie) {
        this.movie = movie;
    }

    public void setTheater(Theater theater) {
        this.theater = theater;
    }
}
