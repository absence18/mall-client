package mall.client.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import mall.client.commons.DBUtil;
import mall.client.vo.Stats;

public class StatsDao {
	private DBUtil dbUtil;
	
	public Stats selectStatsByToday() {
		
		Stats stats = null;
		
		this.dbUtil = new DBUtil();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {			 
			
			String sql = "SELECT stats_day statsDay, stats_count statsCount FROM stats WHERE stats_day = DATE(NOW())";
			conn = this.dbUtil.getConnection();
			stmt = conn.prepareStatement(sql);
			
			//디버깅
			System.out.println(stmt + "<--selectStatsByToday");
			
			rs = stmt.executeQuery();
			if(rs.next()) {
				stats = new Stats();
				stats.setStatsCount(rs.getLong("statsCount"));
				stats.setStatsDay(rs.getString("statsDay"));
			}
			
		} catch(Exception e) {
			e.printStackTrace();
			
		} finally {
			this.dbUtil.close(rs, stmt, conn);
			
		}
		return stats;
	}
		

	public void insertStats() {
		
		this.dbUtil = new DBUtil();
		Connection conn = null;
		PreparedStatement stmt = null;

	      
		try {
			conn = this.dbUtil.getConnection();
			String sql = "INSERT INTO stats(stats_day, stats_count) VALUES (DATE(NOW()),1)";
			stmt = conn.prepareStatement(sql);

			System.out.println(stmt + "<--insertStats()");

			stmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
	         
		} finally {
			this.dbUtil.close(null, stmt, conn);
	         
		}
	      
	}
	
	public void updateStats() {
		
		this.dbUtil = new DBUtil();
	      Connection conn = null;
	      PreparedStatement stmt = null;

	      try {
	    	  
	         conn = this.dbUtil.getConnection();
	         String sql = "UPDATE stats SET stats_count = stats_count+1 WHERE stats_day = DATE(NOW())";
	         stmt = conn.prepareStatement(sql);

	         System.out.println(stmt + "<--updateStats()");

	         stmt.executeUpdate();

	      } catch (Exception e) {
	         e.printStackTrace();
	         
	      } finally {
	         this.dbUtil.close(null, stmt, conn);
	         
	      }
	      
	}
	
	public long selectStatsTotal() {
		
		this.dbUtil = new DBUtil();

		
		long total = 0;
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			
			conn = this.dbUtil.getConnection();
			String sql = "SELECT COUNT(stats_count) total FROM stats";
			stmt = conn.prepareStatement(sql);
			
			System.out.println(stmt + "<--selectStatsTotal()");
			
			rs = stmt.executeQuery();

			if (rs.next()) {
				total = rs.getLong("total");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
	         
		} finally {
			this.dbUtil.close(rs, stmt, conn);
	         
		}
		return total;
	}
	
}
