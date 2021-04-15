package mall.client.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mall.client.commons.DBUtil;
import mall.client.vo.Cart;

public class CartDao {
	
	private DBUtil dbUtil;
	
	// cart삭제 메소드
	public void deleteCart(String clientMail, int ebookNo) {
		
		this.dbUtil = new DBUtil();
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			
			String sql = "DELETE FROM cart WHERE client_mail = ?  AND ebook_no = ?";
			
			conn = this.dbUtil.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, clientMail);
			stmt.setInt(2, ebookNo);
			
			stmt.executeUpdate();
			
		} catch(Exception e) {
			e.printStackTrace();
			
		} finally {
			this.dbUtil.close(null, stmt, conn);
			
		}
	}
	
	
	// 회탈 하기전에 장바구니 털기
	public void deleteCartByClient (String clientMail) {
		
		this.dbUtil = new DBUtil();
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			
			String sql = "DELETE FROM cart WHERE client_mail = ?";
			
			conn = this.dbUtil.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, clientMail);

			//삭제 실행
			stmt.executeUpdate();

		} catch (Exception e){
			e.printStackTrace();
			
		} finally {
			this.dbUtil.close(null, stmt, conn);
			
		}
	}
		
	
	// ebook 중복 검사 메소드
	public boolean selectClientMail(Cart cart) {
		
		boolean flag = true; // 노중복
		
		this.dbUtil = new DBUtil();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			
			conn = this.dbUtil.getConnection();
			String sql = "SELECT * FROM cart WHERE client_mail=? AND ebook_no=?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, cart.getClientMail());
			stmt.setInt(2, cart.getEbookNo());
			System.out.printf("stmt: %s<cartDao.selectCartList>\n", stmt);
			rs = stmt.executeQuery();
			if(rs.next()) {
				flag = false; // 예스중복
				
			}
			
		} catch(Exception e) {
			e.printStackTrace();
			
		} finally {
			this.dbUtil.close(rs, stmt, conn);
			
		}
		return flag;
	}

	
	// insertCart 메소드
	public int insertCart(Cart cart) {
		
		int rowCnt = 0;
		this.dbUtil = new DBUtil();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			
			// try문
			conn = this.dbUtil.getConnection();
			String sql = "INSERT INTO cart(client_mail, ebook_no, cart_date";
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			
		} catch(Exception e) {
			// 예외 발생하면 출력만 하겠다
			e.printStackTrace();
			
		} finally {
			// 최종적으로는 종료해라
			this.dbUtil.close(rs, stmt, conn);
		}
		// 리턴 값
		return rowCnt;
	}
	
	// cartList 메소드
	public List<Map<String, Object>> selectCartList(String clientMail)	{
		
		// 모든 것을 초기화
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		this.dbUtil = new DBUtil();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			
			// try문
			conn = this.dbUtil.getConnection();
			String sql = "SELECT c.cart_no cartNo, e.ebook_no ebookNo, e.ebook_title ebookTitle, c.cart_date cartDate FROM cart c INNER JOIN ebook e ON c.ebook_no = e.ebook_no ORDER BY c.cart_date DESC";
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			
			while(rs.next()) {
			
				// map배열 안에 값 넣기
				Map<String, Object> map = new HashMap<>();
				map.put("cartNo", rs.getInt("cartNo"));
				map.put("ebookNo", rs.getInt("ebookNo"));
				map.put("ebookTitle", rs.getString("ebookTitle"));
				map.put("cartDate", rs.getString("cartDate"));
				list.add(map);
			
			}
		
		} catch(Exception e) {
			// 예외 발생하면 출력만 하겠다
			e.printStackTrace();
			
		} finally {
			// 최종적으로는 종료해라
			this.dbUtil.close(rs, stmt, conn);
		}
		// 리턴 값
		return list;
	}

}
