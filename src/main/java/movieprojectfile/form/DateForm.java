package movieprojectfile.form;

import lombok.Data;
import movieprojectfile.chanmi.domain.Member;
import movieprojectfile.chanmi.domain.OpenMovie;

@Data
public class DateForm {

    private Member member;

    private OpenMovie openMovie;

}
