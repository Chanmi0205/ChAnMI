package movieprojectfile.chanmi.domain;

import jakarta.persistence.*;
import lombok.Getter;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Member {

    @Id
    @Column(length = 100)
    private String Member_PK;

    @Column(length = 500, unique = true, nullable = false)
    private String ID; // 로그인 할 때 사용하는 ID값, 중복X


    @Column(length = 1000, nullable = false)
    private String name; // 로그인 후 사용하는 닉네임, 중복O

    @Column(length = 1000, nullable = false)
    private String PW; // 비밀번호


    protected Member() {
    }

    public Member(String ID, String name, String PW) {
        this.Member_PK = RandomStringUtils.randomAlphanumeric(100);;
        this.ID = ID;
        this.name = name;
        this.PW = PW;
    }

    public void changeMember(String ID, String name) {
        this.ID = ID;
        this.name = name;
    }

    public void allChangeMember(String ID, String name, String PW) {
        this.ID = ID;
        this.name = name;
        this.PW = PW;
    }
}