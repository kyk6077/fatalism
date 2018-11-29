package com.iu.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.iu.action.ActionFoward;
import com.iu.member.MemberDAO;
import com.iu.member.MemberService;

/**
 * Servlet implementation class MemberController
 */
@WebServlet("/MemberController")
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private MemberService memberService;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberController() {
        super();
        // TODO Auto-generated constructor stub
        memberService = new MemberService();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String command = request.getPathInfo();
		ActionFoward actionFoward=null;
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		if(command.equals("/memberJoin.do")) {
			actionFoward = memberService.insert(request, response);
		}else if(command.equals("/memberLogin.do")) {
			actionFoward = memberService.login(request, response);
		}else if(command.equals("/memberLogout.do")){
			actionFoward = memberService.logout(request, response);
		}else if(command.equals("/memberUpdate.do")) {
			actionFoward = memberService.update(request, response);
		}else if(command.equals("/memberCheckId.do")) {
			actionFoward = memberService.checkId(request, response);
		}else if(command.equals("/memberDelete.do")) {
			actionFoward = memberService.delete(request, response);
		}else if(command.equals("/findId.do")){
			actionFoward = memberService.findId(request, response);
		}else if(command.equals("/findId2.do")) {
			actionFoward = memberService.findId2(request, response);
		}else if(command.equals("/findPw.do")) {
			actionFoward = memberService.findPw(request, response);
		}else if(command.equals("/findPw2.do")) {
			actionFoward = memberService.findPw2(request, response);
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
