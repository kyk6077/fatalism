package com.fatalism.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fatalism.action.ActionFoward;
import com.fatalism.review.ReviewService;

/**
 * Servlet implementation class ReviewController
 */
@WebServlet("/ReviewController")
public class ReviewController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private ReviewService reviewService;   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReviewController() {
        super();
        reviewService = new ReviewService();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		ActionFoward actionFoward = null;
		String command = request.getPathInfo();
		if(command.equals("/reviewList.do")) {
			actionFoward = reviewService.selectList(request, response);
		}else if(command.equals("/reviewWrite.do")) {
			actionFoward = reviewService.insert(request, response);
		}else if(command.equals("/reviewSelectOne.do")) {
			actionFoward = reviewService.selectOne(request, response);
		}else if(command.equals("/reviewPwCheck.do")) {
			actionFoward = reviewService.pwCheck(request, response);
		}else if(command.equals("/reviewDelete.do")) {
			actionFoward = reviewService.delete(request, response);
		}else if(command.equals("/reviewUpdate.do")) {
			actionFoward = reviewService.update(request, response);
		}else if(command.equals("/comment.do")) {
			actionFoward = reviewService.commentInsert(request, response);
		}else if(command.equals("/commentDelete.do")) {
			actionFoward = reviewService.commentDelete(request, response);
		}else if(command.equals("/commentUpdate.do")) {
			actionFoward = reviewService.commentUpdate(request, response);
		}else {
			System.out.println("실패");
		}
		
		if(actionFoward.isCheck()) {
			RequestDispatcher view = request.getRequestDispatcher(actionFoward.getPath());
			view.forward(request, response);
		}else {
			response.sendRedirect(actionFoward.getPath());
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
