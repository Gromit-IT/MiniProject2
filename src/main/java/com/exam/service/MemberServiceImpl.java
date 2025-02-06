package com.exam.service;

import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.exam.dto.MemberDTO;
import com.exam.mapper.MemberMapper;

@Service
public class MemberServiceImpl implements MemberService {

	MemberMapper mapper;
	
	public MemberServiceImpl(MemberMapper mapper) {
		this.mapper = mapper;
	}

	@Override
	public MemberDTO idCheck(String userid) {
		return mapper.idCheck(userid);
	}

	@Override
	@Transactional // 모두 성공하면 commit, 하나라도 실패하면 모두 rollback
	//DML에는 @Transactional 붙여주는게 좋음
	public int memberAdd(MemberDTO dto) {
		return mapper.memberAdd(dto);
	}

	@Override
	public MemberDTO login(Map<String, String> map) {
		// TODO Auto-generated method stub
		return mapper.login(map);
	}

	@Override
	public MemberDTO mypage(String userid) {
		return mapper.mypage(userid);
	}

	@Override
	public int withdrawal(String userid) {
		return mapper.withdrawal(userid);
	}

}
