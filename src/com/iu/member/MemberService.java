package com.iu.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.iu.action.ActionFoward;

public class MemberService {
	private MemberDAO memberDAO;

	public MemberService() {
		memberDAO = new MemberDAO();
	}
	
	//주문내역
	
	
	//회원 정보 수정
	public ActionFoward update(HttpServletRequest request, HttpServletResponse response) {
		ActionFoward actionFoward = new ActionFoward();
		String method = request.getMethod();
		String message="다시입력하세요";
		
		if(method.equals("POST")) {
			request.setAttribute("message", message);
			request.setAttribute("path", "./memberUpdate.do");
			actionFoward.setCheck(true);
			actionFoward.setPath("../WEB-INF/view/common/result.jsp");
			try {
				System.out.println("up");
				String phone = request.getParameter("phone")+"-";
				phone += request.getParameter("phone1")+"-";
				phone += request.getParameter("phone2");
				String email = request.getParameter("email")+"@";
				email += request.getParameter("email1");
				
				MemberDTO memberDTO = new MemberDTO();
				memberDTO.setId(request.getParameter("id"));
				memberDTO.setPw(request.getParameter("pw"));
				memberDTO.setName(request.getParameter("name"));
				memberDTO.setNum_address(Integer.parseInt(request.getParameter("num_address")));
				memberDTO.setMain_address(request.getParameter("main_address"));
				memberDTO.setSub_address(request.getParameter("sub_address"));
				memberDTO.setCountry("I");
				memberDTO.setPhone(request.getParameter("phone"));
				memberDTO.setEmail(request.getParameter("email"));
				int result = memberDAO.update(memberDTO);
				if(result>0) {
					HttpSession session = request.getSession();
					session.setAttribute("member", memberDTO);
					actionFoward.setCheck(false);
					actionFoward.setPath("../index.jsp");
				}
				
			}catch (Exception e) {
				// TODO: handle exception
			}

			
		}else {
			
			
			actionFoward.setCheck(true);
			actionFoward.setPath("../WEB-INF/view/member/memberUpdate.jsp");
		}
		
		return actionFoward;
	}
	
	
	//로그아웃
	public ActionFoward logout(HttpServletRequest request, HttpServletResponse response) {
		ActionFoward actionFoward = new ActionFoward();
		HttpSession session = request.getSession();
		session.invalidate();
		actionFoward.setCheck(false);
		actionFoward.setPath("../index.jsp");
		
		return actionFoward;
	}
	
	

	//로그인
	public ActionFoward login(HttpServletRequest request, HttpServletResponse response) {
		//아이디 비번 받아서 처리하는 일을함
		ActionFoward actionFoward= new ActionFoward();
		String method = request.getMethod();
		String message = "아이디나 비밀번호가 틀렸습니다.";
		if(method.equals("POST")) {
			request.setAttribute("message", message);
			request.setAttribute("path", "./memberLogin.do");
			actionFoward.setCheck(true);
			actionFoward.setPath("../WEB-INF/view/common/result.jsp");
			

			MemberDTO memberDTO = new MemberDTO();
			memberDTO.setId(request.getParameter("id"));
			memberDTO.setPw(request.getParameter("pw"));
			
			try {
				System.out.println("login");
				memberDTO = memberDAO.login(memberDTO);

			} catch (Exception e) {
				memberDTO= null;
				e.printStackTrace();
			}

			if(memberDTO!=null) {
				HttpSession session = request.getSession();
				session.setAttribute("member", memberDTO);
				actionFoward.setCheck(false);
				actionFoward.setPath("../index.jsp");
			}

		}else {
			//GET
			actionFoward.setCheck(true);
			actionFoward.setPath("../WEB-INF/view/member/memberLogin.jsp");
		}

		return actionFoward;
	}


	//중복확인
	public ActionFoward checkId(HttpServletRequest request, HttpServletResponse response) {
		ActionFoward actionFoward = new ActionFoward();
		String result = "1";//1이면 사용가능
		boolean check =true;
		String id = request.getParameter("id");
		try {
			check=memberDAO.checkId(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(check) {
			result="2";//사용불가능
		}

		request.setAttribute("result", result);
		actionFoward.setCheck(true);
		actionFoward.setPath("../WEB-INF/view/member/memberCheckId.jsp");

		return actionFoward;

	}


	//insert
	public ActionFoward insert(HttpServletRequest request, HttpServletResponse response) {
		ActionFoward actionFoward = new ActionFoward();
		String method = request.getMethod();
		//System.out.println("0");
		//System.out.println(request.getParameter("name"));
		if(method.equals("POST")) {
			try {
				String phone = request.getParameter("phone")+"-";
				phone += request.getParameter("phone1")+"-";
				phone += request.getParameter("phone2");
				String email = request.getParameter("email")+"@";
				email += request.getParameter("email1");
				MemberDTO memberDTO = new MemberDTO();
				memberDTO.setId(request.getParameter("id"));
				memberDTO.setPw(request.getParameter("pw"));
				memberDTO.setName(request.getParameter("name"));
				memberDTO.setNum_address(Integer.parseInt(request.getParameter("num_address")));
				memberDTO.setMain_address(request.getParameter("main_address"));
				memberDTO.setSub_address(request.getParameter("sub_address"));
				memberDTO.setCountry("I");
				memberDTO.setPhone(phone);
				memberDTO.setEmail(email);
				memberDTO.setMoney(1000);
				memberDTO.setKind("U");
				int result = memberDAO.insert(memberDTO);
				if(result>0) {
					request.setAttribute("message", "Join Success");
					request.setAttribute("path", "../index.jsp");
				}else {
					request.setAttribute("message", "Join Fail");
					request.setAttribute("path", "./memberJoin.do");
				}

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			actionFoward.setCheck(true);
			actionFoward.setPath("../WEB-INF/view/common/result.jsp");

		}else {
			actionFoward.setCheck(true);
			actionFoward.setPath("../WEB-INF/view/member/memberJoin.jsp");
		}//post

		return actionFoward;

	}

}
