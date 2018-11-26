package com.iu.member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.iu.util.DBConnector;

public class MemberDAO {
	
	//번호로 비번 찾기
	public MemberDTO findPw2(MemberDTO memberDTO) throws Exception{
		Connection con = DBConnector.getConnect();
		String sql = "select pw from member where id=? and name=? and phone=? and phone2=?";
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, memberDTO.getId());
		st.setString(2, memberDTO.getName());
		st.setString(3, memberDTO.getPhone());
		st.setString(4, memberDTO.getPhone2());
		ResultSet rs = st.executeQuery();
		while(rs.next()) {
			memberDTO.setPw(rs.getString("pw"));
		}
		DBConnector.disConnect(rs, st, con);
		return memberDTO;
	}
	
	
	//이메일로 비번 찾기
	public MemberDTO findPw(MemberDTO memberDTO) throws Exception{
		Connection con = DBConnector.getConnect();
		String sql = "select pw from member where id=? and name=? and email=? and email2=?";
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, memberDTO.getId());
		st.setString(2, memberDTO.getName());
		st.setString(3, memberDTO.getEmail());
		st.setString(4, memberDTO.getEmail2());
		ResultSet rs = st.executeQuery();
		while(rs.next()) {
			memberDTO.setPw(rs.getString("pw"));
		}
		DBConnector.disConnect(rs, st, con);
		return memberDTO;
	}
	
	//핸드폰 번호로 아이디찾기
	public MemberDTO findId2(MemberDTO memberDTO) throws Exception{
		Connection con = DBConnector.getConnect();
		String sql = "select id from member where name=? and phone=? and phone2=?";
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, memberDTO.getName());
		st.setString(2, memberDTO.getPhone());
		st.setString(3, memberDTO.getPhone2());
		ResultSet rs = st.executeQuery();
		while(rs.next()) {
			memberDTO.setId(rs.getString("id"));
		}
		DBConnector.disConnect(rs, st, con);
		return memberDTO;
	}
	
	
	
	//이메일로아이디 찾기
	public MemberDTO findId(MemberDTO memberDTO) throws Exception{
		Connection con = DBConnector.getConnect();
		String sql = "select id from member where name=? and email=? and email2=?";
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, memberDTO.getName());
		st.setString(2, memberDTO.getEmail());
		st.setString(3, memberDTO.getEmail2());
		ResultSet rs = st.executeQuery();
		while(rs.next()) {
			System.out.println("1");
			memberDTO.setId(rs.getString("id"));
		}
		DBConnector.disConnect(rs, st, con);
		return memberDTO;
		
	}
	
	//회원 탈퇴
	public int delete(MemberDTO memberDTO) throws Exception{
		Connection con = DBConnector.getConnect();
		String sql = "delete member where id=?";
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, memberDTO.getId());
		int result = st.executeUpdate();
		DBConnector.disConnect(st, con);
		return result;
		
	}
	
	
	//회원 정보 수정
	public int update(MemberDTO memberDTO) throws Exception{
		Connection con = DBConnector.getConnect();
		String sql="update member set pw=?, name=?, num_address=?, main_address=?, sub_address=?, country=?, phone=?,email=?,phone2=?,email2=? where id=?";
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, memberDTO.getPw());
		st.setString(2, memberDTO.getName());
		st.setInt(3, memberDTO.getNum_address());
		st.setString(4, memberDTO.getMain_address());
		st.setString(5, memberDTO.getSub_address());
		st.setString(6, memberDTO.getCountry());
		st.setString(7, memberDTO.getPhone());
		st.setString(8, memberDTO.getEmail());
		st.setString(9, memberDTO.getPhone2());
		st.setString(10, memberDTO.getEmail2());
		st.setString(11, memberDTO.getId());

		int result = st.executeUpdate();
		
		DBConnector.disConnect(st, con);
		return result;
	}
	
	
	//로그인
	public MemberDTO login(MemberDTO memberDTO) throws Exception{
		Connection con = DBConnector.getConnect();
		String sql = "select * from member where id=? and pw=?";
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, memberDTO.getId());
		st.setString(2, memberDTO.getPw());
		ResultSet rs = st.executeQuery();
		
		if(rs.next()) {
			memberDTO.setName(rs.getString("name"));
			memberDTO.setNum_address(rs.getInt("num_address"));
			memberDTO.setMain_address(rs.getString("main_address"));
			memberDTO.setSub_address(rs.getString("sub_address"));
			memberDTO.setCountry(rs.getString("country"));
			memberDTO.setPhone(rs.getString("phone"));
			memberDTO.setEmail(rs.getString("email"));
			memberDTO.setKind(rs.getString("kind"));
			memberDTO.setMoney(rs.getInt("money"));
			memberDTO.setPoint(rs.getInt("point"));
		}else {
			
			memberDTO=null;
		}
		DBConnector.disConnect(rs, st, con);
		return memberDTO;
	}
	
	
	//중복확인
	public boolean checkId(String id) throws Exception{
		boolean check = true;
		Connection con = DBConnector.getConnect();
		String sql="select * from member where id =?";
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, id);
		ResultSet rs = st.executeQuery();
		check = rs.next();
		DBConnector.disConnect(rs, st, con);
		return check;
	}
	
	
	
	//insert
	public int insert(MemberDTO memberDTO) throws Exception{
		Connection con = DBConnector.getConnect();
		String sql = "insert into member values(?,?,?,?,?,?,?,?,?,1000,'U',?,?,1000)";
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, memberDTO.getId());
		st.setString(2, memberDTO.getPw());
		st.setString(3, memberDTO.getName());
		st.setInt(4, memberDTO.getNum_address());
		st.setString(5, memberDTO.getMain_address());
		st.setString(6, memberDTO.getSub_address());
		st.setString(7, memberDTO.getCountry());
		st.setString(8, memberDTO.getPhone());
		st.setString(9, memberDTO.getEmail());
		st.setString(10, memberDTO.getPhone2());
		st.setString(11, memberDTO.getEmail2());
		
		int result = st.executeUpdate();
		DBConnector.disConnect(st, con);
		return result;
	}
	
	

}
