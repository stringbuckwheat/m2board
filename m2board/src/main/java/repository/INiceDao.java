package repository;

import java.sql.*;
import vo.Nice;

public interface INiceDao {
	int insertNice(Connection conn, Nice nice) throws SQLException;
}
