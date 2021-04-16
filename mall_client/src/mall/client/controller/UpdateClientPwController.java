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

/**
 * Servlet implementation class UpdateClientPwControlle
 */
@WebServlet("/UpdateClientPwController")
public class UpdateClientPwController extends HttpServlet {
	private ClientDao clientDao;

	//pw변경 form으로 연결 : updateClientPw
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//회원 확인 - 세션에서 아이디 가져와서 로그인 안되어있으면 index로 돌아가기
		HttpSession session = request.getSession();
		if(session.getAttribute("loginClient") == null) {
			response.sendRedirect(request.getContextPath()+"/IndexController");
			return;
		}	
		//forward
		request.getRequestDispatcher("/WEB-INF/view/client/updateClientPw.jsp").forward(request, response);
	}

	//pw변경
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//회원 확인
		HttpSession session = request.getSession();
		if(session.getAttribute("loginClient") == null) {
			response.sendRedirect(request.getContextPath()+"/IndexController");
			return;
		}
		//전처리 - 세션에서 아이디 받기 , 새 비밀번호 받기
		Client client = new Client();
		client.setClientMail(((Client)session.getAttribute("loginClient")).getClientMail());	
		client.setClientPw(request.getParameter("clientPw")); 
		System.out.println(client.toString()+"<UpdateClientPwController>");

		//dao 호출 - 비밀번호 변경
		clientDao = new ClientDao();
		clientDao.updateClientPw(client);

		//logout 세션 끊기
		session.invalidate();

		//redirect로 index돌아가기
		response.sendRedirect(request.getContextPath()+"/IndexController");
	}

}