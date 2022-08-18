package repository;

import java.sql.*;
import java.util.*;

import vo.Board;

public class BoardDao implements IBoardDao {

	@Override
	public List<Board> selectBoardListByPage(Connection conn, int rowPerPage, int beginRow) throws SQLException {
		System.out.println("--------------------- BoardDao.selectBoardListByPage()");
		
		List<Board> list = new ArrayList<>();
		
		String sql = "select board_no boardNo, title, writer, content, create_date createDate, `read`, nice from board order by create_date desc limit ?, ?";
		
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, beginRow);
			stmt.setInt(2, rowPerPage);
			
			System.out.println("stmt " + stmt);
			
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				Board b = new Board();
				
				b.setBoardNo(rs.getInt("boardNo"));
				b.setTitle(rs.getString("title"));
				b.setWriter(rs.getString("writer"));
				b.setContent(rs.getString("content"));
				b.setCreateDate(rs.getString("createDate"));
				b.setRead(rs.getInt("read"));
				b.setNice(rs.getInt("nice"));
				
				list.add(b);
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
	public Board selectBoardOne(Connection conn, int boardNo) throws SQLException {
		Board board = null;
		String sql = "select board_no boardNo, title, writer, content, create_date createDate, `read`, nice FROM board where board_no = ?";
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, boardNo);
			
			rs = stmt.executeQuery();
			
			if(rs.next()) {
				board = new Board();
				
				board.setBoardNo(rs.getInt("boardNo"));
				board.setTitle(rs.getString("title"));
				board.setWriter(rs.getString("writer"));
				board.setContent(rs.getString("content"));
				board.setCreateDate(rs.getString("createDate"));
				board.setRead(rs.getInt("read"));
				board.setNice(rs.getInt("nice"));
			}
			
		} finally {
			if (rs != null) { rs.close(); }
			if (stmt != null) { stmt.close(); }
		}
		
		return board;
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
