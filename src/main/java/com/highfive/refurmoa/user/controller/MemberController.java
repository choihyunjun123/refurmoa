package com.highfive.refurmoa.user.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.highfive.refurmoa.entity.Member;
import com.highfive.refurmoa.user.service.MemberServiceImpl;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberServiceImpl memberServiceImpl;

    // 로그인
    @PostMapping("/login")
    public int loginUser(@RequestBody Member member) {
        return memberServiceImpl.login(member.getMemberId(), member.getPassword());
    }

    // ID 찾기
    @PostMapping("/findid")
    public String findID(@RequestBody Member member) {
        return memberServiceImpl.findID(member.getName(), member.getPhone());
    }

    // PW 찾기
    @PostMapping("/findpw")
    public String findPW(@RequestBody Member member) {
        return memberServiceImpl.findPW(member.getMemberId(), member.getName(), member.getPhone());
    }

    // 회원가입
    @PostMapping("/signup")
    public void insertMember(@RequestBody Member member) {
        memberServiceImpl.insertMember(member);
    }

    // ID 중복 검사
    @PostMapping("/signup/distinct")
    public long countMemberId(@RequestBody Member vo) {
        return memberServiceImpl.countMemberId(vo.getMemberId());
    }
    
 // 회원정보 불러오기
 	@RequestMapping("/user/info")
 	public List<Member> listMember(@RequestBody Member vo){
 		return (List<Member>)memberServiceImpl.listMember(vo.getMemberId());
 	}
 	
 	// 회원탈퇴
 	@RequestMapping("/user/delete")
 	public void deleteMember(@RequestBody Member vo){
 		memberServiceImpl.deleteMember(vo.getMemberId());
 	}
 	
 	// 회원정보 수정
 	@RequestMapping("/user/update")
 	public void updateMember(@RequestBody Member member){
 		memberServiceImpl.updateMember(member);
 	}
 	
 	//	회원주소검색
 	@RequestMapping("/cs/as/user/addr")
 	public String userLocationInfo(@RequestBody Member vo){
 		return memberServiceImpl.userLocationInfo(vo.getMemberId(), vo.isAcceptLocation());
 	}
 	
 	// 관리자 페이지 회원관리 회원목록 불러오기
 	@RequestMapping("/admin/user")
 	public List<Member> listAdminMember(){
 		return (List<Member>)memberServiceImpl.listAdminMember();
 	}

}