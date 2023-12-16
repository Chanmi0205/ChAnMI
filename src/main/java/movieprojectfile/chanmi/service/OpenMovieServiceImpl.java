package movieprojectfile.chanmi.service;

import lombok.RequiredArgsConstructor;
import movieprojectfile.chanmi.domain.Member;
import movieprojectfile.chanmi.domain.Movie;
import movieprojectfile.chanmi.domain.OpenMovie;
import movieprojectfile.chanmi.repository.OpenMovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OpenMovieServiceImpl implements OpenMovieService {

    private final OpenMovieRepository openMovieRepository;

    @Override
    public List<OpenMovie> openMovieList() {
        return openMovieRepository.openMovieList();
    }

    @Override
    public List<OpenMovie> findOpenMovie(String movieName) {
        return openMovieRepository.findOpenMovieList(movieName);
    }

}
