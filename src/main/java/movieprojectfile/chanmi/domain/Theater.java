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
    @JoinColumn(name = "cinemaName")
    private Cinema cinema;

    private int columnNumber;

    private int rowNumber;

    protected Theater() {}

    public Theater(String theaterName) {
        Theater_PK = RandomStringUtils.randomAlphanumeric(1);;
        this.theaterName = theaterName;
    }

    public void setColumnNumber(int columnNumber) {
        this.columnNumber = columnNumber;
    }

    public void setRowNumber(int rowNumber) {
        this.rowNumber = rowNumber;
    }

    public void setCinema(Cinema cinema) {
        this.cinema = cinema;
    }
}
