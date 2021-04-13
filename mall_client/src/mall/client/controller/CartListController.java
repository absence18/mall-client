package mall.client.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mall.client.model.CartDao;

@WebServlet("/CartListController")
public class CartListController extends HttpServlet {
	private CartDao cartDao;
	// doGet으로 한다
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// session 검사 (로그인되거나 redirect되거나)
		HttpSession session = request.getSession();
		if(session.getAttribute("loginClient") != null) {
			response.sendRedirect(request.getContextPath()+"IndexController");
			return;
		}
		
		// DAO 호출하신분~
		this.cartDao = new CartDao();
		
		List<Map<String, Object>> cartList = null;
		
		try {
			cartList = this.cartDao.selectCartList();
			
		} catch(Exception e) {
			// 예외 발생시 출력만 하고 넘기라
			e.printStackTrace();
			
		}
		
		// foward를 이용하여 view에 request와 response 권한 넘기기
		request.setAttribute("cartList", cartList);
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/view/cart/cartList.jsp");
		rd.forward(request, response);
	}

}
