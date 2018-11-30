package com.fatalism.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fatalis.cart.CartService;
import com.fatalis.product.ProductService;
import com.iu.action.ActionFoward;

/**
 * Servlet implementation class ProductController
 */
@WebServlet("/CartController")
public class CartController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       private CartService cartService;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CartController() {
        super();
        // TODO Auto-generated constructor stub
        cartService = new CartService();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ActionFoward actionFoward = null;
		String command = request.getPathInfo();
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		if(command.equals("/cartList.do")) {
			actionFoward = cartService.cartSelect(request, response);
		}else if(command.equals("/selectDelete.do")) {
			actionFoward = cartService.selectDelete(request, response);
		}else if(command.equals("/allDelete.do")) {
			actionFoward = cartService.allDelete(request, response);
		}else if(command.equals("/order.do")) {
			actionFoward = cartService.order(request, response);
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
