package movieprojectfile.chanmi.domain;

import jakarta.persistence.*;
import lombok.Getter;
import org.apache.commons.lang3.RandomStringUtils;

@Entity
@Getter
public class Theater {

    @Id
    @Column(length = 100)
    private String Theater_PK;

    @Column(name = "theaterName", length = 1500, nullable = false)
    private String theaterName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CINEMA_PK")
    private Cinema cinema;

    protected Theater() {}

    public Theater(String theaterName) {
        Theater_PK = RandomStringUtils.randomAlphanumeric(1);;
        this.theaterName = theaterName;
    }

    public void setCinema(Cinema cinema) {
        this.cinema = cinema;
    }
}
