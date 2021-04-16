package mall.client.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mall.client.model.CartDao;
import mall.client.vo.*;

@WebServlet("/InsertCartController")
public class InsertCartController extends HttpServlet {
	
	private CartDao cartDao;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 세션 검사
		HttpSession session = request.getSession();
		if(session.getAttribute("loginClient") == null) {
			response.sendRedirect(request.getContextPath()+"/IndexController");
			return;
		}
		
		// 장바구니에 추가
		int ebookNo = Integer.parseInt(request.getParameter("ebookNo"));
		this.cartDao = new CartDao();
		Cart cart = new Cart();
		cart.setEbookNo(ebookNo);
		cart.setClientMail(((Client)session.getAttribute("loginClient")).getClientMail());
		
		//카트안에 동일한ebook이 있는지 확인
		if(this.cartDao.selectClientMail(cart)) {
			this.cartDao.insertCart(cart);
			
		} else {
			System.out.println("이미 장바구니에 존재합니다.");
			
		}
		response.sendRedirect(request.getContextPath()+"/CartListController");
	}

}