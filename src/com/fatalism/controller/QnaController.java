package com.fatalism.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fatalism.action.ActionFoward;
import com.fatalism.qna.QnaService;

/**
 * Servlet implementation class QnaController
 */
@WebServlet("/QnaController")
public class QnaController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private QnaService qnaService;   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QnaController() {
        super();
        qnaService = new QnaService();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ActionFoward actionFoward = null;
		String command = request.getPathInfo();
		if(command.equals("/qnaList.do")) {
			actionFoward = qnaService.selectList(request, response);
		}else if(command.equals("/qnaWrite.do")) {
			actionFoward = qnaService.insert(request, response);
		}else if(command.equals("/qnaSelectOne.do")) {
			actionFoward = qnaService.selectOne(request, response);
		}else if(command.equals("/qnaPwCheck.do")) {
			actionFoward = qnaService.pwCheck(request, response);
		}else if(command.equals("/qnaDelete.do")) {
			actionFoward = qnaService.delete(request, response);
		}else if(command.equals("/qnaUpdate.do")) {
			actionFoward = qnaService.update(request, response);
		}else if(command.equals("/reboardWrite.do")) {
			actionFoward = qnaService.reboardInsert(request, response);
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
