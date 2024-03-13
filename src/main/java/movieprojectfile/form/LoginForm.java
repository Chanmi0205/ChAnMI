package movieprojectfile.form;

import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class LoginForm {

    private String id;

    private String PW;

}
