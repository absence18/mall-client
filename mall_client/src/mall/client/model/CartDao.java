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
	public List<Map<String, Object>> selectCartList()	{
		
		// 모든 것을 초기화
		List<Map<String, Object>> list = new ArrayList<>();
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
