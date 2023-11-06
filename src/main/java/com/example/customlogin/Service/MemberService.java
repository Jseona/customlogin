package com.example.customlogin.Service;

import com.example.customlogin.Constant.Role;
import com.example.customlogin.DTO.MemberDTO;
import com.example.customlogin.Entity.MemberEntity;
import com.example.customlogin.Repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
//UserDetailsService : 보안 인증에 필요한 사용자정보를 가지고 있음
public class MemberService implements UserDetailsService {
    private final MemberRepository memberRepository;
    private final ModelMapper modelMapper = new ModelMapper();
    private final PasswordEncoder passwordEncoder;  //암호를 암호화 하기 위해

    //로그인 처리 (커스텀 보안 인증시 반드시 처리해줘야 하는 부분)
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        //이메일로 회원을 조회
        MemberEntity memberEntity = memberRepository.findByEmail(email);

        if (memberEntity == null) {
            throw new UsernameNotFoundException(email);  //오류메세지를 새로 지정
        }

        //보안 인증에서 해당 데이터로 로그인 처리
        return User.builder()
                .username(memberEntity.getEmail())
                .password(memberEntity.getPassword())
                .roles(memberEntity.getRole().toString())
                .build();
    }

    //회원가입 처리(수동작업, 로그인 처리 처럼 자동으로 가져와서 하는 것이 없음)
    public void saveMember(MemberDTO memberDTO) throws Exception {
        //암호화 처리
        String password = passwordEncoder.encode(memberDTO.getPassword());
        //MemberEntity에 생성자가 없으므로 수동으로 저장
        MemberEntity memberEntity = new MemberEntity();
        memberEntity.setEmail(memberDTO.getEmail());  //이메일은 중복사용이 불가능
        memberEntity.setName(memberDTO.getName());
        memberEntity.setPassword(password);  //비밀번호는 암호화한 값으로 넣어주기
        memberEntity.setAddress(memberDTO.getAddress());
        memberEntity.setRole(Role.USER);  //기본 가입시 일반회원으로 등록

        validateDuplicateMember(memberEntity);  //저장할 데이터를 중복체크(신규회원 등록시에만)
        memberRepository.save(memberEntity);
    }

    //이메일 중복 체크
    private void validateDuplicateMember(MemberEntity memberEntity) {
        //데이터베이스에서 조회
        MemberEntity findMember = memberRepository.findByEmail(memberEntity.getEmail());

        if (findMember != null) {  //이메일이 존재하면
            throw new IllegalStateException("이미 가입된 회원입니다.");  //오류발생
        }
    }
}
