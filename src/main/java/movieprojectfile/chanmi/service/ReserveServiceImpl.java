//package movieprojectfile.chanmi.service;
//
//import lombok.RequiredArgsConstructor;
//import movieprojectfile.chanmi.domain.Member;
//import movieprojectfile.chanmi.domain.OpenMovie;
//import movieprojectfile.chanmi.domain.ReserveMovie;
//import movieprojectfile.chanmi.domain.Site;
//import movieprojectfile.chanmi.repository.ReserveRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Repository;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//@RequiredArgsConstructor
//public class ReserveServiceImpl implements ReserveService {
//
//    private final ReserveRepository reserveRepository;
//
//    // 영화 예매
//    @Override
//    public String reserveMovie(OpenMovie openMovie, Member member, Site site) {
//        reserveRepository.reserveMovie(openMovie, member, site);
//        return openMovie.getMovie().getMovieName();
//    }
//
//    @Override
//    public String reserveMovie(OpenMovie openMovie, Member member) {
//        return null;
//    }
//
//    // 예매 취소
//    @Override
//    public void cancelReserveMovie(ReserveMovie reserveMovie) {
//        reserveRepository.deleteReserveMovie(reserveMovie);
//    }
//
//    // 예매 내역 조회
//    @Override
//    public List<ReserveMovie> reserveMovieList(Member member) {
//        return reserveRepository.findReserveMovieList(member);
//    }
//}
