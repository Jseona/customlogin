package com.example.customlogin.Entity;

import com.example.customlogin.Constant.Role;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@Table(name = "custommember")
//회원 테이블(로그인 테이블은 자동생성자 사용X)
public class MemberEntity {
    @Id
    @Column(name = "member_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;         //번호

    @Column
    private String name;    //회원명
    @Column(unique = true)
    private String email;   //이메일(로그인시 사용할 id)

    @Column
    private String password;//비밀번호

    @Column
    private String address; //주소

    @Enumerated(EnumType.STRING)
    private Role role;      //회원분류
}
