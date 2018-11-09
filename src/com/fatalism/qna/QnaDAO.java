package com.fatalism.qna;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.Random;

import com.fatalism.board.BoardDAO;
import com.fatalism.board.BoardDTO;
import com.fatalism.notice.NoticeDAO;
import com.fatalism.notice.NoticeDTO;
import com.fatalism.util.DBConnector;

public class QnaDAO implements BoardDAO{

	public static void main(String[] args) {
		QnaDAO qnaDAO = new QnaDAO();
//		Random r = new random
		QnaDTO qnaDTO = null;
		try {
			for(int i=0;i<10;i++) {
			qnaDTO = new QnaDTO();
			qnaDTO.setSubject("qsubject"+i);
			qnaDTO.setWriter("qWriter"+i);
			qnaDTO.setContents("qcontenst"+i);
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
		String sql = "insert into board values(bt_seq.nextval,?,?,sysdate,0,?,'Q',?,0,0,0)";
		System.out.println(qnaDTO.getWriter());
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1,qnaDTO.getSubject());
		st.setString(2,qnaDTO.getWriter());
		st.setString(3,qnaDTO.getContents());
		st.setInt(4,qnaDTO.getPnum());
		
		int result = st.executeUpdate();
		
		DBConnector.disConnect(con, st);
		return result;
	}

	@Override
	public int delete(int num) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update() throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<BoardDTO> selectList() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BoardDTO selectOne() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}


	

	
	
}
