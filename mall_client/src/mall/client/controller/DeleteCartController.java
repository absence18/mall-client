package mall.client.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mall.client.model.CartDao;
import mall.client.vo.Client;

@WebServlet("/DeleteCartController")
public class DeleteCartController extends HttpServlet {
	private CartDao cartDao;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 세션 유효성 검사 (로그인 검사)
		HttpSession session = request.getSession();
		if(session.getAttribute("loginClient") == null) {
			response.sendRedirect(request.getContextPath()+"/IndexController");
			return;
			
		}
		
		String clientMail = ((Client)(session.getAttribute("loginClient"))).getClientMail();
		
		int ebookNo = Integer.parseInt(request.getParameter("ebookNo"));
		
		this.cartDao = new CartDao();
		
		this.cartDao.deleteCart(clientMail, ebookNo);
		
		response.sendRedirect(request.getContextPath() + "/CartListController");
		
		
	}

}
