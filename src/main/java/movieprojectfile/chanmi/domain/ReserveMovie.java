package movieprojectfile.chanmi.domain;


import jakarta.persistence.*;
import lombok.Getter;
import org.apache.commons.lang3.RandomStringUtils;

@Entity
@Getter
@Table(name = "RESERVE_MOVIE")
public class ReserveMovie {

    @Id
    @Column(name = "RESERVEMOVIE_PK", length = 100)
    private String ReserveMovie_PK;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "OPENMOVIE_PK")
    private OpenMovie openMovie;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_PK")
    private Member member;

    public ReserveMovie() {
        ReserveMovie_PK = RandomStringUtils.randomAlphanumeric(1);
    }

    public void setOpenMovie(OpenMovie openMovie) {
        this.openMovie = openMovie;
    }

    public void setMember(Member member) {
        this.member = member;
    }
}
