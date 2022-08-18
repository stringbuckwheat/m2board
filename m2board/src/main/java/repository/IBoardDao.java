package repository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import vo.Board;

public interface IBoardDao {
	List<Map<String, Object>> selectBoardListByPage(Connection conn, int rowPerPage, int beginRow) throws SQLException;	
	int selectBoardCnt(Connection conn) throws SQLException;
	Map<String, Object> selectBoardOne(Connection conn, int boardNo) throws SQLException;
	void updateRead(Connection conn, int boardNo) throws SQLException;
}
