package com.example.customlogin.Controller;

import com.example.customlogin.DTO.MemberDTO;
import com.example.customlogin.Service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;

    //로그인 폼으로 이동
    //로그인 처리는 SecurityConfig에서 자동처리 되므로 Controller에는 작성할 필요X
    @GetMapping("/member/login")
    public String loginForm(Model model) throws Exception {
        //model.addAttribute("errorMessage", "오류메세지 테스트");
        return "member/login";
    }

    //로그인 오류 발생시 처리
    @GetMapping("/member/login/error")
    public String loginError(Model model) throws Exception {
        model.addAttribute("loginErrorMsg", "아이디 또는 비밀번호를 확인해주세요.");
        return "member/login";
    }

    //회원가입 폼
    @GetMapping("/member/register")
    public String registerForm(Model model) throws Exception {
        MemberDTO memberDTO = new MemberDTO();
        model.addAttribute("memberDTO", memberDTO);
        return "member/register";
    }

    @PostMapping("/member/register")
    public String registerProc(@Valid MemberDTO memberDTO,
                               BindingResult bindingResult,
                               RedirectAttributes redirectAttributes,
                               Model model) throws Exception {
        if (bindingResult.hasErrors()) {
            return "/member/register";
        }
        try {
            memberService.saveMember(memberDTO);  //신규회원 등록 성공 시
            redirectAttributes.addAttribute("errorMessage", "가입을 축하드립니다.");
        } catch (IllegalStateException e) {  //신구회원 등록 실패 시 서비스에서 등록한 메세지 출력
            redirectAttributes.addAttribute("errorMessage", e.getMessage());
            return "/member/register";
        }

        return "redirect:/";
    }

    @GetMapping("/admin/list")
    public String admin(Model model) throws Exception {
        return "admin/list";
    }

    @GetMapping("/user/list")
    public String user(Model model) throws Exception {
        return "user/list";
    }
}
