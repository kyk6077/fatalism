package com.fatalism.qna;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.fatalism.board.BoardDAO;
import com.fatalism.board.BoardDTO;
import com.fatalism.notice.NoticeDTO;
import com.fatalism.page.RowNumber;
import com.fatalism.page.Search;
import com.fatalism.util.DBConnector;

import oracle.jdbc.proxy.annotation.Pre;

public class QnaDAO implements BoardDAO{

//	public static void main(String[] args) {
//		QnaDAO qnaDAO = new QnaDAO();
//		QnaDTO qnaDTO = null;
//		try {
//			for(int i=1;i<20;i++) {
//			qnaDTO = new QnaDTO();
//			qnaDTO.setSubject("qsubject"+i);
//			qnaDTO.setWriter("qWriter"+i);
//			qnaDTO.setContents("qcontenst"+i);
//			qnaDTO.setPnum(i);
//			qnaDTO.setStep(1);
//			qnaDTO.setDepth(1);
//			
//			System.out.println(qnaDTO.getWriter());
//			qnaDAO.insert(qnaDTO);
//			Thread.sleep(100);
//			}
//			System.out.println("끝");
//		} catch (Exception e) {
//			System.out.println("실패");
//			e.printStackTrace();
//		}
//		
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
	
	
	
	public int insert(QnaDTO qnaDTO) throws Exception {
		Connection con = DBConnector.getConnect();
		String sql = "insert into board values(bt_seq.nextval,?,?,sysdate,0,?,'Q',bt_seq.currval,1,1,'S',?)";
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1,qnaDTO.getSubject());
		st.setString(2,qnaDTO.getWriter());
		st.setString(3,qnaDTO.getContents());
		st.setString(4,qnaDTO.getPw());
		int result = st.executeUpdate();
		
		DBConnector.disConnect(con, st);
		return result;
	}

	public int replyInsert(QnaDTO qnaDTO) throws Exception {
		Connection con = DBConnector.getConnect();
		String sql = "insert into board values(bt_seq.nextval,?,?,sysdate,0,?,'Q',?,?,?,'S',?)";
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1,qnaDTO.getSubject());
		st.setString(2,qnaDTO.getWriter());
		st.setString(3,qnaDTO.getContents());
		st.setInt(4,qnaDTO.getRef());
		st.setInt(5,qnaDTO.getStep()+1);
		st.setInt(6,qnaDTO.getDepth()+1);
		st.setString(7,qnaDTO.getPw());
		int result = st.executeUpdate();
		
		
		DBConnector.disConnect(con, st);
		return result;
	}
	
	public int replyStep(int ref, int step) throws Exception{
		Connection con = DBConnector.getConnect();
		String sql ="update board set step=step+1 where ref = ? and step > ?";
		PreparedStatement st = con.prepareStatement(sql);
		st.setInt(1,ref);
		st.setInt(2,step);
		int result = st.executeUpdate();
		
		DBConnector.disConnect(con, st);
		return result;
	}

	public List<QnaDTO> selectList(RowNumber rowNumber, Search search) throws Exception {
		Connection con = DBConnector.getConnect();
		List<QnaDTO> ar = new ArrayList<>();
		String sql ="select * from ( "
				+ "select rownum R, Q.* from( "
				+ "select * from board where kind='Q' "
				+ "and "+search.getKind()+" like ? "
				+ "order by ref desc, step asc) Q )"
				+ "where R between ? and ? ";
		
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, "%"+search.getSearch()+"%");
		st.setInt(2, rowNumber.getStartRow());
		st.setInt(3, rowNumber.getLastRow());
		ResultSet rs = st.executeQuery();
		
		while(rs.next()) {
			QnaDTO qDTO = new QnaDTO();
			qDTO.setNum(rs.getInt("num"));
			qDTO.setSubject(rs.getString("subject"));
			qDTO.setWriter(rs.getString("writer"));
			qDTO.setReg_date(rs.getDate("reg_date"));
			qDTO.setHit(rs.getInt("hit"));
			qDTO.setContents(rs.getString("contents"));
			qDTO.setHide(rs.getString("hide"));
			qDTO.setKind("Q");
			qDTO.setDepth(rs.getInt("depth"));
			ar.add(qDTO);
		}
		
		DBConnector.disConnect(con, st, rs);
		return ar;
	}

	
	public int update(QnaDTO qnaDTO) throws Exception {
		Connection con = DBConnector.getConnect();
		String sql ="update board set subject=? , contents=? where num=?";
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1,qnaDTO.getSubject());
		st.setString(2, qnaDTO.getContents());
		st.setInt(3, qnaDTO.getNum());
		int result = st.executeUpdate();
		
		DBConnector.disConnect(con, st);
		return result;
	}
	
	
	public QnaDTO selectOne(int num) throws Exception {
		Connection con = DBConnector.getConnect();
		String sql="select * from board where num=?";
		PreparedStatement st = con.prepareStatement(sql);
		st.setInt(1,num);
		ResultSet rs = st.executeQuery();
		
		QnaDTO qnaDTO = null;
		if(rs.next()) {
			qnaDTO = new QnaDTO();
			qnaDTO.setNum(num);
			qnaDTO.setSubject(rs.getString("subject"));
			qnaDTO.setWriter(rs.getString("writer"));
			qnaDTO.setReg_date(rs.getDate("reg_date"));
			qnaDTO.setHit(rs.getInt("hit"));
			qnaDTO.setContents(rs.getString("contents"));
			qnaDTO.setRef(rs.getInt("ref"));
			qnaDTO.setStep(rs.getInt("step"));
			qnaDTO.setDepth(rs.getInt("depth"));
		}
		
		DBConnector.disConnect(con, st, rs);
		return qnaDTO;
	}
	
	public int getNum() throws Exception {
		Connection con = DBConnector.getConnect();
		String sql="select COUNT(num) from board where kind='Q'";
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
