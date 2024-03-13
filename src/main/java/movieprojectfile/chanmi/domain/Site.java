package movieprojectfile.chanmi.domain;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class Site {

    @Id
    @Column(length = 1500)
    private String site_PK;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Theater_PK")
    private Theater theater;

    private String site_name;

    public Site() {
    }

    public Site(String site_PK, Theater theater, String site_name) {
        this.site_PK = site_PK;
        this.theater = theater;
        this.site_name = site_name;
    }
}