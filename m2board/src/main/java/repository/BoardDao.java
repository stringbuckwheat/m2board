package repository;

import java.sql.*;
import java.util.*;

import vo.Board;

public class BoardDao implements IBoardDao {

	@Override
	public List<Map<String, Object>> selectBoardListByPage(Connection conn, int rowPerPage, int beginRow) throws SQLException {
		System.out.println("--------------------- BoardDao.selectBoardListByPage()");
		
		List<Map<String, Object>> list = new ArrayList<>();
		
		String sql = "select b.board_no boardNo, title, writer, `read`, b.create_date createDate, ifnull(t.cnt, 0) as nice"
				+ " from board b"
				+ " left join"
				+ "	(select board_no, count(*) cnt"
				+ "	from nice"
				+ "	group by board_no) t"
				+ " on b.board_no = t.board_no"
				+ " order by create_date desc limit ?, ?";		
		
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, beginRow);
			stmt.setInt(2, rowPerPage);
			
			System.out.println("stmt " + stmt);
			
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				Map<String, Object> m = new HashMap<>();
				
				m.put("boardNo", rs.getInt("boardNo"));
				m.put("title", rs.getString("title"));
				m.put("writer", rs.getString("writer"));
				m.put("read", rs.getInt("read"));
				m.put("nice", rs.getInt("nice"));
				m.put("createDate", rs.getString("createDate"));
								
				list.add(m);
			}
			
			System.out.println("list: " + list);
			
		} finally {
			if (rs != null) { rs.close(); }
			if (stmt != null) { stmt.close(); }
		}
		
		return list;
	}

	@Override
	public int selectBoardCnt(Connection conn) throws SQLException {
		System.out.println("--------------------- BoardDao.selectBoardCnt()");

		int totalRow = 0;
		
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			stmt = conn.prepareStatement("select count(*) cnt FROM board");
			rs = stmt.executeQuery();
			
			if(rs.next()) {
				totalRow = rs.getInt("cnt");
			}
			
		} finally {
			if (rs != null) { rs.close(); }
			if (stmt != null) { stmt.close(); }
		}
		
		return totalRow;
	}

	@Override
	public Map<String, Object> selectBoardOne(Connection conn, int boardNo) throws SQLException {
		Map<String, Object> map = null;
		String sql = "select b.board_no boardNo, title, writer, content, `read`, b.create_date createDate, ifnull(t.cnt, 0) as nice"
				+ " from board b"
				+ " left join"
				+ "	(select board_no, count(*) cnt"
				+ "	from nice"
				+ "	group by board_no) t"
				+ " on b.board_no = t.board_no"
				+ " where b.board_no = ?";
		
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, boardNo);
			
			rs = stmt.executeQuery();
			
			if(rs.next()) {
				map = new HashMap<>();
				
				map.put("boardNo", rs.getInt("boardNo"));
				map.put("title", rs.getString("title"));
				map.put("writer", rs.getString("writer"));
				map.put("content", rs.getString("content"));
				map.put("createDate", rs.getString("createDate"));
				map.put("read", rs.getInt("read"));
				map.put("nice", rs.getInt("nice"));
			}
			
		} finally {
			if (rs != null) { rs.close(); }
			if (stmt != null) { stmt.close(); }
		}
		
		return map;
	}

	@Override
	public void updateRead(Connection conn, int boardNo) throws SQLException {
		String sql = "update board set `read` = `read`+1 where board_no = ?";
		PreparedStatement stmt = null;
		
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, boardNo);
			
			stmt.executeUpdate();
		} finally {
			if (stmt != null) { stmt.close(); }
		}			
	}
}
