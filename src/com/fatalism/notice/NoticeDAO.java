package com.fatalism.notice;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


import com.fatalism.board.BoardDTO;
import com.fatalism.page.Search;
import com.fatalism.page.RowNumber;
import com.fatalism.util.DBConnector;

public class NoticeDAO{

//	public static void main(String[] args) {
//		NoticeDAO noticeDAO = new NoticeDAO();
//		try {
//			List<NoticeDTO> ar = noticeDAO.selectList();
//			for(int i=0;i<ar.size();i++) {
//				System.out.println(ar.get(i).getSubject());
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		
		
		
		
//		NoticeDTO noticeDTO = null;
//		try {
//			for(int i=0;i<60;i++) {
//			noticeDTO = new NoticeDTO();
//			noticeDTO.setSubject("subject"+i);
//			noticeDTO.setWriter("Writer"+i);
//			noticeDTO.setContents("contenst"+i);
//			System.out.println(noticeDTO.getWriter());
//			noticeDAO.insert(noticeDTO);
//			Thread.sleep(100);
//			}
//			System.out.println("끝");
//		} catch (Exception e) {
//			System.out.println("실패");
//			e.printStackTrace();
//		}
		
//	}
	
	
	public int insert(BoardDTO boardDTO) throws Exception {
		Connection con = DBConnector.getConnect();
		String sql = "insert into board values(bt_seq.nextval,?,?,sysdate,0,?,'N',0,0,0,0)";
		System.out.println(boardDTO.getWriter());
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1,boardDTO.getSubject());
		st.setString(2,boardDTO.getWriter());
		st.setString(3,boardDTO.getContents());
		int result = st.executeUpdate();
		
		DBConnector.disConnect(con, st);
		return result;
	}
	
	public List<NoticeDTO> selectList(RowNumber rowNumber, Search search) throws Exception {
		Connection con = DBConnector.getConnect();
		List<NoticeDTO> ar = new ArrayList<>();
		String sql ="select * from ( "
				+ "select rownum R, N.* from( "
				+ "select * from board where kind='N' "
				+ "and "+search.getKind()+" like ?) N "
				+ "order by R desc) "
				+ "where R between ? and ? ";
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, "%"+search.getSearch()+"%");
		st.setInt(2, rowNumber.getStartRow());
		st.setInt(3, rowNumber.getLastRow());
		ResultSet rs = st.executeQuery();
		
		while(rs.next()) {
			NoticeDTO nDTO = new NoticeDTO();
			nDTO.setNum(rs.getInt("num"));
			nDTO.setSubject(rs.getString("subject"));
			nDTO.setWriter(rs.getString("writer"));
			nDTO.setReg_date(rs.getDate("reg_date"));
			nDTO.setHit(rs.getInt("hit"));
			nDTO.setContents(rs.getString("contents"));
			nDTO.setKind("N");
			ar.add(nDTO);
		}
		
		DBConnector.disConnect(con, st, rs);
		return ar;
	}

	public int getNum() throws Exception {
		Connection con = DBConnector.getConnect();
		String sql="select COUNT(num) from board where kind='N'";
		PreparedStatement st = con.prepareStatement(sql);
		ResultSet rs = st.executeQuery();
		rs.next();
		int num = rs.getInt(1);
		DBConnector.disConnect(con, st, rs);
		return num;
	}
	
	public int delete(int num) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	
	public int update() throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}


	
	public BoardDTO selectOne() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}


	
	
}
