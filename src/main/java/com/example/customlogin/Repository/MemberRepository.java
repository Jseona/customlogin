package com.example.customlogin.Repository;

import com.example.customlogin.Entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<MemberEntity, Integer> {
    //로그인은 이메일로 조회(MemberEntity에서 email필드로 조회)
    MemberEntity findByEmail(String email);
}
