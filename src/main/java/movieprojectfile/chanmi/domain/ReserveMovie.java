package movieprojectfile.chanmi.domain;


import jakarta.persistence.*;
import lombok.Getter;
import org.apache.commons.lang3.RandomStringUtils;

@Entity
@Getter
@Table(name = "RESERVE_MOVIE")
public class ReserveMovie {

    @Id
    @Column(name = "RESERVE_MOVIE_PK", length = 1500)
    private String Reserve_Movie_PK;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "OPEN_MOVIE_PK")
    private OpenMovie openMovie;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Site_PK")
    private Site site;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_PK")
    private Member member;

    public ReserveMovie() {
    }

    public ReserveMovie(OpenMovie openMovie, Site site, Member member) {
        Reserve_Movie_PK = RandomStringUtils.randomAlphanumeric(1500);
        this.openMovie = openMovie;
        this.site = site;
        this.member = member;
    }
}
