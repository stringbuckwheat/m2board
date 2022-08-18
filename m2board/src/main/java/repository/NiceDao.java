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

}
