package movieprojectfile.chanmi.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Cinema {

    @Id
    @Column(length = 100)
    private String Cinema_PK;

    @Column(name = "cinemaName", length = 15000)
    private String cinemaName;

    @Column(length = 1500)
    private String address;

    @OneToMany(mappedBy = "cinema")
    private List<Theater> theaterList = new ArrayList<>();

    protected Cinema() {}

    public Cinema(String cinemaName, String address) {
        Cinema_PK = RandomStringUtils.randomAlphanumeric(1);;
        this.cinemaName = cinemaName;
        this.address = address;
    }

    public void setTheaterList(List<Theater> theaterList) {
        this.theaterList = theaterList;
    }
}
