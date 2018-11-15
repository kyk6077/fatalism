package com.iu.member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.iu.util.DBConnector;

public class MemberDAO {
	
	//회원 정보 수정
	public int update(MemberDTO memberDTO) throws Exception{
		Connection con = DBConnector.getConnect();
		String sql="update member set pw=?, name=?, num_address=?, main_address=?, sub_address=?, country=?, phone=?,email=? where id=?";
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, memberDTO.getPw());
		st.setString(2, memberDTO.getName());
		st.setInt(3, memberDTO.getNum_address());
		st.setString(4, memberDTO.getMain_address());
		st.setString(5, memberDTO.getSub_address());
		st.setString(6, memberDTO.getCountry());
		st.setString(7, memberDTO.getPhone());
		st.setString(8, memberDTO.getEmail());
		st.setString(9, memberDTO.getId());

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
			
		}else {
			
			memberDTO=null;
		}
		System.out.println(memberDTO.getName());
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
		String sql = "insert into member values(?,?,?,?,?,?,?,?,?,1000,'U')";
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
		
		int result = st.executeUpdate();
		DBConnector.disConnect(st, con);
		return result;
	}
	
	

}
