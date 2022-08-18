package service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import common.DBUtil;
import repository.BoardDao;
import repository.IBoardDao;
import vo.Board;

public class BoardService implements IBoardService {
	private IBoardDao boardDao;
	
	@Override
	public Map<String, Object> getBoardListByPage(int rowPerPage, int currentPage) {
		Map<String, Object> map = new HashMap<>();
		Connection conn = null;
		
		int beginRow = (currentPage - 1) * rowPerPage;

		try {
			conn = DBUtil.getConnection();
			this.boardDao = new BoardDao();
			
			List<Board> list = boardDao.selectBoardListByPage(conn, rowPerPage, beginRow);
			int totalRow = boardDao.selectBoardCnt(conn);
			int lastPage = (int) Math.ceil((double)totalRow / rowPerPage);
			
			map.put("list", list);
			map.put("lastPage", lastPage);

		} catch (Exception e) {

			e.printStackTrace();

		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return map;
	}

	@Override
	public Board getBoardOne(int boardNo) {
		Board board = null;
		Connection conn = null;
		
		try {
			conn = DBUtil.getConnection();
			this.boardDao = new BoardDao();
			
			board = boardDao.selectBoardOne(conn, boardNo);
			
			if(board != null) {
				boardDao.updateRead(conn, boardNo);
			}
			
		} catch (Exception e) {

			e.printStackTrace();

		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}		
		
		return board;
	}

	
}
