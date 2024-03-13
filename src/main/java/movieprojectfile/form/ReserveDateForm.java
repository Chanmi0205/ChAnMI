package movieprojectfile.form;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ReserveDateForm {

    private String memberID;

    private String movieName;

    @NotBlank
    private String selectOpenMoviePK;

}
