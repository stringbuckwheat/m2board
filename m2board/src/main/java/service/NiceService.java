package service;

import java.sql.Connection;
import java.sql.SQLException;

import common.DBUtil;
import repository.BoardDao;
import repository.INiceDao;
import repository.MemberDao;
import repository.NiceDao;
import vo.Nice;

public class NiceService implements INiceService {
	INiceDao niceDao;

	@Override
	public boolean addNice(Nice nice) {
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

	@Override
	public int getNice(int boardNo) {
		int nice = 0;
		Connection conn = null;

		try {
			conn = DBUtil.getConnection();
			this.niceDao = new NiceDao();
			nice = niceDao.selectNice(conn, boardNo);
			
		} catch (Exception e) {

			e.printStackTrace();

		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}		
		
		
		return nice;
	}

	
}
