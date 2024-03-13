package movieprojectfile.form;

import lombok.Data;
import movieprojectfile.chanmi.domain.Member;
import movieprojectfile.chanmi.domain.OpenMovie;

import java.util.List;

// 메인 화면에서 날짜 선택 화면으로 넘어갈 때 전달할 데이터

@Data
public class MainForm {

    private Member member;

    private List<OpenMovie> openMovieList;

}
