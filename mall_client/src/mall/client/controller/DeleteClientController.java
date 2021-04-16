package mall.client.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mall.client.model.CartDao;
import mall.client.model.ClientDao;
import mall.client.vo.Client;

@WebServlet("/DeleteClientController")
public class DeleteClientController extends HttpServlet {
	private ClientDao clientDao;
	private CartDao cartDao;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 로그인 유효성검사
		HttpSession session = request.getSession();
		if(session.getAttribute("loginClient") == null) {
			response.sendRedirect(request.getContextPath()+"/IndexController");
			return;
		}
		
		//세션에서 clientMail 가져오기
		String clientMail = ((Client)(session.getAttribute("loginClient"))).getClientMail();
		System.out.println(clientMail+"<--삭제할 clientMail");
		
		// 삭제메서드
		this.clientDao = new ClientDao();
		this.cartDao = new CartDao();
		
		clientDao.deleteClient(clientMail);
		cartDao.deleteCartAll(clientMail);
		
		// 초기화
		session.invalidate();
		
		// 돌아가기
		response.sendRedirect(request.getContextPath()+"/IndexController");
	}
	
}
