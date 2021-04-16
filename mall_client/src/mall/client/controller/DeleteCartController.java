package mall.client.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mall.client.model.CartDao;
import mall.client.vo.Cart;


@WebServlet("/DeleteCartController")
public class DeleteCartController extends HttpServlet {
	// model
	private CartDao cartDao;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 로그인 유효성 검사
		HttpSession session = request.getSession();
		if(session.getAttribute("loginClient") != null) {
			response.sendRedirect(request.getContextPath()+"/IndexController");
			return;
		}
		
		// 정보수집
		int cartNo = Integer.parseInt(request.getParameter("cartNo"));
		System.out.println(cartNo + "장바구니 삭제cartNo");
		
		// 메서드호출
		this.cartDao = new CartDao();
		cartDao.deleteCart(cartNo);
		response.sendRedirect(request.getContextPath()+"/CartListController");
	}
	
}
