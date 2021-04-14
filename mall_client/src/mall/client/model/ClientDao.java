package mall.client.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import mall.client.commons.DBUtil;
import mall.client.vo.Client;

public class ClientDao {
	
	private DBUtil dbUtil;

	
	
	// client가입 메서드
	public int insertClient(Client client) {
		//전처리
		this.dbUtil = new DBUtil();
		int rowCnt = 0;
		Connection conn = null;
		PreparedStatement stmt = null;
		//db연결 insert
		try {
			conn = this.dbUtil.getConnection();
			String sql="INSERT INTO client(client_mail, client_pw, client_date) VALUES(?,PASSWORD(?),NOW())";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, client.getClientMail());
			stmt.setString(2, client.getClientPw());
			//디버깅
			System.out.printf("stmt: %s<ClientDao.insertClient>\n", stmt);
			stmt.executeUpdate();

		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			this.dbUtil.close(null, stmt, conn);
		}
		//return 문
		return rowCnt;
	}
	
	
	// clientMail 중복검사 메서드
	public String selectClientMail(String clientMail) {
		
		String returnClientMail = null;
		this.dbUtil = new DBUtil();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			
			conn = this.dbUtil.getConnection();
			String sql = "SELECT client_mail clientMail FROM client WHERE client_mail = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, clientMail);
			
			rs = stmt.executeQuery();
			if(rs.next()) {
				returnClientMail = rs.getString("clientMail");
			}
			
		} catch(Exception e) {
			e.printStackTrace();
			
		} finally {
			this.dbUtil.close(rs, stmt, conn);
			
		}
		
		return returnClientMail;
	}
	
	
	// 로그인 메서드
	public Client login(Client client) {
		
		this.dbUtil = new DBUtil();
		Client returnClient = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			
			conn = this.dbUtil.getConnection();
			String sql = "SELECT * FROM client WHERE client_mail=? AND client_pw=PASSWORD(?)";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, client.getClientMail());
			stmt.setString(2, client.getClientPw());
			rs = stmt.executeQuery();
			
			if(rs.next()) {
				
				returnClient = new Client();
				returnClient.setClientMail(rs.getString("client_mail"));
				
			}
			
		} catch(Exception e) {
			e.printStackTrace();
			
		} finally {
			this.dbUtil.close(rs, stmt, conn);
			
		}
		return returnClient;
		
	}
}