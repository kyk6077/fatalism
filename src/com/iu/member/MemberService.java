package com.iu.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.iu.action.ActionFoward;

public class MemberService {
	private MemberDAO memberDAO;
	
	public MemberService() {
		memberDAO = new MemberDAO();
	}
	
	//insert
	public ActionFoward insert(HttpServletRequest request, HttpServletResponse response) {
		ActionFoward actionFoward = new ActionFoward();
		String method = request.getMethod();
		
		if(method.equals("POST")) {
		
		try {
		MemberDTO memberDTO = new MemberDTO();
		memberDTO.setId(request.getParameter("id"));
		memberDTO.setPw(request.getParameter("pw"));
		memberDTO.setName(request.getParameter("name"));
		memberDTO.setNum_address(Integer.parseInt(request.getParameter("num_address")));
		memberDTO.setMain_address(request.getParameter("main_address"));
		memberDTO.setSub_address(request.getParameter("sub_address"));
		memberDTO.setCountry(request.getParameter("country"));
		memberDTO.setPhone(request.getParameter("phone"));
		memberDTO.setEmail(request.getParameter("email"));
		memberDTO.setMoney(Integer.parseInt(request.getParameter("money")));
		memberDTO.setKind(request.getParameter("kind"));
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
