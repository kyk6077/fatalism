package com.fatalism.reply;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.fatalism.util.DBConnector;

public class ReplyDAO {
	
	public List<ReplyDTO> selectList(int bnum) throws Exception{
		Connection con = DBConnector.getConnect();
		String sql="select * from Reply where bnum=?";
		PreparedStatement st = con.prepareStatement(sql);
		st.setInt(1, bnum);
		ResultSet rs = st.executeQuery();
		List<ReplyDTO> ar = new ArrayList<>();
		while(rs.next()) {
			ReplyDTO replyDTO = new ReplyDTO();
			replyDTO.setNum(rs.getInt("num"));
			replyDTO.setId(rs.getString("id"));
			replyDTO.setContents(rs.getString("contents"));
			replyDTO.setReg_date(rs.getDate("reg_date"));
			replyDTO.setBnum(rs.getInt("bnum"));
			ar.add(replyDTO);
		}
		DBConnector.disConnect(con, st, rs);
		return ar;
	}
	
	public int insert(ReplyDTO replyDTO) throws Exception{
		Connection con = DBConnector.getConnect();
		String sql = "insert into reply values(REPLY_SEQ.NEXTVAL,?,?,sysdate,?)";
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1,replyDTO.getId());
		st.setString(2,replyDTO.getContents());
		st.setInt(3, replyDTO.getBnum());
		int result = st.executeUpdate();
		
		DBConnector.disConnect(con, st);
		return result;
	}
	
	public int delete(int num) throws Exception{
		Connection con = DBConnector.getConnect();
		String sql="delete reply where num=?";
		PreparedStatement st = con.prepareStatement(sql);
		st.setInt(1,num);
		int result = st.executeUpdate();
		DBConnector.disConnect(con, st);
		return result;
	}
	
	public int deleteList(int bnum) throws Exception{
		Connection con = DBConnector.getConnect();
		String sql="delete reply where bnum=?";
		PreparedStatement st = con.prepareStatement(sql);
		st.setInt(1,bnum);
		int result = st.executeUpdate();
		DBConnector.disConnect(con, st);
		return result;
	}
	
	public int update(ReplyDTO replyDTO) throws Exception{
		Connection con = DBConnector.getConnect();
		String sql="update reply set contents=? where num=?";
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, replyDTO.getContents());
		st.setInt(2, replyDTO.getNum());
		int result = st.executeUpdate();
		DBConnector.disConnect(con, st);
		return result;
	}
	
	
	
}
