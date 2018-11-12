package com.fatalism.review;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.fatalism.board.BoardDTO;
import com.fatalism.page.RowNumber;
import com.fatalism.page.Search;
import com.fatalism.qna.QnaDTO;
import com.fatalism.util.DBConnector;

public class ReviewDAO{
	
		public static void main(String[] args) {
			ReviewDAO reviewDAO = new ReviewDAO();
			ReviewDTO reviewDTO = null;
			try {
				for(int i=1;i<70;i++) {
				reviewDTO = new ReviewDTO();
				reviewDTO.setSubject("rsubject"+i);
				reviewDTO.setWriter("rWriter"+i);
				reviewDTO.setContents("rcontenst"+i);
				reviewDTO.setPnum(i);
				System.out.println(reviewDTO.getWriter());
				reviewDAO.insert(reviewDTO);
				Thread.sleep(100);
				}
				System.out.println("끝");
			} catch (Exception e) {
				System.out.println("실패");
				e.printStackTrace();
			}
			
		}
	
	
	
	public int insert(ReviewDTO reviewDTO) throws Exception {
			Connection con = DBConnector.getConnect();
			String sql = "insert into board values(bt_seq.nextval,?,?,sysdate,0,?,'R',?,null,null,null)";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1,reviewDTO.getSubject());
			st.setString(2,reviewDTO.getWriter());
			st.setString(3,reviewDTO.getContents());
			st.setInt(4,reviewDTO.getPnum());
			
			int result = st.executeUpdate();
			
			DBConnector.disConnect(con, st);
			return result;
		
	}


	public List<ReviewDTO> selectList(RowNumber rowNumber, Search search) throws Exception {
		Connection con = DBConnector.getConnect();
		List<ReviewDTO> ar = new ArrayList<>();
		String sql ="select * from ( "
				+ "select rownum R, R.* from( "
				+ "select * from board where kind='R' "
				+ "and ? like ?) R "
				+ "order by R desc) "
				+ "where R between ? and ? ";
		
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1,search.getKind());
		st.setString(2, "%"+search.getSearch()+"%");
		st.setInt(3, rowNumber.getStartRow());
		st.setInt(4, rowNumber.getLastRow());
		ResultSet rs = st.executeQuery();
		
		while(rs.next()) {
			ReviewDTO rDTO = new ReviewDTO();
			rDTO.setNum(rs.getInt("num"));
			rDTO.setSubject(rs.getString("subject"));
			rDTO.setWriter(rs.getString("writer"));
			rDTO.setReg_date(rs.getDate("reg_date"));
			rDTO.setHit(rs.getInt("hit"));
			rDTO.setContents(rs.getString("contents"));
			rDTO.setKind("R");
			ar.add(rDTO);
		}
		
		DBConnector.disConnect(con, st, rs);
		return ar;
	}
	
	public int delete(int num) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}


	public int update() throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	
	public List<BoardDTO> selectList() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	
	public BoardDTO selectOne() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public int getNum() throws Exception {
		Connection con = DBConnector.getConnect();
		String sql="select BT_seq.nextval from dual";
		PreparedStatement st = con.prepareStatement(sql);
		ResultSet rs = st.executeQuery();
		rs.next();
		int num = rs.getInt(1);
		DBConnector.disConnect(con, st, rs);
		return num;
	}
	
	
}
