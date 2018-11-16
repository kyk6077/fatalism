package com.fatalism.review;

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
import com.fatalism.qna.QnaDTO;

public class ReviewService implements BoardService{
	private ReviewDAO reviewDAO;

	public ReviewService() {
		reviewDAO = new ReviewDAO();
	}


	@Override
	public ActionFoward insert(HttpServletRequest request, HttpServletResponse response) {
		ActionFoward actionFoward = new ActionFoward();
		String method = request.getMethod();
		if(method.equals("POST")) {
			ReviewDTO reviewDTO = new ReviewDTO();
			try {
				reviewDTO.setSubject(request.getParameter("subject"));
				reviewDTO.setWriter(request.getParameter("writer"));
				reviewDTO.setContents(request.getParameter("contents"));
				reviewDTO.setHide(request.getParameter("hide_radio"));
				reviewDTO.setPw(request.getParameter("board_pw"));
				int result = reviewDAO.insert(reviewDTO);
				if(result>0) {
					request.setAttribute("message", "Write Success");
					request.setAttribute("path", "./reviewList.do");
					actionFoward.setPath("../WEB-INF/view/common/result.jsp");
					actionFoward.setCheck(true);
				}else {
					request.setAttribute("message","Write Fail");
					request.setAttribute("path","./reviewWrite.do");
					actionFoward.setCheck(true);
					actionFoward.setPath("../WEB-INF/view/common/result.jsp");
				}

			}catch (Exception e) {
				request.setAttribute("message","Write Fail");
				request.setAttribute("path","./reviewWrite.do");
				actionFoward.setCheck(true);
				actionFoward.setPath("../WEB-INF/view/common/result.jsp");
			}

		}else {
			request.setAttribute("board","review");
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
	public ActionFoward selectList(HttpServletRequest request, HttpServletResponse response) {
		ActionFoward actionFoward = new ActionFoward();
		List<ReviewDTO> ar = null;
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
			int totalCount = reviewDAO.getNum();
			Pager pager = makePager.MakePage(totalCount);

			ar = reviewDAO.selectList(rowNumber,pager.getSearch());
			actionFoward.setCheck(true);
			actionFoward.setPath("../WEB-INF/view/board/boardList.jsp");
			request.setAttribute("list", ar);
			request.setAttribute("pager",pager);
			request.setAttribute("board","review");
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
	public ActionFoward selectOne(HttpServletRequest request, HttpServletResponse response) {
		ActionFoward actionFoward = new ActionFoward();

		try {
			ReviewDTO reviewDTO = reviewDAO.selectOne(Integer.parseInt(request.getParameter("num")));
			if(reviewDTO!=null) {
				request.setAttribute("boardDTO",reviewDTO);
				actionFoward.setCheck(true);
				actionFoward.setPath("../WEB-INF/view/board/boardSelectOne.jsp");
			}else {
				request.setAttribute("message", "Fail");
				request.setAttribute("path", "./reviewList.do");
				actionFoward.setCheck(true);
				actionFoward.setPath("../WEB-INF/view/common/result.jsp");
			}
		}catch (Exception e) {
			request.setAttribute("message", "Fail");
			request.setAttribute("path", "./reviewList.do");
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
				int result = reviewDAO.pwCheck(num, pw);
				request.setAttribute("num", num);
				if(result>0) {
					actionFoward.setCheck(true);
					actionFoward.setPath("./reviewSelectOne.do");
				}else {
					actionFoward.setCheck(true);
					actionFoward.setPath("../WEB-INF/view/board/secretBoard.jsp");
					request.setAttribute("board","review");
					request.setAttribute("num",num);
				}
			}catch (Exception e) {
				System.out.println("post Exception");
			}
		}else {
			try {
				num = Integer.parseInt(request.getParameter("num"));
				request.setAttribute("board","review");
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
