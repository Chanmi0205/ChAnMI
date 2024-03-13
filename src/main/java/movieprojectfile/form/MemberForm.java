package movieprojectfile.form;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

// 회원 가입, 회원 정보 수정에 쓰일 폼

@Data
public class MemberForm {

    @Pattern(regexp = "(?=.*[^ㄱ-힣])(?=.*[\\w]).{5,15}")
    private String id;

    @Size(min = 5, max = 15)
    private String name;

    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{10,30}")
    private String PW;

    private String oneMorePW;


}
