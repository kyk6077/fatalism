package com.fatalism.boardimg;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.fatalism.board.BoardDTO;
import com.fatalism.util.DBConnector;

public class BoardimgDAO {
	
	//pnum은 올릴때 product num 받아서 올림
	public int insert(BoardimgDTO boardimgDTO) throws Exception {
		Connection con = DBConnector.getConnect();
		System.out.println(boardimgDTO.getBnum());
		System.out.println(boardimgDTO.getFname());
		System.out.println(boardimgDTO.getOname());
		String sql="insert into boardimg values(BI_SEQ.nextval,?,?,?)";
		PreparedStatement st = con.prepareStatement(sql);
		st.setInt(1,boardimgDTO.getBnum());
		st.setString(2, boardimgDTO.getFname());
		st.setString(3, boardimgDTO.getOname());
		int result = st.executeUpdate();
		
		DBConnector.disConnect(con, st);
		return result;
	}
	
	public BoardimgDTO selectOne(int bnum) throws Exception {
		Connection con = DBConnector.getConnect();
		String sql="select * from boardimg where bnum=?";
		PreparedStatement st = con.prepareStatement(sql);
		st.setInt(1,bnum);
		ResultSet rs = st.executeQuery();
		BoardimgDTO boardimgDTO = null;
		if(rs.next()) {
			boardimgDTO = new BoardimgDTO();
			boardimgDTO.setNum(rs.getInt("num"));
			boardimgDTO.setBnum(rs.getInt("bnum"));
			boardimgDTO.setFname(rs.getString("fname"));
			boardimgDTO.setOname(rs.getString("oname"));
		}
			
		DBConnector.disConnect(con, st, rs);
		return boardimgDTO;
	}
	
	public List<BoardimgDTO> selectList() throws Exception {
		Connection con = DBConnector.getConnect();
		String sql="select * from boardimg";
		PreparedStatement st = con.prepareStatement(sql);
		ResultSet rs = st.executeQuery();
		List<BoardimgDTO> ar = new ArrayList<>();
		if(rs.next()) {
			BoardimgDTO boardimgDTO = new BoardimgDTO();
			boardimgDTO = new BoardimgDTO();
			boardimgDTO.setNum(rs.getInt("num"));
			boardimgDTO.setBnum(rs.getInt("bnum"));
			boardimgDTO.setFname(rs.getString("fname"));
			boardimgDTO.setOname(rs.getString("oname"));
			ar.add(boardimgDTO);
		}

		DBConnector.disConnect(con, st, rs);
		return ar;
	}
	
}
