package com.fatalism.notice;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fatalism.action.ActionFoward;
import com.fatalism.board.BoardService;
import com.fatalism.page.MakePager;
import com.fatalism.page.Pager;
import com.fatalism.page.RowNumber;
import com.fatalism.page.Search;
import com.fatalism.qna.QnaDTO;

public class NoticeService implements BoardService{
	private NoticeDAO noticeDAO;

	public NoticeService() {
		noticeDAO = new NoticeDAO();
	}


	@Override
	public ActionFoward selectList(HttpServletRequest request, HttpServletResponse response) {
		ActionFoward actionFoward = new ActionFoward();
		List<NoticeDTO> ar = null;
		String message = "listSuccess";
		try {
			int curPage = 1;
			try{curPage = Integer.parseInt(request.getParameter("curPage"));
			}catch(Exception e) {}
			Search search = new Search();
			search.setSearch(request.getParameter("search"));
			search.setKind(request.getParameter("kind"));
			MakePager makePager = new MakePager(curPage, search);
			RowNumber rowNumber = makePager.MakeRow();
			int totalCount = noticeDAO.getNum();
			Pager pager = makePager.MakePage(totalCount);
			ar = noticeDAO.selectList(rowNumber,pager.getSearch());
			actionFoward.setCheck(true);
			actionFoward.setPath("../WEB-INF/view/board/boardList.jsp");
			request.setAttribute("list", ar);
			request.setAttribute("pager",pager);
			request.setAttribute("board","notice");
		} catch (Exception e) {
			actionFoward.setCheck(true);
			actionFoward.setPath("../WEB-INF/common/result.jsp");
			message = "list Fali";
			request.setAttribute("path", "../index.jsp");
			request.setAttribute("message", message);
			e.printStackTrace();
		}
		return actionFoward;
	}

	@Override
	public ActionFoward insert(HttpServletRequest request, HttpServletResponse response) {
		ActionFoward actionFoward = new ActionFoward();
		String method = request.getMethod();
		if(method.equals("POST")) {
			NoticeDTO noticeDTO = new NoticeDTO();
			try {
				noticeDTO.setSubject(request.getParameter("subject"));
				noticeDTO.setWriter(request.getParameter("writer"));
				noticeDTO.setContents(request.getParameter("contents"));
				noticeDTO.setHide(request.getParameter("hide_radio"));
				noticeDTO.setPw(request.getParameter("board_pw"));
				int result = noticeDAO.insert(noticeDTO);
				if(result>0) {
					request.setAttribute("message", "Write Success");
					request.setAttribute("path", "./noticeList.do");
					actionFoward.setPath("../WEB-INF/view/common/result.jsp");
					actionFoward.setCheck(true);
				}else {
					request.setAttribute("message","Write Fail");
					request.setAttribute("path","./noticeWrite.do");
					actionFoward.setCheck(true);
					actionFoward.setPath("../WEB-INF/view/common/result.jsp");
				}

			}catch (Exception e) {
				request.setAttribute("message","Write Fail");
				request.setAttribute("path","./noticeWrite.do");
				actionFoward.setCheck(true);
				actionFoward.setPath("../WEB-INF/view/common/result.jsp");
			}

		}else {
			request.setAttribute("board","notice");
			actionFoward.setCheck(true);
			actionFoward.setPath("../WEB-INF/view/board/boardWrite.jsp");
		}

		return actionFoward;
	}
	@Override
	public ActionFoward delete(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ActionFoward update(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public ActionFoward selectOne(HttpServletRequest request, HttpServletResponse response) {
		ActionFoward actionFoward = new ActionFoward();

		try {
			NoticeDTO noticeDTO = noticeDAO.selectOne(Integer.parseInt(request.getParameter("num")));
			if(noticeDTO!=null) {
				request.setAttribute("boardDTO",noticeDTO);
				actionFoward.setCheck(true);
				actionFoward.setPath("../WEB-INF/view/board/boardSelectOne.jsp");
			}else {
				request.setAttribute("message", "Fail");
				request.setAttribute("path", "./noticeList.do");
				actionFoward.setCheck(true);
				actionFoward.setPath("../WEB-INF/view/common/result.jsp");
			}
		}catch (Exception e) {
			request.setAttribute("message", "Fail");
			request.setAttribute("path", "./noticeList.do");
			actionFoward.setCheck(true);
			actionFoward.setPath("../WEB-INF/view/common/result.jsp");
		}

		return actionFoward;
	}


	@Override
	public ActionFoward pwCheck(HttpServletRequest request, HttpServletResponse response) {
		ActionFoward actionFoward = new ActionFoward();
		String method = request.getMethod();
		actionFoward.setCheck(true);
		actionFoward.setPath("../WEB-INF/view/board/secretBoard.jsp");
		
		int num = -1;
		if(method.equals("POST")) {
			try {
				num = Integer.parseInt(request.getParameter("num"));
				String pw = request.getParameter("pw");
				int result = noticeDAO.pwCheck(num, pw);
				request.setAttribute("num", num);
				if(result>0) {
					actionFoward.setCheck(true);
					actionFoward.setPath("./noticeSelectOne.do");
				}else {
					actionFoward.setCheck(true);
					actionFoward.setPath("../WEB-INF/view/board/secretBoard.jsp");
					request.setAttribute("board","notice");
					request.setAttribute("num",num);
				}
			}catch (Exception e) {
				System.out.println("post Exception");
			}
		}else {
			try {
				num = Integer.parseInt(request.getParameter("num"));
				request.setAttribute("board","notice");
				request.setAttribute("num",num);
				actionFoward.setCheck(true);
				actionFoward.setPath("../WEB-INF/view/board/secretBoard.jsp");
			}catch (Exception e) {
				System.out.println("get Exception");
			}
		}
		
		return actionFoward;
	}

	
}
