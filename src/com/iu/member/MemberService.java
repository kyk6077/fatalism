package com.iu.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.iu.action.ActionFoward;

public class MemberService {
	private MemberDAO memberDAO;

	public MemberService() {
		memberDAO = new MemberDAO();
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
		System.out.println("0");
		System.out.println(request.getParameter("name"));
		if(method.equals("POST")) {
			System.out.println("1");
			try {
				MemberDTO memberDTO = new MemberDTO();
				System.out.println("2");
				memberDTO.setId(request.getParameter("id"));
				memberDTO.setPw(request.getParameter("pw"));
				memberDTO.setName(request.getParameter("name"));
				memberDTO.setNum_address(Integer.parseInt(request.getParameter("num_address")));
				memberDTO.setMain_address(request.getParameter("main_address"));
				memberDTO.setSub_address(request.getParameter("sub_address"));
				
				memberDTO.setCountry(request.getParameter("country"));
				
				memberDTO.setPhone(request.getParameter("phone"));
				
				memberDTO.setEmail(request.getParameter("email"));
				
				memberDTO.setMoney(1000);
				memberDTO.setKind("U");
				System.out.println("3");
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
