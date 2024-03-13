package movieprojectfile.chanmi.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import movieprojectfile.chanmi.SessionConst;
import movieprojectfile.chanmi.domain.*;
import movieprojectfile.chanmi.repository.MemberRepository;
import movieprojectfile.chanmi.repository.OpenMovieRepository;
import movieprojectfile.chanmi.repository.ReserveRepository;
import movieprojectfile.form.ReserveDateForm;
import movieprojectfile.form.ReserveSiteForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class ReserveController {

    private final OpenMovieRepository openMovieRepository;
    private final MemberRepository memberRepository;
    private final ReserveRepository reserveRepository;

    @GetMapping("/content/reserve/reserveMovieDate")
    public String openDateForm(@RequestParam("movieName") String movieName, HttpServletRequest request, Model model) {

        List<OpenMovie> openMovieList = openMovieRepository.openDate(movieName);

        HttpSession session = request.getSession(false);
        Member loginmember = (Member)session.getAttribute(SessionConst.LOGIN_MEMBER);

        ReserveDateForm reserveDateForm = new ReserveDateForm();
        reserveDateForm.setMemberID(loginmember.getID());
        reserveDateForm.setMovieName(movieName);

        model.addAttribute("movieImg", openMovieList.get(1).getMovie().getMovieImg());
        model.addAttribute("loginMember", loginmember);
        model.addAttribute("openMovieList", openMovieList);
        model.addAttribute("reserveDateForm", reserveDateForm);

        return "/content/reserve/reserveMovieDate";

    }

    @PostMapping("/content/reserve/reserveMovieDate")
    public String openDate(@Valid @ModelAttribute("reserveDateForm") ReserveDateForm reserveDateForm, BindingResult bindingResult, HttpServletRequest request, Model model) {

        HttpSession session = request.getSession(false);

        if (session == null) {
            return "content/home/home";
        }
        Member loginMember = (Member)session.getAttribute(SessionConst.LOGIN_MEMBER);

        if(bindingResult.hasErrors()) {
            List<OpenMovie> openMovieList = openMovieRepository.openDate(reserveDateForm.getMovieName());
            model.addAttribute("movieImg", openMovieList.get(1).getMovie().getMovieImg());
            model.addAttribute("loginMember", loginMember);
            model.addAttribute("openMovieList", openMovieList);
            model.addAttribute("reserveDateForm", reserveDateForm);
            return "content/reserve/reserveMovieDate";
        }

        String openMoviePk = reserveDateForm.getSelectOpenMoviePK();
        String memberID = reserveDateForm.getMemberID();

        OpenMovie openMovie = openMovieRepository.openMovie(openMoviePk);

        Map<String, Site> siteList = openMovieRepository.siteList(openMovie.getTheater().getTheater_PK());
        Map<String, Site> reserveSiteList = openMovieRepository.reserveSiteList(openMoviePk);

        ReserveSiteForm reserveSiteForm = new ReserveSiteForm();
        reserveSiteForm.setMemberID(memberID);
        reserveSiteForm.setOpenMoviePK(openMoviePk);

        model.addAttribute("loginMember", loginMember);

        model.addAttribute("movieName", openMovie.getMovie().getMovieName());
        model.addAttribute("movieImg", openMovie.getMovie().getMovieImg());
        model.addAttribute("theaterColumn", openMovie.getTheater().getColumnNumber());
        model.addAttribute("theaterRow", openMovie.getTheater().getRowNumber());

        model.addAttribute("openMovie", openMovie);

        model.addAttribute("siteList", siteList);
        model.addAttribute("reserveSiteList", reserveSiteList);

        model.addAttribute("reserveSiteForm", reserveSiteForm);

        return "/content/reserve/reserveMovieSite";
    }

    @PostMapping("/content/reserve/reserveMovieSite")
    public String site(@ModelAttribute("reserveSiteForm") ReserveSiteForm reserveSiteForm, BindingResult bindingResult, HttpServletRequest request, Model model) {

        HttpSession session = request.getSession(false);
        if (session == null) {
            return "content/home/home";
        }

        Member loginmember = (Member)session.getAttribute(SessionConst.LOGIN_MEMBER);
        OpenMovie openMovie = openMovieRepository.openMovie(reserveSiteForm.getOpenMoviePK());

        if(reserveSiteForm.getSiteNameList().isEmpty()) {

            Map<String, Site> siteList = openMovieRepository.siteList(openMovie.getTheater().getTheater_PK());
            Map<String, Site> reserveSiteList = openMovieRepository.reserveSiteList(reserveSiteForm.getOpenMoviePK());

            model.addAttribute("loginMember", loginmember);

            model.addAttribute("movieName", openMovie.getMovie().getMovieName());
            model.addAttribute("movieImg", openMovie.getMovie().getMovieImg());
            model.addAttribute("theaterColumn", openMovie.getTheater().getColumnNumber());
            model.addAttribute("theaterRow", openMovie.getTheater().getRowNumber());

            model.addAttribute("openMovie", openMovie);

            model.addAttribute("siteList", siteList);
            model.addAttribute("reserveSiteList", reserveSiteList);

            model.addAttribute("reserveSiteForm", reserveSiteForm);
            bindingResult.reject("siteError");
            return "/content/reserve/reserveMovieSite";
        }

        List<String> siteNameList = reserveSiteForm.getSiteNameList();

        for (String reserveSitePK : siteNameList) {
            Site site = reserveRepository.findSite(reserveSitePK);
            reserveRepository.reserveMovie(openMovie, loginmember, site);
        }

        return home(request, model);

    }


    @GetMapping("/")
    public String home(HttpServletRequest request, Model model) {

        HttpSession session = request.getSession(false);
        List<Movie> movieList = openMovieRepository.openMovieList();

        model.addAttribute("movieList", movieList);

        if (session == null || session.getAttribute(SessionConst.LOGIN_MEMBER) == null) {
            return "content/home/home";
        }

        Member loginmember = (Member)session.getAttribute(SessionConst.LOGIN_MEMBER);

        model.addAttribute("loginMember", loginmember);

        return "content/home/loginHome";
    }

}
