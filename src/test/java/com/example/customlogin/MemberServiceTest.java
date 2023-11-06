package com.example.customlogin;

import com.example.customlogin.DTO.MemberDTO;
import com.example.customlogin.Service.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
public class MemberServiceTest {
    @Autowired
    private MemberService memberService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    @DisplayName("회원가입 테스트")
    public void saveMemberTest() throws Exception {
        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setName("홍길동");
        memberDTO.setEmail("test4@gmail.com");
        memberDTO.setAddress("경기도 부천시");
        memberDTO.setPassword("1111");

        memberService.saveMember(memberDTO);
    }
}
