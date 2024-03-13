package movieprojectfile.chanmi.repository;

import movieprojectfile.chanmi.domain.Movie;
import movieprojectfile.chanmi.domain.OpenMovie;
import movieprojectfile.chanmi.domain.Site;
import movieprojectfile.chanmi.domain.Theater;

import java.util.List;
import java.util.Map;

public interface OpenMovieRepository {

    // PK로 상영중인 영화 출력
    OpenMovie openMovie(String OpenMoviePK);

    // 메인 화면

    // 날짜 선택 화면 - 영화 이름을 가지고 상영중인 날짜 및 시간 출력
    List<OpenMovie> openDate(String movieName);

    // 최종 예매 내역 화인
    List<Movie> openMovieList();

    List<OpenMovie> findOpenMovieList(String movieName);

    List<String> siteNameList(String theaterPK);

    // 모든 좌석 조회
    Map<String, Site> siteList(String theaterPK);

    // 예매 가능한 좌석만 반환하기
    Map<String, Site> reserveSiteList(String OpenMoviePK);

    List<Theater> AllTheater();

    List<Site> AllSite(String theaterPK);

}
