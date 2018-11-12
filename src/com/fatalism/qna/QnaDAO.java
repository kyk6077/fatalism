package com.fatalism.qna;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.fatalism.board.BoardDTO;
import com.fatalism.notice.NoticeDTO;
import com.fatalism.page.RowNumber;
import com.fatalism.page.Search;
import com.fatalism.util.DBConnector;

public class QnaDAO{

	public static void main(String[] args) {
		QnaDAO qnaDAO = new QnaDAO();
		QnaDTO qnaDTO = null;
		try {
			for(int i=1;i<20;i++) {
			qnaDTO = new QnaDTO();
			qnaDTO.setSubject("qsubject"+i);
			qnaDTO.setWriter("qWriter"+i);
			qnaDTO.setContents("qcontenst"+i);
			qnaDTO.setPnum(i);
			qnaDTO.setStep(1);
			qnaDTO.setDepth(1);
			
			System.out.println(qnaDTO.getWriter());
			qnaDAO.insert(qnaDTO);
			Thread.sleep(100);
			}
			System.out.println("끝");
		} catch (Exception e) {
			System.out.println("실패");
			e.printStackTrace();
		}
		
	}
	
	public int insert(QnaDTO qnaDTO) throws Exception {
		Connection con = DBConnector.getConnect();
		String sql = "insert into board values(bt_seq.nextval,?,?,sysdate,0,?,'Q',?,bt_seq.currval,?,?)";
		System.out.println(qnaDTO.getWriter());
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1,qnaDTO.getSubject());
		st.setString(2,qnaDTO.getWriter());
		st.setString(3,qnaDTO.getContents());
		st.setInt(4,qnaDTO.getPnum());
//		st.setInt(5,qnaDTO.getRef());
		st.setInt(5,qnaDTO.getStep());
		st.setInt(6,qnaDTO.getDepth());
		
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
				+ "and ? like ?) Q "
				+ "order by R desc) "
				+ "where R between ? and ? ";
		
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1,search.getKind());
		st.setString(2, "%"+search.getSearch()+"%");
		st.setInt(3, rowNumber.getStartRow());
		st.setInt(4, rowNumber.getLastRow());
		ResultSet rs = st.executeQuery();
		
		while(rs.next()) {
			QnaDTO qDTO = new QnaDTO();
			qDTO.setNum(rs.getInt("num"));
			qDTO.setSubject(rs.getString("subject"));
			qDTO.setWriter(rs.getString("writer"));
			qDTO.setReg_date(rs.getDate("reg_date"));
			qDTO.setHit(rs.getInt("hit"));
			qDTO.setContents(rs.getString("contents"));
			qDTO.setKind("Q");
			ar.add(qDTO);
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
