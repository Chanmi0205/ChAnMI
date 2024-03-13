package movieprojectfile.chanmi.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import movieprojectfile.chanmi.SessionConst;
import movieprojectfile.chanmi.domain.Member;
import movieprojectfile.chanmi.domain.Site;
import movieprojectfile.chanmi.domain.Theater;
import movieprojectfile.chanmi.repository.OpenMovieRepository;
import movieprojectfile.form.FindSiteForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class FindMovieController {

    private final OpenMovieRepository openMovieRepository;
    @GetMapping("/content/site/selectCinemaSite")
    public String theaterList(HttpServletRequest request, Model model) {

        HttpSession session = request.getSession(false);
        Member loginmember = (Member)session.getAttribute(SessionConst.LOGIN_MEMBER);
        model.addAttribute("loginMember", loginmember);

        List<Theater> theaterList = openMovieRepository.AllTheater();
        model.addAttribute("theaterList", theaterList);

        FindSiteForm findSiteForm = new FindSiteForm();
        model.addAttribute("findSiteForm", findSiteForm);
        return "/content/site/selectCinemaSite";
    }

    @PostMapping("/content/site/selectCinemaSite")
    public String SiteList(@ModelAttribute("findSiteForm") FindSiteForm findSiteForm, HttpServletRequest request, Model model) {

        HttpSession session = request.getSession(false);
        Member loginmember = (Member)session.getAttribute(SessionConst.LOGIN_MEMBER);
        model.addAttribute("loginMember", loginmember);

        String theaterPK = findSiteForm.getTheaterPK();

        List<Site> siteList = openMovieRepository.AllSite(theaterPK);
        int columnNumber = siteList.get(1).getTheater().getColumnNumber();
        int rowNumber = siteList.get(1).getTheater().getRowNumber();

        model.addAttribute("theaterColumn", columnNumber);
        model.addAttribute("theaterRow", rowNumber);
        model.addAttribute("siteList", siteList);
        return "/content/site/findCinemaSite";
    }

}
