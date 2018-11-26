package com.iu.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.iu.action.ActionFoward;
import com.sun.org.apache.bcel.internal.generic.ACONST_NULL;

public class MemberService {
	private MemberDAO memberDAO;

	public MemberService() {
		memberDAO = new MemberDAO();
	}
	
	public ActionFoward findPw2(HttpServletRequest request, HttpServletResponse response) {
		ActionFoward actionFoward = new ActionFoward();
			String method = request.getMethod();
			
			if(method.equals("POST")) {
				MemberDTO memberDTO = new MemberDTO();
				memberDTO.setId(request.getParameter("id"));
				memberDTO.setName(request.getParameter("name"));
				memberDTO.setPhone(request.getParameter("phone"));
				memberDTO.setPhone2(request.getParameter("phone2"));
				try {
					memberDTO= memberDAO.findPw2(memberDTO);
					if(memberDTO.getPw()==null){
						request.setAttribute("message", "정보를 잘못입력했습니다.");
						request.setAttribute("path", "./findPw.do");
						actionFoward.setCheck(true);
						actionFoward.setPath("../WEB-INF/view/common/result.jsp");
					}
				} catch (Exception e) {
					memberDTO = null;
					e.printStackTrace();
				}
				if(memberDTO.getPw()!=null) {
					request.setAttribute("findPw2", memberDTO);
					actionFoward.setPath("../WEB-INF/view/member/viewPw2.jsp");
					actionFoward.setCheck(true);
				}
			}else {
				actionFoward.setPath("../WEB-INF/view/member/findPw.jsp");
				actionFoward.setCheck(true);
				
			}
		return actionFoward;
}
	
	//이메일로 비번 찾기
	public ActionFoward findPw(HttpServletRequest request, HttpServletResponse response) {
		ActionFoward actionFoward = new ActionFoward();
		String method = request.getMethod();
		
		if(method.equals("POST")) {
			MemberDTO memberDTO = new MemberDTO();
			memberDTO.setId(request.getParameter("id"));
			memberDTO.setName(request.getParameter("name"));
			memberDTO.setEmail(request.getParameter("email"));
			memberDTO.setEmail2(request.getParameter("email2"));
			
			try {
				memberDTO = memberDAO.findPw(memberDTO);
				if(memberDTO.getPw()==null){
					request.setAttribute("message", "정보를 잘못입력했습니다.");
					request.setAttribute("path", "./findPw.do");
					actionFoward.setCheck(true);
					actionFoward.setPath("../WEB-INF/view/common/result.jsp");
				}
			} catch (Exception e) {
				memberDTO=null;
				e.printStackTrace();
			}
			if(memberDTO.getPw()!=null) {
				request.setAttribute("findPw", memberDTO);
				actionFoward.setPath("../WEB-INF/view/member/viewPw.jsp");
				actionFoward.setCheck(true);
			}
		}else {
			actionFoward.setPath("../WEB-INF/view/member/findPw.jsp");
			actionFoward.setCheck(true);
		}
		
		return actionFoward;
	}
	
	
	//핸드폰으로 찾기
	public ActionFoward findId2(HttpServletRequest request, HttpServletResponse response) {
			ActionFoward actionFoward = new ActionFoward();
				String method = request.getMethod();
				
				if(method.equals("POST")) {
					MemberDTO memberDTO = new MemberDTO();
					
					memberDTO.setName(request.getParameter("name"));
					memberDTO.setPhone(request.getParameter("phone"));
					memberDTO.setPhone2(request.getParameter("phone2"));
					try {
						memberDTO= memberDAO.findId2(memberDTO);
						if(memberDTO.getId()==null) {
							request.setAttribute("message", "없는계정입니다.");
							request.setAttribute("path", "./findId.do");
							actionFoward.setCheck(true);
							actionFoward.setPath("../WEB-INF/view/common/result.jsp");
						}
					} catch (Exception e) {
						memberDTO = null;
						e.printStackTrace();
					}
					if(memberDTO.getId()!=null) {
						request.setAttribute("findId2", memberDTO);
						actionFoward.setPath("../WEB-INF/view/member/viewId2.jsp");
						actionFoward.setCheck(true);
					}
				}else {
					actionFoward.setPath("../WEB-INF/view/member/findId.jsp");
					actionFoward.setCheck(true);
					
				}
			return actionFoward;
	}
	
	
	//이메일로아이디 찾기
	public ActionFoward findId(HttpServletRequest request, HttpServletResponse response) {
		ActionFoward actionFoward = new ActionFoward();
		String method = request.getMethod();
		
		if(method.equals("POST")) {
			MemberDTO memberDTO = new MemberDTO();
			
			memberDTO.setName(request.getParameter("name"));
			memberDTO.setEmail(request.getParameter("email"));
			memberDTO.setEmail2(request.getParameter("email2"));
			try {
				memberDTO = memberDAO.findId(memberDTO);
				if(memberDTO.getId()==null) {
					request.setAttribute("message", "없는계정입니다.");
					request.setAttribute("path", "./findId.do");
					actionFoward.setCheck(true);
					actionFoward.setPath("../WEB-INF/view/common/result.jsp");
					
				}
				
			} catch (Exception e) {
				memberDTO=null;
				e.printStackTrace();
			}
			if(memberDTO.getId()!=null) {
				request.setAttribute("findId", memberDTO);
				actionFoward.setPath("../WEB-INF/view/member/viewId.jsp");
				actionFoward.setCheck(true);
			}
		}else {
			actionFoward.setPath("../WEB-INF/view/member/findId.jsp");
			actionFoward.setCheck(true);
		}
		
		return actionFoward;
	}
	
	
	//주문내역
	
