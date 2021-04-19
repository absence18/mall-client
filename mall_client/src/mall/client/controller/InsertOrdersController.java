package mall.client.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mall.client.model.*;
import mall.client.vo.*;

@WebServlet("/InsertOrdersController")
public class InsertOrdersController extends HttpServlet {
	private OrdersDao ordersDao;
	private CartDao cartDao;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 로그인 유효성검사
		HttpSession session = request.getSession();
		if(session.getAttribute("loginClient") == null) {
			response.sendRedirect(request.getContextPath()+"/IndexController");
			return;
		}
		
		// 의존객체 생성 후 주입
		this.ordersDao = new OrdersDao();
		this.cartDao = new CartDao();
		
		// 정보수집
		int ebookNo = Integer.parseInt(request.getParameter("ebookNo"));
		System.out.println(ebookNo+"<-- 주문목록추가ebookNo");
		Client client = (Client)session.getAttribute("loginClient");
		
		Orders orders = new Orders();
		orders.setEbookNo(ebookNo);
		orders.setClientNo(client.getClientNo());
		
		Cart cart = new Cart();
		cart.setEbookNo(ebookNo);
		cart.setClientMail(client.getClientMail());
		
		// model 호출
		// insert 후 delete되기전 db에 문제가 생기면? --> insert도 취소(롤백) -> 트랜잭션
		ordersDao.insertOrders(orders);
		cartDao.deleteCart(ebookNo);
		
		// View forward
		response.sendRedirect(request.getContextPath()+"/OrdersListController");
	}
}
