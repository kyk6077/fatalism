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
import com.fatalism.reply.ReplyDAO;
import com.fatalism.reply.ReplyDTO;

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
				e.printStackTrace();
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
		ActionFoward actionFoward = new ActionFoward();
		actionFoward.setCheck(true);
		actionFoward.setPath("../WEB-INF/view/common/result.jsp");
		
		try {
			int num = Integer.parseInt(request.getParameter("num"));
			String pw = request.getParameter("board_pw");
			int result = reviewDAO.delete(num,pw);
			if(result>0) {
				request.setAttribute("message","Delete Success");
				request.setAttribute("path","./reviewList.do");
			}else {
				request.setAttribute("message","Delete Fail");
				request.setAttribute("path","./reviewSelectOne.do?num="+num);
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("message","Delete Error");
			request.setAttribute("path","./reviewList.do");
		}
		
		return actionFoward;
	}

	@Override
	public ActionFoward update(HttpServletRequest request, HttpServletResponse response) {
		ActionFoward actionFoward = new ActionFoward();
		String method = request.getMethod();
		ReviewDTO reviewDTO=null;
		actionFoward.setCheck(true);
		actionFoward.setPath("./reviewList.do");
		if(method.equals("POST")) {
			try {
				reviewDTO = new ReviewDTO();
				reviewDTO.setNum(Integer.parseInt(request.getParameter("num")));
				reviewDTO.setSubject(request.getParameter("subject"));
				reviewDTO.setContents(request.getParameter("contents"));
				int result = reviewDAO.update(reviewDTO);
				request.setAttribute("message","Fail");
				request.setAttribute("path","./reviewSelectOne.do?num="+reviewDTO.getNum());
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
				reviewDTO = reviewDAO.selectOne(num);
				request.setAttribute("boardDTO",reviewDTO);
				request.setAttribute("board","review");
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
				ReplyDAO replyDAO = new ReplyDAO();
				List<ReplyDTO> ar=null;
					request.setAttribute("board","review");
					request.setAttribute("boardDTO",reviewDTO);
					actionFoward.setCheck(true);
					actionFoward.setPath("../WEB-INF/view/board/boardSelectOne.jsp");
					ar = replyDAO.selectList(reviewDTO.getNum());
					if(!ar.isEmpty()) {
						request.setAttribute("replyList", ar);
					}
			}else {
				request.setAttribute("message", "Fail");
				request.setAttribute("path", "./reviewList.do");
				actionFoward.setCheck(true);
				actionFoward.setPath("../WEB-INF/view/common/result.jsp");
			}
		}catch (Exception e) {
			e.printStackTrace();
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

	public ActionFoward commentInsert(HttpServletRequest request, HttpServletResponse response){
		ActionFoward actionFoward = new ActionFoward();
		ReplyDAO replyDAO = new ReplyDAO();
		ReplyDTO replyDTO = new ReplyDTO();
		try {
			int num = Integer.parseInt(request.getParameter("num"));
			replyDTO.setId(request.getParameter("id"));
			replyDTO.setNum(num);
			replyDTO.setContents(request.getParameter("contents"));
			int result = replyDAO.insert(replyDTO);
			
			if(result>0) {
				request.setAttribute("replyDTO", replyDTO);
				actionFoward.setCheck(true);
				actionFoward.setPath("../WEB-INF/view/board/replyView.jsp");
			}else {
				actionFoward.setCheck(true);
				actionFoward.setPath("./reviewSelectOne.do?num="+num);				
			}
		} catch (Exception e) {
			e.printStackTrace();
			actionFoward.setCheck(true);
			actionFoward.setPath("./reviewList.do");	
		}
		
		return actionFoward;
	}

}
