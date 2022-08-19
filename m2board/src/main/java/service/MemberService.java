package service;

import java.sql.*;

import common.DBUtil;
import repository.IMemberDao;
import repository.MemberDao;
import vo.Member;

public class MemberService implements IMemberService {
	IMemberDao memberDao;

	@Override
	public boolean addMember(Member paramMember) {
		boolean result = true;
		Connection conn = null;
		
		try {
			conn = DBUtil.getConnection();
			conn.setAutoCommit(false);
			
			this.memberDao = new MemberDao();
			
			if(memberDao.insertMember(conn, paramMember) != 1) {
				// 회원 가입 실패
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
	public Member getMemberLogin(Member paramMember) {
		Member member = null;
		Connection conn = null;

		
		try {
			conn = DBUtil.getConnection();
			this.memberDao = new MemberDao();
			
			member = memberDao.selectMember(conn, paramMember);			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return member;
	}
}
