package com.fatalism.qna;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fatalism.action.ActionFoward;
import com.fatalism.board.BoardService;
import com.fatalism.notice.NoticeDTO;
import com.fatalism.page.MakePager;
import com.fatalism.page.Pager;
import com.fatalism.page.RowNumber;
import com.fatalism.page.Search;

public class QnaService implements BoardService{
	private QnaDAO qnaDAO;

	public QnaService() {
		qnaDAO = new QnaDAO();
	}




	@Override
	public ActionFoward insert(HttpServletRequest request, HttpServletResponse response) {
		ActionFoward actionFoward = new ActionFoward();
		String method = request.getMethod();
		if(method.equals("POST")) {
			QnaDTO qnaDTO = new QnaDTO();
			try {
				qnaDTO.setSubject(request.getParameter("subject"));
				qnaDTO.setWriter(request.getParameter("writer"));
				qnaDTO.setContents(request.getParameter("contents"));
				qnaDTO.setPnum(1);
				qnaDTO.setPw(request.getParameter("board_pw"));
				int result = qnaDAO.insert(qnaDTO);
				if(result>0) {
					request.setAttribute("message", "Write Success");
					request.setAttribute("path", "./qnaList.do");
					actionFoward.setPath("../WEB-INF/view/common/result.jsp");
					actionFoward.setCheck(true);
				}else {
					request.setAttribute("message","Write Fail");
					request.setAttribute("path","./qnaWrite.do");
					actionFoward.setCheck(true);
					actionFoward.setPath("../WEB-INF/view/common/result.jsp");
				}

			}catch (Exception e) {
				request.setAttribute("message","Write Fail");
				request.setAttribute("path","./qnaWrite.do");
				actionFoward.setCheck(true);
				actionFoward.setPath("../WEB-INF/view/common/result.jsp");
			}

		}else {
			request.setAttribute("board","qna");
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
			int result = qnaDAO.delete(num,pw);
			if(result>0) {
				request.setAttribute("message","Delete Success");
				request.setAttribute("path","./qnaList.do");
			}else {
				request.setAttribute("message","Delete Fail");
				request.setAttribute("path","./qnaSelectOne.do?num="+num);
			}
		} catch (Exception e) {
			request.setAttribute("message","Delete Error");
			request.setAttribute("path","./qnaList.do");
		}
		
		return actionFoward;
	}

	@Override
	public ActionFoward update(HttpServletRequest request, HttpServletResponse response) {
		ActionFoward actionFoward = new ActionFoward();
		String method = request.getMethod();
		QnaDTO qnaDTO=null;
		actionFoward.setCheck(true);
		actionFoward.setPath("./qnaList.do");
		if(method.equals("POST")) {
			try {
				qnaDTO = new QnaDTO();
				qnaDTO.setNum(Integer.parseInt(request.getParameter("num")));
				qnaDTO.setSubject(request.getParameter("subject"));
				qnaDTO.setContents(request.getParameter("contents"));
				int result = qnaDAO.update(qnaDTO);
				request.setAttribute("message","Fail");
				request.setAttribute("path","./qnaSelectOne.do?num="+qnaDTO.getNum());
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
				qnaDTO = qnaDAO.selectOne(num);
				request.setAttribute("boardDTO",qnaDTO);
				request.setAttribute("board","qna");
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
	public ActionFoward selectList(HttpServletRequest request, HttpServletResponse response) {
		ActionFoward actionFoward = new ActionFoward();

		List<QnaDTO> ar = null;
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
			int totalCount = qnaDAO.getNum();
			Pager pager = makePager.MakePage(totalCount);

			ar = qnaDAO.selectList(rowNumber,pager.getSearch());
			actionFoward.setCheck(true);
			actionFoward.setPath("../WEB-INF/view/board/boardList.jsp");
			request.setAttribute("list", ar);
			request.setAttribute("pager",pager);
			request.setAttribute("board","qna");
		} catch (Exception e) {
			e.printStackTrace();
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
	public ActionFoward selectOne(HttpServletRequest request, HttpServletResponse response) {
		ActionFoward actionFoward = new ActionFoward();

		
		try {
			QnaDTO qnaDTO = qnaDAO.selectOne(Integer.parseInt(request.getParameter("num")));
			if(qnaDTO!=null) {
				request.setAttribute("board","qna");
				request.setAttribute("boardDTO",qnaDTO);
				actionFoward.setCheck(true);
				actionFoward.setPath("../WEB-INF/view/board/boardSelectOne.jsp");
			}else {
				request.setAttribute("message", "Fail");
				request.setAttribute("path", "./qnaList.do");
				actionFoward.setCheck(true);
				actionFoward.setPath("../WEB-INF/view/common/result.jsp");
			}
		}catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("message", "Fail");
			request.setAttribute("path", "./qnaList.do");
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
				int result = qnaDAO.pwCheck(num, pw);
				request.setAttribute("num", num);
				if(result>0) {
					actionFoward.setCheck(true);
					actionFoward.setPath("./qnaSelectOne.do");
				}else {
					actionFoward.setCheck(true);
					actionFoward.setPath("../WEB-INF/view/board/secretBoard.jsp");
					request.setAttribute("board","qna");
					request.setAttribute("num",num);
				}
			}catch (Exception e) {
				System.out.println("post Exception");
			}
		}else {
			try {
				num = Integer.parseInt(request.getParameter("num"));
				request.setAttribute("board","qna");
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
