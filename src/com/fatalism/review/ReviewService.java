package com.fatalism.review;

import java.io.File;
import java.util.Enumeration;
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
import com.fatalism.upload.UploadDAO;
import com.fatalism.upload.UploadDTO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;


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
			int maxSize = 1024*1024*10;
			String path = request.getServletContext().getRealPath("upload");
			File file = new File(path);
			if(!file.exists()) {
				file.mkdirs();
			}
			try {
				MultipartRequest multi = new MultipartRequest(request,path, maxSize,"utf-8",new DefaultFileRenamePolicy());
				reviewDTO.setSubject(multi.getParameter("subject"));
				reviewDTO.setWriter(multi.getParameter("writer"));
				reviewDTO.setContents(multi.getParameter("contents"));
				reviewDTO.setHide(multi.getParameter("hide_radio"));
				reviewDTO.setPw(multi.getParameter("board_pw"));
				int result = reviewDAO.insert(reviewDTO);
				if(result>0) {
					UploadDAO uploadDAO = new UploadDAO();
					UploadDTO uploadDTO = new UploadDTO();
					Enumeration<Object> e = multi.getFileNames();
					while(e.hasMoreElements()) {
						String s = (String)e.nextElement();
						uploadDTO.setFname(multi.getFilesystemName(s));
						uploadDTO.setOname(multi.getOriginalFileName(s));
						uploadDTO.setStep(0);
						uploadDAO.insert(uploadDTO);
					}
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
			int bnum = Integer.parseInt(request.getParameter("bnum"));
			replyDTO.setId(request.getParameter("id"));
			replyDTO.setBnum(bnum);
			replyDTO.setContents(request.getParameter("contents"));
			int result = replyDAO.insert(replyDTO);
			
			if(result>0) {
				List<ReplyDTO> ar = replyDAO.selectList(bnum);
				request.setAttribute("replyList", ar);
				actionFoward.setCheck(true);
				actionFoward.setPath("../WEB-INF/view/board/replyView.jsp");
			}else {
				request.setAttribute("message","fail");
				actionFoward.setCheck(true);
				actionFoward.setPath("../WEB-INF/view/common/resultjax.jsp");			
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("message","fail");
			actionFoward.setCheck(true);
			actionFoward.setPath("../WEB-INF/view/common/resultjax.jsp");
		}
		
		return actionFoward;
	}
	
	public ActionFoward commentDelete(HttpServletRequest request, HttpServletResponse response){
		ActionFoward actionFoward = new ActionFoward();
		ReplyDAO replyDAO = new ReplyDAO();
		try {
			int result = replyDAO.delete(Integer.parseInt(request.getParameter("num")));
			if(result>0) {
				request.setAttribute("result", "success");
			}else {
				request.setAttribute("result", "fail");				
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("result", "fail2");
		}
		actionFoward.setCheck(true);
		actionFoward.setPath("../WEB-INF/view/common/replyDeleteAjax.jsp");
		return actionFoward;
	}
	
	public ActionFoward commentUpdate(HttpServletRequest request, HttpServletResponse response){
		ActionFoward actionFoward = new ActionFoward();
		ReplyDAO replyDAO = new ReplyDAO();
		ReplyDTO replyDTO = new ReplyDTO();
		try {
			int num = Integer.parseInt(request.getParameter("num"));
			replyDTO.setNum(num);
			replyDTO.setContents(request.getParameter("contents"));
			int result = replyDAO.update(replyDTO);
			if(result>0) {
				request.setAttribute("contents", replyDTO.getContents());
				actionFoward.setCheck(true);
				actionFoward.setPath("../WEB-INF/view/common/replyUpdate.jsp");
			}else {
				request.setAttribute("message", "Fail");
				actionFoward.setCheck(true);
				actionFoward.setPath("../WEB-INF/view/common/result.jsp");
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("message", "error");
			actionFoward.setCheck(true);
			actionFoward.setPath("../WEB-INF/view/common/result.jsp");
		}
		
		return actionFoward;
	}

}
