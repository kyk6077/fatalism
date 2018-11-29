package com.fatalis.cart;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fatalis.cart.CartInfoDTO;
import com.iu.action.ActionFoward;
import com.iu.member.MemberDTO;

public class CartService {
	
	private CartDAO cartDAO;
	
	public CartService() {
		cartDAO = new CartDAO();
	}
	//전체 삭제
	public ActionFoward allDelete(HttpServletRequest request, HttpServletResponse response) {
		ActionFoward actionFoward = new ActionFoward();
		String id = null;
		try {
			MemberDTO memberDTO = (MemberDTO)request.getSession().getAttribute("member");
			id = memberDTO.getId();
			int num = cartDAO.allDelete(id);
			System.out.println(id);
			
			if(num>0) {
				request.setAttribute("message", "전체품목삭제했습니다.");
				request.setAttribute("path", "./cartList.do");
			}else {
				request.setAttribute("message", "삭제를 못했습니다.");
				request.setAttribute("path", "./cartList.do");
			}
		} catch (Exception e) {
			request.setAttribute("message", "오류");
			request.setAttribute("path", "./cartList.do");
			e.printStackTrace();
		}
		actionFoward.setCheck(true);
		actionFoward.setPath("../WEB-INF/view/common/result.jsp");
		return actionFoward;
	}
	
	
	//선택삭제
	public ActionFoward selectDelete(HttpServletRequest request, HttpServletResponse response) {
		ActionFoward actionFoward = new ActionFoward();
		String id = null;
		try {
			int num =Integer.parseInt(request.getParameter("num"));
			MemberDTO memberDTO = (MemberDTO)request.getSession().getAttribute("member");
			id = memberDTO.getId();
			num = cartDAO.selectDelete(num);
			
			if(num>0) {
				request.setAttribute("message", "삭제되었습니다");
				request.setAttribute("path", "./cartList.do");
			}else {
				request.setAttribute("message", "삭제실패");
				request.setAttribute("path", "./cartList.do");
			}
		} catch (Exception e) {
			request.setAttribute("message", "오류");
			request.setAttribute("path", "./cartList.do");
			e.printStackTrace();
		}
		actionFoward.setCheck(true);
		actionFoward.setPath("../WEB-INF/view/common/result.jsp");
		return actionFoward;
	}
	
	
	
	public ActionFoward cartSelect(HttpServletRequest request, HttpServletResponse response) {
		ActionFoward actionFoward = new ActionFoward();
		List<CartInfoDTO> ar = null;
		String id =null;
		try {
		MemberDTO memberDTO = (MemberDTO)request.getSession().getAttribute("member");
		id = memberDTO.getId();
		}catch (Exception e) {
			//비회원 처리
			id = "aaa";
		}
		try {
			ar = cartDAO.cartSelect(id);
			request.setAttribute("cart", ar);
			actionFoward.setCheck(true);
			actionFoward.setPath("../WEB-INF/view/cart/cartList.jsp");
			
		} catch (Exception e) {
			System.out.println("p");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return actionFoward;
	}
	
	
	public ActionFoward order(HttpServletRequest request, HttpServletResponse response) {
		ActionFoward actionFoward = new ActionFoward();
		List<CartInfoDTO> ar = null;
		String id =null;
		try {
		MemberDTO memberDTO = (MemberDTO)request.getSession().getAttribute("member");
		id = memberDTO.getId();
		}catch (Exception e) {
			//비회원 처리
			id = "aaa";
		}
		try {
			System.out.println("1");
			ar = cartDAO.cartSelect(id);
			request.setAttribute("cart", ar);
			actionFoward.setCheck(true);
			actionFoward.setPath("../WEB-INF/view/cart/order.jsp");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return actionFoward;
	}
	

}
