package com.fatalism.notice;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.fatalism.board.BoardDAO;
import com.fatalism.board.BoardDTO;
import com.fatalism.page.Search;
import com.fatalism.page.RowNumber;
import com.fatalism.util.DBConnector;

public class NoticeDAO implements BoardDAO{

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
	
	@Override
	public int delete(int num,String pw) throws Exception{
		Connection con = DBConnector.getConnect();
		String sql = "delete board where num=? and pw=?";
		PreparedStatement st = con.prepareStatement(sql);
		st.setInt(1,num);
		st.setString(2, pw);
		int result = st.executeUpdate();
		
		DBConnector.disConnect(con, st);
		return result;
	}
	
	public int insert(BoardDTO boardDTO) throws Exception {
		Connection con = DBConnector.getConnect();
		String sql = "insert into board values(bt_seq.nextval,?,?,sysdate,0,?,'N',null,null,null,?,?)";
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1,boardDTO.getSubject());
		st.setString(2,boardDTO.getWriter());
		st.setString(3,boardDTO.getContents());
		st.setString(4,boardDTO.getHide());
		st.setString(5,boardDTO.getPw());
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
				+ "and "+search.getKind()+" like ? "
				+ "order by num desc) N )"
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
			nDTO.setHide(rs.getString("hide"));
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

	
	public int update(NoticeDTO noticeDTO) throws Exception {
		Connection con = DBConnector.getConnect();
		String sql ="update board set subject=? , contents=? where num=?";
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1,noticeDTO.getSubject());
		st.setString(2, noticeDTO.getContents());
		st.setInt(3, noticeDTO.getNum());
		int result = st.executeUpdate();
		
		DBConnector.disConnect(con, st);
		return result;
	}

	
	
	public NoticeDTO selectOne(int num) throws Exception {
		Connection con = DBConnector.getConnect();
		String sql="select * from board where num=?";
		PreparedStatement st = con.prepareStatement(sql);
		st.setInt(1,num);
		ResultSet rs = st.executeQuery();
		
		NoticeDTO noticeDTO = null;
		if(rs.next()) {
			noticeDTO = new NoticeDTO();
			noticeDTO.setNum(num);
			noticeDTO.setSubject(rs.getString("subject"));
			noticeDTO.setWriter(rs.getString("writer"));
			noticeDTO.setReg_date(rs.getDate("reg_date"));
			noticeDTO.setHit(rs.getInt("hit"));
			noticeDTO.setContents(rs.getString("contents"));
		}
		
		DBConnector.disConnect(con, st, rs);
		return noticeDTO;
	}

	@Override
	public int pwCheck(int num, String pw) throws Exception {
		Connection con = DBConnector.getConnect();
		String sql = "select num from board where num=? and pw=?";
		PreparedStatement st = con.prepareStatement(sql);
		st.setInt(1, num);
		st.setString(2, pw);
		ResultSet rs = st.executeQuery();
		int result = 0;
		if(rs.next()) {
			result = rs.getInt(1);			
		};
		DBConnector.disConnect(con, st, rs);
		return result;
	}

}
