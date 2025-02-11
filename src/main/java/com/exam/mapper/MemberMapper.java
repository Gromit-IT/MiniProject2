package com.exam.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.exam.dto.MemberDTO;

@Mapper
public interface MemberMapper {

	public MemberDTO idCheck(String userid);
	
	//DML => int
	public int memberAdd(MemberDTO dto);
	
	public MemberDTO login(Map<String, String>map);
	
	public MemberDTO mypage(String userid);
	public MemberDTO findByUserid(String userid);
	public int withdrawal(String userid);
	
}
