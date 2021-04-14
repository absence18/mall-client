package mall.client.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mall.client.model.ClientDao;
import mall.client.vo.Client;


@WebServlet("/InsertClientController")
public class InsertClientController extends HttpServlet {
	private ClientDao clientDao;
	//폼
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if(session.getAttribute("loginClient") != null) {//로그인 되어있으면 회원가입 폼 못들어옴
			response.sendRedirect(request.getContextPath()+"/IndexController");
			return;
		}
		request.getRequestDispatcher("/WEB-INF/view/client/insertClient.jsp").forward(request, response);
	}

	//action C-> M -> redirect
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//insertClient.jsp에서 받은 데이터 정리
		this.clientDao = new ClientDao();
		String clientMail = request.getParameter("clientMail");
		String clientPw = request.getParameter("clientPw");

		Client client = new Client();
		client.setClientMail(clientMail);
		client.setClientPw(clientPw);
		System.out.println(client.toString() + "<insertClientController.doPost>");


		//mail 중복 검사
		if(clientDao.selectClientMail(clientMail) != null) {//clientMail 중복된 경우 insertClient로 돌아감
			response.sendRedirect(request.getContextPath()+"/InsertClientController");
			return;
		}
		//dao 회원가입 호출
		clientDao.insertClient(client);
		//index로 돌아감
		response.sendRedirect(request.getContextPath()+"/IndexController");
	}

}