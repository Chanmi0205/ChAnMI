package movieprojectfile.form;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class SettingForm {

    private String Member_PK;

    @Pattern(regexp = "(?=.*[^ㄱ-힣])(?=.*[\\w]).{5,15}", message = "5자 이상, 15자 이하 한글를 제외하고 아이디를 작성해 주십시오.")
    private String id;

    @Size(min = 5, max = 15, message = "5자 이상, 15자 이하로 닉네임을 작성해 주십시오.")
    private String name;

    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{10,30}", message = "비밀번호는 대소문자, 숫자, 특수문자 포함해 10자 이상 30자 이내로 작성해\n 주십시오.")
    private String PW;

    private String oneMorePW;


}