	//회원탈퇴
	public ActionFoward delete(HttpServletRequest request, HttpServletResponse response) {
		ActionFoward actionFoward = new ActionFoward();
		MemberDTO memberDTO=null;
		HttpSession session = request.getSession();
		memberDTO = (MemberDTO)session.getAttribute("member");
		System.out.println(memberDTO.getId());
		String message = "오류";
		try {
			
			int result = memberDAO.delete(memberDTO);
			if(result>0) {
				message="탈퇴되었습니다.";
				session.invalidate();
			}
		} catch (Exception e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("message", message);
		request.setAttribute("path", "../index.jsp");
		actionFoward.setCheck(true);
		actionFoward.setPath("../WEB-INF/view/common/result.jsp");
		return actionFoward;
	}
	
	
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
				/*String phone = request.getParameter("phone")+"-";
				phone += request.getParameter("phone2");
				String email = request.getParameter("email")+"@";
				email += request.getParameter("email2");*/
				
				MemberDTO memberDTO = new MemberDTO();
				memberDTO.setId(request.getParameter("id"));
				memberDTO.setPw(request.getParameter("pw"));
				memberDTO.setName(request.getParameter("name"));
				memberDTO.setNum_address(Integer.parseInt(request.getParameter("num_address")));
				memberDTO.setMain_address(request.getParameter("main_address"));
				memberDTO.setSub_address(request.getParameter("sub_address"));
				memberDTO.setCountry("I");
				memberDTO.setPhone(request.getParameter("phone"));
				memberDTO.setPhone2(request.getParameter("phone2"));
				memberDTO.setEmail(request.getParameter("email"));
				memberDTO.setEmail2(request.getParameter("email2"));
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
		String id=request.getParameter("id");
		boolean result = true;
		try {
			result=memberDAO.checkId(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String message ="1";//불가능
		if(!result) {
			message="2";//가능한 아이디
		}

		request.setAttribute("message", message);
		actionFoward.setCheck(true);
		actionFoward.setPath("../WEB-INF/view/common/resultjax.jsp");

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
				/*String phone = request.getParameter("phone")+"-";
				phone += request.getParameter("phone2");
				
				String email = request.getParameter("email")+"@";
				email += request.getParameter("email2");*/
				MemberDTO memberDTO = new MemberDTO();
				memberDTO.setId(request.getParameter("id"));
				memberDTO.setPw(request.getParameter("pw"));
				memberDTO.setName(request.getParameter("name"));
				memberDTO.setNum_address(Integer.parseInt(request.getParameter("num_address")));
				memberDTO.setMain_address(request.getParameter("main_address"));
				memberDTO.setSub_address(request.getParameter("sub_address"));
				memberDTO.setCountry("I");
				memberDTO.setPhone(request.getParameter("phone"));
				memberDTO.setPhone2(request.getParameter("phone2"));
				memberDTO.setEmail(request.getParameter("email"));
				memberDTO.setEmail2(request.getParameter("email2"));
				memberDTO.setMoney(1000);
				memberDTO.setKind("U");
				memberDTO.setPoint(1000);
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
