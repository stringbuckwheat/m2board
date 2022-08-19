package repository;

import java.sql.*;

import vo.Nice;

public class NiceDao implements INiceDao {

	@Override
	public int insertNice(Connection conn, Nice nice) throws SQLException {
		String sql = "insert into nice(id, board_no, create_date) values (?, ?, now())";
		int row = 0;
		PreparedStatement stmt = null;

		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, nice.getId());
			stmt.setInt(2, nice.getBoardNo());
			
			row = stmt.executeUpdate();
			
		} finally {
			if (stmt != null) { stmt.close(); }
		}
	
		return row;
	}

	@Override
	public int selectNice(Connection conn, int boardNo) throws SQLException {
		int nice = 0;
		String sql = "select count(*) niceNum from nice where board_no = ?";
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, boardNo);
			
			rs = stmt.executeQuery();
			
			if(rs.next()) {
				nice = rs.getInt("niceNum");
			}
			
		} finally {
			if(rs != null) {rs.close();}
			if (stmt != null) { stmt.close(); }
		}
		
		return nice;
	}
}
