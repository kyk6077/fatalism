package com.fatalism.reply;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.fatalism.util.DBConnector;

public class ReplyDAO {
	
	public List<ReplyDTO> selectList(int num) throws Exception{
		Connection con = DBConnector.getConnect();
		String sql="select * from Reply where num=?";
		PreparedStatement st = con.prepareStatement(sql);
		st.setInt(1, num);
		ResultSet rs = st.executeQuery();
		List<ReplyDTO> ar = new ArrayList<>();
		while(rs.next()) {
			ReplyDTO replyDTO = new ReplyDTO();
			replyDTO.setNum(rs.getInt("num"));
			replyDTO.setId(rs.getString("id"));
			replyDTO.setContents(rs.getString("contents"));
			replyDTO.setReg_date(rs.getDate("reg_date"));
			ar.add(replyDTO);
		}
		DBConnector.disConnect(con, st, rs);
		return ar;
	}
	
	public int insert(ReplyDTO replyDTO) throws Exception{
		Connection con = DBConnector.getConnect();
		String sql = "insert into reply values(?,?,?,sysdate)";
		PreparedStatement st = con.prepareStatement(sql);
		st.setInt(1,replyDTO.getNum());
		st.setString(2,replyDTO.getId());
		st.setString(3,replyDTO.getContents());
		int result = st.executeUpdate();
		
		DBConnector.disConnect(con, st);
		return result;
	}
	
}
