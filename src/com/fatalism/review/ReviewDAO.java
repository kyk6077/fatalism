package com.fatalism.review;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.fatalism.board.BoardDAO;
import com.fatalism.board.BoardDTO;
import com.fatalism.page.RowNumber;
import com.fatalism.page.Search;
import com.fatalism.qna.QnaDTO;
import com.fatalism.util.DBConnector;

public class ReviewDAO implements BoardDAO{
	
//		public static void main(String[] args) {
//			ReviewDAO reviewDAO = new ReviewDAO();
//			ReviewDTO reviewDTO = null;
//			try {
//				for(int i=1;i<70;i++) {
//				reviewDTO = new ReviewDTO();
//				reviewDTO.setSubject("rsubject"+i);
//				reviewDTO.setWriter("rWriter"+i);
//				reviewDTO.setContents("rcontenst"+i);
//				reviewDTO.setPnum(i);
//				System.out.println(reviewDTO.getWriter());
//				reviewDAO.insert(reviewDTO);
//				Thread.sleep(100);
//				}
//				System.out.println("끝");
//			} catch (Exception e) {
//				System.out.println("실패");
//				e.printStackTrace();
//			}
//			
//		}
	
	@Override
	public int delete(int num) throws Exception{
		Connection con = DBConnector.getConnect();
		String sql = "delete board where num=?";
		PreparedStatement st = con.prepareStatement(sql);
		st.setInt(1,num);
		int result = st.executeUpdate();
		
		DBConnector.disConnect(con, st);
		return result;
	}
	
	public int insert(ReviewDTO reviewDTO) throws Exception {
		Connection con = DBConnector.getConnect();
		String sql = "insert into board values(bt_seq.nextval,?,?,sysdate,0,?,'R',null,null,null,null,?,?)";
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1,reviewDTO.getSubject());
		st.setString(2,reviewDTO.getWriter());
		st.setString(3,reviewDTO.getContents());
		st.setString(4,reviewDTO.getHide());
		st.setString(5,reviewDTO.getPw());
		int result = st.executeUpdate();
		
		DBConnector.disConnect(con, st);
		return result;
	}


	public List<ReviewDTO> selectList(RowNumber rowNumber, Search search) throws Exception {
		Connection con = DBConnector.getConnect();
		List<ReviewDTO> ar = new ArrayList<>();
		String sql ="select * from ( "
				+ "select rownum R, V.* from( "
				+ "select * from board where kind='R' "
				+ "and "+search.getKind()+" like ? "
				+ "order by num desc) V) "
				+ "where R between ? and ? ";
		
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, "%"+search.getSearch()+"%");
		st.setInt(2, rowNumber.getStartRow());
		st.setInt(3, rowNumber.getLastRow());
		ResultSet rs = st.executeQuery();
		
		while(rs.next()) {
			ReviewDTO rDTO = new ReviewDTO();
			rDTO.setNum(rs.getInt("num"));
			rDTO.setSubject(rs.getString("subject"));
			rDTO.setWriter(rs.getString("writer"));
			rDTO.setReg_date(rs.getDate("reg_date"));
			rDTO.setHit(rs.getInt("hit"));
			rDTO.setContents(rs.getString("contents"));
			rDTO.setHide(rs.getString("hide"));
			rDTO.setKind("R");
			ar.add(rDTO);
		}
		
		DBConnector.disConnect(con, st, rs);
		return ar;
	}


	public int update() throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	
	public List<BoardDTO> selectList() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	
	public ReviewDTO selectOne(int num) throws Exception {
		Connection con = DBConnector.getConnect();
		String sql="select * from board where num=?";
		PreparedStatement st = con.prepareStatement(sql);
		st.setInt(1,num);
		ResultSet rs = st.executeQuery();
		
		ReviewDTO reviewDTO = null;
		if(rs.next()) {
			reviewDTO = new ReviewDTO();
			reviewDTO.setNum(num);
			reviewDTO.setSubject(rs.getString("subject"));
			reviewDTO.setWriter(rs.getString("writer"));
			reviewDTO.setReg_date(rs.getDate("reg_date"));
			reviewDTO.setHit(rs.getInt("hit"));
			reviewDTO.setContents(rs.getString("contents"));
		}
		
		DBConnector.disConnect(con, st, rs);
		return reviewDTO;
	}

	public int getNum() throws Exception {
		Connection con = DBConnector.getConnect();
		String sql="select COUNT(num) from board where kind='R'";
		PreparedStatement st = con.prepareStatement(sql);
		ResultSet rs = st.executeQuery();
		rs.next();
		int num = rs.getInt(1);
		DBConnector.disConnect(con, st, rs);
		return num;
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
