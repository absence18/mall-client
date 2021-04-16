package mall.client.model;

import java.sql.*;
import java.util.*;
import mall.client.commons.DBUtil;
import mall.client.vo.Cart;

public class CartDao {
	private DBUtil dbUtil;
	// 회원정보삭제
	public int deleteCartAll(String clientMail) {
		
		// 초기화
		int rowCnt = 0;
		this.dbUtil = new DBUtil();
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {			 
			
			String sql = "DELETE FROM cart WHERE client_mail=?";
			conn = this.dbUtil.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, clientMail);
			
			//디버깅
			System.out.println(stmt + "<--회원정보삭제");
			rowCnt = stmt.executeUpdate();
			
		} catch(Exception e) {
			e.printStackTrace();
			
		} finally {
			this.dbUtil.close(null, stmt, conn);
			
		}
		return rowCnt;
	}
	
	// 장바구니 삭제
	public int deleteCart(int cartNo) {
		
		// 초기화
		int rowCnt = 0;
		this.dbUtil = new DBUtil();
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {			 
			
			String sql = "DELETE FROM cart WHERE cart_no=?";
			conn = this.dbUtil.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, cartNo);
			
			//디버깅
			System.out.println(stmt + "<--삭제");
			rowCnt = stmt.executeUpdate();
			
		} catch(Exception e) {
			e.printStackTrace();
			
		} finally {
			this.dbUtil.close(null, stmt, conn);
			
		}
		return rowCnt;
	}
	
	// 중복검사
	public boolean selectClientMail(Cart cart) {
		
		// 초기화
		boolean flag = true;
		this.dbUtil = new DBUtil();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			
			String sql = "SELECT * FROM cart WHERE client_mail=? AND ebook_no=?";
			conn = this.dbUtil.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, cart.getClientMail());
			stmt.setInt(2, cart.getEbookNo());
			
			//디버깅
			System.out.println(stmt + "<--중복검사");
			rs = stmt.executeQuery();
			
			if(rs.next()) {
				flag = false; // 중복검사
			}
			
		} catch(Exception e) {
			e.printStackTrace();
			
		} finally {
			this.dbUtil.close(null, stmt, conn);
			
		}
		return flag;
	}
	
	// 장바구니 추가
	public int insertCart(Cart cart) {
		
		// 초기화
		int rowCnt = 0;
		this.dbUtil = new DBUtil();
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			
			String sql ="INSERT INTO cart(client_mail, ebook_no, cart_date) VALUES(?,?,NOW())";
			conn = this.dbUtil.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, cart.getClientMail());
			stmt.setInt(2, cart.getEbookNo());
			
			//디버깅
			System.out.println(stmt + "<--장바구니추가");
			rowCnt = stmt.executeUpdate();
			
		} catch(Exception e) {
			e.printStackTrace();
			
		} finally {
			this.dbUtil.close(null, stmt, conn);
			
		}
		return rowCnt;
	}
	
	// 장바구니 목록
	public List<Map<String, Object>> selectCartList(String ClientMail) {
		
		// 초기화
		this.dbUtil = new DBUtil();
		List<Map<String, Object>> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {			  
			
			String sql = "SELECT c.cart_no cartNo, e.ebook_no ebookNo, e.ebook_title ebookTitle, c.cart_date cartDate FROM cart c INNER JOIN ebook e ON c.ebook_no = e.ebook_no WHERE client_mail=?";
			conn = this.dbUtil.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, ClientMail);
			
			//디버깅
			System.out.println(stmt + "<--장바구니목록");
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				
				Map<String, Object> map = new HashMap<>();
				map.put("cartNo", rs.getInt("cartNo"));
				map.put("ebookNo", rs.getInt("ebookNo"));
				map.put("ebookTitle", rs.getString("ebookTitle"));
				map.put("cartDate", rs.getString("cartDate"));
				list.add(map);
				
			}
			
		} catch(Exception e) {
			e.printStackTrace();
			
		} finally {
			this.dbUtil.close(rs, stmt, conn);
			
		}
		return list;
	}
	
}
