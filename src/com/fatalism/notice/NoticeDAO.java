package com.fatalism.notice;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.util.List;

import com.fatalism.board.BoardDAO;
import com.fatalism.board.BoardDTO;
import com.fatalism.util.DBConnector;

public class NoticeDAO implements BoardDAO{

//	public static void main(String[] args) {
//		NoticeDAO noticeDAO = new NoticeDAO();
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
//		
//	}
	public int insert(BoardDTO boardDTO) throws Exception {
		Connection con = DBConnector.getConnect();
		String sql = "insert into board values(bt_seq.nextval,?,?,sysdate,0,?,'N',null,null,null,null)";
		System.out.println(boardDTO.getWriter());
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1,boardDTO.getSubject());
		st.setString(2,boardDTO.getWriter());
		st.setString(3,boardDTO.getContents());
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
