package movieprojectfile.chanmi.repository;

import movieprojectfile.chanmi.domain.Member;
import movieprojectfile.chanmi.domain.Movie;
import movieprojectfile.chanmi.domain.OpenMovie;

import java.util.List;

public interface OpenMovieRepository {

    List<OpenMovie> openMovieList();

    List<OpenMovie> findOpenMovieList(String movieName);
}
