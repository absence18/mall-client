package mall.client.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mall.client.model.ClientDao;
import mall.client.vo.Client;

@WebServlet("/ClientOneController")
public class ClientOneController extends HttpServlet {
	
	private ClientDao clientDao;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Client client = new Client();
		
		// 로그인 유효성 검사
		HttpSession session = request.getSession();
		if(session.getAttribute("loginClient") == null) {
			response.sendRedirect(request.getContextPath()+"/IndexController");
			return;
		}
		
		//Dao 호출
				this.clientDao = new ClientDao();
				String clientMail = ((Client)(session.getAttribute("loginClient"))).getClientMail();

				// 클라이언트 상세정보
				Client clientOne = this.clientDao.selectClientOne(clientMail);
				//디버깅
				System.out.println(clientOne+"<--ClientOneController clientOne");

				//세션에 넣기
				request.setAttribute("clientOne", clientOne); // (키, 값)
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/view/client/selectClientOne.jsp");// 위임
				rd.forward(request, response);// 포워딩


			}	
		}