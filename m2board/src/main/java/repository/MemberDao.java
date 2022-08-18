package repository;

import java.sql.*;
import vo.Member;

public class MemberDao implements IMemberDao {

	@Override
	public int insertMember(Connection conn, Member paramMember) throws SQLException {
		int row = 0;
		PreparedStatement stmt = null;
		String sql = "insert into member(id, password, name) values (?, password(?), ?)";
		
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, paramMember.getId());
			stmt.setString(2, paramMember.getPw());
			stmt.setString(3, paramMember.getName());
			
			row = stmt.executeUpdate();
			
		} finally {
			if (stmt != null) { stmt.close(); }
		}
		
		return row;
	}

	@Override
	public Member selectMember(Connection conn, Member paramMember) throws SQLException{
		Member member = null;
		String sql = "select id, name from member where id = ? and password = password(?)";
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		System.out.println(paramMember);
		
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, paramMember.getId());
			stmt.setString(2, paramMember.getPw());
			
			rs = stmt.executeQuery();
			
			if(rs.next()) {
				member = new Member();
				member.setId(rs.getString("id"));
				member.setName(rs.getString("name"));
			}
			
		} finally {
			if (rs != null) { rs.close(); }
			if (stmt != null) { stmt.close(); }
		}
		
		return member;
	}

}
