package movieprojectfile.form;

import lombok.Data;
import movieprojectfile.chanmi.domain.Site;
import org.springframework.data.relational.core.mapping.Embedded;

import java.util.List;

@Data
public class ReserveSiteForm {

    private String memberID;

    private String openMoviePK;

    @Embedded.Empty
    private List<String> siteNameList;

}

