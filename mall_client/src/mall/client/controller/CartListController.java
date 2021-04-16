package mall.client.controller;

import java.io.IOException;
import java.util.List;import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mall.client.model.CartDao;
import mall.client.vo.Client;

@WebServlet("/CartListController")
public class CartListController extends HttpServlet {
	private CartDao cartdao;
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 로그인 유효성 검사
		HttpSession session = request.getSession();
		if(session.getAttribute("loginClient") == null) {
			response.sendRedirect(request.getContextPath()+"/IndexController");
			return;
		}
		
		// dao 호출
		this.cartdao = new CartDao();
		
		// 로그인한 ID로만 조회 가능
		List<Map<String, Object>>
		cartList = this.cartdao.selectCartList(((Client)(session.getAttribute("loginClient"))).getClientMail());
		
		// forward
		request.setAttribute("cartList", cartList);
		RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/view/cart/cartList.jsp");
		rd.forward(request, response);
		
	}
	
}
