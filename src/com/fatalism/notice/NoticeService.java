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
			actionFoward.setPath("../WEB-INF/view/common/result.jsp");
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
		ActionFoward actionFoward = new ActionFoward();
		actionFoward.setCheck(true);
		actionFoward.setPath("../WEB-INF/view/common/result.jsp");
		try {
			int num = Integer.parseInt(request.getParameter("num"));
			String pw = request.getParameter("board_pw");
			int result = noticeDAO.delete(num,pw);
			if(result>0) {
				request.setAttribute("message","Delete Success");
				request.setAttribute("path","./noticeList.do");
			}else {
				request.setAttribute("message","Delete Fail");
				request.setAttribute("path","./noticeSelectOne.do?num="+num);
			}
		} catch (Exception e) {
			request.setAttribute("message","Delete Error");
			request.setAttribute("path","./noticeList.do");
		}
		
		return actionFoward;
	}

	@Override
	public ActionFoward update(HttpServletRequest request, HttpServletResponse response) {
		ActionFoward actionFoward = new ActionFoward();
		String method = request.getMethod();
		NoticeDTO noticeDTO=null;
		actionFoward.setCheck(true);
		actionFoward.setPath("./noticeList.do");
		if(method.equals("POST")) {
			System.out.println("post");
			try {
				noticeDTO = new NoticeDTO();
				noticeDTO.setNum(Integer.parseInt(request.getParameter("num")));
				noticeDTO.setSubject(request.getParameter("subject"));
				noticeDTO.setContents(request.getParameter("contents"));
				int result = noticeDAO.update(noticeDTO);
				request.setAttribute("message","Fail");
				request.setAttribute("path","./noticeSelectOne.do?num="+noticeDTO.getNum());
				actionFoward.setCheck(true);
				actionFoward.setPath("../WEB-INF/view/common/result.jsp");
				if(result>0) {
					request.setAttribute("message","Update Success");
				}
				
			} catch (Exception e) {
				//null값 들어올떄 exception발생 처리해야함
				System.out.println("exception 발생");
				e.printStackTrace();
			}
			
		}else {
			try {
				int num = Integer.parseInt(request.getParameter("num"));
				noticeDTO = noticeDAO.selectOne(num);
				request.setAttribute("boardDTO",noticeDTO);
				request.setAttribute("board","notice");
				actionFoward.setCheck(true);
				actionFoward.setPath("../WEB-INF/view/board/boardUpdate.jsp");
				
			} catch (Exception e) {
				System.out.println("try 에러 10");
				//에러시 list로 
			}
			
		}
		return actionFoward;
	}


	@Override
	public ActionFoward selectOne(HttpServletRequest request, HttpServletResponse response) {
		ActionFoward actionFoward = new ActionFoward();

		try {
			NoticeDTO noticeDTO = noticeDAO.selectOne(Integer.parseInt(request.getParameter("num")));
			if(noticeDTO!=null) {
				request.setAttribute("board","notice");
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
