package service;

import java.sql.Connection;
import java.sql.SQLException;

import common.DBUtil;
import repository.INiceDao;
import repository.MemberDao;
import repository.NiceDao;
import vo.Nice;

public class NiceService implements INiceService {
	INiceDao niceDao;

	@Override
	public boolean getNice(Nice nice) {
		boolean result = true;
		Connection conn = null;
		
		try {
			conn = DBUtil.getConnection();
			conn.setAutoCommit(false);
			
			this.niceDao = new NiceDao();
			
			if(niceDao.insertNice(conn, nice) != 1) {
				// 좋아요 실패 
				throw new Exception();
			}
			
			conn.commit();
			
		} catch(Exception e) {
			result = false;
			e.printStackTrace();
			
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return result;			
		}

}
