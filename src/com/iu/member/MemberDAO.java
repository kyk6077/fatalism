package com.iu.member;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.iu.util.DBConnector;

public class MemberDAO {
	
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
