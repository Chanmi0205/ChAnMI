package movieprojectfile.chanmi.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import movieprojectfile.chanmi.SessionConst;
import movieprojectfile.chanmi.domain.Member;
import movieprojectfile.chanmi.domain.ReserveMovie;
import movieprojectfile.chanmi.repository.MemberRepository;
import movieprojectfile.chanmi.repository.ReserveRepository;
import movieprojectfile.form.LoginForm;
import movieprojectfile.form.MemberForm;
import movieprojectfile.form.SettingForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.AbstractBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberRepository memberRepository;
    private final ReserveRepository reserveRepository;

    @GetMapping("/member/login")
    public String loginForm(@ModelAttribute LoginForm loginForm, Model model) {
        model.addAttribute("loginForm", loginForm);
        return "member/login";
    }

    @PostMapping("/member/login")
    public String login(@Valid @ModelAttribute LoginForm loginForm, BindingResult bindingResult, HttpServletRequest request,
                        @RequestParam(defaultValue = "/") String redirectURL, Model model) {

        if(bindingResult.hasErrors()) {
            return "member/login";
        }

        HttpSession session = request.getSession();

        List<Member> memberList = memberRepository.memberList();

        boolean PWError = false;

        for (Member member : memberList) {
            if( member.getID().equals(loginForm.getId()) && member.getPW().equals(loginForm.getPW()) ) {
                Member loginMember = memberRepository.login(loginForm.getId(), loginForm.getPW());
                session.setAttribute(SessionConst.LOGIN_MEMBER, loginMember);

                model.addAttribute("loginMember", loginMember);
                return "redirect:" + redirectURL;
            } else if( member.getID().equals(loginForm.getId()) ) {
                PWError = true;
                break;
            }
        }

        if(PWError) {
            bindingResult.reject("NotPW");
            return "member/login";
        } else {
            bindingResult.reject("IDIsNull");
            return "member/login";
        }

    }

    @GetMapping("/member/signUp")
    public String signUpForm(@ModelAttribute MemberForm memberForm, Model model) {
        model.addAttribute("memberForm", memberForm);
        return "member/signUp";
    }

    @PostMapping("/member/signUp")
    public String signUp(@Valid @ModelAttribute("memberForm") MemberForm memberForm, BindingResult bindingResult, HttpServletRequest request,
                         @RequestParam(defaultValue = "/") String redirectURL, Model model) {

        if(!memberForm.getPW().equals(memberForm.getOneMorePW())) {
            bindingResult.reject("notPWEquals");
        }

        if(bindingResult.hasErrors()) {
            return "member/signUp";
        }

        Member loginMember = new Member(memberForm.getId(), memberForm.getName(), memberForm.getPW());
        List<Member> memberList = memberRepository.memberList();


        boolean IDError = false;

        for (Member member : memberList) {
             if( member.getID().equals(memberForm.getId())) {
                IDError = true;
                break;
            }
        }

        if(IDError) {
            bindingResult.reject("IDIsNotNull");
            return "member/signUp";
        }

        HttpSession session = request.getSession();
        memberRepository.insertMember(loginMember);

        session.setAttribute(SessionConst.LOGIN_MEMBER, loginMember);

        model.addAttribute("loginMember", loginMember);
        return "redirect:" + redirectURL;

    }

    @GetMapping("/member/setting")
    public String settingForm(HttpServletRequest request, Model model) {

        HttpSession session = request.getSession(false);

        if (session == null) {
            return "content/home/home";
        }
        Member loginmember = (Member)session.getAttribute(SessionConst.LOGIN_MEMBER);

        SettingForm settingMember = new SettingForm();

        settingMember.setMember_PK(loginmember.getMember_PK());
        settingMember.setId(loginmember.getID());
        settingMember.setName(loginmember.getName());

        model.addAttribute("settingMember", settingMember);

        return "member/setting";
    }

    @PostMapping("/member/setting")
    public String setting(@Valid @ModelAttribute("settingMember") SettingForm settingForm, BindingResult bindingResult,
                          @RequestParam(defaultValue = "/") String redirectURL,
                          HttpServletRequest request, Model model) {

        if(!settingForm.getPW().equals(settingForm.getOneMorePW())) {
            bindingResult.reject("notPWEquals");
        }

        if(bindingResult.hasErrors()) {
            return "member/setting";
        }


        List<Member> memberList = memberRepository.memberList();
        boolean IDError = false;

        for (Member member : memberList) {
             if( member.getID().equals(settingForm.getId())) {
                IDError = true;
                break;
            }
        }

        if(IDError) {
            bindingResult.reject("IDIsNotNull");
            return "member/setting";
        }
        Member loginMember = memberRepository.updateAllMember(settingForm.getMember_PK(), settingForm.getId(), settingForm.getName(), settingForm.getPW());

        HttpSession session = request.getSession(false);

        Member changeMember = memberRepository.updateAllMember(loginMember.getMember_PK(), loginMember.getID(), loginMember.getName(), loginMember.getPW());

        session.setAttribute(SessionConst.LOGIN_MEMBER, changeMember);

        model.addAttribute("loginMember", changeMember);
        return "redirect:" + redirectURL;

    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if(session != null) {
            session.invalidate();
        }
        return "redirect:/";
    }

    @GetMapping("/content/reserve/reserveMovieList")
    public String memberReserveListForm(HttpServletRequest request, Model model) {

        HttpSession session = request.getSession(false);
        Member loginMember = (Member)session.getAttribute(SessionConst.LOGIN_MEMBER);

        List<ReserveMovie> reserveMovieList = reserveRepository.findReserveMovieList(loginMember);

        model.addAttribute("reserveMovieList", reserveMovieList);
        model.addAttribute("loginMember", loginMember);

        return "content/reserve/reserveMovieList";
    }

    @GetMapping("/member/cancelMovie")
    public String cancelMovie(@RequestParam("cancelMovie") String cancelReserveMoviePK, HttpServletRequest request, Model model) {

        HttpSession session = request.getSession(false);
        Member loginMember = (Member)session.getAttribute(SessionConst.LOGIN_MEMBER);

        ReserveMovie cancelReserveMovie = reserveRepository.cancelMovie(cancelReserveMoviePK);

        String cancel = reserveRepository.ifCancel(cancelReserveMovie.getOpenMovie().getOpen_Movie_PK());

        if(cancel != null) {
            List<ReserveMovie> reserveMovieList = reserveRepository.findReserveMovieList(loginMember);

            model.addAttribute("reserveMovieList", reserveMovieList);

            model.addAttribute("loginMember", loginMember);
            return "content/reserve/reserveMovieList";
//            bindingResult.reject(cancel);
        } else {
            reserveRepository.deleteReserveMovie(cancelReserveMovie);
        }

        List<ReserveMovie> reserveMovieList = reserveRepository.findReserveMovieList(loginMember);

        model.addAttribute("reserveMovieList", reserveMovieList);

        model.addAttribute("loginMember", loginMember);

        return "content/reserve/reserveMovieList";
    }

    @GetMapping("/member/quit")
    public String quit(HttpServletRequest request) {

        HttpSession session = request.getSession(false);
        Member loginmember = (Member)session.getAttribute(SessionConst.LOGIN_MEMBER);

        memberRepository.deleteMember(loginmember);
        session.invalidate();
        return "content/home/home";

    }

}
